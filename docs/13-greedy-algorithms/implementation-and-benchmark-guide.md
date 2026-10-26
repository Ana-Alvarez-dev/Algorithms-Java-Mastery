# Greedy Algorithms: Implementation and Benchmark Guide

## Scope and Traceability

This contribution starts from main
**`9f65e53437bb7200f658a65f6fb795c20266f85c`**, the merge of Divide and Conquer
research through PR #85. Its [48 recorded cells](../17-benchmarking/results/divide-and-conquer/README.md)
remain separate from this module's future measurements.

The package `org.anaalvarezdev.algorithms.greedy` mirrors main/test/JMH roots.
There are **five production files**, **27 executed module tests**, and **five
JMH methods in three classes**, with **40 default configurations**. Existing
modules, tests, POM and historical measurements are unchanged.

| Documents | Selected responsibility | Evidence |
|---|---|---|
| 01 strategy / 02 safe choice / 03 substructure | Specify an objective and prove a safe rule, not merely a locally appealing action | Activity exchange, room lower bound, Huffman contraction and coin counterexamples |
| 04 correctness | Induction/exchange/invariants with an explicit domain | Arguments and manual traces below; tests are separate finite evidence |
| 05 activity selection | Maximum number of compatible intervals on one resource | Unsorted and validated finish/start-ordered APIs |
| 06 interval scheduling | Same selection problem; contrast assigning every interval to minimum rooms | Shared Interval value, original-position room assignment and overlap oracle |
| 07 Huffman | Deterministic prefix-code construction and two exact cost-only methods | Prefix freedom, exact weighted length, full-tree optimality oracle |
| 08 analysis / 09 mistakes | Preprocessing, snapshots, output space, heap state and applicability limits | Work ledger, domain/ownership tests and controlled JMH protocol |

Fractional/0-1 knapsack, weighted interval scheduling, matroids, approximation
algorithms, bitstream codecs, canonical/length-limited Huffman, arbitrary-precision
frequencies, parallel scheduling and automatic coin-system classification remain
extensions. Dijkstra/Kruskal already exist in Graphs and are not copied here.
Dynamic programming in a test oracle does not complete module 15. The next
numbered module is **14 - Backtracking**, followed by 15 - Dynamic Programming.

## Contracts, Domains and Ownership

| Type / operation | Input and result | Policies |
|---|---|---|
| [Interval](../../src/main/java/org/anaalvarezdev/algorithms/greedy/Interval.java) | Immutable int start/finish, **[start,finish)** | start<finish; signed times and extreme endpoints accepted; empty/reversed throws IllegalArgumentException |
| [ActivitySelection.select](../../src/main/java/org/anaalvarezdev/algorithms/greedy/ActivitySelection.java) | List of intervals -> immutable maximum-cardinality compatible plan | Sorts private snapshot by finish then start; touching endpoints compatible |
| ActivitySelection.selectSorted | Same plan, caller already ordered by finish **then start** | Full null/order validation and snapshot included; ordering inversion throws IllegalArgumentException |
| [IntervalPartitioning.assign](../../src/main/java/org/anaalvarezdev/algorithms/greedy/IntervalPartitioning.java) | List -> immutable Assignment(roomByInputIndex,roomCount) | Assigns **all** intervals; zero-based ids; positions correspond to original order; minimum number of rooms |
| [HuffmanCoding.build](../../src/main/java/org/anaalvarezdev/algorithms/greedy/HuffmanCoding.java) | Map of opaque int symbols to positive int frequencies -> immutable Codebook | Sorted symbol/code map, nonempty binary codewords, exact BigInteger weightedBitLength |
| HuffmanCoding.costWithHeap / costWithTwoQueues | Positive int[] frequencies -> exact BigInteger optimum length | Cost only, no symbols/codewords; same singleton convention; input preserved |
| [CoinChange.greedy](../../src/main/java/org/anaalvarezdev/algorithms/greedy/CoinChange.java) | Positive int denominations and nonnegative int amount -> immutable compressed Change | Duplicate denominations collapsed; descending CoinUse(denomination,quantity), remaining, coinCount and complete() |

Null collections/arrays, interval items, symbols and boxed frequencies throw
NullPointerException. Nonpositive frequencies/denominations or negative amount
throw IllegalArgumentException. Validation still occurs at zero amount; invalid
entries are not silently ignored. Public operations preserve supplied input.
Result lists/maps are defensively immutable; their record constructors freeze
collection ownership, while the algorithm guarantees returned semantic fields.

