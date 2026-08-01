# Complexity Analysis

## Algorithms Java Mastery

The performance of a graph algorithm depends on two principal quantities:

```text
V = Number of Vertices

E = Number of Edges
```

Unlike algorithms over arrays, where input size is usually represented by a single value `n`, graph algorithms must consider both the number of vertices and the number of relationships between them.

The selected graph representation also influences performance. An algorithm implemented with an adjacency list may have a different complexity from the same algorithm implemented with an adjacency matrix.

This document summarizes and compares the time and space complexity of the principal graph representations and algorithms studied throughout this module.

---

# 1. Purpose

The purpose of this document is to:

- explain how graph input size is measured;
- compare graph representations;
- analyse the complexity of traversal algorithms;
- summarize shortest-path and Minimum Spanning Tree algorithms;
- understand how graph density affects performance;
- support appropriate algorithm and representation selection.

---

# 2. Graph Input Size

A graph is commonly defined as:

```text
G = (V, E)
```

where:

- `V` represents the set of vertices;
- `E` represents the set of edges.

Complexity expressions such as:

```text
O(V + E)
```

mean that the required work grows according to both the number of vertices and the number of edges.

For example:

```text
V = 1,000 vertices

E = 3,000 edges
```

A linear graph algorithm may perform work proportional to:

```text
1,000 + 3,000
```

rather than only to the number of vertices.

---

# 3. Maximum Number of Edges

The maximum possible number of edges depends on the graph type.

## Simple Undirected Graph

A simple undirected graph with `V` vertices may contain at most:

```text
V(V - 1) / 2
```

edges.

Each pair of distinct vertices can be connected only once.

---

## Simple Directed Graph

A simple directed graph without self-loops may contain at most:

```text
V(V - 1)
```

edges.

Both directions may exist independently:

```text
u → v

v → u
```

These limits help distinguish sparse and dense graphs.

---

# 4. Sparse and Dense Graphs

A graph is generally considered **sparse** when:

```text
E is much smaller than V²
```

Conceptually:

```text
E ≪ V²
```

A graph is **dense** when the number of edges approaches the maximum possible value.

Conceptually:

```text
E ≈ V²
```

This distinction influences representation and algorithm selection.

```text
Sparse Graph
        ↓
Adjacency List Usually Preferred
```

```text
Dense Graph
        ↓
Adjacency Matrix May Be Appropriate
```

---

# 5. Representation Complexity

The three principal graph representations are:

- edge list;
- adjacency matrix;
- adjacency list.

Each one provides different performance characteristics.

---

# 6. Edge List Complexity

An edge list stores every edge explicitly.

Space complexity:

```text
O(V + E)
```

assuming vertices are also stored.

| Operation | Complexity |
|---|---:|
| Add edge | O(1)* |
| Check whether an edge exists | O(E) |
| Retrieve neighbours | O(E) |
| Iterate over all edges | O(E) |
| Space | O(V + E) |

\* Assuming append to a suitable dynamic collection.

An edge list is efficient for algorithms that process all edges directly, such as Kruskal's algorithm.

---

# 7. Adjacency Matrix Complexity

An adjacency matrix reserves one position for every possible pair of vertices.

Space complexity:

```text
O(V²)
```

| Operation | Complexity |
|---|---:|
| Add edge | O(1) |
| Remove edge | O(1) |
| Check whether an edge exists | O(1) |
| Retrieve all neighbours of one vertex | O(V) |
| Iterate over all possible edges | O(V²) |
| Space | O(V²) |

The adjacency matrix provides efficient edge lookup but may waste memory in sparse graphs.

---

# 8. Adjacency List Complexity

An adjacency list stores only existing edges.

Space complexity:

```text
O(V + E)
```

| Operation | Complexity |
|---|---:|
| Add edge | O(1)* |
| Remove edge | O(degree(v)) |
| Check whether an edge exists | O(degree(v)) |
| Retrieve neighbours | O(1)** |
| Iterate over neighbours | O(degree(v)) |
| Space | O(V + E) |

\* Depending on the neighbour collection.

\** Returning the collection is constant time; iterating through it depends on the vertex degree.

Adjacency lists are generally preferred for sparse graphs and traversal algorithms.

---

# 9. Representation Comparison

