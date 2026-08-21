# Common Mistakes

## Algorithms Java Mastery

Graphs are highly flexible data structures, but that flexibility also makes them easy to model or implement incorrectly.

Many graph-related defects do not originate in the traversal algorithm itself. They usually arise from:

- defining the relationships incorrectly;
- choosing an unsuitable representation;
- violating algorithm preconditions;
- mishandling visited state;
- assuming guarantees that the algorithm does not provide.

Understanding these mistakes is essential because graph algorithms often appear correct on small examples while failing on cyclic, disconnected, directed, weighted, or large-scale inputs.

This document presents the most frequent conceptual, algorithmic, and Java implementation mistakes found when working with graphs.

---

# 1. Purpose

The purpose of this document is to:

- identify common graph-modeling errors;
- explain frequent traversal and implementation defects;
- distinguish invalid algorithm assumptions;
- reinforce correct complexity reasoning;
- establish engineering practices for reliable graph software.

---

# 2. Modeling the Relationship Incorrectly

The first major mistake occurs before implementation: defining the graph incorrectly.

Suppose:

```text
A depends on B
```

The edge must have a clearly documented meaning.

Possible interpretations include:

```text
A → B
```

meaning:

```text
A depends on B
```

or:

```text
B → A
```

meaning:

```text
B must be completed before A
```

Both conventions may be valid, but mixing them produces incorrect results.

This can affect:

- topological sorting;
- dependency traversal;
- in-degree calculations;
- cycle detection;
- scheduling order.

The direction of every edge must be defined before selecting an algorithm.

---

# 3. Treating a Directed Graph as Undirected

In a directed graph:

```text
A → B
```

does not imply:

```text
B → A
```

Adding both relationships unintentionally changes the graph.

Incorrect adjacency representation:

```text
A → B
B → A
```

when the original graph contains only:

```text
A → B
```

This mistake may produce:

- false reachability;
- incorrect shortest paths;
- artificial cycles;
- invalid dependency orders.

Directed edges must be followed only in their declared direction.

---

# 4. Treating an Undirected Graph as Directed

The opposite error also occurs.

For an undirected edge:

```text
A ─── B
```

an adjacency-list representation usually requires:

```text
A → B
B → A
```

Storing only:

```text
A → B
```

creates asymmetric traversal behaviour.

Consequences include:

- reaching `B` from `A` but not `A` from `B`;
- incorrect degree values;
- incomplete connected-component analysis;
- invalid Minimum Spanning Tree behaviour.

An undirected graph representation must preserve symmetry.

---

# 5. Choosing the Wrong Representation

No graph representation is optimal for every problem.

Using an adjacency matrix for a very large sparse graph may require excessive memory:

```text
O(V²)
```

even when:

```text
E ≪ V²
```

Using an edge list for BFS or DFS may require scanning all edges whenever neighbours are needed:

```text
O(E) per neighbour query
```

Typical guidance:

```text
Edge List
        ↓
Global edge processing
```

```text
Adjacency Matrix
        ↓
Dense graphs and frequent edge lookup
```

```text
Adjacency List
        ↓
Sparse graphs and traversal
```

The representation should follow the dominant operations of the problem.

---

# 6. Ignoring Disconnected Components

A traversal starting from one vertex processes only the vertices reachable from that source.

Example:

```text
A ─── B ─── C

D ─── E
```

Running BFS or DFS from `A` visits:

```text
A, B, C
```

It does not visit:

```text
D, E
```

Incorrect conclusion:

```text
The traversal visited the complete graph.
```

To process every component:

```text
for each vertex

    if vertex is unvisited

        start a new traversal
```

This principle is required for:

- counting connected components;
- creating DFS forests;
- validating graph connectivity;
- processing disconnected datasets.

---

# 7. Forgetting the Visited Structure

Graphs may contain cycles.

Example:

```text
A ─── B
|     |
D ─── C
```

Without visited-state tracking, traversal may repeat indefinitely:

```text
A → B → C → D → A → ...
```

This can produce:

- infinite loops;
- infinite recursion;
- duplicate output;
- excessive queue or stack growth.

Every general BFS or DFS implementation must define how discovered vertices are tracked.

---

# 8. Marking Vertices Too Late

A vertex should normally be marked when it is discovered, before further exploration or queue insertion.

For BFS:

```text
Discover
        ↓
Mark
        ↓
Enqueue
```

For recursive DFS:

```text
Enter Vertex
        ↓
Mark
        ↓
Explore Neighbours
```

