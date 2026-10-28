# Arrays: Current Implementation Contracts

Reviewed from main **ee2383bbe83dd9539171454eeb182f81c150ff83** on
**2026-10-28 (America/Buenos_Aires)**. The [review](documentation-review.md)
records sources, findings and verification limits. This map describes the
thirteen existing Arrays classes and fourteen static operations. It does not
add an algorithm or replace the historical research records.

## Model and evidence conventions

Let A0 be the mathematical input contents at method entry; no copy allocation
is implied by that notation. Let n=array.length, c be capacity, s the caller's
active length and i the requested logical index. Assume no concurrent writes
unless callback effects are being discussed. Costs use fixed-word primitive
access/comparison/arithmetic; allocations are assumed to succeed. Asymptotic
Θ(n) statements concern growth, with constant control work for empty inputs.
New result storage counts as auxiliary storage; workspace excluding output is
named separately. A non-negative allocation length alone does not ensure that
memory is available.

The tests column links existing source assertions, not a new test run. The
Arrays test sources contain 103 @Test methods in thirteen files. Current JMH
source has eleven classes and fifteen benchmark methods. Benchmark existence
alone is not correctness evidence or proof of a valid historical run.

## API map

All supplied array references must be non-null for the normal-result contracts.
The rejection column records the current method's behaviour; a documented
precondition does not imply explicit runtime validation.

| Class / static operation | Result and mutation | Empty / failure behaviour | Time and additional storage | Existing tests |
|---|---|---|---|---|
| [AccessArrays.get](../../src/main/java/org/anaalvarezdev/algorithms/arrays/AccessArrays.java) | Returns A0[i]; no write | Need 0<=i<n; null causes NPE, invalid index AIOOBE | Θ(1) work / Θ(1) | [6 tests](../../src/test/java/org/anaalvarezdev/algorithms/arrays/AccessArraysTest.java) |
| [ArrayUpdate.update](../../src/main/java/org/anaalvarezdev/algorithms/arrays/ArrayUpdate.java) | Sets array[i]=value, preserves every other slot | Same normal index domain and failures as get | Θ(1) / Θ(1) | [5 tests](../../src/test/java/org/anaalvarezdev/algorithms/arrays/ArrayUpdateTest.java) |
| [ArrayTraversal.traverse](../../src/main/java/org/anaalvarezdev/algorithms/arrays/ArrayTraversal.java) | On normal return, calls action once per index with the then-current value; own loop does not write | Empty makes no calls; null array causes NPE. Null action fails when invoked, but is not eagerly checked; action failures propagate | Θ(1+n) loop work plus action work; Θ(1) own workspace, plus action/output storage | [4 tests](../../src/test/java/org/anaalvarezdev/algorithms/arrays/ArrayTraversalTest.java) |
| [ArrayAggregation.sum](../../src/main/java/org/anaalvarezdev/algorithms/arrays/ArrayAggregation.java) | Returns exact mathematical sum when final sum fits int; no input write | Empty returns 0; null NPE. Final out-of-range sums wrap and are outside the mathematical-result contract, without rejection | Θ(n) / Θ(1) | [6 tests](../../src/test/java/org/anaalvarezdev/algorithms/arrays/ArrayAggregationTest.java) |
| [FindMinimum.find](../../src/main/java/org/anaalvarezdev/algorithms/arrays/FindMinimum.java) | Returns a member of A0 no greater than any A0 value; no input write | n>=1; empty AIOOBE, null NPE | n-1 value comparisons, Θ(n) / Θ(1) | [9 tests](../../src/test/java/org/anaalvarezdev/algorithms/arrays/FindMinimumTest.java) |
| [FindMaximum.find](../../src/main/java/org/anaalvarezdev/algorithms/arrays/FindMaximum.java) | Returns a member of A0 no smaller than any A0 value; no input write | n>=1; empty AIOOBE, null NPE | n-1 value comparisons, Θ(n) / Θ(1) | [9 tests](../../src/test/java/org/anaalvarezdev/algorithms/arrays/FindMaximumTest.java) |
| [FindMinimumAndMaximum.find](../../src/main/java/org/anaalvarezdev/algorithms/arrays/FindMinimumAndMaximum.java) | Returns fixed-size MinMax with both membership/bound guarantees; no input write | n>=1; empty AIOOBE, null NPE | Θ(n) / Θ(1), including fixed-size result; parity-specific comparisons below | [10 tests](../../src/test/java/org/anaalvarezdev/algorithms/arrays/FindMinimumAndMaximumTest.java) |
| [ArrayReverse.reverse](../../src/main/java/org/anaalvarezdev/algorithms/arrays/ArrayReverse.java) | Same object, array[j]=A0[n-1-j] at every index | Empty/singleton accepted; null NPE | Exactly floor(n/2) swaps; Θ(n) / Θ(1) | [8 tests](../../src/test/java/org/anaalvarezdev/algorithms/arrays/ArrayReverseTest.java) |
| [ArrayCopy.copy](../../src/main/java/org/anaalvarezdev/algorithms/arrays/ArrayCopy.java) | Fresh int[] of same length/values; original unchanged, no mutable element references to share | Empty returns a distinct empty array; null NPE | Θ(n) work / Θ(n) new result storage; Θ(1) workspace excluding output | [8 tests](../../src/test/java/org/anaalvarezdev/algorithms/arrays/ArrayCopyTest.java) |
| [InsertAtIndex.insert](../../src/main/java/org/anaalvarezdev/algorithms/arrays/InsertAtIndex.java) | Inserts at i in active prefix; returns s+1; same capacity | Need 0<=s<c and 0<=i<=s; logical empty allowed only with capacity. Null NPE, invalid/full size IAE, invalid index IOOBE, checked in that order | Exactly s-i shifts plus insertion write; Θ(1+s-i) / Θ(1) | [9 tests](../../src/test/java/org/anaalvarezdev/algorithms/arrays/InsertAtIndexTest.java) |
| [DeleteAtIndex.delete](../../src/main/java/org/anaalvarezdev/algorithms/arrays/DeleteAtIndex.java) | Removes index i from active prefix, returns s-1, clears old slot s-1 to 0 | Need 0<s<=c and 0<=i<s; logical empty rejected. Null NPE, invalid size IAE, invalid index IOOBE, in that order | Exactly s-i-1 shifts plus clearing write; Θ(s-i) / Θ(1) | [10 tests](../../src/test/java/org/anaalvarezdev/algorithms/arrays/DeleteAtIndexTest.java) |
| [RemoveDuplicatesSorted.remove](../../src/main/java/org/anaalvarezdev/algorithms/arrays/RemoveDuplicatesSorted.java) | Compacts distinct original values into sorted prefix [0:k), returns k; physical tail unspecified | Empty returns 0; null NPE; sortedness is assumed, not validated | n-1 value comparisons for n>0; k-1 writes; Θ(n) / Θ(1) | [8 tests](../../src/test/java/org/anaalvarezdev/algorithms/arrays/RemoveDuplicatesSortedTest.java) |
| [PrefixSum.build](../../src/main/java/org/anaalvarezdev/algorithms/arrays/PrefixSum.java) | Fresh long[n+1], prefix[0]=0, prefix[j]=sum(A0[0:j]); original unchanged | Empty returns [0]; null NPE; n+1 must be representable/allocatable | Θ(n) / Θ(n) result storage, Θ(1) workspace excluding output | [11 tests across both operations](../../src/test/java/org/anaalvarezdev/algorithms/arrays/PrefixSumTest.java) |
| [PrefixSum.rangeSum](../../src/main/java/org/anaalvarezdev/algorithms/arrays/PrefixSum.java) | Returns prefix[right+1]-prefix[left]; no writes | Require intact build representation and 0<=left<=right<n. Null NPE, invalid range IOOBE. No provenance/content check | Θ(1) / Θ(1) | Same PrefixSum test file; no extra eleven tests |

