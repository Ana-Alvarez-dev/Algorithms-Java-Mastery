# Graph Representations

## Algorithms Java Mastery

A graph is defined abstractly by its vertices and edges, but an implementation must decide how those elements will be stored in memory.

This decision is known as the **graph representation**.

The selected representation directly affects:

- memory consumption;
- edge lookup;
- neighbour traversal;
- insertion and deletion;
- algorithm complexity;
- implementation clarity.

The same logical graph may be represented in several ways without changing its mathematical meaning. However, the performance of algorithms such as Breadth-First Search, Depth-First Search, shortest paths, and minimum spanning trees depends strongly on the chosen representation.

This document introduces the principal graph representations used in Computer Science:

- edge list;
- adjacency matrix;
- adjacency list.

---

# 1. Purpose

The purpose of this document is to:

- distinguish the abstract graph from its physical representation;
- explain the principal graph storage strategies;
- compare their time and space complexity;
- identify the most appropriate representation according to graph characteristics;
- prepare the learner for graph traversal algorithms.

---

# 2. Abstract Graph and Concrete Representation

A graph is commonly defined as:

```text
G = (V, E)
```

where:

- `V` represents the set of vertices;
- `E` represents the set of edges.

Consider the undirected graph:

```text
      A
     / \
    B---C
     \
      D
```

Its sets may be expressed as:

```text
V = {A, B, C, D}
```

```text
E = {
    (A, B),
    (A, C),
    (B, C),
    (B, D)
}
```

This mathematical definition does not specify how the graph is stored.

The implementation must translate it into data structures that support the required operations.

```text
Abstract Graph
        ↓
Representation Strategy
        ↓
Arrays, Lists, Maps, Sets, or Objects
```

---

# 3. Representation Requirements

A graph representation should support the operations required by the application.

Common operations include:

- adding a vertex;
- adding an edge;
- removing a vertex;
- removing an edge;
- verifying whether an edge exists;
- obtaining the neighbours of a vertex;
- retrieving an edge weight;
- iterating through all vertices;
- iterating through all edges.

No representation is optimal for every operation.

The correct choice depends on:

- the number of vertices;
- the number of edges;
- whether the graph is sparse or dense;
- whether it is directed or undirected;
- whether edges contain weights;
- which operations are performed most frequently.

---

# 4. Edge List

An **edge list** stores the graph as a collection of edges.

For the graph:

```text
      A
     / \
    B---C
     \
      D
```

the representation may be:

```text
(A, B)
(A, C)
(B, C)
(B, D)
```

Each entry contains the two endpoints of an edge.

For a weighted graph, the edge may also store a weight:

```text
(A, B, 5)
(A, C, 2)
(B, C, 3)
(B, D, 7)
```

Conceptually:

```text
Edge
├── Source
├── Destination
└── Weight
```

---

# 5. Edge List Example

A weighted directed graph may contain:

```text
A ──5──▶ B
A ──2──▶ C
B ──4──▶ D
```

Its edge list is:

```text
[
    (A, B, 5),
    (A, C, 2),
    (B, D, 4)
]
```

The representation is simple because each edge appears explicitly.

---

# 6. Advantages of an Edge List

An edge list offers several benefits.

- simple to understand;
- simple to implement;
- efficient for iterating through every edge;
- suitable for algorithms that process edges globally;
- requires storage proportional to the number of edges.

It is particularly useful for algorithms such as:

- Kruskal's minimum spanning tree algorithm;
- edge sorting;
- importing or exporting graph data;
- processing graph datasets stored as relationships.

---

# 7. Limitations of an Edge List

An edge list is less efficient when the implementation frequently needs to:

- determine whether two vertices are adjacent;
- retrieve every neighbour of one vertex;
- remove a specific edge;
- traverse from vertex to vertex.

To locate the neighbours of a vertex, the implementation may need to inspect every edge.

```text
Neighbour lookup = O(E)
```

where `E` is the number of edges.

For this reason, an edge list is generally not the preferred representation for BFS or DFS.

---

# 8. Edge List Complexity

| Operation | Complexity |
|---|---:|
| Add edge | O(1)* |
| Check whether an edge exists | O(E) |
| Retrieve neighbours | O(E) |
| Iterate through all edges | O(E) |
| Space | O(V + E)** |

\* Assuming insertion at the end of a dynamic collection.

\** Vertices may be stored separately from the edge collection.

The exact cost depends on the concrete Java collections used.

