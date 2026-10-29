# Dynamic Programming: Applied Contracts, Dependencies and Witnesses

Reviewed on **2026-10-29 (America/Buenos_Aires)** from main
`bbab550e82a7768bdce789e646dd16621fa8d539`, the PR #112 Backtracking-review merge.
The [review](documentation-review.md) records scope/fresh Java execution;
the [guide](implementation-and-benchmark-guide.md) retains original build,
smoke/probe and author-run provenance. No executable implementation changes.

## Public contracts, validation and ownership

| Source / API | Accepted domain / normal result | Policy |
|---|---|---|
| [FibonacciTabulation.value](../../src/main/java/org/anaalvarezdev/algorithms/dynamicprogramming/FibonacciTabulation.java) | int n=0..92; exact long F(n), F(0)=0,F(1)=1 | Invalid n throws IllegalArgumentException before table allocation; fresh private full table, scalar output |
| Reused [Fibonacci.memoised / iterative](../../src/main/java/org/anaalvarezdev/algorithms/recursion/Fibonacci.java) | Same 0..92 result domain | Private per-call memo or two scalar values; no copied implementation/global warmed cache; naive n<=30 is not a new benchmark method |
| [Knapsack01.valueMemoised / valueTabulated / valueRolling](../../src/main/java/org/anaalvarezdev/algorithms/dynamicprogramming/Knapsack01.java) | Equal-length int arrays, positive weights, nonnegative values; maximum long value of a subset with weight<=C | Original positions used at most once, duplicates are separate items, empty subset allowed, arrays preserved |
| Knapsack01.select | Same optimum plus total weight and increasing original indices | Full-table reconstruction; ties omit highest remaining index; immutable index list, not a minimum-weight/item-count promise |
| [LongestCommonSubsequence.lengthMemoised / lengthTabulated / lengthRolling](../../src/main/java/org/anaalvarezdev/algorithms/dynamicprogramming/LongestCommonSubsequence.java) | Non-null Strings, each <=512 UTF-16 units; maximum common-subsequence length, gaps allowed | No normalisation, code-point/grapheme conversion or substring semantics; empty side gives length zero |
| LongestCommonSubsequence.sequence | One maximum unit subsequence | Full-table trace; mismatch/equal-score ties discard from the first prefix; deterministic but not lexicographically minimum or symmetric witness |

All four knapsack variants share **n<=256, 0<=C<=10000 and
(n+1)(C+1)<=1000000**, including boundary row/column. Rolling still checks the
same grid cap despite allocating only C+1 cells. Example n=99,C=9999 gives
exactly one million cells; n=100,C=10000 is outside the shared domain. These
are comparison/work policies, not intrinsic memory requirements or a wider
mathematical domain promised by compression.

Knapsack validates weights then values for null before checking dimensions,
limits and elements. LCS validates both Strings for null before length caps.
Null throws NullPointerException; other invalid inputs throw
IllegalArgumentException before DP allocation/recursion. Empty inputs do not
bypass validation. No primitive overflow is used as a failure signal.

The maximum knapsack value is <=256*MAX_INT=549755813632, safely long.
Strict-improvement selection can only take an item whose positive weight fits
remaining capacity; remaining stays in [0,C] and returned weight=C-remaining
fits int. The dimension product is computed in long. Selection's public record
constructor freezes its list but does not validate arbitrary caller-created
value/weight/index semantics; those are guarantees of select's output.

Caches/tables are per-call and do not escape the value/length APIs. Knapsack
reads caller arrays without modifying them; fixed context during computation
is required, not a snapshot against concurrent mutation. Strings are immutable.
All bounds are finite educational/numeric domains, not heap/stack/time budgets.

## Sufficient state, sentinels and requests

K(i,c) means maximum value using only items [0,i) under capacity c. L(i,j)
means maximum length over the first i/j UTF-16 units of the fixed Strings.
Capacity alone or one LCS prefix is insufficient. Input context need not be
inside every key because it is fixed for this invocation and no cache is shared.

