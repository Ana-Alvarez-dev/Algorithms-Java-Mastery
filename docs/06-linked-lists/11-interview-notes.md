# Linked Lists: Interview Notes

This review consolidates concepts implemented in the module. Use the
[implementation guide](implementation-and-benchmark-guide.md) for contracts,
source/test traceability and empirical scope.

## Questions That Require Precise Assumptions

| Question | Expected reasoning |
|---|---|
| Why is insertion at a known link constant-time while indexed insertion can be linear? | Separate locating a position from updating a bounded number of links |
| Does a tail make every endpoint removal constant-time? | Singly linked tail removal still needs its predecessor; doubly linked tail removal has that reference |
| Is counting nodes always linear? | Traversal counting is linear; the implemented cached size is constant-time |
| How does circular iteration terminate? | Visit exactly the captured size; the ring has no terminating null link |
| What must change when a doubly linked node is removed? | Both neighboring links and possibly head/tail; then update size |
| Why save next before reversing a link? | Preserve access to the unprocessed suffix while constructing the reversed prefix |
| Which middle is returned for an even length? | The implemented chain algorithm selects position floor(n/2), the second middle |
| Can equal values prove that a cycle exists? | No; cycle detection compares node identity, not value equality |
| Why reject overlapping inputs before destructive merging? | Reusing shared nodes can introduce cycles or duplicate ownership; acyclic shared chains share a tail |
| What makes merging stable? | Preserve within-chain order and choose the left input on equal comparator values |
| Is copying append constant-time with a tail? | Individual tail append is constant-time; copying m values and allocating m nodes is linear |
| Does a fail-fast iterator make a mutable list thread-safe? | No; invalidation detects structural misuse and is not synchronization |
| Can a passing benchmark prove correctness or complexity? | No; use contracts, preservation/termination reasoning, and separate tests |

## Worked Reference Trace

Start with `[A, B, C]` and insert `X` at position 1:

1. Locate predecessor `A` and save successor `B`.
2. Set the new node's successor to `B`.
3. Set `A.next` to the new node.
4. In a doubly linked variant, also repair the reciprocal predecessor links.
5. Update the cached size; head/tail stay consistent.

The local splice has constant link-update work. Locating a distant predecessor
may require traversal. The [tests](../../src/test/java/org/anaalvarezdev/algorithms/linkedlists/LinkedSequenceContractTest.java)
check resulting values and structural invariants, including boundary insertions.

## Contract Choices to State Before Coding

- Nulls and duplicates are allowed by the selected containers.
- Empty endpoint access throws; a null node head represents an empty standalone chain.
- Split transfers nodes; append copies values into newly owned nodes.
- Node algorithm mutation returns the new head and may consume its input chains.
- A genuine circular container is intentional; an unexpected cycle in an acyclic
  algorithm is rejected.

## Review Exercises

Trace the empty-to-singleton-to-empty transitions for each topology. Reverse a
two-node circular ring and state the new head/tail links. Split a ring in the
middle and show why both outputs remain independent rings. Explain why calling
`get(i)` inside a traversal loop can turn a linear iteration into quadratic work.

The next module is [Stacks and Queues](../07-stacks-and-queues/README.md).