Empty interval lists give an empty plan/zero-room assignment. Equal Interval
records are indistinguishable by value; duplicate overlapping intervals cannot
both appear in a one-resource plan but require distinct rooms when assigning
all. Selection is deterministic by finish/start; room processing is by start,
finish, original index, reusing earliest available finish then room id. This
does not promise the numerically smallest free room id if finish times differ.

Empty Huffman input gives an empty map/cost zero. A singleton uses **"0"**, so
its cost equals its frequency. This explicitly requires nonempty codewords;
an abstract singleton zero-bit tree is a different convention. No bitstream
encoder/decoder, text parser, file header or compression container is produced.
The cost is payload codeword length, not total file size or a promised saving.

An empty coin set completes amount zero; positive amounts remain entirely
unpaid. `complete()` means remaining==0, **not minimum coin count**. Quantities
are compressed: amount Integer.MAX_VALUE with coin 1 needs one CoinUse entry,
not billions of objects. A nonzero remainder does not prove unrepresentability.

No comparator subtracts endpoints or symbols. Huffman subtree sums fit long:
at most Integer.MAX_VALUE positive int weights sum to less than 2^62. Weighted
path cost includes an additional depth factor, so it is accumulated exactly
with BigInteger rather than assuming long suffices for every theoretical array.
BigInteger bit arithmetic is distinct from queue-comparison counts.

## Selection: Safe Choice, Trace and Invariant

```text
SELECT: validate/snapshot; sort by (finish,start)
  chosen = empty
  for each interval in order:
    if no last choice or interval.start >= last.finish:
      append interval
  return immutable chosen
```

For any nonempty residual problem, let g finish earliest and let o be the first
interval of an optimal compatible plan. Replacing o by g cannot invalidate its
later intervals, since finish(g)<=finish(o). Cardinality is unchanged, so an
optimum containing g exists. After g, all feasible remaining choices start at
or after its finish; replacing a suboptimal suffix would improve the original
plan. Induction on residual size proves the safe rule plus optimal substructure.
The scan invariant is that chosen is compatible and can prefix an optimal
completion of the unprocessed feasible remainder. Each loop advances once.

For input `[(3,5),(1,4),(0,4),(4,7),(0,4)]`, finish/start order begins with the
two equal `(0,4)` records, then `(1,4),(3,5),(4,7)`. The returned value plan is
**[(0,4),(4,7)]**. The first duplicate is selected, overlapping alternatives
are skipped, and touching `(4,7)` remains feasible. Signed times need no
fictitious zero-time sentinel: the empty-plan check admits a negative first item.

`selectSorted` validates the same full order, including start ties, then uses
the same scan. Its different precondition is explicit; preparing its input can
still cost time elsewhere. A weighted objective invalidates the rule: intervals
[0,2),[2,4) of profit 1 each beat a [0,4) interval in cardinality, but lose to
it if its profit is 100. No weighted-scheduling implementation is claimed.

## Rooms: A Different Objective and Lower-Bound Proof

```text
ASSIGN: validate/snapshot; order by (start,finish,inputIndex)
  heap contains each allocated room's latest (finish,id)
  for each interval:
    if earliest finish <= start: remove/reuse that room
    else: allocate next room id
    record assignment at original input index; insert new latest finish
```

The heap keeps **one latest-finish entry per allocated room**, including rooms
that are currently idle. It is not an active-interval heap from which every
expired interval is removed. Reusing one eligible room is enough for the next
interval; unused idle entries remain available for subsequent assignments.

Legality follows because a reused room's last interval finishes before or at
the next start. When no room is reusable, every allocated room has a last
interval ending after the current start; all those intervals started no later
than it. Together with the new interval, r+1 intervals overlap at that time.
Any solution needs at least r+1 rooms. The algorithm allocates a new room only
at this lower bound, proving minimality. Finite ordered input proves termination.

For original input `[(5,7),(0,4),(1,4),(4,5)]`, processing order uses indices
1,2,3,0. Assign room 0 to index 1, room 1 to index 2, reuse room 0 at time 4,
then room 1 (earlier finish) at time 5. Output is **[1,0,1,0], two rooms**.
Selection may discard intervals; partitioning must place all. Their timings
cannot rank them as interchangeable algorithms.