---

# 9. Adjacency Matrix

An **adjacency matrix** represents a graph using a two-dimensional matrix.

For a graph with `V` vertices, the matrix contains:

```text
V rows
V columns
```

Each position represents a possible connection between two vertices.

For an unweighted graph:

```text
1 = edge exists
0 = edge does not exist
```

---

# 10. Adjacency Matrix Example

Consider:

```text
A ─── B
|     |
C ─── D
```

The vertices are:

```text
A, B, C, D
```

A possible adjacency matrix is:

```text
      A  B  C  D
   ┌────────────
A  │ 0  1  1  0
B  │ 1  0  0  1
C  │ 1  0  0  1
D  │ 0  1  1  0
```

The value at:

```text
matrix[A][B]
```

indicates whether an edge connects `A` and `B`.

---

# 11. Undirected Matrix Symmetry

In an undirected graph:

```text
(A, B) = (B, A)
```

Therefore, the matrix is symmetrical across its principal diagonal.

```text
matrix[A][B] = matrix[B][A]
```

Example:

```text
      A  B  C
   ┌─────────
A  │ 0  1  0
B  │ 1  0  1
C  │ 0  1  0
```

This symmetry reflects the bidirectional nature of undirected edges.

---

# 12. Directed Adjacency Matrix

In a directed graph, the matrix is not necessarily symmetrical.

Consider:

```text
A → B
B → C
C → A
```

The matrix may be:

```text
      A  B  C
   ┌─────────
A  │ 0  1  0
B  │ 0  0  1
C  │ 1  0  0
```

Here:

```text
matrix[A][B] = 1
```

but:

```text
matrix[B][A] = 0
```

because the reverse edge does not exist.

---

# 13. Weighted Adjacency Matrix

For a weighted graph, each matrix position stores the edge weight.

Example:

```text
A ──5── B
A ──2── C
B ──4── C
```

Possible representation:

```text
      A  B  C
   ┌─────────
A  │ 0  5  2
B  │ 5  0  4
C  │ 2  4  0
```

When no edge exists, the implementation requires a special representation.

Possible choices include:

```text
0
∞
null
special constant
```

The correct choice depends on whether zero is a valid edge weight.

---

# 14. Advantages of an Adjacency Matrix

An adjacency matrix provides:

- constant-time edge existence checks;
- simple implementation;
- direct access to edge weights;
- predictable memory layout;
- convenient representation for dense graphs.

Checking an edge requires:

```text
matrix[u][v]
```

Therefore:

```text
Edge lookup = O(1)
```

---

# 15. Limitations of an Adjacency Matrix

The matrix reserves space for every possible vertex pair, even when few edges exist.

Space requirement:

```text
O(V²)
```

For a graph with:

```text
10,000 vertices
```

the matrix requires:

```text
100,000,000 positions
```

even if the graph contains only a small number of edges.

Neighbour iteration also requires scanning an entire row:

```text
O(V)
```

This can be inefficient for sparse graphs.

---

# 16. Adjacency Matrix Complexity

| Operation | Complexity |
|---|---:|
| Add edge | O(1) |
| Remove edge | O(1) |
| Check whether an edge exists | O(1) |
| Retrieve all neighbours | O(V) |
| Add vertex | O(V²)* |
| Space | O(V²) |

\* A fixed matrix may require creating and copying a larger matrix when a vertex is added.

Adjacency matrices are most appropriate when:

- the graph is dense;
- the number of vertices is relatively stable;
- edge lookup is frequent.

---

# 17. Adjacency List

An **adjacency list** stores, for every vertex, a collection of its adjacent vertices.

For the graph:

```text
      A
     / \
    B---C
     \
      D
```

the adjacency list is:

```text
A → B, C
B → A, C, D
C → A, B
D → B
```

Each vertex owns a list of its neighbours.

---

# 18. Directed Adjacency List

For a directed graph:

```text
A → B
A → C
B → D
C → D
```

the adjacency list may be:

```text
A → B, C
B → D
C → D
D → empty
```

Only outgoing edges are normally stored.

If incoming-edge access is also required, the implementation may maintain a second structure.

```text
Outgoing adjacency
Incoming adjacency
```

This introduces additional memory but improves some operations.

---

# 19. Weighted Adjacency List

For a weighted graph, each neighbour entry also stores the edge weight.

Example:

```text
A ──5──▶ B
A ──2──▶ C
B ──4──▶ D
```

