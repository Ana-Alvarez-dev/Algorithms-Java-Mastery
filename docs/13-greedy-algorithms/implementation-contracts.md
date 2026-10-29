# Greedy: Applied Contracts, Safe Choices and Cost Map

Reviewed on **2026-10-29 (America/Buenos_Aires)** from main
`0bc276ff49a5cd6ae265f2c4968d2c507ad3e1d7`, the PR #110 Divide and Conquer-review
merge. The [review](documentation-review.md) records scoped findings and native
Java evidence; the [guide](implementation-and-benchmark-guide.md) preserves
original implementation and author-experiment provenance. No algorithm changes.

## Public contracts and ownership

| Source / operation | Accepted domain and normal result | Failure / ownership policy |
|---|---|---|
| [Interval](../../src/main/java/org/anaalvarezdev/algorithms/greedy/Interval.java) | Immutable nonempty [start,finish), signed int endpoints | start>=finish throws IllegalArgumentException; touching intervals are compatible |
| [ActivitySelection.select](../../src/main/java/org/anaalvarezdev/algorithms/greedy/ActivitySelection.java) | Maximum-cardinality compatible plan from any interval order | Null list/items throw NullPointerException; private sort by finish/start; input preserved, unmodifiable result |
| ActivitySelection.selectSorted | Same result under nondecreasing finish-then-start order | List.copyOf rejects nulls before order scan; a comparator inversion throws IllegalArgumentException; no silent sort |
| [IntervalPartitioning.assign](../../src/main/java/org/anaalvarezdev/algorithms/greedy/IntervalPartitioning.java) | Assign **every** interval to the minimum rooms; original-index list of zero-based room ids | Null list/items fail; input preserved; empty input gives empty assignment/zero rooms; returned list unmodifiable |
| [HuffmanCoding.build](../../src/main/java/org/anaalvarezdev/algorithms/greedy/HuffmanCoding.java) | Opaque int symbols, positive int frequencies; deterministic sorted code map and exact BigInteger weighted payload length | Null map/keys/values fail; nonpositive frequency throws IllegalArgumentException; input preserved, map defensively copied/unmodifiable |
| HuffmanCoding.costWithHeap / costWithTwoQueues | Positive int[] frequencies; exact same optimum cost without codewords | Null fails; all frequencies validated before bases; no input mutation; empty cost zero, singleton cost its frequency |
| [CoinChange.greedy](../../src/main/java/org/anaalvarezdev/algorithms/greedy/CoinChange.java) | Positive int denominations, nonnegative int amount; compressed descending distinct uses, remainder and long coinCount | Null fails first; negative amount/nonpositive denominations throw IllegalArgumentException, including invalid coins at amount zero; input preserved |

Output records freeze their collection fields but are not general semantic
validators: callers can construct Assignment, Codebook, Change or CoinUse values
that do not satisfy the corresponding factory's guarantees. Interval itself
does validate its bounds. Codebook's public constructor copies its map but does
not validate arbitrary code strings, prefix freedom or weightedBitLength.

Interval records, Integer ids and Huffman strings are immutable values. A
List.copyOf snapshot may reuse an appropriate immutable input; ownership
semantics do not promise fresh allocation or a distinct identity. None of these
operations provides a snapshot guarantee against concurrent caller mutation
while reading. All selected methods use per-call state, not a shared memo cache.

## Prove the rule for a specified residual state

A safe-choice claim is existential: **some optimum contains this action**.
The action is justified before computing the residual optimum; this does not
make subsequent choices independent or arbitrary. Fix objective, feasibility,
state and tie policies, prove an exchange/lower bound, prove residual
replacement and finite progress, then connect the Java contracts and tests.

Optimal substructure alone is insufficient. With {1,3,4}, amount 6, fixing
coin 4 and solving residual 2 optimally gives three coins, while 3+3 gives two.
For activity selection, arbitrarily choosing [0,10) can block [0,1),[1,2).
An optimal completion of that bad first choice remains globally inferior.

### Activity selection: earliest finish and compatible prefix

For a nonempty residual set, let g finish earliest and let o be the first
interval of an optimal compatible plan. Replacing o by g preserves cardinality
and every later activity because finish(g)<=finish(o). Thus some optimum starts
with g. The residual is exactly the activities with start>=finish(g), including
touching endpoints. If its chosen suffix were suboptimal, replacing it would
improve the whole, a contradiction. Induction on residual size proves maximum
cardinality. This proof does not optimise weighted profit or total duration.