## Huffman: Exchange, Contraction and Exact Cost

```text
BUILD: validate positive frequencies; create leaf queue
  while more than one node:
    remove the two minimum (weight,minimumSymbol) nodes
    merge them as left/right children; insert summed weight
  traverse with an explicit stack, assigning 0/1 edges
  return immutable codes and sum(frequency * codewordLength)
```

With at least two positive-frequency symbols, an optimal prefix tree can be
chosen full: an internal node with one child would be removable to reduce
positive weighted cost. In an optimal full tree choose sibling leaves at
maximum depth. Exchanging the two minimum weights into those positions cannot
increase cost: moving a lower weight to a greater depth changes the sum by
(lower-higher)*(greaterDepth-smallerDepth)<=0. Thus an optimum exists in which
the two minima are siblings. Contracting them into weight x+y reduces the
alphabet by one; expanding an optimum adds exactly x+y to weighted path cost.
If the contracted tree were suboptimal, replacing it would improve the original,
a contradiction. Induction gives optimality; arbitrary safe ties remain optimal.

This also proves the merge-cost identity **for n>=2**: weighted path length is
the sum of all n-1 merged weights. Singleton cost uses the separate one-bit
policy above; it is not inferred from a zero-merge sum.

Tie order is (subtree weight, minimum symbol id); disjoint subtrees have distinct
minimum symbols. Therefore insertion-map iteration does not change code values.
The first removal becomes the 0-child. Prefix freedom follows because symbols
are only leaves. Explicit stack traversal avoids recursive Java code generation;
materialised strings and the stack still consume storage.

For symbols 0..5 with frequencies **[45,13,12,16,9,5]**, merge weights are
14,25,30,55,100. Their sum is **224**, and deterministic codes are
`0:0, 1:101, 2:100, 3:111, 4:1101, 5:1100`. The frequencies here sum to 100;
this is the unscaled version of the book's thousand-occurrence example.

The heap cost-only method repeats n initial offers and n-1 two-minimum merges.
The second cost method sorts a private leaf array and maintains a monotone
queue of merged weights. Taking the smaller front across those two sorted
queues always yields the global minimum. New merge weights are nondecreasing,
so appending preserves the internal queue's order. The available-node count
falls by one per merge; both methods terminate and have the same optimum cost.
Neither cost-only method builds codewords or measures a full encoder.

## Coins: A Deliberately Limited Heuristic

```text
GREEDY_COINS: validate; sort a private denomination copy
  for distinct denominations descending:
    quantity = remaining / denomination
    remaining = remaining % denomination
    record positive quantity
```

The invariant is originalAmount=sum(denomination*quantity)+remaining, with
remaining nonnegative. Positive denominations make division meaningful; each
loop position decreases, so the procedure terminates. This establishes validity
of the reported payment/remainder, **not global optimality or completeness**.

- Coins {1,3,4}, amount 6: greedy gives 4+1+1 (**three** coins); optimum 3+3
  uses **two**. An exact complete answer can still be suboptimal.
- Coins {3,4}, amount 6: greedy gives 4 with remainder 2, despite 3+3 being
  feasible. Failure of this rule does not prove no representation exists.
- For the selected family {1,2,4,8,16,32}, two coins below the maximum can be
  replaced by one next-denomination coin. Repeated carrying gives the greedy
  normal form: as many 32s as needed, then binary digits. This proves optimal
  coin count for this family, not for arbitrary currency systems.

The dynamic-programming coin reference is test-only. It does not add a production
optimal-change solver or complete the future Dynamic Programming module.

## Complexity and Work Ledger

Let n be interval/symbol count, k chosen intervals, r allocated rooms, d supplied
denominations and L total materialised Huffman codeword characters.

| Operation | Work | Storage / output |
|---|---|---|
| Activity select | O(n log n) worst sort plus linear validation/scan | O(n) snapshot/sort state plus O(k) immutable plan |
| Validated selectSorted | O(n+1), including order validation and result | O(n) worst snapshot/output; only scalar scan state alone is O(1) |
| Room assignment | O(n log n) ordering plus O(n log(r+1)) heap work | O(n) snapshot/entries/assignment, O(r) latest-room heap |
| Huffman build | O(n log n) comparison/tree work plus O(L) code construction and exact cost arithmetic | O(n+L), including strings; not merely tree storage |
| Huffman heap cost | O(n log n) comparisons, n-1 BigInteger accumulations | O(n) heap/boxing state; no code strings |
| Huffman two-queue cost | O(n log n) worst sort plus Theta(n) cursor merges and exact accumulations | O(n) private int/long arrays; no code strings |
| Greedy coins | O(d log(d+1)+1) worst preprocessing/scan | O(d) copy and at most d compressed uses; no amount-sized array |