If BFS marks a vertex only when dequeued, several neighbours may enqueue the same vertex.

If DFS marks only after recursion, cycles may cause repeated calls before the vertex becomes protected.

---

# 9. Marking Vertices Too Early in the Wrong Context

Although standard BFS and DFS mark vertices immediately, some advanced algorithms use more detailed states.

For directed-cycle detection, a simple boolean may not distinguish between:

```text
Visited and Active
```

and:

```text
Visited and Finished
```

DFS-based cycle detection often requires:

```text
UNDISCOVERED
ACTIVE
FINISHED
```

Treating every previously visited vertex as evidence of a cycle produces false positives.

Visited-state design must match the algorithm.

---

# 10. Treating Every Visited Neighbour as a Cycle

In an undirected graph, each edge is commonly stored in both directions.

Example:

```text
A ─── B
```

From `B`, vertex `A` is already visited.

This does not form a cycle. It is simply the parent edge.

A cycle exists when DFS encounters a visited neighbour that is not the current vertex's parent.

Incorrect rule:

```text
Visited neighbour = cycle
```

Correct reasoning:

```text
Visited neighbour
        +
Not parent
        ↓
Cycle
```

---

# 11. Using BFS When DFS Is Required

BFS and DFS both traverse graphs, but they produce different structural behaviour.

BFS is appropriate for:

- level-order exploration;
- shortest unweighted paths;
- minimum edge counts.

DFS is often appropriate for:

- cycle detection;
- topological sorting;
- exhaustive branch exploration;
- backtracking;
- strongly connected components.

Selecting one only because it is familiar may complicate the solution or remove an important guarantee.

---

# 12. Using DFS for Unweighted Shortest Paths

DFS can find a path, but it does not guarantee the path with the fewest edges.

Example:

```text
A ─── B ─── C ─── D
 \_______________/
```

DFS may follow:

```text
A → B → C → D
```

even though:

```text
A → D
```

is shorter.

For minimum edge-count paths in unweighted graphs, use BFS.

---

# 13. Using BFS for Unequal Weighted Edges

BFS minimizes the number of edges, not the sum of arbitrary weights.

Example:

```text
A ──10──▶ B
│
2
│
▼
C ──3──▶ B
```

BFS may prefer the direct one-edge route:

```text
A → B
```

but the lower-cost route is:

```text
A → C → B
```

with cost:

```text
5
```

Weighted graphs require an algorithm that considers edge costs.

---

# 14. Using Dijkstra with Negative Weights

Dijkstra's algorithm requires non-negative edge weights.

A negative edge may improve the distance of a vertex after the algorithm has treated its current distance as final.

Example:

```text
A ──2──▶ B
│
5
│
▼
C ──-10──▶ B
```

The route through `C` has cost:

```text
-5
```

Therefore, standard Dijkstra is invalid.

Use an algorithm such as Bellman–Ford when negative edges are permitted.

---

# 15. Ignoring Negative Cycles

A negative cycle can reduce path cost indefinitely.

```text
Cycle cost < 0
```

Repeated traversal produces:

```text
Lower Cost
        ↓
Lower Cost Again
        ↓
No Finite Minimum
```

Returning a numeric shortest distance in this situation is mathematically incorrect.

Algorithms that support negative weights should define whether they:

- detect reachable negative cycles;
- report affected vertices;
- reject the result.

---

# 16. Confusing Path Length with Path Cost

A path with fewer edges is not necessarily cheaper.

Example:

```text
Path A

2 edges
Cost = 20
```

```text
Path B

4 edges
Cost = 8
```

In unweighted graphs:

```text
Path length = path cost
```

when every edge has unit cost.

In weighted graphs, the concepts must remain separate.

---

# 17. Forgetting Predecessor Tracking

A distance map answers:

```text
What is the minimum cost?
```

It does not necessarily answer:

```text
Which path produces that cost?
```

To reconstruct the route, the algorithm usually stores:

```text
predecessor[vertex]
```

Without predecessor information, returning the actual path may require rerunning the algorithm or performing additional analysis.

---

# 18. Treating Unreachable Vertices as Distance Zero

Distance zero means:

```text
The vertex is the source
```

or a zero-cost relationship under a specific model.

It must not mean:

```text
Unreachable
```

Unreachable vertices should use an explicit representation such as:

```text
∞
```

or:

```text
no value
```

The representation must also avoid ambiguity with valid zero-weight paths.

---

# 19. Ignoring Numeric Overflow

A common sentinel is:

```text
Long.MAX_VALUE
```

Incorrect relaxation:

```text
Long.MAX_VALUE + weight
```

may overflow.

Before addition:

```text
Is the current distance finite?
```

The implementation should also consider whether valid path totals can exceed the chosen numeric type.

Using `long` reduces risk compared with `int`, but it does not remove it.

---

# 20. Ignoring Stale Priority-Queue Entries

Dijkstra and Prim implementations may insert multiple entries for the same vertex or connection.

Example:

```text
(B, distance 10)
```

Later:

```text
(B, distance 4)
```

Both may remain inside the priority queue.

When the outdated entry is removed, it should be ignored.

For Dijkstra:

```text
queued distance != current known distance
        ↓
Stale entry
```

Failing to handle stale entries may introduce unnecessary work and complicate correctness reasoning.

---

# 21. Assuming PriorityQueue Iteration Is Ordered

Java's `PriorityQueue` guarantees priority only for the head element.

It does not guarantee that ordinary iteration produces sorted output.

Incorrect:

```text
for (Element element : priorityQueue) {
    // assume priority order
}
```

To process by priority:

```text
while (!priorityQueue.isEmpty()) {
    Element next = priorityQueue.poll();
}
```

This is relevant for Dijkstra, Prim, scheduling, and other priority-based graph algorithms.

---

# 22. Applying Topological Sorting to a Cyclic Graph

Topological sorting requires a Directed Acyclic Graph.

Example cycle:

```text
A → B → C → A
```

The dependencies require each vertex to appear before another in a circular chain.

No valid ordering exists.

An implementation must detect cycles through:

- DFS active-state detection; or
- Kahn's processed-vertex count.

Returning a partial order as though it were complete is incorrect.

---

# 23. Assuming Topological Order Is Unique

A DAG may have multiple valid orders.

Example:

```text
A → C
B → C
```

Valid:

```text
A, B, C
```

Also valid:

```text
B, A, C
```

Automated tests should normally validate:

```text
For every edge u → v:
position(u) < position(v)
```

rather than comparing against one arbitrary list.

Deterministic tie-breaking should be required only when it is part of the contract.

---

# 24. Forgetting Isolated Vertices in Topological Sorting

A vertex with no incoming or outgoing edges still belongs to the graph.

Example:

```text
A → B

C
```

A valid topological order must include:

```text
A, B, C
```

in an order that preserves `A` before `B`.

If the implementation creates vertices only from edge endpoints, isolated vertices may disappear.

The graph must store its vertex set explicitly.

---

# 25. Reversing Dependency Direction

Suppose:

```text
A must be completed before B
```

Representing this as:

```text
B → A
```

when the algorithm expects:

```text
A → B
```

reverses:

- in-degree values;
- topological order;
- available-task detection;
- dependency traversal.

The meaning of the edge must be documented consistently throughout the graph abstraction and algorithms.

---

# 26. Confusing an MST with a Shortest-Path Tree

A Minimum Spanning Tree minimizes:

```text
Total selected edge weight
```

A shortest-path tree minimizes:

```text
Distance from one source to every vertex
```

An MST may provide a longer route between two specific vertices.

A shortest-path tree may have a larger total edge weight.

Using Prim or Kruskal to solve a route problem is therefore incorrect.

---

# 27. Applying MST Algorithms to Directed Graphs

The classical Minimum Spanning Tree problem is defined for weighted undirected graphs.

Directed graphs require different structures and algorithms, such as directed spanning arborescences.

Treating directed edges as undirected changes the problem.

Kruskal and Prim should not be applied without confirming the graph model.

---

# 28. Assuming MST Edge Weights Must Be Non-Negative

Unlike Dijkstra, Kruskal and Prim can process negative edge weights.

A negative edge may be desirable because it reduces the total tree cost.

Rejecting all negative weights from MST input imposes an unnecessary restriction.

The important requirements are:

- undirected graph;
- meaningful comparable weights;
- connectivity if a single spanning tree is required.

---

# 29. Forgetting Cycle Detection in Kruskal

Kruskal does not simply select the `V - 1` smallest edges.

Those edges may form a cycle while leaving another vertex disconnected.

The algorithm must verify whether the endpoints already belong to the same component.

```text
Same component
        ↓
Reject edge
```

Union-Find is the standard structure for this operation.

---

# 30. Forgetting Connectivity in MST Algorithms

A disconnected graph has no single spanning tree.

Example:

```text
A ─── B

C ─── D
```