NPE = NullPointerException; AIOOBE = ArrayIndexOutOfBoundsException;
IAE = IllegalArgumentException; IOOBE = IndexOutOfBoundsException. Implicit
exceptions in the simple methods must not be described as explicit validation.
The normal contracts above do not require every invalid-input combination to
have been tested. Allocation/resource failures are separate from input-domain
result guarantees.

## Logical insertion and deletion proofs

For insertion, the postcondition is:

- array[0:i]=A0[0:i], array[i]=value;
- array[i+1:s+1]=A0[i:s];
- slots [s+1:c) remain as at entry, and the returned active size is s+1.

At right-shift loop header current=j, the destination suffix
array[j+1:s+1] equals A0[j:s], and the unread prefix array[0:j] is unchanged.
Copying array[j-1] to array[j] then decrementing j preserves the property.
The non-negative integer j-i decreases to zero. Writing value at i establishes
the result. Forward shifting would overwrite values still needed as sources.
The existing spare slot is not required to contain zero.

For deletion, processed array[i:current] equals
A0[i+1:current+1], and the unread suffix after current remains unchanged.
Copying from current+1 while advancing current preserves that property; the
variant s-1-current decreases to zero. Clearing s-1 finishes the contract.
Slots before i and from s onward remain unchanged. This removes a logical
value without reducing physical length. Caller code must use the returned size;
reusing the original s models another operation on a different logical state.

## Callback effects and normal completion

ArrayTraversal's own index progresses from zero to n. If every action returns
normally, it makes exactly n calls and terminates. Action exceptions stop the
remaining visits; non-termination inside an action prevents normal completion.
Callbacks can mutate captured arrays or retain results. For [1,2], changing
array[1] to 99 during the first call changes the next argument to 99.
Therefore neither purity nor Θ(1) total extra space follows from the loop alone.
The source Javadoc is clarified without changing executable tokens.

The current precondition asks for a non-null action, but the body does not
validate it eagerly: empty input with null action currently returns normally.
That observation is not a new promise to accept null actions or a newly tested
rejection policy. Existing traversal tests cover normal order, singleton,
empty-with-valid-action and null array; they do not cover every callback effect.