For nonempty Huffman cost input with n>=2 there are **n-1 merges**, **2(n-1)
minimum removals/front selections**, and n-1 cost additions. Heap cost performs
**2n-1 offers** (n leaves plus n-1 merged weights); initial offers are not a
linear-time heapify. The two-queue method sorts inside the public operation.
Comparison counts, BigInteger bit work, source allocation expressions and
elapsed time are different evidence. Full codebook traversal/map output adds
work absent from cost-only comparisons. O(n log n) is a worst bound, not a
claim that adaptive sorting has identical work on every distribution.

## Automated Verification

Verification reported **2026-10-26**, using **OpenJDK 21+35-2513**, passed normal
Maven tests and benchmark-profile clean package: **645 repository tests**, zero
failures/errors/skips, including **27 module tests** (prior base 618).

| Test class | Tests | Independent evidence |
|---|---:|---|
| [IntervalTest](../../src/test/java/org/anaalvarezdev/algorithms/greedy/IntervalTest.java) | 2 | Empty/reversed rejection; signed/extreme endpoints without subtraction |
| [ActivitySelectionTest](../../src/test/java/org/anaalvarezdev/algorithms/greedy/ActivitySelectionTest.java) | 6 | All 1,024 sets from ten intervals on endpoints 0..4, comparing exhaustive compatible subsets; 200 seeded lists, touching/ties/duplicates, ordered-input rejection, determinism/nonmutation/immutability |
| [IntervalPartitioningTest](../../src/test/java/org/anaalvarezdev/algorithms/greedy/IntervalPartitioningTest.java) | 6 | All 1,024 sets and 500 seeded schedules against independent maximum overlap; legal per-room assignments, original indices, signed times and immutable/deterministic output |
| [HuffmanCodingTest](../../src/test/java/org/anaalvarezdev/algorithms/greedy/HuffmanCodingTest.java) | 7 | All 1,093 weight arrays length 0..6 over {1,2,3}, compared with all binary root partitions; prefix-free/recovered cost, 100 seeded alphabets, equal/skewed/large weights and deterministic immutable maps |
| [CoinChangeTest](../../src/test/java/org/anaalvarezdev/algorithms/greedy/CoinChangeTest.java) | 6 | Two failure counterexamples; binary coins match exact DP for every amount 0..4,096; invalid/empty/duplicate domains, compressed MAX_VALUE quantity and input/output ownership |

Huffman's independent small oracle enumerates all full-tree root partitions;
it does not assume the minimum-pair merge rule being tested. A 1,000-symbol
uniform alphabet has independently expected cost 9,976; four MAX_INT weights
and 46 Fibonacci weights exercise long subtree sums/skew without a deliberate
stack overflow. Tests are finite evidence, not proof of all possible inputs.

All **five JMH methods** are discovered and **40 short smoke configurations**
complete with one 50 ms warmup/measurement and one fork. No smoke score becomes
research evidence. A separate probe checks **all 40 default combinations,
three calls each**, using a DP cardinality reference, event-sweep room lower
bound, legal assignments and a separate sorted-multiset Huffman cost reference,
including repeatability and unchanged trial inputs. Package JavaDoc passes
doclint excluding missing tags. These are local checks, not a new CI status.

## JMH Design and Windows Evidence Protocol

All classes use average **microseconds/op (`us/op`)**, one thread, **3 warmup /
5 measurement iterations at 500 ms**, and **two forks**. Trial-only input
construction uses seed **20261026**. JMH consumes returned results; generated
code/profiler inspection remains distinct from discovery or functional tests.

| Template | Sizes / patterns | Default cells | Timed contract |
|---|---|---:|---|
| [ActivitySelectionBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/greedy/ActivitySelectionBenchmark.java) | 100/10,000; DISJOINT, TOUCHING, OVERLAPPING, RANDOM | 16 | Full sortAndSelect versus validated scanPresorted, with snapshots and immutable plans |
| [IntervalPartitioningBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/greedy/IntervalPartitioningBenchmark.java) | 100/10,000; same four interval shapes | 8 | Full assignRooms: validation, sort, heap/records and immutable original-index assignment |
| [HuffmanCostBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/greedy/HuffmanCostBenchmark.java) | 100/1,000; UNIFORM, RANDOM, SKEWED, TWO_LEVEL | 16 | Full heapCost versus twoQueueCost, including boxing or clone/sort/arrays and exact accumulation |

