# Complexity Analysis

## Algorithms Java Mastery

The [implementation guide](implementation-and-benchmark-guide.md) supplies the
contracts, traces, proofs, source ledgers, tests and measurement protocol.
Let n be interval/symbol count, k selected intervals, r allocated rooms,
d denominations and L materialised Huffman codeword characters. Empty and
constant-size cases have constant cost; int comparisons are word operations.
Exact BigInteger arithmetic and string character work are separate quantities.

| Reviewed operation | Work | Storage / output |
|---|---|---|
| ActivitySelection.select | O(n log n) worst sorting plus linear validation/scan | O(n) snapshot/sort state plus O(k) plan |
| ActivitySelection.selectSorted | O(n+1) including validation, scan and result | O(n) worst snapshot/output; scalar scan alone O(1) |
| IntervalPartitioning.assign | O(n log n) ordering plus O(n log(r+1)) heap work | O(n) snapshot/entries/assignment, O(r) room availability heap |
| HuffmanCoding.build | O(n log n) comparison/tree work plus O(L) characters and exact arithmetic | O(n+L), including code strings and traversal state |
| HuffmanCoding.costWithHeap | O(n log n) comparisons plus n-1 exact additions | O(n) queue/boxing; no codes |
| HuffmanCoding.costWithTwoQueues | O(n log n) worst sorting plus linear cursor merges and exact additions | O(n) private leaf/merge arrays; no codes |
| CoinChange.greedy | O(d log(d+1)+1) worst preprocessing/scan | O(d) private copy and compressed uses, no amount-sized structure |

Activity selection and classical unweighted interval scheduling are the same
problem here. Assigning every interval to rooms is another objective; their
scores are not a ranking of interchangeable solutions. Time bounds alone also
do not justify the validity of a greedy choice: arbitrary greedy change can
be fast, suboptimal and incomplete.

## Include Preparation and Retained Results

A last-finish scan uses constant scalar state. Public snapshots, sorting
workspace and an immutable selected plan do not disappear from the space
account. The validated presorted method still snapshots and returns a plan.
Its benchmark excludes caller order preparation by design, not as a universal
claim that scheduling has free sorting or constant space.

The room heap retains each room's last availability, including idle rooms.
There is one peek and one offer per interval, and n-r reused-room removals;
room allocation happens r times. These source operation counts are not actual
comparator/instruction counts or measured allocation bytes. r ranges from one
to n for nonempty schedules, depending on maximum overlap.

## Huffman Heap, Queues and Output

For n>=2, cost computation has n-1 merges and 2(n-1) minimum removals/front
selections. Heap cost performs 2n-1 offers including initial leaves. Individual
offers have O(n log n) aggregate worst preprocessing, unlike a separate linear
heapify algorithm. Two-queue cost sorts inside its public call, then merges
through monotone cursors. Neither produces codewords; full build additionally
creates the tree, map, strings and exact weighted length.

Positive int frequencies have a long-bounded subtree sum, but total weighted
path length also multiplies by depth and is kept exact with BigInteger.
O(n+L) includes materialised codewords; quoting only O(n) tree space would omit
the returned representation. Explicit-stack traversal does not prove constant
memory or a fixed byte count. Singleton nonempty code policy has its own base.

## Model and Empirical Boundaries

O(n log n) is a worst comparison bound; it is not an assertion of identical
sorting work for every fixture. One seed, two sizes and a finite shape grid do
not prove an exponent or universal speed ranking. Heap boxing, snapshots,
BigInteger arithmetic, candidate/output counts and runtime optimisations can
affect elapsed time, but timings alone do not identify their shares or CPU/cache
causes. The [first report](../17-benchmarking/results/greedy/README.md) now
completes all three templates with 40 means/errors and every printed iteration,
using author-reported source `674ab35` and JDK 21.0.9/JMH 1.37. Ten errors reach
25% of their means. The source output/heap/merge ledgers remain analytical;
presorted preparation, different interval objectives and omitted Huffman codes
prevent interpreting the means as isolated or interchangeable operation costs.
No fitted exponent or significance/equivalence test is claimed.