If the result contains fewer than:

```text
V - 1 edges
```

the graph may be disconnected.

The method should define whether it:

- throws an exception;
- reports failure;
- returns a minimum spanning forest.

Silently returning an incomplete tree is incorrect.

---

# 31. Assuming the MST Is Always Unique

Equal edge weights may produce multiple Minimum Spanning Trees.

Tests should not require one exact edge set unless deterministic tie-breaking is defined.

Prefer validating:

- selected edge count;
- connectivity;
- absence of cycles;
- expected minimum total weight.

---

# 32. Using a Recursive DFS Without Considering Depth

Recursive DFS may reach:

```text
O(V)
```

call-stack depth.

For a long chain:

```text
A → B → C → D → ...
```

Java may throw:

```text
StackOverflowError
```

even though enough heap memory remains available.

For deep or untrusted graphs, an iterative DFS using `ArrayDeque` may be safer.

---

# 33. Using the Wrong Java Collection

Examples of inefficient choices include:

## BFS with ArrayList front removal

```text
list.remove(0);
```

requires shifting remaining elements:

```text
O(n)
```

Use:

```text
Queue<V> queue = new ArrayDeque<>();
```

## DFS with Stack

Java's legacy `Stack` class is usually less appropriate than:

```java
Deque<V> stack = new ArrayDeque<>();
```

## Edge membership with List

A list provides:

```text
O(degree(v))
```

lookup.

A `HashSet` may provide average:

```text
O(1)
```

when faster membership checking is required.

Collection choice is part of algorithm design.

---

# 34. Exposing Mutable Internal Collections

Suppose a graph returns its actual neighbour list:

```text
List<V> neighbours(V vertex)
```

and external code modifies it.

This may violate invariants such as:

- undirected symmetry;
- edge count;
- duplicate prevention;
- valid endpoint membership.

Prefer:

- unmodifiable views;
- defensive copies;
- controlled mutation methods.

Graph state should not be modifiable outside its defined contract.

---

# 35. Failing to Validate Edge Endpoints

An edge may reference a vertex that is not present in the graph.

Example:

```text
Graph vertices = {A, B}

Edge = A → C
```

This creates inconsistent state.

Possible policies include:

- reject the edge;
- automatically add missing vertices;
- require explicit vertex creation first.

The chosen policy must remain consistent and documented.

---

# 36. Mishandling Duplicate Edges

A simple graph allows at most one edge between a pair of vertices.

A multigraph may allow several.

Using a list without validation may unintentionally create duplicates.

Consequences include:

- incorrect degree values;
- repeated relaxation;
- duplicate traversal work;
- misleading edge counts;
- altered MST behaviour.

The graph abstraction must define whether parallel edges are valid.

---

# 37. Mishandling Self-Loops

A self-loop is:

```text
A → A
```

Its meaning depends on the algorithm.

- In a directed dependency graph, it forms a cycle.
- In an MST, it cannot connect different vertices and should be ignored or rejected.
- In degree calculations, it may require special treatment.
- In traversal, visited-state tracking prevents repeated processing.

Self-loops should not be treated identically across all algorithms without analysis.

---

# 38. Assuming One Traversal Order

BFS and DFS depend on neighbour order.

Example:

```text
A → B, C
```

Possible BFS order:

```text
A, B, C
```

Another valid order:

```text
A, C, B
```

Possible DFS orders differ even more as each first choice affects the complete branch.

Tests should not require a particular order unless the representation guarantees one.

For deterministic traversal, use ordered neighbour collections.

---

# 39. Modifying the Graph During Traversal

Adding or removing vertices or edges during BFS, DFS, or shortest-path computation may cause:

- concurrent modification exceptions;
- skipped neighbours;
- repeated processing;
- inconsistent distances;
- broken invariants.

Possible solutions include:

- treating the graph as immutable during execution;
- traversing a snapshot;
- defining controlled concurrent semantics;
- separating mutation from analysis.

The algorithm contract should state whether concurrent mutation is permitted.

---

# 40. Ignoring Graph Density in Complexity Analysis

Writing:

```text
BFS = O(V + E)
```

is correct for an adjacency list, but the practical behaviour changes with density.

Sparse graph:

```text
E = O(V)
```

Traversal approaches linear growth.

Dense graph:

```text
E = O(V²)
```

Traversal approaches quadratic growth.

Density also influences whether adjacency matrices or lists are more appropriate.

---

# 41. Writing BFS or DFS as O(V)

Traversal does not process only vertices.

