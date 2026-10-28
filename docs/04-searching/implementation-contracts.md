# Searching: Current Contracts and Proofs

Reviewed on **2026-10-28 (America/Buenos_Aires)** from main
`af63005fa6ac23c68de9c20d062443a67821db47`. The
[dated review](documentation-review.md#ordered-documentation-review-follow-up-2026-10-28)
records sources, corrections and verification limits. This map describes the
eight existing public static search operations; it adds no Java API.

## Model and API correspondence

Let n=array.length and t be the int target. Assume non-null input and no
external mutation during a call. All methods leave the array unchanged; none
sorts, copies the input, inserts, checks sortedness or maintains an index.
Except LinearSearch, all require nondecreasing input under int comparison.
Unsorted calls to those methods are outside their result contract, even when
a particular result happens to be correct. Null input causes NPE: LinearSearch
dereferences array.length; the other seven explicitly call requireNonNull.
Do not infer one shared exception message or eager validation implementation.

Use the word model for fixed-width comparisons, arithmetic and valid indexed
access. Define B(n)=1+log2(n+1), so constant empty-input control work is explicit.
Result storage is additional storage, with workspace excluding output named
when useful. No allocation/resource guarantee is implied.

| Existing API | Normal result, including empty input | Work and additional storage | Existing tests |
|---|---|---|---|
| [LinearSearch.search](../../src/main/java/org/anaalvarezdev/algorithms/searching/LinearSearch.java) | min{i:A[i]=t}, or -1; empty -1 | Theta(1+c), c=r+1 if first match r, else n; constant workspace | [9 tests](../../src/test/java/org/anaalvarezdev/algorithms/searching/LinearSearchTest.java) |
| [BinarySearch.search](../../src/main/java/org/anaalvarezdev/algorithms/searching/BinarySearch.java) | Any valid matching index, or -1; empty -1 | Best Theta(1), worst Theta(B(n)); constant workspace | [13 tests](../../src/test/java/org/anaalvarezdev/algorithms/searching/BinarySearchTest.java) |
| [RecursiveBinarySearch.search](../../src/main/java/org/anaalvarezdev/algorithms/searching/RecursiveBinarySearch.java) | Same match/absence contract; current midpoint policy gives the same result as iterative search on identical stable input | Best Theta(1), worst Theta(B(n)) work and logical stack; no subarray copies | [13 tests](../../src/test/java/org/anaalvarezdev/algorithms/searching/RecursiveBinarySearchTest.java) |
| [FirstOccurrence.search](../../src/main/java/org/anaalvarezdev/algorithms/searching/FirstOccurrence.java) | Smallest matching index, or -1; empty -1 | Theta(B(n)) even on immediate equality, constant workspace | [10 tests](../../src/test/java/org/anaalvarezdev/algorithms/searching/FirstOccurrenceTest.java) |
| [LastOccurrence.search](../../src/main/java/org/anaalvarezdev/algorithms/searching/LastOccurrence.java) | Largest matching index, or -1; empty -1 | Theta(B(n)), constant workspace | [10 tests](../../src/test/java/org/anaalvarezdev/algorithms/searching/LastOccurrenceTest.java) |
| [SearchInsertPosition.search](../../src/main/java/org/anaalvarezdev/algorithms/searching/SearchInsertPosition.java) | Smallest i with A[i]>=t, or n; empty 0 | Theta(B(n)), constant workspace; returns a position without insertion | [10 tests](../../src/test/java/org/anaalvarezdev/algorithms/searching/SearchInsertPositionTest.java) |
| [SearchRange.search](../../src/main/java/org/anaalvarezdev/algorithms/searching/SearchRange.java) | Fresh {first,last} for equality, or {-1,-1}; empty {-1,-1} | Theta(B(n)), constant workspace and fixed two-int output; last search skipped if absent | [8 tests](../../src/test/java/org/anaalvarezdev/algorithms/searching/SearchRangeTest.java) |
| [ClosestValueSearch.search](../../src/main/java/org/anaalvarezdev/algorithms/searching/ClosestValueSearch.java) | Lexicographically minimise (absolute mathematical distance,index); empty -1 | Best Theta(1) on empty/minimum path, worst Theta(B(n)); constant workspace | [12 original tests](../../src/test/java/org/anaalvarezdev/algorithms/searching/ClosestValueSearchTest.java) and [regressions](../../src/test/java/org/anaalvarezdev/algorithms/searching/ClosestValueSearchRegressionTest.java) |

The source inventory has nine test classes plus one fixture helper: 89 @Test
methods and one @ParameterizedTest method with twelve named arguments. The
earlier hosted result is 101 Searching invocations (85 original +16 added by
Testing); this inspection is not another execution of them. The regression
source includes bounded enumeration, seeded full-int/duplicate inputs, safe
translation/scaling and fixture isolation. Their existing assertions are
distinct from this review's independent mathematical models.

## Intervals and finite index arithmetic

Ordinary iterative search maintains 0<=low<=high+1<=n. Its active interval is
inclusive [low,high]. Before a non-empty probe, every matching position is
still within it. Sortedness excludes all matches in the discarded side when
the midpoint differs from t. On equality any matching midpoint satisfies the
contract. On empty exit there is no remaining possible match. The integer
variant high-low+1 is non-negative and strictly decreases on every continuation.

For m>0 candidates and the lower midpoint, the left and right child sizes are
floor((m-1)/2) and floor(m/2). The larger child is floor(m/2). Recursive search
uses the same exclusion and an induction hypothesis on smaller intervals; a
base case without decreasing sizes would not prove termination.

The midpoint low+(high-low)/2 is safe because low and high are non-negative
valid endpoints while probing. Inclusive high<=n-1; half-open high<=n. Their
difference fits int, and the computed midpoint lies inside the active interval.
For the half-open guard low<high, middle<high<=n, so it is a valid element index.
Updates middle+1 are at most n; inclusive middle-1 can reach -1 and is then
handled by the guard. Neither uses an overflowing n+1 index calculation.

This argument does not cover arbitrary negative sentinels or arbitrary integer
domains. For low=2,147,483,645 and high=2,147,483,646, low+high overflows int,
while the intended midpoint is 2,147,483,645. These are arithmetic controls,
not evidence that a JVM can allocate a corresponding giant array.

## Boundary search invariants

For FirstOccurrence, maintain a valid recorded match result or -1, together
with the active inclusive interval. If any match exists, the globally smallest
match is the minimum of the recorded candidate (when present) and all active
matching indices. Initially all matches are active. On equality at m, record m
and set high=m-1: discarded matches cannot improve on m. On inequality,
sortedness removes only non-matches. A recorded candidate remains above high.
The active-size variant decreases. At empty exit the recorded candidate is
the global first match, or no match exists and result remains -1.

LastOccurrence is symmetric: use the maximum of recorded/active matching
indices, record m and set low=m+1 on equality. A recorded result remains below
low. Empty exit establishes the last match. Merely saying “a recorded match
remains valid” would not establish either extremal-index guarantee.

SearchInsertPosition uses [low,high) and a stronger partition invariant:

- 0<=low<=high<=n;
- every index in [0,low) has A[i]<t;
- every index in [high,n) has A[i]>=t.

Initially both excluded partitions are empty. If A[m]<t, sortedness lets low
advance to m+1. Otherwise high becomes m. Both partitions are preserved and
high-low decreases. At low=high, those partitions prove the exact lower bound,
including sentinel n when every value is smaller. There is no element at n.
Equality itself is not a globally single-transition predicate on a sorted
array; A[i]>=t is the predicate used here.

For a general [a,b] value interval with a<=b, an extension could compute lower
bound a and upper bound b (first value strictly greater than b). Their half-open
index range gives the count and enumeration. Avoid int lowerBound(b+1): at
MAX_VALUE that addition wraps. The current SearchRange concerns equality and
returns inclusive endpoints. Count is zero for {-1,-1}, otherwise last-first+1;
the representable index/length bounds make this count fit int. Enumerating k
values adds Theta(k) work/output when materialised; endpoints alone do not.

## Nearest value composition

Empty input returns -1. At/below A[0], index zero is closest and is the smallest
index of that minimum. Otherwise u=lowerBound(t) is positive. If u<n, sortedness
makes A[u-1] the largest lower value and A[u] the smallest value at least t;
no other value can have a smaller distance. The upper candidate is already its
value's first copy. If the lower value wins or ties, its first copy can precede
u-1, so a second lower bound finds that copy. If u=n, a lower bound on A[n-1]
finds the first maximum. These cases prove both distance and lowest index.

Widen an operand before subtracting: Math.abs((long)value-t). The int-input
domain bounds the true difference by plus/minus (2^32-1), so the long absolute
value is exact and never encounters Long.MIN_VALUE. Widening after int
subtraction/abs cannot repair lost bits. With MIN_VALUE and MAX_VALUE, the
true gap is 4,294,967,295; wrapped int subtraction can instead have magnitude 1.
This range argument supports the existing code and does not extend to arbitrary
long inputs. The code fix and its hosted regression evidence remain S01/module 16.

## Operation counts and probability models

LinearSearch has zero equality comparisons on empty input, r+1 on first match r
and n when absent. For n>=1 and a unique uniformly located successful target,
the expectation is (n+1)/2. With success probability p under that conditional
model it is p*(n+1)/2+(1-p)*n. Under arbitrary first-match probabilities p_i and
absence probability p_abs, use sum p_i*(i+1)+p_abs*n. A fixed first match and an
all-equal exact-match array give one comparison, not a uniform-position sample.

Ordinary inclusive binary search has at most floor(log2(n))+1 midpoint probes
for n>0, attained along the last-target/rightmost path of distinct values.
It has zero probes at n=0. For p probes the current core methods execute 2p-1
value comparisons on success and 2p when absent, counting equality/order tests
and excluding guards. An absent recursive search adds a final empty helper
call, so helper depth is p+1; successful helper depth is p. The public wrapper
adds another frame. Logical depth is not measured JVM stack occupancy.

For representable array lengths n<=MAX_VALUE, these core searches have at most
31 non-empty probes. This is a bound on the stated int-index model, not an
allocation experiment or a stack-availability guarantee. Asymptotic statements
use the word-model family as sizes grow, rather than an unbounded physical JVM.

First/last/lower-bound variants do not exit on equality. Their next interval
sizes are floor((m-1)/2) or floor(m/2), so a path can neither terminate before
the shorter-child depth nor exceed the longer-child depth. For n>0 their probe
counts lie between floor(log2(n+1)) and floor(log2(n))+1, giving Theta(B(n))
including control work. SearchRange composes at most two such searches; closest
value composes at most two lower bounds after its constant fast paths.

For ordinary binary search with distinct values and uniformly chosen successful
positions, expected probes are Theta(log n). At most 2^h-1 positions can occur
in its first h probe levels. Taking h=floor(log2(n))-1 for n>=4 leaves at least
half the positions deeper than h, giving the logarithmic lower bound; the
worst-case bound supplies the upper bound. Fixed midpoint queries or duplicate
values need a different probability model.

## Library and measured-evidence boundary

The [Java SE 21 Arrays API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Arrays.html)
returns -(insertionPoint)-1 on binary-search absence, whereas this repository's
exact searches return -1. For [2,4,6]/5 the library returns -3; lower-bound
position is 2. Any matching duplicate from the library is not necessarily a
first occurrence. Replacing these calls requires adapting their result contract.
Numeric promotion/overflow and absolute-value caveats are checked against the
[JLS](https://docs.oracle.com/javase/specs/jls/se21/html/jls-15.html#jls-15.18.2)
and [Math API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Math.html#abs(long)).

Current JMH source comprises
[LinearSearchBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/searching/LinearSearchBenchmark.java),
[BinarySearchBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/searching/BinarySearchBenchmark.java)
and [BinarySearchVariantsBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/searching/BinarySearchVariantsBenchmark.java):
three classes/four methods and 65 default configurations. These comprise
linear's four scenarios, binary's three and the variants' two methods/three
scenarios, each over five sizes. Trial setup builds ascending distinct values;
timing includes search/result consumption and excludes construction/sorting.
Linear MIDDLE uses n/2, binary MIDDLE uses (n-1)/2; all default sizes are even.
Binary MIDDLE is an initial-midpoint match, not average successful search.
ABSENT is always -1 below the minimum. Trials reuse fixed input and target.

Those bodies do not time boundary variants, closest-value selection, changing
targets, duplicate-heavy inputs, sorting break-even or stack memory. No timing
for those tasks follows from shared logarithmic reasoning. The
[historical 65-row record](../17-benchmarking/results/searching/README.md)
retains every reported mean/error and missing header/source/environment limits.
Current annotations do not establish its effective settings or artifact hash.