Representation:

```text
A → (B, 5), (C, 2)
B → (D, 4)
C → empty
D → empty
```

Conceptually:

```text
Adjacency Entry
├── Destination
└── Weight
```

---

# 20. Advantages of an Adjacency List

An adjacency list provides:

- memory proportional to actual graph size;
- efficient neighbour iteration;
- suitability for sparse graphs;
- natural support for BFS and DFS;
- flexible growth.

Space requirement:

```text
O(V + E)
```

Neighbour traversal requires examining only the actual adjacent vertices.

```text
O(degree(v))
```

for a vertex `v`.

---

# 21. Limitations of an Adjacency List

Checking whether a specific edge exists may require scanning a vertex's neighbour collection.

With a simple list:

```text
Edge lookup = O(degree(v))
```

For a high-degree vertex, this may be expensive.

The cost can be reduced by storing neighbours in a hash-based set.

However, this introduces:

- additional memory;
- more implementation complexity;
- different iteration characteristics.

The representation must therefore consider both graph-level and collection-level choices.

---

# 22. Adjacency List Complexity

| Operation | Complexity |
|---|---:|
| Add edge | O(1)* |
| Remove edge | O(degree(v)) |
| Check whether an edge exists | O(degree(v)) |
| Retrieve neighbours | O(1)** |
| Iterate through neighbours | O(degree(v)) |
| Space | O(V + E) |

\* Assuming insertion into a suitable neighbour collection.

\** Returning the neighbour collection reference is O(1); iterating through it depends on the number of neighbours.

For undirected graphs, each edge is generally stored twice:

```text
u → v
v → u
```

The total storage remains:

```text
O(V + E)
```

because constant factors are ignored in asymptotic analysis.

---

# 23. Representation Comparison

| Characteristic | Edge List | Adjacency Matrix | Adjacency List |
|---|---:|---:|---:|
| Space | O(V + E) | O(V²) | O(V + E) |
| Add edge | O(1) | O(1) | O(1) |
| Edge lookup | O(E) | O(1) | O(degree(v)) |
| Retrieve neighbours | O(E) | O(V) | O(degree(v)) |
| Iterate all edges | O(E) | O(V²) | O(V + E) |
| Best suited for | Edge-oriented processing | Dense graphs | Sparse graphs and traversal |

No representation is universally superior.

---

# 24. Sparse Graphs

A graph is considered **sparse** when the number of edges is much smaller than the maximum possible number.

Conceptually:

```text
E ≪ V²
```

Example:

```text
A ─── B ─── C ─── D ─── E
```

Only a small fraction of possible edges exists.

For sparse graphs, adjacency lists are usually preferred because they store only actual connections.

Typical sparse graphs include:

- road networks;
- software dependency graphs;
- web-link structures;
- many social networks;
- communication networks.

---

# 25. Dense Graphs

A graph is considered **dense** when it contains many of the possible edges between its vertices.

Conceptually:

```text
E approaches V²
```

Example:

```text
Every vertex is connected to many others
```

Adjacency matrices may be appropriate because:

- much of the matrix is used;
- edge lookup is constant time;
- implementation remains simple.

Dense graphs may appear in:

- fully connected cost models;
- small relationship networks;
- mathematical optimization problems;
- simulations with extensive interaction.

---

# 26. Directed and Undirected Storage

The graph direction affects storage.

## Undirected graph

For an edge:

```text
A ─── B
```

an adjacency list normally stores:

```text
A → B
B → A
```

An adjacency matrix stores:

```text
matrix[A][B] = true
matrix[B][A] = true
```

## Directed graph

For:

```text
A → B
```

the representation stores only:

```text
A → B
```

unless incoming adjacency is maintained separately.

Failing to handle this distinction correctly may alter the graph's meaning.

---

# 27. Vertex Identification

A representation must determine how vertices are identified.

Possible approaches include:

- integer indices;
- strings;
- domain objects;
- unique identifiers;
- enumerated values.

Example using integer indices:

```text
0 → A
1 → B
2 → C
3 → D
```

This mapping makes array-based representations practical.

For arbitrary objects, a map may associate each vertex with its neighbour collection.

Conceptually:

```text
Vertex
   ↓
Neighbour Collection
```

---

# 28. Java Representation with Arrays

An adjacency matrix may be represented using:

```java
boolean[][] adjacencyMatrix;
```

For weighted graphs:

```java
int[][] weights;
```

or:

```java
double[][] weights;
```

The representation must define clearly:

- which indices correspond to which vertices;
- how absent edges are represented;
- whether self-loops are permitted;
- whether the graph is directed.

---

# 29. Java Representation with Collections

An adjacency list may be represented conceptually as:

```text
Map<V, List<V>>
```

For example:

```text
Map<String, List<String>> adjacencyList;
```

A weighted version may use:

```text
Map<V, List<Edge<V>>>
```

where an edge contains:

```text
destination
weight
```

Alternative neighbour collections include:

- `ArrayList`;
- `LinkedList`;
- `HashSet`;
- `LinkedHashSet`;
- sorted sets.

The appropriate collection depends on required operations.

---

# 30. List or Set for Neighbours

Using a `List` allows:

- predictable iteration;
- efficient append;
- possible duplicate edges.

Using a `Set` allows:

- prevention of duplicate neighbours;
- faster average edge lookup with hash-based sets;
- additional memory overhead.

Comparison:

| Neighbour Collection | Duplicate Edges | Lookup |
|---|---:|---:|
| `List` | Possible | O(degree(v)) |
| `HashSet` | Prevented | O(1) average |
| `LinkedHashSet` | Prevented | O(1) average |
| Sorted Set | Prevented | O(log degree(v)) |

The representation should reflect the graph contract.

---

# 31. Edge Object Representation

Weighted or metadata-rich graphs commonly represent each edge as an object.

Conceptually:

```text
public record Edge<V>(
    V source,
    V destination,
    double weight
) {
}
```

Depending on the architecture, the source may be omitted from adjacency entries because the owning vertex already provides that information.

Conceptually:

```text
public record AdjacentVertex<V>(
    V destination,
    double weight
) {
}
```

This avoids redundant storage.

---

# 32. Mutable and Immutable Graphs

A graph may be designed as:

- mutable;
- immutable.

A mutable graph supports operations such as:

```text
addVertex
addEdge
removeVertex
removeEdge
```

An immutable graph is constructed once and then only queried.

Immutable representations may provide:

- simpler reasoning;
- safer sharing;
- easier concurrent access;
- stable traversal behaviour.

Mutable representations offer flexibility but require stronger invariant management.

---

# 33. Representation Invariants

Every graph implementation should preserve explicit invariants.

Possible invariants include:

- every edge endpoint corresponds to a stored vertex;
- duplicate edges follow the graph's contract;
- self-loops are either permitted or rejected consistently;
- undirected edges are represented symmetrically;
- edge weights satisfy required constraints;
- neighbour collections remain synchronized with vertex removal;
- the recorded edge count is accurate.

For an undirected adjacency list:

```text
v belongs to neighbours(u)
```

must imply:

```text
u belongs to neighbours(v)
```

unless the graph is temporarily being modified within a controlled operation.

---

# 34. Removing a Vertex

Vertex removal may be simple or expensive depending on the representation.

## Adjacency matrix

Removing a vertex may require:

- removing a row;
- removing a column;
- rebuilding or shifting the matrix;
- updating vertex-index mappings.

Possible complexity:

```text
O(V²)
```

## Adjacency list

Removing the vertex's own adjacency collection is straightforward.

However, incoming references from other vertices must also be removed.

Possible complexity without incoming indexes:

```text
O(V + E)
```

This operation illustrates why mutation requirements matter when choosing a representation.

---

# 35. Graph Traversal and Representation

BFS and DFS repeatedly obtain the neighbours of visited vertices.

With an adjacency list:

```text
Total traversal = O(V + E)
```

because only stored vertices and edges are examined.

With an adjacency matrix:

```text
Total traversal = O(V²)
```

because each visited vertex requires scanning a complete matrix row.

Therefore, the representation directly affects traversal complexity.

---

# 36. Edge-Oriented Algorithms

Some algorithms focus on processing all edges rather than repeatedly retrieving neighbours.

Examples include:

- Kruskal's algorithm;
- edge sorting;
- constraint processing;
- graph serialization.

An edge list may be especially appropriate because it provides direct iteration over the edge set.

However, some systems maintain multiple representations simultaneously when different operations are important.

---

# 37. Multiple Internal Representations

A graph implementation may maintain more than one structure.

Example:

```text
Adjacency List
        +
Edge List
```

This may improve:

- neighbour traversal;
- global edge iteration;
- incoming-edge lookup;
- repeated edge-existence checks.