It also examines edges.

Correct adjacency-list complexity:

```text
O(V + E)
```

Ignoring `E` is a common analytical mistake.

Similarly, graph storage should generally be expressed using both quantities.

---

# 42. Ignoring the Representation in Complexity Claims

The same traversal may require:

```text
O(V + E)
```

with an adjacency list, but:

```text
O(V²)
```

with an adjacency matrix.

A complexity statement without representation assumptions may be incomplete.

Documentation should identify:

- graph representation;
- supporting collection operations;
- included or excluded storage costs.

---

# 43. Treating Hash-Based Operations as Guaranteed O(1)

`HashMap` and `HashSet` commonly provide average constant-time operations.

Their behaviour depends on:

- correct `equals()` and `hashCode()`;
- reasonable key distribution;
- controlled load factor;
- key immutability.

Custom vertex objects with incorrect equality or hashing may cause:

- duplicate logical vertices;
- failed visited checks;
- inaccessible adjacency entries;
- inconsistent distance maps.

---

# 44. Mutating Vertex Keys

If mutable objects are used as `HashMap` or `HashSet` keys, changing fields involved in `equals()` or `hashCode()` may make them unreachable inside the collection.

Conceptually:

```text
Insert Vertex Key
        ↓
Modify Hash-Relevant State
        ↓
Lookup Fails
```

Prefer immutable vertex identifiers or stable key objects.

---

# 45. Overfitting Tests to Examples

A traversal may pass a small example while failing on:

- cycles;
- disconnected graphs;
- isolated vertices;
- self-loops;
- duplicate edges;
- directed asymmetry;
- multiple valid results;
- large depth;
- negative weights;
- unreachable targets.

Graph tests should validate general properties, not only one memorized sequence.

---

# 46. Better Testing Principles

For BFS and DFS, verify:

- every returned vertex is reachable;
- no vertex appears more than once;
- every reachable vertex appears;
- direction is respected.

For topological sorting, verify:

```text
position(u) < position(v)
```

for every edge `u → v`.

For shortest paths, verify:

- path uses valid edges;
- path cost equals recorded distance;
- algorithm preconditions are enforced.

For MSTs, verify:

- all vertices are connected;
- result is acyclic;
- exactly `V - 1` edges exist;
- total weight is minimal.

---

# 47. Engineering Best Practices

To avoid graph-related defects:

- define the meaning of vertices and edges explicitly;
- document edge direction;
- distinguish weighted and unweighted graphs;
- select the representation according to density and operations;
- validate graph invariants during construction;
- use visited state appropriate to the algorithm;
- enforce algorithm preconditions;
- avoid exposing mutable internal collections;
- use stable and immutable vertex keys;
- test properties rather than incidental order;
- distinguish theoretical complexity from observed benchmark results.

---

# 48. Engineering Perspective

Graph algorithms are often mathematically correct but incorrectly applied.

The implementation may faithfully execute BFS, Dijkstra, topological sorting, or Prim while still producing an invalid result because the graph was modeled incorrectly or the algorithm's assumptions were violated.

Professional graph engineering therefore requires three forms of correctness:

```text
Model Correctness
        ↓
Does the graph represent the real problem?
```

```text
Algorithm Correctness
        ↓
Is the selected algorithm valid for this graph?
```

```text
Implementation Correctness
        ↓
Does the code preserve the algorithm and graph invariants?
```

All three are necessary.

---

# 49. Relationship with the Next Document

This document reviewed the most frequent conceptual and implementation mistakes involving graph algorithms.

The next document, **Interview Notes**, summarizes:

- essential graph definitions;
- common algorithm comparisons;
- complexity results;
- Java implementation considerations;
- frequently asked technical interview questions.

It serves as a focused review of the complete module.

---

# 50. Key Takeaways

After completing this document, the learner should understand that:

- many graph defects originate in incorrect modeling rather than incorrect syntax;
- directed and undirected graphs require different storage behaviour;
- BFS and DFS require visited-state management;
- BFS finds shortest paths only when edge costs are equal;
- Dijkstra must not be used with negative weights;
- topological sorting requires a DAG;
- MSTs and shortest-path trees solve different optimization problems;
- disconnected graphs require explicit handling;
- graph representation affects both memory and algorithm complexity;
- Java collection choices influence correctness and performance;
- traversal and topological orders may not be unique;
- tests should validate mathematical properties rather than one incidental output;
- reliable graph software depends on correct modeling, valid algorithm selection, preserved invariants, and disciplined implementation.