# Linked Lists Implementations

This Java 21 package implements the
[Linked Lists module](../../../../../../../docs/06-linked-lists/README.md).
The [implementation and benchmark guide](../../../../../../../docs/06-linked-lists/implementation-and-benchmark-guide.md)
defines the selected API, node ownership, invariants, exact costs and run commands.

| Type | Responsibility | Test |
|---|---|---|
| `LinkedSequence<E>` | Common observable contract and snapshot/copy helpers | `LinkedSequenceContractTest`, inherited by the three concrete tests |
| `SinglyLinkedList<E>` | Null-terminated forward chain, cached endpoints and size | `SinglyLinkedListTest` |
| `DoublyLinkedList<E>` | Reciprocal links and backward iteration | `DoublyLinkedListTest` |
| `CircularLinkedList<E>` | Singly linked ring, finite one-lap iteration | `CircularLinkedListTest` |
| `LinkedNode<E>` | Explicit nodes for independent chain exercises | `LinkedListAlgorithmsTest` |
| `LinkedListAlgorithms` | Floyd detection, middle, reversal, stable merge and sorted deduplication | `LinkedListAlgorithmsTest` |

Containers allow null/repeated values, reject invalid indices, and keep their
nodes private. Split transfers ownership; copying append leaves its source
unchanged. Iterators reject structural changes and do not support removal.
Standalone node algorithms cannot alter private container topology.

`LinkedListOperationsBenchmark` provides fifteen JMH methods comparing manual
and library structures across traversal, middle access and a restored prepend/
remove pair. Setup is excluded; timed outputs are consumed by JMH. The
[first reported evidence record](../../../../../../../docs/17-benchmarking/results/linked-lists/README.md)
preserves 30 console means and errors supplied on 2026-10-19, without JSON/log
files. Supplied headers confirm JMH 1.37 and JDK 21.0.9; 50 measurement and 30
warmup iteration averages are recorded for five configurations. The author
identified the executed source as commit `a16a431`; the report records the full
SHA. On 2026-10-12, Java 21 verification passed 338 repository tests, including
all 81 Linked Lists tests, and built the executable benchmark jar.
