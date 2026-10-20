# Tree Implementations

This Java 21 package implements the selected scope of
[module 09](../../../../../../../docs/09-trees/README.md).
The [implementation guide](../../../../../../../docs/09-trees/implementation-and-benchmark-guide.md)
connects contracts, pseudocode, traces, correctness, complexity, tests and JMH.

| Type | Contract / responsibility | Verification |
|---|---|---|
| OrderedIntSet | Unique primitive keys; boolean updates, OptionalInt extrema/neighbours, edge height | Ten shared contract tests inherited by both concrete sets |
| AbstractIntSearchTree | Package-private node storage and common iterative queries/snapshot/diagnostic | Ordering, count, identity uniqueness and independent output |
| IntBinarySearchTree | Iterative unbalanced BST, successor-based keyed deletion | 13 tests, including root/child repair and sorted-spine height |
| IntAvlTree | Cached heights, single/double rotation and deletion rebalance | 14 tests, including zero-balance child and 10,000-key growth/drain |
| BinaryTreeNode | Immutable standalone proper-tree input; distinct from container nodes | Null/leaf, duplicates, extreme values and deep trees |
| TreeTraversals | Four iterative orders, independent mutable lists | Six tests, 626 small shapes and 100,000-node spines |
| TreeAnalysis | Size, edge-height/diameter, local-height balance | Five tests, all-pairs-distance oracle and deep explicit frames |
| IntArrayCapacity | Bounded growth arithmetic | Two tests without huge allocations |
| IntMinHeap | Dynamic primitive min-heap; duplicates and independent heap-order arrays | Eleven tests, 5,000-step JDK oracle and 1,093 small arrays |
| HeapSort | Ascending in-place max-heap sort, unstable | Four tests against Arrays.sort |
| KSmallest | Copied min-heap plus sorted extraction, multiplicity preserved | Four tests, including 7,108 small array/k combinations |

Verification on 2026-10-19 with OpenJDK 21 passed **524 repository tests**,
including **59 module tests**. Twelve JMH methods built; 24 short smoke cases
completed and a separate probe checked all 72 default method/size/fixture
combinations. JavaDoc passes doclint excluding missing-tag checks.

BST/AVL null keys are inapplicable to a primitive API. Standalone null root is
empty; array application inputs reject null. Standalone traversals require a
proper tree without shared child identity; this precondition is not revalidated
on each visit. Empty/leaf heights are -1/0; empty diameter is defined as zero.
Floor/ceiling are inclusive neighbours, not strict successor/predecessor.

A heap snapshot is not sorted. Growing offer can copy linearly, despite
logarithmic sift work; snapshot and complete traversal allocate linear output.
The guide documents these bounds and retained-capacity/GC qualifications.

The [first lookup subset](../../../../../../../docs/17-benchmarking/results/trees/README.md)
completes the lookup template's Result/Interpretation with all 24 cells, errors
and printed iterations on JDK 21.0.9. The report explains sorted-BST source path
work and records four high-error SHUFFLED AVL cells. The author's reflog
identifies execution source `c576cb79aa9747aced1cd041e97f64fc99e60010`.
HeapOperationsBenchmark remains pending author timings; its discovery listing
is separate from measurement. Evidence stays Markdown without JSON/log artifacts.
