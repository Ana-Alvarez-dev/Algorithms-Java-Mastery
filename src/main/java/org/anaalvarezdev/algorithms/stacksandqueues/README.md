# Stacks and Queues Implementations

This Java 21 package implements the selected scope of
[module 07](../../../../../../../docs/07-stacks-and-queues/README.md).
The [implementation and benchmark guide](../../../../../../../docs/07-stacks-and-queues/implementation-and-benchmark-guide.md)
connects specification, pseudocode, traces, invariants, complexity, tests and JMH.

| Type | Responsibility | Test evidence |
|---|---|---|
| `Stack`, `ArrayStack`, `LinkedStack` | Non-null LIFO; dynamic array and private node representations | `StackContractTest`, inherited by both concrete test classes; array growth tests |
| `FifoQueue`, `CircularArrayQueue`, `LinkedQueue`, `TwoStackQueue` | Non-null FIFO; ring, node and transfer representations | `QueueContractTest`, inherited by three concrete test classes; wrapped-growth and transfer cases |
| `DoubleEndedQueue`, `BoundedArrayDeque` | Fixed-capacity double-ended ring with strict/sentinel pairs | `BoundedArrayDequeTest` |
| `ArrayCapacity` | Validated capacity and overflow-safe growth arithmetic | Capacity checks in `ArrayStackTest` |
| `DelimiterValidator` | Three bracket kinds; ignores other characters | `DelimiterValidatorTest` |
| `PostfixEvaluator` | Checked int arithmetic and expression structure | `PostfixEvaluatorTest` |
| `NextGreaterElement` | Nearest strictly greater right index | Exhaustive small-array quadratic oracle |
| `SlidingWindowMaximum` | Monotonic deque of candidate indices | Exhaustive small-array/window scanning oracle |
| `QueueReverser` | In-place reversal with auxiliary stack | All three queue representations and identity checks |

Empty strict reads/removals throw; peek/poll return null. Null insertion is
always rejected before mutation. Snapshots are mutable, shallow and independent,
in removal order. These educational interfaces are not full JCF implementations.
No iterator or thread-safety contract is added.

Verification on 2026-10-19 with OpenJDK 21 passed **418 repository tests**,
including **80 module tests**. Both JMH classes built, all ten methods were
discovered and smoke-checked, and a separate probe checked all 40 method/size
combinations. The
[first reported results](../../../../../../../docs/17-benchmarking/results/stacks-and-queues/README.md)
complete both templates' Result and Interpretation sections: 20 configurations,
200 measurement and 120 warmup averages, JDK 21.0.9/JMH 1.37, author-reported
source `804f69f32b0da715ed41f390034523890c24bca5`. Interpretation is preliminary;
eleven errors reach at least 25% of their means. Smoke scores are excluded.

The [project traceability review](../../../../../../../docs/00-project/11-traceability-review.md)
records the current chain and unresolved earlier-module limitations.