The scan invariant is that the chosen prefix is compatible and admits an
optimal completion among unprocessed feasible intervals. Rejecting an interval
starting before lastFinish cannot extend that prefix. Accepting the first
feasible interval in finish order makes the safe next choice. Each input item
is processed once. The empty-plan guard admits a negative first start despite
lastFinish initially being zero; zero is not a lower-bound time sentinel.

For [(3,5),(1,4),(0,4),(4,7),(0,4)], finish/start ordering begins with the two
(0,4) values. Select one, skip all overlaps, then select (4,7). Duplicates are
allowed but two identical nonempty intervals cannot share this plan. Tie rules
select a deterministic representative; no separate lexicographically minimum
schedule objective or identity guarantee is declared.

The sorted variant requires the **full comparator order**, not finish alone:
[(1,3),(0,3)] is rejected even though a finish-only greedy proof could permit
that tie. Both public variants validate/snapshot and materialise the answer;
constant scalar scan state is not constant complete-operation space.

### Rooms: legality plus a matching overlap bound

Process entries by (start,finish,original index). Maintain one heap entry
(finish,id) for every allocated room, representing its latest assigned interval.
This includes idle rooms. The output prefix assigns every processed input to
one room with no per-room overlap; earlier original indices are retained in
Entry and written to their original positions.

If the minimum finish<=current start, reuse it legally and replace its heap
entry. Otherwise all allocated rooms have latest finish>start and latest
starts<=current start, so they and the new interval overlap at that time.
Every valid solution then needs the new room count. Creating rooms only under
that lower bound, while always maintaining legality, proves minimum room count
equal to peak overlap. At equal endpoints, a finishing interval is not active
when the next starts. Finite input scanning establishes termination.

Trace [(5,7),(0,4),(1,4),(4,5)]: process indices 1,2,3,0. Allocate ids 0 and 1,
reuse 0 at time 4, then reuse 1 at time 5 because it finishes earlier. Return
[1,0,1,0], roomCount=2. Reuse is minimum (finish,id), **not minimum free id**
across differing finish times. No interval is discarded, unlike selection.

For n inputs and r allocated rooms, source operations are n peeks, n offers,
n-r reused-room removals and r allocations. Heap size is r, not necessarily
current overlap. These are API-operation counts, not comparator counts, CPU
events or measured allocations. Only one idle entry needs removal per reuse;
removing all expired entries would describe a different heap invariant.

### Huffman: exchange, internal proof base and exact cost

For n>=2 positive weights, an optimal prefix tree can be chosen full: suppressing
a unary node strictly decreases the positive weighted depths. There is a deepest
sibling leaf pair. Exchange the two minimum weights x,y into those positions;
moving a lower weight deeper and a higher one shallower cannot increase cost.
Thus an optimum contains that sibling pair. Contract it to weight x+y. Expanding
an optimum for this contracted tree adds exactly x+y; a better contracted tree
would improve the original optimum. Induction justifies two-minimum merging.

**The internal proof uses depth-zero roots.** If n=2, contraction leaves one
root of cost zero and expansion costs x+y. The separate public singleton API
emits "0" and costs its frequency. Substituting that public cost into the
internal contraction formula would incorrectly double the two-symbol cost.
The iterative Java algorithms never make that recursive public call. Empty
public input returns zero; n>=2 cost equals the sum of all n-1 merged weights.

Build orders available subtrees by (weight,minimum symbol id), making the first
removal the 0-child and the second the 1-child. Disjoint subtrees have distinct
minimum symbols, so map insertion order does not affect tie results. Symbols
are leaves; therefore no codeword prefixes another. This deterministic rule is
not a canonical Huffman length format, text parser or encoded-bitstream API.

For symbols 0..5 with [45,13,12,16,9,5], merge weights 14,25,30,55,100 sum
to 224. Codewords are 0:0, 1:101, 2:100, 3:111, 4:1101, 5:1100. Zero/negative
frequencies are rejected, not silently removed.
Payload optimality says nothing about headers or total compressed-file size.

