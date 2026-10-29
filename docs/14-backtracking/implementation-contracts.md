# Backtracking: Applied Contracts, Restoration and Search Ledgers

Reviewed on **2026-10-29 (America/Buenos_Aires)** from main
`df79799f8d8412df840c6e9f5cd1588a35f0fe5c`, the PR #111 Greedy-review merge.
The [review](documentation-review.md) records scope and fresh native execution;
the [guide](implementation-and-benchmark-guide.md) preserves original build,
probe/smoke and author-experiment provenance. No executable source changes.

## Public specifications

| Source / API | Accepted domain / normal result | Policy |
|---|---|---|
| [Permutations.unique](../../src/main/java/org/anaalvarezdev/algorithms/backtracking/Permutations.java) | Non-null int[], length 0..9; all distinct value arrangements | Equal values indistinguishable; lexicographic rows; input preserved; immutable rows/outer list |
| Permutations.countUnique / countUsingFrequencies | Non-null int[], length 0..10; same exact long count | Full search with canonical sorted positions or remaining multiplicities; no formula/cache/rows |
| [Combinations.choose](../../src/main/java/org/anaalvarezdev/algorithms/backtracking/Combinations.java) | 0<=k<=n<=18; all k-subsets of **0..n-1** | Strictly increasing rows, lexicographic outer order, immutable output; not an arbitrary-multiset API |
| Combinations.countWithPruning / countWithoutPruning | Same n/k domain; exact long C(n,k) | Capacity-bound versus dead-prefix traversal, same result; no result rows |
| [NQueens.solutions](../../src/main/java/org/anaalvarezdev/algorithms/backtracking/NQueens.java) | n=0..12; all ordinary boards as zero-based column per row | Lexicographic immutable rows; rotations/reflections separate; no preplaced queens or first-board API |
| NQueens.countWithFlags / countWithBitMasks | Same n domain; exact long ordinary-board count | Full legal-prefix traversal, no boards or symmetry quotient; masks validated before shifts |
| [SubsetSum.exists / existsWithoutBounds](../../src/main/java/org/anaalvarezdev/algorithms/backtracking/SubsetSum.java) | Non-null signed int[], length 0..24; any long target; exact boolean feasibility | Select each position at most once; duplicates are different positions; empty subset allowed; include-first short circuit; no count/witness list |

Null arrays throw NullPointerException. Oversized arrays/invalid scalar domains
throw IllegalArgumentException before recursion or output preparation. Even an
all-equal length-10 input is rejected by unique, and length-25/target-zero is
rejected by both subset APIs. These work/output caps are not mathematical
limits, primitive overflow boundaries or fixed resource budgets.

Empty permutation, choose(n,0), choose(0,0) and NQueens(0) each have **one empty
solution**, count one. NQueens(2/3) has no solutions. Empty subset input is true
exactly for target zero; target zero is feasible for every accepted array.
Lexicographic and value-immutability contracts do not promise fresh empty-list
identities. Caller arrays are preserved and per-call mutable arrays are private;
no static cache or shared search state is retained.

Counters fit long: permutation leaves<=10!, combination count<=C(18,9)=48620,
queen solutions<=12! before diagonal filtering. For subset length<=24, every
sum has magnitude<=24*2^31<2^36. A prefix plus a disjoint suffix min/max still
uses each input position at most once, so those bound additions also fit long.
Compare the arbitrary long target directly with these safe sums; do not compute
an unchecked target-sum at Long.MIN_VALUE/MAX_VALUE. Concurrent caller mutation
while reading is not a promised input-snapshot protocol.

## Helper contracts and normal-return restoration

A search proof needs sound outputs, complete candidates, uniqueness under the
chosen equivalence, preserved constraints and a decreasing measure. Count,
enumeration and decision use different terminal rules. Normal-return obligations
apply to the relevant logical parent state; they do not require wiping every
array cell or materialising an explicit search tree.

### Permutations: canonical index representatives or multiplicities

For visit(sorted,used,path,depth,output), sorted is a private sorted clone.
The live path is exactly [0,depth); used positions supply precisely its multiset.
Within an equal-value block, used positions form an initial segment: a position
can be selected only after its equal predecessor is already used. The first
unused equal position remains available whenever that value is still needed.
Thus each next value has one canonical index representative. Induction gives
all distinct value suffixes exactly once; ascending candidates yield
lexicographic output. This removes equivalent index histories, not legal values.