Knapsack/LCS cache answers are nonnegative, so -1 denotes unknown and zero is
a known optimum. Base states return directly; only nonbase expansions write
these memo cells. Let R be expanded nonbase states. Each emits at most two
helper requests, giving at most **1+2R calls**, including hits and bases.
Repeated requests are not repeated expansions; neither count measures JVM
frames or generated instructions. Memoisation still allocates and fills **every**
dense grid cell before the first helper call, even when R is tiny or zero.

Dependency measures are n for Fibonacci, i for knapsack, i+j for LCS. Every
recursive edge decreases its measure, and iterative orders use only already
exact dependencies. This proves algorithmic termination under the model;
cache syntax alone establishes neither the recurrence nor adequate resources.

## Fibonacci: exact values and reused work

For table[i], induction on i uses exact bases and two exact predecessors.
At n=0 the one-cell table already contains zero; at n>=1, table[1]=1, then
indices 2..n are filled. For n=5, [0,1,1,2,3,5]. Every computed value is
<=F(92)=7540113804746346429; no unused F(93) is computed. Invalid n fails first.

All three selected variants make max(n-1,0) recurrence additions. Reused memo
allocates/fills n+1 long entries and, unlike the K/L helpers, stores reached
base values too. For n>=2 it has 2n-1 helper requests including hits; n=0/1
has one. Full tabulation keeps n+1 entries during the call; rolling keeps two
values. Returning only a long does not mean the full-table method returns or
retains a reusable table between calls. Addition counts do not isolate call,
allocation or JIT costs, and primitive-word models do not extend to arbitrary-
precision Fibonacci without bit analysis.

## Knapsack: complete choices and storage versions

```text
K(0,c)=0
K(i,c)=K(i-1,c)                                      if w[i-1]>c
K(i,c)=max(K(i-1,c),v[i-1]+K(i-1,c-w[i-1]))            otherwise
```

Every feasible subset either omits the last item or takes it once. Taking
leaves only earlier items and reduced capacity; replacing a worse residual
with a better one improves the whole. Both branches construct feasible subsets
and cover all possibilities. Induction on i proves the recurrence. Positive
weights and the empty subset give zero at capacity zero; capacity need not be
filled exactly. A density-greedy rule is not justified by this recurrence.

Full tabulation fills row i from the completed row i-1 at capacities 0..C.
The invariant after a row is table[i][c]=K(i,c) for all c. One-row storage
instead iterates c downwards. Before updating c, cells below c still represent
K(i-1,*), while higher processed cells represent K(i,*). Because w[i-1]>0,
c-w[i-1]<c, so the take read uses the previous item prefix. An ascending loop
can read its own update and take one item multiple times: a single (2,3) item
at C=4 must give 3, not 6. This proof is specific to descending capacity; it
is not the rolling direction used by LCS.

For [10,20,30]/[60,100,120], C=50, row optima at C are 60,160,220.
The value 220 comes from indices [1,2], not the density-greedy first item.
Zero-value items are legal and may be omitted on ties; equal-valued/weighted
items still occupy distinct original positions.

### Selection: optimal residual and conceptual mask tie

During the backward trace, chosen suffix items plus K(i,remaining) equal the
original optimum; their weight is C-remaining. A strict increase over
K(i-1,remaining) forces a feasible take branch, so adding i-1 and reducing
remaining by its weight preserves the invariant. Otherwise omission preserves
the optimum. i decreases to zero; reversing collected descending indices gives
increasing original indices, and the list is frozen.

Define the **conceptual** numeric mask M(S)=sum(2^j,j in S). At a tie, any
subset omitting highest remaining index i-1 has a smaller M than every subset
including it, regardless of lower bits. If taking is forced, all optimal masks
contain that bit; apply the same argument to the residual prefix. Induction
therefore gives the smallest numeric mask among optima. No int/long mask is
stored or returned, and n<=256 is not reduced to 32/64 by this proof.

Weights [2,1], values [3,3], C=2 select index 0 with weight 2, although index 1
has smaller weight. The tie is not minimum weight, fewest items or the
lexicographically smallest index-list objective. A value-only rolling array
contains no reconstruction history for this policy.

## LCS: both directions of the prefix recurrence

