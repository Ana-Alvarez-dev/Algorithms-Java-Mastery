# Sorting: Current Contracts and Proof Map

Reviewed on **2026-10-28 (America/Buenos_Aires)** from main
`8d8a5beec76c97583d721c36ab3903aa1fd267b7`. The
[review report](documentation-review.md) identifies sources, scoped findings
and verification limits. This map describes five existing int[] methods, not
new object/comparator, descending, subrange or external-sorting APIs.

## Common observable contract

Each public sort(int[]) returns void and orders the supplied array ascending.
For entry contents A0 and result A, length is unchanged, A[j]<=A[j+1] for every
valid adjacent pair, and count(A,v)=count(A0,v) for every int value v. Set
membership alone is insufficient: [1,1,2] and [1,2,2] have different counts.
The methods explicitly reject null with NullPointerException and message
"values must not be null". Empty/singleton inputs complete with constant work.

Normal-result reasoning assumes no external mutation during a call and enough
resources for required allocation/calls. A0/B below are mathematical snapshots,
not assertions that the method allocates copies. Use the fixed-word model for
int comparison, index arithmetic and valid indexed access. All comparisons use
relational operators rather than subtracting keys. Counts/formulas below use
mathematical integers; computing them in Java requires widening before products. Frames and result buffers
count as auxiliary space; input mutation does not imply constant total space.

| Existing implementation | Actual variant and work | Additional storage | Existing evidence |
|---|---|---|---|
| [SelectionSort](../../src/main/java/org/anaalvarezdev/algorithms/sorting/SelectionSort.java) | Suffix minimum, skip self-exchange; exactly n(n-1)/2 key comparisons, at most max(0,n-1) swaps; Theta(1+n²) all cases | Theta(1) | [SelectionSortTest](../../src/test/java/org/anaalvarezdev/algorithms/sorting/SelectionSortTest.java) inherits the contract |
| [BubbleSort](../../src/main/java/org/anaalvarezdev/algorithms/sorting/BubbleSort.java) | Left-to-right adjacent strict exchanges, shrinking end, stop on no exchange; best Theta(1+n), worst Theta(1+n²); exactly I swaps | Theta(1) | [BubbleSortTest](../../src/test/java/org/anaalvarezdev/algorithms/sorting/BubbleSortTest.java) inherits the contract |
| [InsertionSort](../../src/main/java/org/anaalvarezdev/algorithms/sorting/InsertionSort.java) | Save key, shift strictly greater values, fill hole; exactly I shifts and max(0,n-1) final key writes; Theta(1+n+I) | Theta(1) | [InsertionSortTest](../../src/test/java/org/anaalvarezdev/algorithms/sorting/InsertionSortTest.java) inherits the contract |
| [MergeSort](../../src/main/java/org/anaalvarezdev/algorithms/sorting/MergeSort.java) | Top-down inclusive halves, one reusable array, copy active interval before merging, left wins equality, no merge skip; Theta(n log n) all cases for n>=2 | Theta(n) buffer plus O(log n) logical stack; constant at n<2 | [MergeSortTest](../../src/test/java/org/anaalvarezdev/algorithms/sorting/MergeSortTest.java) adds one large odd-length test |
| [QuickSort](../../src/main/java/org/anaalvarezdev/algorithms/sorting/QuickSort.java) | Deterministic middle-value Hoare partition, recurse smaller side and loop larger; balanced Theta(n log n), worst Theta(n²), constant at n<2 | O(1+log(n+1)) worst logical stack, constant partition state; no linear buffer | [QuickSortTest](../../src/test/java/org/anaalvarezdev/algorithms/sorting/QuickSortTest.java) adds three large-input methods |

I is the number of strict inversions i<j with A0[i]>A0[j]. Expected statements
for Bubble/Insertion use uniformly random permutations of distinct keys:
E[I]=n(n-1)/4, since each pair has probability 1/2 of inversion. The expected
shift/swap lower bound and quadratic upper bound give Theta(n²). Selection's
comparison count needs no probability model; Merge's current always-merge
cost is likewise independent of input order. Quick's documented expected
Theta(n log n) assumes uniformly random distinct-key permutations, not a random
pivot implementation or every duplicate distribution. This pass does not
derive a full distributional proof for that exact Hoare implementation.

## Selection and Bubble loop reasoning

Selection's outer prefix contains the i smallest original occurrences in
nondecreasing order, and every prefix value is at most every remaining suffix
value. The entire array remains a permutation. In the scan, minimumIndex is
the first minimum of the already examined suffix portion; strict comparison
extends it. Swapping that minimum into i preserves counts and extends the
ordered prefix. Remaining scan/outer lengths decrease to termination. Handle
n<2 separately rather than interpreting n-1 as a negative pass count.