Before each child, mark used[i] and write path[depth]. After it returns, unmark
used[i], restoring the parent's used state and live prefix. Child writes at or
beyond depth are no longer logically live and need not be cleared. At depth=n,
return one and, if enumerating, copy the completed path into an immutable row.
The outer list is frozen after search. A saved reference to the working path
would not satisfy this contract.

For [2,1,1], sort to [1a,1b,2]. Root 1a gives [1,1,2] and [1,2,1]; root 1b
is skipped while 1a is unused; root 2 gives [2,1,1]. Used-index flags without
the equal-copy rule would still duplicate value rows. At n=0 the root is already
complete and returns one, not zero.

The frequency helper stores only remaining counts for u distinct values.
Each branch decrements one positive count, recurses with remaining-1 and
increments it on return. Remaining plus prefix multiplicities equals the
original multiset throughout. One branch per available value gives the same
value-prefix tree as canonical positions; counts need no explicit value labels
or output rows. Both variants terminate as remaining length decreases.

Let multiplicities be m1..mu. At depth d, the number of distinct value prefixes is

```text
A_d = sum d! / product(a_j!)
      over 0<=a_j<=m_j and sum(a_j)=d
```

Then P=A_n=n!/product(m_j!), I=sum(A_d,d=0..n-1) and total helper calls=I+P.
Position loops perform exactly n*I candidate checks; frequency loops u*I.
Preparation (clone/sort and compression/state allocation) is additional.
For all-equal n>0, I=n,P=1: n^2 versus n checks, both n+1 calls. For DISTINCT,
u=n, so checks are equal despite different bookkeeping and historical means.
These are source-loop counts, not instruction or profiler counts.

### Combinations: complete increasing tuples and a derived ledger

For enumerate/count(n,from,remaining), the prefix is increasing and remaining
is the number still needed. Every child starts at candidate+1 and has
remaining-1. A feasible next candidate must satisfy candidate<=n-remaining;
otherwise too few greater values remain. Every valid increasing completion
satisfies this bound, so pruning is sound and complete. Increasing order
prevents permutation duplicates and ascending iteration gives lexicographic rows.

The unpruned counter instead allows every candidate through n-1. An empty
candidate range with remaining>0 returns zero; remaining=0 returns one.
Infeasible prefixes are not extra solutions. from/remaining are scalar child
arguments. Enumeration overwrites the next path slot; on return the unchanged
live prefix remains valid. Each complete length-k path is copied before reuse.
The measure remaining decreases on every edge and candidate loops are finite.

For choose(4,2), root candidates are 0,1,2, with leaves [0,1],[0,2],[0,3],
[1,2],[1,3],[2,3]. Pruned calls by depth are 1,3,6 (total 10); unpruned calls
are 1,4,6 (11), including dead prefix [3]. Both return six rows/count six.

At depth d, unpruned calls are C(n,d), so total calls=sum(C(n,d),d=0..k).
In a pruned depth-d prefix, the last value is at most n-k+d-1, and every
increasing d-tuple within that range is reachable. Hence there are
C(n-k+d,d) calls. Summing over d by the hockey-stick identity gives
**C(n+1,k)**. Both formulas include root, successful bases and relevant dead
calls; edge/loop iteration count is one less than total helper calls.

| n,k | Returned count | Pruned calls | Unpruned calls |
|---|---:|---:|---:|
| 0,0 | 1 | 1 | 1 |
| 4,2 | 6 | 10 | 11 |
| 18,2 | 153 | 171 | 172 |
| 18,9 | 48620 | 92378 | 155382 |
| 18,18 | 1 | 19 | 262144 |

Each pruned prefix extends to some full row. Counting at most k+1 prefixes
per complete row bounds visited calls by (k+1)C(n,k). Copying k entries per
row plus row/outer containers supplies a matching lower bound, including k=0.
Thus the selected choose implementation has **Theta((k+1)C(n,k))**
materialisation work/retained output under the bounded word model. Its working
path/depth is O(k+1). One returned row at k=n does not imply one helper call.

### N-Queens: exact occupancy, masks and ordinary placement paths

At row r, exactly the earlier r rows have queens, with distinct columns and
no shared diagonals. Flag indices are down=r-column+n-1 and up=r+column;
for n>0 they are within 0..2n-2. Reject if any of the three flags is occupied.
A rejected attack cannot be repaired by later queens. Every legal next queen
passes all checks, so no board is omitted; fixed row order gives a unique path.