The heap cost method initially offers n leaves, then repeats n-1 two-minimum
merges. The two-queue method sorts a private leaf copy and maintains sorted
unconsumed leaves plus a nondecreasing internal-weight queue. Each step takes
the smaller front twice, yielding global minima a<=b. The next available value
is >=b, and the just-created a+b is also >=b because weights are positive.
The next pair sums to at least 2*b>=a+b, so the next merged weight cannot
decrease; appending preserves queue order. Each merge reduces available-node count by one. Both methods
therefore implement the same safe rule and terminate with the same exact cost.

For n>=2, each cost method has n-1 merges, 2(n-1) minimum removals/front takes
and n-1 BigInteger cost additions. Heap offers total 2n-1; leaves are inserted
individually, not through a separate linear heapify. Two-queue sorting is inside
the public operation, followed by linear cursor work. Neither method builds codes.

### Coins: payment validity versus optimality/completeness

Validate all supplied denominations, sort a clone, skip duplicate values and
visit distinct coins descending. For remaining R and positive coin c, quotient
q=R/c and new remainder R%c satisfy R=q*c+newR, 0<=newR<c. Recording q only
when positive preserves originalAmount=sum(c*q)+remaining. The finite cursor
scan proves termination. All recorded coins/quantities are positive, ordered,
distinct and compressed; coinCount is their sum, at most amount since c>=1.

With {1,3,4}, amount 6, the output is 4+1+1, complete but suboptimal versus
3+3. With {3,4}, amount 6, it gives 4/remainder 2 although 3+3 exists.
complete() means remaining==0, **not minimum count**; failure is not proof of
unrepresentability. Empty coins complete only zero. Invalid coin values still
fail at zero amount. A single coin 1 at MAX_INT amount produces one CoinUse,
not billions of entries or amount-proportional iteration.

For the selected {1,2,4,8,16,32} family, carrying any two equal submaximum coins
to the next denomination decreases count; after repeated carrying, all smaller
quantities are binary digits and the number of 32s is the quotient. This is the
greedy normal form and proves optimality for this family, not arbitrary currency
systems. The exact DP routine is a test oracle, not a production solver or
completion of the later Dynamic Programming module.

## Arithmetic, output and cost models

Comparators compare int/long fields directly instead of subtracting them.
Array-based Huffman has n<=2^31-1 positive int weights, so subtree sums<2^62.
For the map alphabet, at most 2^32 distinct int ids can occur; even total
2^32*(2^31-1)<2^63 fits long. Weighted depth adds another factor, so cost uses
BigInteger; this does not make its arithmetic constant-cost or output unlimited.
These are mathematical bounds, not feasible-heap promises for huge structures.

Let L be total returned Huffman codeword characters for n>=2. For a full tree,
if D is the sum of internal-node depths, leaf depths satisfy L=D+2(n-1).
The strings created along all node prefixes have total length D+L=O(L).
Thus explicit traversal/prefix materialisation adds O(L) character work/storage
to O(n) tree/queue state. Full build creates 2n-1 Node values and visits them
once, with one Frame per node in source. These counts do not measure surviving
heap objects, bytes, JIT optimisation or garbage-collection behaviour.
The singleton's explicit code has length one and is handled separately.

| Public operation | Source work model | State / output |
|---|---|---|
| select | O(n log(n+1)+1) worst sorting plus linear validation/scan | O(n) snapshot/sort plus O(k) selected values |
| selectSorted | O(n+1) validation/scan/result | O(n) worst snapshot/output; O(1) scalar greedy state |
| assign | O(n log(n+1)+1) ordering plus O(n log(r+1)) heap work | O(n) entries/assignment, O(r) availability entries |
| Huffman build | O(n log(n+1)+1) comparison/tree work plus O(L) characters and exact arithmetic | O(n+L), not just the tree |
| Huffman heap cost | O(n log(n+1)+1) comparisons plus exact accumulations | O(n) queue/boxing, no code strings |
| Huffman two-queue cost | O(n log(n+1)+1) worst sorting plus linear merges/exact accumulations | O(n) private leaf/internal arrays |
| greedy coins | O(d log(d+1)+1) worst preprocessing/scan | O(d) copy/compressed uses; no amount-sized structure |

Here k=selected count, r=rooms, d=supplied denomination count. Empty cases keep
constant base/validation work. Collection traversal/copy costs assume ordinary
finite stable inputs. Sorting adaptivity, library arithmetic and code strings
are not a fixed physical operation penalty. A greedy decision alone imposes
no universal lower-memory or faster-runtime relation to a DP formulation.

## Java tests and empirical chain