```text
L(0,j)=L(i,0)=0
L(i,j)=1+L(i-1,j-1)                                  if a[i-1]==b[j-1]
L(i,j)=max(L(i-1,j),L(i,j-1))                         otherwise
```

When the trailing units equal x, appending x to a shorter-prefix optimum
constructs a common subsequence of length 1+L(i-1,j-1). For the upper bound,
an optimum cannot end in a different unit: then neither final x position is
used and x could be appended, contradicting maximality. Its final x can be
matched to both trailing positions. Removing it leaves a common subsequence
of the shorter prefixes, giving the matching upper bound.

When trailing units differ, an empty witness belongs to both predecessor
cases. A nonempty witness's final unit differs from at least one of them; it
can omit that trailing position. Thus some predecessor case
contains an optimum, and both predecessor solutions are valid for the larger
prefixes. The maximum gives the exact answer. Induction on i+j proves the
recurrence and handles empty prefixes. No mismatch reset is used: that would
solve contiguous substring instead. For ABC/AC, rows including boundary zero
are [0,0,0],[0,1,1],[0,1,1],[0,1,2], answer AC.

### Rolling row: old above, new left, saved diagonal

Only lengthRolling exchanges input roles to use shorter columns. At row i and
column j, saved diagonal is L(i-1,j-1), old row[j] is L(i-1,j), and already
updated row[j-1] is L(i,j-1). Save above before overwriting row[j], apply the
recurrence, then set diagonal=above. Boundary cell row[0] stays zero.
Induction over rows
and columns proves the final length with min(m,k)+1 cells. If one input is
empty, the inner loop is empty but the outer loop still runs over the longer
String; zero output length is not zero complete-operation work.

### Sequence: score-preserving trace and exact units

sequence retains the original first/second orientation and full table. At
(i,j), the number of still-unfilled output positions equals L(i,j). A match
writes the unit at --next and decrements both indices, decreasing the score
by one. On mismatch, following a maximum predecessor preserves the score;
equal predecessors choose i--. Every step decreases i+j. Reaching an empty
prefix gives score/next zero, so all allocated output cells are filled and
selected positions form a common subsequence in their original order.

ABCBDAB/BDCABA gives BCBA; AB/BA gives A, but BA/AB gives B. Length is symmetric;
this selected witness need not be. The char array is filled backwards by index
then passed directly to String, preserving the chosen UTF-16-unit order. No
normalisation, code-point/grapheme conversion or lexicographic tie objective
is hidden. Two supplementary characters sharing a high surrogate may have that
single surrogate as their maximum unit subsequence; this is explicitly tested.

## Full-cost ledgers and shared domain

Let G_K=(n+1)(C+1), G_L=(m+1)(k+1), with R reached nonbase states. Java matrices
are arrays of rows; row references/objects are included in the grid storage
order, not treated as one flat allocation. These are abstract word/state counts,
not measured physical zeroing, allocations, GC bytes or cache effects.

- Knapsack full table updates n*(C+1) positions; memo fills G_K cells before
  expanding at most n*(C+1) nonbase states. Rolling performs exactly
  U=sum(max(0,C-w[i]+1)) inner updates, plus validation/item traversal and row
  preparation. The TOO_HEAVY control has R=n, U=0, not zero public work.
- For unit weights and C>=n, memo reaches n(n+1)/2 nonbase states. At n=16,
  C=32/128 both have R=136, while G_K changes 561->2193. Full-table updates
  change 528->2064 and rolling updates 512->2048. Values/shape are fixed context;
  these counts do not measure elapsed shares.
- LCS full table/rolling visit m*k inner positions. IDENTICAL equal-length
  memo reaches n diagonal states; DISJOINT reaches all n^2 nonbase states.
  Both dense calls still fill (n+1)^2 memo cells. Empty-axis dense allocation
  also remains, unlike a base helper's constant return.