An accepted candidate's three entries were all false. Setting them establishes
the child invariant; clearing them after normal return restores the parent
without erasing an ancestor's occupied entry. The path prefix is restored by
logical depth, not clearing stale deeper columns. At row=n return one and
optionally copy the path; n=0 returns before any diagonal indexing. Row increase
proves termination. Ascending columns yield lexicographic board lists.

For n=4, safe prefix [1,3,0] completes with 2. Its reflection [2,0,3,1] is
another ordinary solution, not removed by the algorithm. The count is not the
number of symmetry classes, and no counter exits after the first board.

In mask recursion, columns marks used columns and left/right are diagonal
attacks **in the current row**. With full=(1L<<n)-1,
available=full & ~(columns|left|right) gives exactly safe children. Extracting
available & -available picks the lowest column bit; subtracting it removes one
candidate. Child columns|bit has one more occupied column; attacks become
((left|bit)<<1)&full and (right|bit)>>>1. Child scalars are passed by value,
so no shared flags need undo. n-popcount(columns) decreases; columns==full
is the complete base, including full=0 for n=0. The 0..12 cap is validated
before shifting; Java's masked shift distance is not an arbitrary-board model.

For V legal-prefix calls and Q complete boards, flags inspect exactly
**n*(V-Q)** candidates, masks execute **V-1** available-child iterations.
They visit the same ordinary-prefix tree but do different local work. Flags
still allocate/write a path in count-only calls. Both have O(n+1) logical
depth; constant mask words per frame do not make total auxiliary space constant.

### Signed subset sum: exact decision with a sound interval bound

For search(i,sum), sum is the exact value selected from positions below i.
The postcondition is whether some subset of positions i..n-1 completes target.
At sum==target return true: exclude every undecided item. This can be a terminal
of the **visited** tree before i=n. If i=n with a different sum, return false.
Otherwise include/exclude the next position partitions all completions. The
logical OR evaluates include first and omits exclude when include succeeds;
this is correct for feasibility, not full counting or witness enumeration.

For the bounded helper, minimum[i] sums negative suffix values and maximum[i]
positive suffix values. Every suffix subset lies between them. Therefore
an outside target is impossible; an inside target still needs exact search.
For [8,-9], target -1, including 8 can still complete with -9. Rejecting
sum>target would be wrong. For [2,2,2], target 5 lies inside the root interval
but no subset works. Equal values remain separate selectable positions, unlike
value-unique permutations. Zero values can create equivalent sum states;
production has no memo table or parity shortcut.

index strictly increases, so n-index proves termination. sum/index are scalar
child values and bounds arrays are read-only; early true/false requires no
shared path rollback. Public exists always validates and builds two suffix
arrays first, even for target zero or a root miss. The reference omits those
arrays but has the same exact result and input-preservation contract.

For default even n with all twos: RANGE_MISS target=2n+1 has one bounded call
versus 2^(n+1)-1 without bounds. PARITY_MISS target=2n-1 follows an include
chain with every excluded sibling bounded out, totaling 2n+1 bounded calls;
there is no implemented parity test. PRESENT target=n succeeds on the first
n/2 includes, n/2+1 calls in both. SIGNED_MISS alternates -2,+2/target 1 and
retains many prefixes under interval bounds. These controls describe full
workflows, not constant-time public root checks or general polynomial search.

## Costs, output and finite-resource boundaries

Bounds count constant-cost primitive word operations within the accepted caps.
Let I/P/u be as above, C=C(n,k), V visited states and Q queen boards. Add
clone/sort/compression when present; logical helper depth excludes fixed wrapper
cost and is not measured JVM frames, bytes or a safe-recursion limit.

| Operation | Work | Working state / retained result |
|---|---|---|
| Position / frequency permutation count | Preparation plus O(nI+P+1) / O(uI+P+1) | O(n+1) private arrays/depth, one long; position count also writes a private path |
| Permutation unique | Position search plus Theta((n+1)P) copying/containers | O(n+1) working state plus Theta((n+1)P) output |
| Combination count | Theta(V), exact call sums above | O(k+1) logical depth, one long |
| Combination choose | Theta((k+1)C) including pruned search/copying | O(k+1) working state plus Theta((k+1)C) output |
| Queen flags / masks count | O(nV+1) / O(V) word work | O(n+1) arrays/depth or primitive frames, one long |
| Queen solutions | Flag search plus Theta((n+1)Q) copies/containers for nonempty result | O(n+1) working state plus output; no stored search tree |
| Subset decision | O(n+1) bounded preparation plus O(V); worst search O(2^n) | O(n+1) logical depth, plus bounded suffix arrays, one boolean |