The [fresh review](documentation-review.md) records **710 passing tests**, zero
failures/errors/skips, including **27** module invocations. Five concrete test
classes plus [SmallSchedules](../../src/test/java/org/anaalvarezdev/algorithms/greedy/SmallSchedules.java)
make six sources; loops/enumerations are not separate JUnit invocations.

| Existing tests | Independent evidence |
|---|---|
| [IntervalTest](../../src/test/java/org/anaalvarezdev/algorithms/greedy/IntervalTest.java), 2 | Signed/extreme endpoints and empty/reversed rejection |
| [ActivitySelectionTest](../../src/test/java/org/anaalvarezdev/algorithms/greedy/ActivitySelectionTest.java), 6 | All 1024 subsets of ten intervals against exhaustive compatible subsets; 200 seeded/linked lists; ties/touching/duplicates/signed/null/order/ownership |
| [IntervalPartitioningTest](../../src/test/java/org/anaalvarezdev/algorithms/greedy/IntervalPartitioningTest.java), 6 | Same small sets and 500 seeded schedules against independent peak overlap at starts; full per-room legality, original indices, deterministic ids and ownership |
| [HuffmanCodingTest](../../src/test/java/org/anaalvarezdev/algorithms/greedy/HuffmanCodingTest.java), 7 | 1093 small weight arrays against exhaustive full-tree root partitions, prefix freedom/recovered costs; 100 seeded alphabets; 1000 equal weights cost 9976; four MAX_INT and 46 Fibonacci weights; map-order/ownership and singleton bases |
| [CoinChangeTest](../../src/test/java/org/anaalvarezdev/algorithms/greedy/CoinChangeTest.java), 6 | Two failure counterexamples; every amount 0..4096 versus exact DP for binary coins; invalid/empty/duplicates, compressed MAX amount and ownership |

Huffman's seeded larger cases cross-check algorithms, prefix freedom and
recovered cost; the independent full-tree optimum oracle is bounded to the
small exhaustive domain. No two-minimum rule is assumed inside that oracle.
Proofs and finite native tests supply complementary evidence, not a measurement
of general-purpose compression, memory footprints or unbounded resource safety.

Fresh JMH discovery lists **5 methods / 40 default cells**, 16 activity, 8 rooms,
16 Huffman. All use avgt **us/op**, one thread, 3/5 x 500 ms warmup/measurement,
two forks; [fixtures](../../src/jmh/java/org/anaalvarezdev/algorithms/greedy/GreedyInputs.java)
use seed 20261026 outside timing. Discovery is not a fresh timing/smoke/probe run.

- [Activity](../../src/jmh/java/org/anaalvarezdev/algorithms/greedy/ActivitySelectionBenchmark.java): sizes 100/10000, DISJOINT/TOUCHING/OVERLAPPING/RANDOM; full validated calls with snapshots/plans. Presorted preparation is excluded for scanPresorted; mean subtraction does not isolate sorting.
- [Rooms](../../src/jmh/java/org/anaalvarezdev/algorithms/greedy/IntervalPartitioningBenchmark.java): same sizes/shapes; complete assignment/sort/heap/records/output. It solves a different objective from dropping intervals.
- [Huffman cost](../../src/jmh/java/org/anaalvarezdev/algorithms/greedy/HuffmanCostBenchmark.java): sizes 100/1000, UNIFORM/RANDOM/SKEWED/TWO_LEVEL; heap boxing versus clone/sort/arrays and exact cost; no codeword/codec throughput.

Disjoint/touching fixtures select n and need one room; overlapping fixtures
all cover time 100, select one and need n rooms. Random cardinality/room count
is not inferred from elapsed means. Frequency distributions change populations,
and a single seed does not isolate hardware effects. CoinChange has no selected
benchmark question here; Interval is a domain record.

The [historical report](../17-benchmarking/results/greedy/README.md) retains all
40 means/errors, 400 measured and 240 warmup iteration averages, source
**author-attributed** `674ab35b7d82f1a9ab7fbab63fa1a9e80253965c` (PR #86),
Oracle JDK 21.0.9/JMH 1.37, 5 min 51 sec completion. Ten errors reach 25%; all
remain intact. Missing jar hash/full command/timestamp are not recovered by
this review. Complete-workflow means do not identify allocation/heap/sort/CPU
shares, establish optimality, universal ratios, significance or an asymptotic
fit. Current Corretto functional execution is a separate evidence statement.