| Representation | Space | Edge Lookup | Neighbour Traversal | Best Use |
|---|---:|---:|---:|---|
| Edge List | O(V + E) | O(E) | O(E) | Global edge processing |
| Adjacency Matrix | O(V²) | O(1) | O(V) | Dense graphs |
| Adjacency List | O(V + E) | O(degree(v)) | O(degree(v)) | Sparse graphs and traversal |

The representation should be selected according to the operations performed most frequently.

---

# 10. Breadth-First Search Complexity

Breadth-First Search visits every reachable vertex and examines its outgoing edges.

## With an Adjacency List

```text
Time = O(V + E)
```

Reasoning:

- every vertex is enqueued and dequeued at most once;
- every adjacency entry is examined once.

Additional space:

```text
O(V)
```

for:

- the queue;
- visited-state tracking;
- optional distances;
- optional predecessors.

---

## With an Adjacency Matrix

Every visited vertex requires scanning an entire matrix row.

```text
Time = O(V²)
```

Additional space remains:

```text
O(V)
```

---

# 11. Depth-First Search Complexity

Depth-First Search visits each reachable vertex and examines every relevant edge.

## With an Adjacency List

```text
Time = O(V + E)
```

Additional space:

```text
O(V)
```

for:

- visited-state tracking;
- recursion or explicit stack;
- optional predecessor information.

The recursion depth may reach:

```text
O(V)
```

in the worst case.

---

## With an Adjacency Matrix

```text
Time = O(V²)
```

because every visited vertex requires scanning a complete row.

---

# 12. BFS and DFS Comparison

| Algorithm | Adjacency List | Adjacency Matrix | Additional Space |
|---|---:|---:|---:|
| BFS | O(V + E) | O(V²) | O(V) |
| DFS | O(V + E) | O(V²) | O(V) |

Both traversals have the same asymptotic time complexity under the same representation.

Their main difference is the exploration strategy and supporting data structure.

```text
BFS → Queue

DFS → Stack or Recursion
```

---

# 13. Topological Sorting Complexity

Topological sorting may be implemented through:

- DFS finishing order;
- Kahn's algorithm.

## With an Adjacency List

Both strategies require:

```text
O(V + E)
```

time.

Additional space:

```text
O(V)
```

for:

- vertex states;
- recursion or stack;
- in-degree values;
- queue;
- result ordering.

---

## With an Adjacency Matrix

```text
O(V²)
```

time may be required because outgoing edges are found by scanning complete rows.

---

# 14. Unweighted Shortest Paths

Breadth-First Search calculates shortest paths in unweighted graphs.

With an adjacency list:

```text
Time = O(V + E)
```

Additional space:

```text
O(V)
```

for distances, predecessors, the queue, and visited-state tracking.

This is generally the most efficient shortest-path strategy when every edge has equal cost.

---

# 15. Dijkstra's Algorithm Complexity

Dijkstra's complexity depends on both the graph representation and the mechanism used to select the minimum tentative distance.

## Adjacency List and Binary Heap

```text
O((V + E) log V)
```

This is often simplified to:

```text
O(E log V)
```

for connected graphs.

The algorithm requires:

- adjacency list;
- distance map;
- predecessor map;
- minimum priority queue.

Additional space is commonly:

```text
O(V + E)
```

including the graph representation.

---

## Adjacency Matrix and Linear Selection

```text
O(V²)
```

This implementation may be appropriate for dense graphs where:

```text
E ≈ V²
```

---

# 16. Bellman–Ford Complexity

Bellman–Ford relaxes every edge up to:

```text
V - 1 times
```

Therefore:

```text
Time = O(VE)
```

Additional space:

```text
O(V)
```

excluding the graph representation.

Bellman–Ford is less efficient than Dijkstra but supports:

- negative edge weights;
- reachable negative-cycle detection.

---

# 17. Floyd–Warshall Complexity

Floyd–Warshall calculates shortest paths between every pair of vertices.

It performs three nested iterations over the vertex set.

```text
Time = O(V³)
```

The distance matrix requires:

```text
Space = O(V²)
```

This algorithm is generally suitable when:

- all-pairs shortest paths are required;
- the graph is relatively small;
- many distance queries will be performed;
- a matrix representation is practical.

---

# 18. Shortest-Path Comparison