| Public operation | Full word-cost bound | Additional state / output |
|---|---|---|
| Fibonacci memo/table / rolling | O(n+1) | O(n+1) array/depth or O(1) scalar state |
| Knapsack memo / table | O(G_K+R) / O(G_K) | Full grid; memo adds O(n+1) logical depth |
| Knapsack rolling | O(n+C+1+U) | O(C+1), scalar result |
| Knapsack select | Full-table work plus O(n+1) trace | Full grid plus O(n) retained indices |
| LCS memo / table | O(G_L+R) / O(G_L) | Full grid; memo adds O(m+k+1) logical depth |
| LCS rolling | O(m*k+m+k+1) | O(min(m,k)+1), scalar result |
| LCS sequence | Full-table work plus O(m+k+1) trace and O(L+1) construction | Full grid plus O(L+1) returned UTF-16 data |

C is a numeric capacity, so the usual O(nC) bound is pseudopolynomial in
binary input size. The shared work cap does not change that classification.
Sentinels/recurrences, lookup edges, dense setup, transition work and returned
witnesses are different obligations. No sparse cache, arbitrary-precision
Fibonacci, unbounded/fractional knapsack or compressed witness algorithm is added.

## Native tests and author measurements

Fresh [verification](documentation-review.md) runs the actual Java classes:
**710 tests**, zero failures/errors/skips, including **22** module invocations.
Three source classes declare 5/9/8 @Test methods; inner cases are not separate
JUnit invocations. Reused Recursion Fibonacci is also exercised by the suite.

| Existing tests | Independent evidence |
|---|---|
| [FibonacciTabulationTest](../../src/test/java/org/anaalvarezdev/algorithms/dynamicprogramming/FibonacciTabulationTest.java), 5 | All 93 accepted values against BigInteger sequence, Cassini products, reuse agreement, invalid/boundary/interleaved calls |
| [Knapsack01Test](../../src/test/java/org/anaalvarezdev/algorithms/dynamicprogramming/Knapsack01Test.java), 9 | 7381 small item arrays at nine capacities (66429 cases), exhaustive subsets and numeric-mask tie oracle; 100 seeded arrays, density/reuse counterexamples, exact million-cell/item/long boundaries, identities and ownership |
| [LongestCommonSubsequenceTest](../../src/test/java/org/anaalvarezdev/algorithms/dynamicprogramming/LongestCommonSubsequenceTest.java), 8 | 3969 binary String pairs against subsequence enumeration; 100 seeded pairs, witness legality, UTF-16/surrogates/ties, 512-unit boundary and identities |

Fresh JMH discovery lists nine methods and **57 default cells**:

- [Fibonacci](../../src/jmh/java/org/anaalvarezdev/algorithms/dynamicprogramming/FibonacciDpBenchmark.java): n=20/40/90, memoised/table/rolling, nine cells in **ns/op**, including fresh private preparation.
- [Knapsack values](../../src/jmh/java/org/anaalvarezdev/algorithms/dynamicprogramming/KnapsackValueBenchmark.java): n=16/64, C=32/128, LIGHT/TOO_HEAVY, three methods, 24 cells in **us/op**; no Selection output.
- [LCS lengths](../../src/jmh/java/org/anaalvarezdev/algorithms/dynamicprogramming/LcsLengthBenchmark.java): n=32/128, IDENTICAL/DISJOINT/SHIFTED/RANDOM, three methods, 24 cells in **us/op**; no sequence reconstruction.

[Trial fixtures](../../src/jmh/java/org/anaalvarezdev/algorithms/dynamicprogramming/DynamicProgrammingInputs.java)
are generated outside timing. Seed 20261026 shuffles knapsack values and creates
ASCII RANDOM text; deterministic shapes are not multiple random population
samples. All use one thread, avgt, 3/5 x 500 ms warmup/measurement, two forks.
Discovery verifies registration, not a new timed/smoke/probe experiment.

The [historical report](../17-benchmarking/results/dynamic-programming/README.md)
retains all 57 means/errors, 570 measured and 342 warmup iteration averages,
source **author-attributed** `d572651484f3ec6cffe03ad589bddd5a4a8fb8a3` (PR #90),
JDK 21.0.9/JMH 1.37, 8 min 30 sec completion. Its eleven high-error cells,
literal `? 0` interval marker and per-row units remain intact. Complete value/
length means neither time witness output nor isolate dense setup/calls/CPU
causes, prove significance or fit an exponent. Current Corretto native tests
and historical Oracle measurements are separate evidence statements.