For queen output with Q=0, keep constant outer-container work; no result rows
are copied. All enumeration retains returned rows beyond the active DFS path.
Total calls, local candidate tests, copied entries and live state are different
quantities. A uniform b-branch/depth-d bound sums b^i, with separate chain/root
bases; it does not count local costs or retained output automatically.

These are algorithmic-family models under finite Java domains. Mathematical
termination and exact normal results are not promises of sufficient heap/stack
or fixed elapsed time. No general symmetry reduction, DP, branch-and-bound,
streaming API or arbitrary-size mask implementation is introduced.

## Native tests and measurement traceability

Fresh [verification](documentation-review.md) runs the actual Java classes:
**710 tests**, zero failures/errors/skips; **27** module invocations. Four test
sources contain four concrete test classes. Looped/exhaustive cases are not
separately declared JUnit invocations.

| Existing tests | Independent checks |
|---|---|
| [PermutationsTest](../../src/test/java/org/anaalvarezdev/algorithms/backtracking/PermutationsTest.java), 7 | 120 small multisets against complete index permutations/value deduplication and BigInteger multinomials; 100 seeded arrays, extremes, 10!, caps, restoration and ownership |
| [CombinationsTest](../../src/test/java/org/anaalvarezdev/algorithms/backtracking/CombinationsTest.java), 6 | Every n<=12/k against bitmask subsets; n=18 counts against BigInteger binomials, Pascal identity, 48620-row boundary, ordering and ownership |
| [NQueensTest](../../src/test/java/org/anaalvarezdev/algorithms/backtracking/NQueensTest.java), 7 | All boards through n=9 from complete column permutations with leaf-only diagonal rejection; published ordinary counts through 12, 14200-row boundary, reflections, caps and repeatability |
| [SubsetSumTest](../../src/test/java/org/anaalvarezdev/algorithms/backtracking/SubsetSumTest.java), 7 | 1093 signed arrays and targets -8..8 against iterative reachable-sum sets; 100 seeded full-int arrays, extreme targets, zeros, signed examples, reversal/negation, caps and input preservation |

The external [OEIS A000170](https://oeis.org/A000170) list was checked on
2026-10-29 for ordinary counts at offset zero. It supplements the independent
small board oracle and proof; it supplies no benchmark measurements or new
arbitrary-n acceptance domain.

Fresh discovery lists **8 JMH methods / 46 default cells**:

- [Permutations](../../src/jmh/java/org/anaalvarezdev/algorithms/backtracking/PermutationCountBenchmark.java): n=6/8, DISTINCT/DUPLICATE_HEAVY/ALL_EQUAL, 12 cells; both include clone/sort/private state, no rows.
- [Combinations](../../src/jmh/java/org/anaalvarezdev/algorithms/backtracking/CombinationCountBenchmark.java): n=12/18, TWO/HALF/ALL, 12 cells; validated counts, no rows or formula cache.
- [Queens](../../src/jmh/java/org/anaalvarezdev/algorithms/backtracking/NQueensCountBenchmark.java): n=8/10/12, 6 cells; full ordinary counts, flags include arrays/path, masks use scalar child state.
- [Subsets](../../src/jmh/java/org/anaalvarezdev/algorithms/backtracking/SubsetSumBenchmark.java): n=12/18, RANGE_MISS/PARITY_MISS/PRESENT/SIGNED_MISS, 16 cells; suffix preparation included only in bounded, no witness/count/DP.

All use avgt **us/op**, one thread, 3/5 x 500 ms warmup/measurement, two forks.
[Trial fixture generation](../../src/jmh/java/org/anaalvarezdev/algorithms/backtracking/BacktrackingInputs.java)
is excluded; permutation shuffling uses seed 20261026 and other fixtures are
deterministic controls. Discovery checks registration, not a fresh timed run.

The [author report](../17-benchmarking/results/backtracking/README.md) retains
46 means/errors, 460 measured and 276 warmup iteration averages, source
**author-attributed** `b62df1b84810dfe5dea023d5d00bac2eb05c0cd0` (PR #88),
Oracle JDK 21.0.9/JMH 1.37, 6 min 42 sec. Two errors reach 25%; all remain
intact, including tiny nonzero printed uncertainties. Source ledgers do not
assign CPU/allocation/predicate shares, prove significance or an exponent fit.
Counts/decisions do not measure enumeration throughput. Current Corretto
functional verification and historical Oracle measurements remain separate.