However, additional representations introduce new responsibilities:

- more memory usage;
- synchronization between structures;
- more complex mutations;
- greater risk of inconsistent state.

Redundant representations should be adopted only when their benefits are justified.

---

# 38. Representation Selection Guide

Use an **edge list** when:

- algorithms process all edges directly;
- the implementation imports or exports edge records;
- neighbour lookup is uncommon;
- simplicity is important.

Use an **adjacency matrix** when:

- the graph is dense;
- the vertex set is relatively stable;
- constant-time edge lookup is required;
- the number of vertices is manageable.

Use an **adjacency list** when:

- the graph is sparse;
- traversal is frequent;
- memory efficiency matters;
- the graph grows dynamically;
- neighbours must be processed efficiently.

---

# 39. Example Selection Scenarios

## Road network

Characteristics:

- many cities;
- relatively few direct roads per city;
- frequent neighbour traversal.

Recommended:

```text
Adjacency List
```

## Small fully connected cost graph

Characteristics:

- few vertices;
- most vertex pairs connected;
- frequent cost lookup.

Recommended:

```text
Adjacency Matrix
```

## Kruskal's algorithm input

Characteristics:

- edges must be sorted by weight;
- global edge iteration is central.

Recommended:

```text
Edge List
```

The representation should follow the dominant operations of the problem.

---

# 40. Automated Testing Perspective

A graph representation should be verified through tests covering:

## Vertex operations

- adding a new vertex;
- rejecting or ignoring duplicates according to the contract;
- removing an existing vertex;
- removing an absent vertex.

## Edge operations

- adding directed edges;
- adding undirected edges;
- preventing invalid endpoints;
- storing weights correctly;
- preventing duplicates when required.

## Query operations

- detecting existing edges;
- rejecting absent edges;
- returning correct neighbours;
- preserving direction;
- retrieving correct weights.

## Boundary cases

- empty graph;
- isolated vertex;
- self-loop;
- disconnected components;
- parallel edges when permitted;
- removal of a highly connected vertex.

---

# 41. Common Representation Mistakes

### Treating a directed graph as undirected

Adding the reverse edge unintentionally changes the graph.

### Storing an undirected edge only once in an adjacency list

Neighbour traversal becomes inconsistent unless the implementation deliberately uses a different model.

### Using zero to represent absence when zero-weight edges are valid

The representation becomes ambiguous.

### Choosing an adjacency matrix for a very large sparse graph

Most allocated positions remain unused.

### Choosing an edge list for traversal-heavy algorithms

Neighbour discovery becomes unnecessarily expensive.

### Allowing external code to modify internal neighbour collections

Representation invariants may be violated.

### Forgetting to remove incoming edges with a vertex

Dangling relationships remain in the graph.

---

# 42. Engineering Perspective

Graph representation is an architectural decision.

Two implementations may model exactly the same graph while producing very different memory usage and algorithmic performance.

For example:

```text
Adjacency Matrix
        ↓
Fast edge lookup
High memory cost
```

```text
Adjacency List
        ↓
Efficient sparse traversal
Slower direct edge lookup
```

Professional software engineering requires selecting the representation according to the actual workload rather than habit.

The graph's structure, required operations, expected scale, mutation frequency, and clarity of implementation should all influence the decision.

---

# 43. Relationship with the Next Document

This document explained how graphs are stored.

The next document introduces **Breadth-First Search (BFS)**, which explores a graph level by level using a queue.

BFS depends on efficient neighbour retrieval, which is why adjacency lists are frequently used for sparse graph traversal.

The progression is:

```text
Graph Definition
        ↓
Graph Terminology
        ↓
Graph Representation
        ↓
Breadth-First Search
```

---

# 44. Key Takeaways

After completing this document, the learner should understand that:

- the mathematical definition of a graph is independent of its memory representation;
- edge lists store relationships directly;
- adjacency matrices represent every possible vertex pair;
- adjacency lists store only actual neighbours;
- adjacency matrices require `O(V²)` space;
- adjacency lists generally require `O(V + E)` space;
- edge lists are useful for edge-oriented processing;
- adjacency matrices are appropriate for dense graphs and frequent edge lookup;
- adjacency lists are generally appropriate for sparse graphs and traversal;
- directed and undirected graphs require different storage behaviour;
- Java collection choices influence the complexity of graph operations;
- representation invariants are essential for correctness;
- selecting a representation is an engineering decision based on graph structure and expected operations.