| Algorithm | Graph Conditions | Time Complexity | Additional Space |
|---|---|---:|---:|
| BFS | Unweighted | O(V + E) | O(V) |
| Dijkstra with heap | Non-negative weights | O((V + E) log V) | O(V) to O(V + E)* |
| Dijkstra with matrix | Non-negative weights | O(V²) | O(V) |
| Bellman–Ford | Negative weights allowed | O(VE) | O(V) |
| Floyd–Warshall | All-pairs | O(V³) | O(V²) |

\* Depending on whether graph representation and duplicate priority-queue entries are included.

The fastest valid algorithm should be selected according to the graph's properties and required result.

---

# 19. Kruskal's Algorithm Complexity

Kruskal's algorithm performs two principal activities:

```text
Sort edges
        +
Union-Find operations
```

Sorting requires:

```text
O(E log E)
```

Union-Find operations with path compression and union by rank or size require approximately:

```text
O(E α(V))
```

Since sorting dominates:

```text
Overall Time = O(E log E)
```

Additional space:

```text
O(V + E)
```

for:

- sorted edges;
- Disjoint Set Union;
- selected MST edges.

---

# 20. Prim's Algorithm Complexity

Prim's complexity depends on the representation and priority-selection strategy.

## Adjacency List and Binary Heap

```text
O(E log V)
```

Additional space:

```text
O(V + E)
```

including the graph representation and candidate priority queue.

---

## Adjacency Matrix

```text
O(V²)
```

The matrix version may be competitive for dense graphs.

---

# 21. Minimum Spanning Tree Comparison

| Algorithm | Typical Representation | Time Complexity | Suitable Context |
|---|---|---:|---|
| Kruskal | Edge list | O(E log E) | Sparse or edge-oriented graphs |
| Prim with heap | Adjacency list | O(E log V) | Sparse connected graphs |
| Prim with matrix | Adjacency matrix | O(V²) | Dense graphs |

The asymptotic results are similar for many sparse graphs because:

```text
log E = O(log V)
```

when the graph contains at most a polynomial number of edges in `V`.

---

# 22. Union-Find Complexity

Kruskal uses the Disjoint Set Union structure.

Without optimization, Union-Find operations may become expensive.

With:

- path compression;
- union by rank or size;

the amortized complexity of each operation is:

```text
O(α(V))
```

where `α` is the inverse Ackermann function.

For practical input sizes, this behaves almost like constant time.

However, the formal complexity should not be written simply as `O(1)`.

---

# 23. Overall Algorithm Comparison

| Algorithm | Primary Objective | Typical Time Complexity |
|---|---|---:|
| BFS | Level-order traversal and unweighted distances | O(V + E) |
| DFS | Depth-oriented traversal | O(V + E) |
| Topological Sort | Dependency ordering | O(V + E) |
| Dijkstra | Non-negative shortest paths | O((V + E) log V) |
| Bellman–Ford | Shortest paths with negative edges | O(VE) |
| Floyd–Warshall | All-pairs shortest paths | O(V³) |
| Kruskal | Minimum Spanning Tree | O(E log E) |
| Prim | Minimum Spanning Tree | O(E log V) |

These results assume efficient and appropriate graph representations.

---

# 24. Influence of Graph Density

The same algorithm may behave differently depending on graph density.

## Sparse Graph

When:

```text
E = O(V)
```

BFS and DFS require:

```text
O(V)
```

up to constant factors.

Dijkstra with a binary heap requires approximately:

```text
O(V log V)
```

---

## Dense Graph

When:

```text
E = O(V²)
```

BFS and DFS with adjacency lists require:

```text
O(V²)
```

Dijkstra with a binary heap approaches:

```text
O(V² log V)
```

while a matrix-based implementation requires:

```text
O(V²)
```

Therefore, an asymptotically simpler data structure for sparse graphs may not always be preferable for dense graphs.

---

# 25. Complexity of Degree-Based Operations

In an adjacency list, many operations depend on the degree of a vertex.

For a vertex `v`:

```text
degree(v)
```

represents the number of adjacent edges in an undirected graph.

Retrieving and processing every neighbour requires:

```text
O(degree(v))
```

Across the complete undirected graph:

```text
sum of all vertex degrees = 2E
```

This explains why traversing every adjacency list remains:

```text
O(V + E)
```

rather than:

```text
O(VE)
```

---

# 26. Directed Graph Degree Relationship

In a directed graph:

```text
sum of all out-degrees = E
```

and:

```text
sum of all in-degrees = E
```

Each directed edge contributes:

- one to the source's out-degree;
- one to the destination's in-degree.