DISJOINT intervals are [3i,3i+1); TOUCHING are [i,i+1). OVERLAPPING starts are
0..99 and finishes 101..200, so all share time 100; RANDOM starts are 0..4n-1
with durations 1..100. All lists are shuffled in setup. The first two shapes
need one room and select every interval; OVERLAPPING needs n rooms and selects
one. RANDOM cardinality/overlap is verified by reference, not invented from time.

Activity's ordered list is prepared **outside timing** for scanPresorted. It
models a caller already holding reusable order, not free sorting in the full
workflow. Both timed APIs validate/snapshot and build the same value plan.
Never subtract their means to claim an isolated sorting cost. Room assignment
solves another objective and must not be ranked against dropping intervals.

Huffman UNIFORM uses frequency 10; RANDOM 1..1,000; SKEWED one MAX_INT and
remaining ones; TWO_LEVEL alternates 1/1,000, shuffled before use. Both methods
preserve input and compute the same exact cost. They do not build codewords,
decode data or measure total file compression. CoinChange has test/counterexample
evidence without a timing question selected here; Interval is a domain value.

Each template has Research Question, Theoretical Expectation, Measured Work,
Result and Interpretation. **Result/Interpretation await measured author output**.
No score, error, allocation rate, stack-byte usage, cache/CPU cause, significance
or fitted exponent is invented. One seed/two sizes do not cover all inputs.

After creating/merging the feature PR, run from the Windows repository root:

```powershell
git switch main
git pull --ff-only origin main
.\mvnw.cmd -version
.\mvnw.cmd -Pbenchmark clean package
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar -l ".*greedy.*"
```

Expect **645 tests**, BUILD SUCCESS and **five listed methods**. Use the explicit
author-verified JDK 21 path or adapt it to the installed JDK. Maven release=21
controls bytecode compatibility, not which executable launches JMH. Packaging
builds/tests the jar; it does not execute performance measurements.

For the complete **40-cell** initial run:

```powershell
git rev-parse HEAD
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*greedy.*" -foe true
```

Alternatively run the three classes separately, preserving each run's own SHA,
header and completion marker (16/8/16 cells):

```powershell
git rev-parse HEAD
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*greedy.ActivitySelectionBenchmark.*" -foe true
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*greedy.IntervalPartitioningBenchmark.*" -foe true
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*greedy.HuffmanCostBenchmark.*" -foe true
```

Capture SHA **before measurement and before later result-documentation pulls**.
Send actual JDK/JMH/settings, complete final tables with every error, printed
fork iterations and completion markers, plus known CPU/OS metadata. Keep shape
versus distribution parameter columns, class-specific sizes and microsecond
units clear. Do not pool unlike objectives or separate runs without a design.
Evidence stays Markdown without JSON/log exports; console text is sufficient.
Linux uses a selected JDK 21, `bash mvnw` and the same jar/options. Short smoke
overrides stay separate from the controlled measurement protocol.

## Sources and Review Decisions

The supplied **CLRS Third Edition (2009)** was consulted: §16.1, printed
pp. 415-422 (half-open activities, exchange Theorem 16.1 and iterative scan);
§16.2, pp. 423-427 (safe choice/substructure, 0-1 versus fractional knapsack);
§16.3, pp. 428-436 (prefix trees, weighted length and Lemmas 16.2/16.3).
Theorem 16.1 on p. 418 was visually checked against the PDF. These are
third-edition locations, not fourth-edition pagination. Room allocation's
maximum-overlap proof and the two-queue invariant are developed above for the
selected code; they are not attributed to an unverified book subsection.

Signed integer intervals, snapshot policies, deterministic ties, nonempty
singleton Huffman codes, opaque symbols, BigInteger cost, cost-only variants
and compressed greedy change are explicit repository choices, not verbatim
book code. Huffman optimises a prefix-code payload model, not every compressor;
greedy change is explicitly a heuristic on arbitrary denominations. Mathematical
properties, Java correctness, finite tests and future empirical data remain
distinct links of the traceability chain.
