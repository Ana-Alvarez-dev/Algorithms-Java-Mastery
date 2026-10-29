# State-Space Trees

## Visited tree, conceptual completion and root count

The [applied map](implementation-contracts.md) counts helper invocations,
including the root and terminal calls. In the visited SubsetSum decision tree,
sum==target is a successful terminal even before index==length: excluding all
remaining positions supplies a full mathematical subset. Unvisited alternative
witnesses are intentionally omitted. This is distinct from enumerating a full
binary position tree or materialising every solution.

A state-space tree models a search: the root is the empty construction, an
edge is a decision, and a node is a partial state. Complete solutions are
successful leaves; rejected or exhausted states can be unsuccessful leaves.
The tree is a reasoning model, not necessarily an allocated data structure.

## Selected States

| Search | Node state | Child decision | Terminal result |
|---|---|---|---|
| Permutations | Prefix plus unused positions/multiplicities | Next value | Length-n arrangement |
| Combinations | Next allowed index and remaining cardinality | Increasing next index | Remaining=0 |
| N-Queens | Row and occupied columns/diagonals | Safe column | Row=n |
| Subset sum | Input index and current sum | Include/exclude next position | Witness or exhausted/bounded-out branch |

For choose(4,2), children are increasing values. Its successful paths are
[0,1], [0,2], [0,3], [1,2], [1,3], [2,3]. The capacity bound omits choices
that leave too few values. An unpruned reference keeps those dead prefixes.

## Tree Work Versus Active State

Let V be total visited nodes and d maximum active depth. Depth-first traversal
can visit exponentially many nodes while retaining only O(d) frames plus the
current constraint/path representation. More visited nodes do not by themselves
mean more peak memory. Retaining all solutions, an explicit tree or a memo
table adds separate storage.

A uniform branching bound b gives at most sum(b^i, i=0..d) nodes. For fixed
b>1 this is O(b^d); b=1 is a chain of d+1 nodes, b=0 only the root. Local
candidate checks and output copies must still be counted. Permutations and
queens have changing branching factors, so a uniform bound can be loose.

## What Is Actually Allocated

The Java implementation keeps private arrays and the call stack. Enumeration
copies each finished row into immutable lists; count-only variants retain no
result rows. Mask-based queens use constant-size primitive state per frame,
with O(n) logical recursion depth rather than constant total space.

Tree size, result count and elapsed time are separate quantities. For example,
choose(n,n) has one result, n+1 pruned calls and 2^n unpruned calls. This is an
analytical source count, not a measured CPU-event count.

The [implementation and benchmark guide](implementation-and-benchmark-guide.md) defines the exact call ledgers, input caps and ownership policy.
Next: [candidate generation](03-candidate-generation.md).