This property explains why calculating all in-degrees for Kahn's algorithm requires:

```text
O(V + E)
```

with an adjacency list.

---

# 27. Best, Average, and Worst Cases

Many graph algorithms are generally expressed using worst-case asymptotic complexity.

For example, BFS may find a target early, but its worst-case execution still examines the complete reachable component.

```text
Best practical case
        ↓
Target found near source
```

```text
Worst case
        ↓
All reachable vertices and edges examined
```

Unless the algorithm contract includes early termination, complexity analysis should normally describe the complete traversal.

---

# 28. Reachable Subgraph Complexity

A source-based traversal does not necessarily process the complete graph.

If only:

```text
Vᵣ reachable vertices
```

and:

```text
Eᵣ reachable edges
```

exist from the source, the actual work may be expressed as:

```text
O(Vᵣ + Eᵣ)
```

However, general worst-case analysis uses:

```text
O(V + E)
```

because all vertices may be reachable.

---

# 29. Additional Space

Graph algorithm space complexity often includes several structures:

```text
Visited Set
Distance Map
Predecessor Map
Queue or Stack
Priority Queue
Result Collection
```

Many algorithms require:

```text
O(V)
```

additional space.

However, priority queues may temporarily contain multiple entries for the same vertex, particularly in Dijkstra or Prim implementations that do not support decrease-key operations.

The practical queue size may therefore depend on the number of edge relaxations or candidate insertions.

---

# 30. Recursion Space

Recursive DFS uses the Java call stack.

Worst-case depth:

```text
O(V)
```

A graph shaped like a long chain may produce one recursive call per vertex.

```text
A → B → C → D → ... → V
```

Although asymptotic space remains `O(V)`, real execution may fail with:

```text
StackOverflowError
```

before available heap memory is exhausted.

An iterative implementation avoids call-stack limitations but still requires `O(V)` explicit stack space.

---

# 31. Representation Space Is Separate

When analysing an algorithm, distinguish between:

```text
Graph Storage
```

and:

```text
Additional Algorithmic Space
```

Example for BFS with an adjacency list:

```text
Graph storage = O(V + E)

Additional traversal space = O(V)
```

Combined:

```text
Total space = O(V + E)
```

However, documentation should state clearly which interpretation is being used.

---

# 32. Complexity and Java Collections

The selected Java collection affects practical and theoretical behaviour.

Examples:

| Operation | `ArrayList` | `HashSet` | `ArrayDeque` | `PriorityQueue` |
|---|---:|---:|---:|---:|
| Append | O(1) amortized | — | O(1) amortized | O(log n) |
| Membership test | O(n) | O(1) average | O(n) | O(n) |
| Remove front | O(n) | — | O(1) | O(log n) for root |
| Remove highest priority | — | — | — | O(log n) |

Using an inappropriate collection can change the effective complexity of the complete algorithm.

For example, implementing BFS with an `ArrayList` and removing index zero would introduce repeated shifting.

Prefer:

```java
Queue<V> queue = new ArrayDeque<>();
```

---

# 33. Hash-Based Collection Assumptions

Graph implementations frequently use:

```text
HashMap
HashSet
```

Their primary operations provide average:

```text
O(1)
```

performance.

However, this depends on:

- appropriate hash functions;
- controlled load factor;
- consistent `equals()` and `hashCode()`;
- absence of pathological collision patterns.

Complexity documentation should distinguish average hash-based performance from worst-case behaviour where relevant.

---

# 34. Benchmarking and Complexity

Benchmarking measures observed execution time.

Complexity analysis describes how resource usage grows as input size increases.

They answer different questions.

```text
Complexity Analysis
        ↓
How does growth behave theoretically?
```

```text
Benchmarking
        ↓
How does one implementation behave experimentally?
```

A benchmark cannot prove asymptotic complexity.

Likewise, asymptotic analysis does not predict exact execution time.

Both approaches should complement one another.

---

# 35. Benchmark Variables

When benchmarking graph algorithms, relevant variables include:

- number of vertices;
- number of edges;
- graph density;
- degree distribution;
- connectedness;
- edge-weight distribution;
- graph representation;
- collection implementation;
- traversal source;
- number of reachable vertices.

Comparing algorithms on graphs with the same number of vertices but very different edge counts may produce misleading conclusions.

---

# 36. Common Complexity Mistakes

## Using only `n`