Sorted input has no Selection swaps. [2,3,...,n,1] attains n-1; reverse distinct
input uses floor(n/2). All have the same n(n-1)/2 comparisons. Counting swap
assignments is another measure: a three-assignment swap has two array writes
and one temporary assignment, not three array writes.

Bubble's suffix beyond end is already final, sorted and no smaller than the
active prefix. At inner header index=j, A[j] is a maximum of the portion
processed in that pass. One compare/exchange extends that maximum to j+1;
the pass therefore places an active maximum at end. A pass without swaps has
checked an unchanged, ordered active prefix, which joins the completed suffix.
Both loops have bounded indices; exchanges preserve the permutation.

Each strict adjacent swap removes exactly one inversion and a sorted result
has none, giving exactly I swaps. Sorted input has max(0,n-1) comparisons.
For [2,3,...,n,1], I=n-1 but the final 1 moves left one position per pass:
there are n-1 passes and n(n-1)/2 comparisons. Bubble work is therefore not
generally Theta(n+I), despite its exact inversion/swap relationship.

## Insertion hole invariant

At outer index i>=1, [0:i) is a sorted permutation of its original prefix and
the later unprocessed suffix is unchanged. Save key=B[i], where B is the
state at this outer iteration. At inner header position=p, the logical hole
is p+1. The unchanged prefix A[0:p+1)=B[0:p+1), and the shifted destination
A[p+2:i+1)=B[p+1:i) contains only values greater than key. Excluding the hole,
those regions plus the saved key have exactly the multiset B[0:i+1).

Initially p=i-1 and the shifted region is empty. If p>=0 and A[p]>key, copying
to p+1 and decrementing p preserves these properties. p+1 is non-negative and
decreases. At exit p<0 or A[p]<=key; filling the hole establishes the next
ordered prefix and restores its full multiset. The guard checks p>=0 before
accessing A[p]. Each shift crosses one larger preceding value, hence total
shifts equal I and total work is Theta(1+n+I).

The old region starting at p+1 incorrectly included the hole/key at the first
header. Temporary duplicates during shifting are not a permutation violation
when the saved key and excluded hole are accounted for correctly.

## Shared-workspace merge

For n>=2, the public method allocates one int[n]. Recursion splits size m into
ceil(m/2) and floor(m/2), strictly smaller until singleton intervals. After
both subcalls sort their intervals, copying [left:right+1) to auxiliary freezes
two ordered input runs for this merge. The auxiliary buffer is reused; recursion
does not allocate fresh half-arrays or use an int infinity sentinel.

At destination d, the written prefix is the ordered merge of exactly the
consumed auxiliary prefixes. The remaining heads are their smallest unconsumed
values, and d-left=(first-left)+(second-middle-1). Selecting the smaller head,
or the non-exhausted side, extends the invariant. Choosing left on equality
preserves tie order. Advancing one head per write decreases remaining output;
at exit every occurrence is present exactly once. Induction on interval size
therefore establishes ordering, permutation and the stable movement rule.

For n>=1 and h=ceil(log2 n), the total active-interval copy count is
M(n)=n*h-(2^h-n); M(0)=0. The recurrence is M(m)=M(ceil(m/2))+M(floor(m/2))+m
for m>=2, with M(1)=0. Destination writes have the same total. Leaf depths
h-1/h give the same sum; at powers of two it is n*log2 n. A single merge of
two non-empty runs of total m uses at most m-1 key comparisons. Boundary
tests, copies, result writes and buffer zero-initialisation are separate work.
Peak auxiliary storage is linear; cumulative movement is Theta(n log n).

The current implementation always merges. A different version that checks
ordered halves and skips each merge can have Theta(n) best work on sorted
input while retaining O(n log n) worst work. It is not the current API body.

## Hoare partition and bounded stack

For left<right, save pivot=A[left+(right-left)/2] as a value. Scans start just
outside the interval and advance before reading. After scanning, the passed
left prefix is <=pivot and the passed right suffix >=pivot. If the indices
have not crossed, swapping the stopped values establishes barriers for the
next scans; both indices advance even on equality. Initially the pivot's
presence stops the scans; after exchanges those barriers keep reads in bounds.
The gap between scan indices strictly decreases until they cross.

The returned boundary b satisfies left<=b<right and separates values <=pivot
in [left,b] from values >=pivot in [b+1,right]. On an immediate return b cannot
be right: the initial lower midpoint is strictly before right and stops the
left scan. After a swap the right scan decreases, also ensuring b<right.
The left-side barrier prevents b<left. Both children are non-empty and smaller;
their sizes are k and m-k, not Lomuto's k and m-k-1. Sorting both regions gives
an ordered permutation. b is not a final pivot index, and Hoare's scan count
is not Lomuto's exact m-1 pivot comparisons.