## Aggregation and numeric range

For each int addition, signed output represents the mathematical sum modulo
2^32. Inductively, after processing i elements, the accumulator has the low
32 bits of sum(A0[0:i]). If the final sum fits int, those bits decode to that
exact signed value. Thus [Integer.MAX_VALUE,1,-1] and
[Integer.MIN_VALUE,-1,1] satisfy the final-result domain even though an
intermediate prefix wraps. The invariant “each intermediate int equals the
unbounded mathematical prefix sum” would be false for them.

A final mathematical sum outside int range is excluded by the existing API
contract; no exception is promised. A wider-sum API would be a separate change.
These observations do not rehabilitate the two historical large ascending-input
aggregation means: their final sums are out of range. The current bounded JMH
fixture i%100 and the separately reported corrected run remain distinct evidence.

## Pairwise min/max comparisons

After the initial one-element (odd n) or two-element (even n) prefix, minimum
and maximum bound, and belong to, all processed original values. Each following
pair uses one comparison to order its members, then one comparison of the
smaller with minimum and one of the larger with maximum. Updating only from
input members preserves membership and bounds. Index advances by two until all
values are included, with constant result storage and no array writes.

Counting value comparisons only, excluding loop/parity/control comparisons:

- odd n: 3(n-1)/2, including zero for n=1;
- even n: 1+3(n-2)/2 = 3n/2-2;
- independent full scans: 2n-2.

These are analytical counts, not an assertion of a measured speedup. Both
strategies remain Θ(n). The historical min/max timing comparison is retained
with its original limitations.

## Sorted compaction proof and physical tail

For n>0, initialise write=1 and read=1. At each read header, 1<=write<=read<=n;
array[0:write] is exactly the distinct values of A0[0:read] in sorted order,
and the unread suffix array[read:n] is still original. Comparing the next value
with the last written value either finds another copy or appends a new distinct
value. Since write<=read, writing cannot destroy a future unread source.
Advancing read maintains the invariant; n-read is a decreasing non-negative
variant. At exit the returned write counts all unique values. Empty input
returns zero before initialisation. There are n-1 comparisons and k-1 writes
for non-empty input, including self-writes when all values are unique.

Only [0:k) is the result. For [1,1,1,2,2,3], the current method leaves
[1,2,3,2,2,3] and returns 3. The whole array is no longer sorted, so blindly
calling remove again on the full physical array violates its precondition.
No zeroing or uniqueness of the tail is guaranteed. Sorting itself is not
performed or validated by this compaction API.

## PrefixSum construction and queries

At the build loop's header index=i, prefix[0]=0 and, for all 0<=j<=i,
prefix[j]=sum(A0[0:j]); the input remains unchanged. The update at i+1 extends
that invariant. The variant n-i decreases and at exit it establishes the
whole length-n+1 representation, including [0] when n=0.

For an int[] of any representable length, every contiguous range sum's magnitude
is at most n*2^31 < 2^62, hence fits long. For build to allocate n+1 slots,
also require n<Integer.MAX_VALUE and sufficient available storage. The code
does not turn failed allocation into an input-validation exception policy.
The representational bound is an analytical guarantee, not a claim that a JVM
can allocate every array up to that size.

For valid inclusive [left,right], cancellation of the mathematical prefixes
gives sum(A0[left:right+1])=prefix[right+1]-prefix[left]. No special left=0
case is needed and the result fits long for a valid build representation.
There is no valid non-empty inclusive query for an empty original input.
The method checks null and bounds, not prefix[0], cumulative meaning or origin.
A forged/mutated long[] can therefore give arbitrary/wrapping differences.

The prefix remains a snapshot of the original input even if that input is
later changed. Updating it for mutable data requires rebuilding or another
representation; this API does not maintain a live view. Build costs Θ(n) time
and output storage. Afterward each valid query uses Θ(1) work. For range lengths
k_j, direct queries cost Θ(q+Σ k_j), versus Θ(n+q) including a build; Θ(qn)
is the full-length-range special case, not every query workload.

## Teaching scope and empirical boundary

Standalone average, count, sortedness, second-largest, rotation, two-array
merge and duplicate detection are educational examples in this module. They
do not acquire production classes/tests by appearing in a note. Canonical
linear/binary search APIs and their tests live under Searching. This contract
map does not silently substitute sorted compaction for duplicate detection.

Historical [105 Arrays means](../17-benchmarking/results/arrays/README.md) and
[five corrected aggregation observations](../17-benchmarking/results/arrays/aggregation-corrected-run.md)
remain separate. Current JMH bodies confirm allocation in copy/build payloads,
callback consumption in traversal, trial-state reuse in reverse/insert/delete,
invocation restoration in compaction and a fixed full range in prefix queries.
Their current defaults do not establish the unknown effective settings of old
executions. No profiler attribution, new comparison or recovered old jar hash
is supplied by this documentation pass.