Graph input normally requires both `V` and `E`.

## Ignoring representation

Adjacency-list and matrix implementations may have different complexities.

## Writing BFS or DFS as O(V)

Edges must also be examined.

Correct:

```text
O(V + E)
```

## Assuming every edge lookup is O(1)

This is true for matrices but not necessarily for adjacency lists.

## Ignoring graph density

Dense and sparse graphs may favour different representations.

## Treating priority-queue search as O(1)

Only root access is constant time. Insertion and removal require logarithmic time.

## Ignoring sorting in Kruskal

Edge sorting dominates the algorithm.

## Claiming Union-Find is strictly O(1)

The optimized amortized bound is `O(α(V))`.

## Combining graph storage and auxiliary space inconsistently

The analysis must state what is included.

---

# 37. Algorithm Selection Guide

Use BFS when:

- the graph is unweighted;
- minimum edge count is required;
- level-order exploration is appropriate.

Use DFS when:

- deep exploration is required;
- cycle detection is needed;
- dependency processing or backtracking is central.

Use topological sorting when:

- the graph is directed and acyclic;
- dependency order is required.

Use Dijkstra when:

- edge weights are non-negative;
- single-source shortest paths are needed.

Use Bellman–Ford when:

- negative edges may exist;
- negative-cycle detection is required.

Use Floyd–Warshall when:

- all-pairs shortest paths are required;
- cubic time is acceptable.

Use Kruskal or Prim when:

- the graph is weighted and undirected;
- minimum total connection cost is required.

---

# 38. Complexity Reference Table

| Topic | Time Complexity | Space Complexity |
|---|---:|---:|
| Edge-list storage | — | O(V + E) |
| Adjacency-matrix storage | — | O(V²) |
| Adjacency-list storage | — | O(V + E) |
| BFS with adjacency list | O(V + E) | O(V) additional |
| DFS with adjacency list | O(V + E) | O(V) additional |
| Topological sort | O(V + E) | O(V) additional |
| Dijkstra with heap | O((V + E) log V) | O(V) additional* |
| Bellman–Ford | O(VE) | O(V) additional |
| Floyd–Warshall | O(V³) | O(V²) |
| Kruskal | O(E log E) | O(V + E) |
| Prim with heap | O(E log V) | O(V + E) |
| Prim with matrix | O(V²) | O(V²) including matrix |

\* A priority queue may temporarily contain additional stale entries depending on the implementation.

---

# 39. Engineering Perspective

Graph complexity cannot be evaluated by considering the algorithm name alone.

A complete analysis should consider:

```text
Graph Properties
        +
Representation
        +
Supporting Collections
        +
Algorithm
        +
Required Result
```

For example, saying:

```text
Dijkstra is O(E log V)
```

is incomplete without acknowledging that this result assumes an efficient adjacency-list and priority-queue implementation.

Similarly:

```text
BFS is O(V + E)
```

assumes that the neighbours of each vertex can be enumerated without scanning all possible vertices.

Professional engineering decisions should therefore connect theoretical complexity with concrete implementation choices.

---

# 40. Relationship with the Next Document

This document compared the computational cost of the principal graph representations and algorithms.

The next document presents **Common Algorithms**, connecting graph techniques with practical problems such as:

- network routing;
- dependency resolution;
- connectivity analysis;
- navigation;
- scheduling;
- recommendation systems;
- infrastructure design.

The objective is to recognize which graph strategy applies to each problem class.

---

# 41. Key Takeaways

After completing this document, the learner should understand that:

- graph complexity is normally expressed using both `V` and `E`;
- graph density influences representation and algorithm selection;
- adjacency matrices require `O(V²)` space;
- adjacency lists require `O(V + E)` space;
- BFS, DFS, and topological sorting require `O(V + E)` time with adjacency lists;
- Dijkstra with a binary heap requires `O((V + E) log V)` time;
- Bellman–Ford requires `O(VE)` time;
- Floyd–Warshall requires `O(V³)` time and `O(V²)` space;
- Kruskal requires `O(E log E)` time;
- Prim with an adjacency list and binary heap requires `O(E log V)` time;
- graph representation is part of algorithm analysis;
- auxiliary space should be distinguished from graph-storage space;
- Java collection choices may affect the final complexity;
- theoretical complexity and experimental benchmarking provide complementary evidence;
- the appropriate algorithm depends on graph properties, required operations, and implementation constraints.