All arithmetic uses valid non-negative endpoints while partitioning:
right<=n-1, left-1>=-1 and right+1<=n<=MAX_VALUE. The midpoint difference fits
int and its result lies inside the interval. Sentinels are advanced before
array access; a conceptual -1/n is never an element index. This does not
prove safety for arbitrary int-domain endpoints or concurrent mutation.

Only the smaller child is recursively processed. Its size is at most floor(m/2),
so each additional active helper frame at least halves the problem size.
With base calls included, depth is at most floor(log2 n)+1 for n>=1, plus the
public wrapper; empty input has a constant call depth. The larger child runs
in the existing frame. This is an upper bound, not Theta(log n) on every
input, a measured stack-byte count or a guarantee of available JVM stack.

## Quadratic time with constant depth on a family

For n>=2, begin with [1,...,n]. For left=n-2 down to zero, swap positions left
and left+floor((n-1-left)/2). This reverses the future minimum-pivot exchanges.
In the resulting array, every active suffix has its minimum at the chosen
middle. A partition puts that minimum at its left boundary, leaving sizes
1 and m-1; the right suffix has the same recursively constructed property.
For example n=5 gives [2,4,1,3,5].

For m>=3, the first scans stop at left/middle, swap, then the next right scan
reaches the minimum at left. This takes m+2 pivot comparisons. Size two uses
three. Summing over m=n..2 gives n(n+1)/2+2n-4 comparisons, proving quadratic
work on this family. There are n-2 swaps and n-1 partitions. Each recursive
child has size one, so maximum helper depth is two; the larger suffix is
handled by the loop. Small stack does not establish a worst-time guarantee.
This is a mathematical construction checked by independent bounded models,
not a new benchmark dataset or a hardware/performance result.

## Stability, repeatability and evidence

Bubble's strict adjacent swaps, Insertion's strict shifts and Merge's left-first
ties preserve relative equal-key order in tagged movement models. Selection
on [(2,a),(2,b),(1,c)] yields [(1,c),(2,b),(2,a)]; current-style Hoare exchanges
[(1,a),(1,b)] into [(1,b),(1,a)]. Those show instability mechanisms without
inventing an object/comparator production API. Equal int identities are not
observable in the actual primitive tests.

A deterministic unstable algorithm can repeat its result on the same input.
Conversely, stable sorting only by key preserves different arrival orders of
equal-key records; it is not a canonical record-order guarantee across inputs.
A unique key policy for repeatable pagination is a separate requirement.

The shared [SortingContractTest](../../src/test/java/org/anaalvarezdev/algorithms/sorting/SortingContractTest.java)
has thirteen inherited methods for each of five concrete classes, plus one
Merge and three Quick methods: 69 historical Sorting invocations, not 69 newly
executed methods in this pass. It covers 1093 small arrays per algorithm and
110 seeded cases per algorithm, with reference comparisons, duplicates, bounds,
extremes, idempotence and null policy. Idempotence or isSorted alone would not
prove the full contract. The [guide](implementation-and-benchmark-guide.md)
dates earlier Java 21 verification; this review does not rerun it.

## Benchmark and extension boundary

[ElementarySortingBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/sorting/ElementarySortingBenchmark.java)
has four methods, three sizes and five distributions (60 cells).
[EfficientSortingBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/sorting/EfficientSortingBenchmark.java)
has four methods, five sizes and five distributions (100 cells).
[SortingBenchmarkInputs](../../src/jmh/java/org/anaalvarezdev/algorithms/sorting/SortingBenchmarkInputs.java)
is a fixture helper, not a third benchmark class. Clone/allocation is timed;
generation is excluded. Merge's internal buffer is also timed. Controls are
separate measurements and not subtractable isolated-sort estimates.

RANDOM draws full-int values and can repeat keys. NEARLY_SORTED makes a bounded
number of adjacent exchanges that may overlap/cancel; each changes inversion
count by one, so it is not an exact number of remaining inversions. One fixed
seed per pair does not sample the uniform distinct-key expectation. No current
timing measures object stability, a stack bound or this adversarial family.

The [48-cell historical subset](../17-benchmarking/results/sorting/README.md)
retains its means/errors, 480 iteration-average findings, JDK 25.0.1 runtime and
missing exact execution-commit/command/CPU limits. Its runtime library reference
is not a Java 21 measurement. This review adds no score, export or artifact hash.
Generic/object sorts, merge-skip, three-way partitioning, introsort and interview
interval/majority/pair-sum examples remain teaching extensions with their own
contracts and storage/arithmetic obligations.
