# Graph Terminology

## Algorithms Java Mastery

Before studying graph representations and algorithms, it is necessary to establish a precise vocabulary.

Graphs can model many different structures, including computer networks, social relationships, transportation routes, software dependencies, and communication systems. Although these applications are different, they are described using the same fundamental concepts from graph theory.

Understanding this terminology is essential for interpreting graph algorithms correctly, analysing their complexity, and communicating technical decisions clearly.

---

# 1. Purpose

The purpose of this document is to:

- define the principal concepts used in graph theory;
- explain the relationships between vertices and edges;
- distinguish common graph classifications;
- establish the vocabulary required for the remaining documents in this module.

---

# 2. Graph

A **graph** is a mathematical and computational structure composed of a set of vertices and a set of edges.

It is commonly represented as:

```text
G = (V, E)
```

where:

- `V` is the set of vertices;
- `E` is the set of edges.

Example:

```text
V = {A, B, C, D}

E = {(A, B), (A, C), (B, D)}
```

Graphically:

```text
      A
     / \
    B   C
    |
    D
```

---

# 3. Vertex

A **vertex**, also called a **node**, represents an entity in a graph.

Depending on the problem, a vertex may represent:

- a person;
- a city;
- a computer;
- a web page;
- a software module;
- a task;
- a database record.

Example:

```text
V = {A, B, C}
```

The graph contains three vertices.

The terms **vertex** and **node** are often used interchangeably, although academic graph theory commonly prefers **vertex**.

---

# 4. Edge

An **edge** represents a relationship or connection between two vertices.

Example:

```text
(A, B)
```

This edge connects vertex `A` with vertex `B`.

Graphically:

```text
A ─── B
```

An edge may represent:

- a friendship;
- a road;
- a network connection;
- a dependency;
- a communication channel;
- a transition between states.

---

# 5. Adjacent Vertices

Two vertices are **adjacent** when an edge directly connects them.

Example:

```text
A ─── B ─── C
```

In this graph:

- `A` is adjacent to `B`;
- `B` is adjacent to `A` and `C`;
- `A` is not adjacent to `C`.

Adjacency refers only to direct connections.

---

# 6. Incident Edge

An edge is **incident** to the vertices it connects.

For the edge:

```text
(A, B)
```

the edge is incident to:

- vertex `A`;
- vertex `B`.

This concept describes the relationship between an edge and its endpoints.

---

# 7. Directed Graph

A **directed graph**, or **digraph**, contains edges with a defined direction.

A directed edge is commonly represented as:

```text
A → B
```

This means that the relationship goes from `A` to `B`.

It does not necessarily imply:

```text
B → A
```

Example:

```text
A → B → C
```

Directed graphs are commonly used to represent:

- software dependencies;
- web links;
- task prerequisites;
- one-way roads;
- state transitions;
- message flows.

---

# 8. Undirected Graph

An **undirected graph** contains edges without an assigned direction.

Example:

```text
A ─── B
```

The connection can be interpreted in both directions.

Conceptually:

```text
A is connected to B

and

B is connected to A
```

Undirected graphs are commonly used to represent:

- mutual friendships;
- two-way roads;
- physical network connections;
- collaboration relationships.

---

# 9. Weighted Graph

A **weighted graph** associates a numerical value with each edge.

Example:

```text
A ──5── B
```

The weight `5` may represent:

- distance;
- travel time;
- financial cost;
- bandwidth;
- risk;
- resource consumption.

A weighted graph can be directed or undirected.

Example:

```text
A ──4── B
 \      /
  7    2
   \  /
    C
```

Weighted graphs are fundamental for shortest-path and minimum-spanning-tree algorithms.

---

# 10. Unweighted Graph

An **unweighted graph** does not assign an explicit numerical cost to its edges.

Example:

```text
A ─── B ─── C
```

All edges are treated as equivalent connections.

In shortest-path problems involving unweighted graphs, each edge is commonly interpreted as having the same cost.

---

# 11. Degree

The **degree** of a vertex in an undirected graph is the number of edges incident to it.

Example:

```text
    B
    |
A ─ C ─ D
```

The degree of `C` is:

```text
degree(C) = 3
```

The degrees of the remaining vertices are:

```text
degree(A) = 1
degree(B) = 1
degree(D) = 1
```

A self-loop may require special consideration because it connects a vertex to itself.

---

# 12. In-Degree and Out-Degree

In directed graphs, degree is divided into two concepts.

## In-degree

The **in-degree** of a vertex is the number of directed edges entering it.

## Out-degree

The **out-degree** of a vertex is the number of directed edges leaving it.

Example:

```text
A → C ← B
    |
    ↓
    D
```

For vertex `C`:

```text
in-degree(C) = 2
out-degree(C) = 1
```

These concepts are especially important in dependency analysis and topological sorting.

---

# 13. Path

A **path** is a sequence of vertices in which every consecutive pair is connected by an edge.

Example:

```text
A → B → C → D
```

A path from `A` to `D` is:

```text
A, B, C, D
```

The existence of a path indicates that one vertex is reachable from another.

---

# 14. Path Length

The **length of a path** in an unweighted graph is commonly defined as the number of edges it contains.

Example:

```text
A → B → C → D
```

The path contains three edges.

Therefore:

```text
Path length = 3
```

In a weighted graph, the path cost may instead be calculated as the sum of its edge weights.

Example:

```text
A ──2── B ──5── C
```

Total path cost:

```text
2 + 5 = 7
```

Path length and path cost should not be confused.

---

# 15. Simple Path

A **simple path** is a path that does not repeat vertices.

Example:

```text
A → B → C → D
```

is a simple path when all vertices are distinct.

A path that returns to a previously visited vertex is not simple.

---

# 16. Cycle

A **cycle** is a path that begins and ends at the same vertex.

Example:

```text
A → B → C
↑       |
└───────┘
```

The sequence:

```text
A → B → C → A
```

forms a cycle.

Cycles are important in problems involving:

- dependency validation;
- deadlock detection;
- network analysis;
- topological sorting;
- recursive relationships.

---

# 17. Acyclic Graph

An **acyclic graph** contains no cycles.

Example:

```text
A → B → C
    |
    ↓
    D
```

No directed path returns to its starting vertex.

A directed acyclic graph is commonly called a:

```text
DAG — Directed Acyclic Graph
```

DAGs are widely used to represent:

- task dependencies;
- build systems;
- course prerequisites;
- workflow execution;
- version histories.

---

# 18. Connected Graph

An undirected graph is **connected** when a path exists between every pair of vertices.

Example:

```text
A ─── B ─── C
      |
      D
```

Every vertex can be reached from every other vertex.

A connected graph forms a single structural component.

---

# 19. Disconnected Graph

A graph is **disconnected** when at least one pair of vertices has no path connecting them.

Example:

```text
A ─── B

C ─── D
```

This graph contains two separate components.

Traversal algorithms must account for disconnected graphs because starting from one vertex may not visit every vertex in the complete structure.

---

# 20. Connected Component

A **connected component** is a maximal connected subgraph of an undirected graph.

Example:

```text
Component 1

A ─── B ─── C
```

```text
Component 2

D ─── E
```

Each component is internally connected, but no edge connects the two components.

---

# 21. Strong Connectivity

A directed graph is **strongly connected** when every vertex is reachable from every other vertex while respecting edge direction.

Example:

```text
A → B
↑   ↓
└── C
```

If directed paths exist in both directions between all vertices, the graph is strongly connected.

A **strongly connected component** is a maximal portion of a directed graph with this property.

---

# 22. Weak Connectivity

A directed graph is **weakly connected** when it becomes connected after ignoring the direction of its edges.

Example:

```text
A → B ← C
```

The graph may not be strongly connected, but its vertices belong to one connected structure when direction is ignored.

---

# 23. Self-Loop

A **self-loop** is an edge connecting a vertex to itself.

Example:

```text
A ↺
```

Formally:

```text
(A, A)
```

Self-loops may represent:

- recursive dependencies;
- repeated states;
- transitions that preserve the current state.

Some graph models allow self-loops, while others prohibit them.

---

# 24. Parallel Edges

**Parallel edges** are multiple edges connecting the same pair of vertices.

Example:

```text
A ═══ B
```

A graph that permits parallel edges is commonly called a **multigraph**.

Parallel edges may represent multiple:

- routes;
- communication channels;
- transactions;
- relationships between the same entities.

---

# 25. Simple Graph

A **simple graph** generally contains:

- no self-loops;
- no parallel edges.

Each pair of vertices can therefore be connected by at most one edge.

The exact definition may vary slightly depending on whether the graph is directed or undirected.

---

# 26. Subgraph

A **subgraph** is formed from a subset of a graph's vertices and edges.

If:

```text
G = (V, E)
```

then a subgraph contains:

```text
V' ⊆ V
E' ⊆ E
```

Example:

Original graph:

```text
A ─── B ─── C
      |
      D
```

Possible subgraph:

```text
A ─── B
      |
      D
```

Subgraphs allow algorithms to analyse specific portions of a larger structure.

---

# 27. Complete Graph

A **complete graph** is a simple undirected graph in which every pair of distinct vertices is connected by an edge.

Example with four vertices:

```text
A ─── B
|\   /|
| \ / |
| / \ |
|/   \|
C ─── D
```

A complete graph with `n` vertices is commonly denoted:

```text
Kₙ
```

The number of edges is:

```text
n(n - 1) / 2
```

Complete graphs are highly dense.

---

# 28. Sparse Graph

A **sparse graph** contains relatively few edges compared with the maximum possible number.

Example:

```text
A ─── B ─── C ─── D
```

Sparse graphs are common in:

- road networks;
- dependency systems;
- social connections;
- web-link structures.

Adjacency lists are generally appropriate for sparse graphs.

---

# 29. Dense Graph

A **dense graph** contains many of the edges that could potentially exist between its vertices.

Example:

```text
Every vertex connected to many others
```

Dense graphs may be more suitable for adjacency-matrix representation because many matrix positions correspond to actual edges.

The distinction between sparse and dense graphs influences both memory usage and algorithm selection.

---

# 30. Tree as a Graph

A tree can be defined as an undirected graph that is:

- connected;
- acyclic.

Example:

```text
      A
     / \
    B   C
       / \
      D   E
```

A tree with `V` vertices contains exactly:

```text
V - 1 edges
```

Trees are therefore specialized graphs with stricter structural properties.

---

# 31. Forest

A **forest** is an acyclic undirected graph that may contain multiple connected components.

Conceptually:

```text
Tree 1        Tree 2

  A             D
 / \             \
B   C             E
```

Removing an edge from a tree may divide it into a forest.

---

# 32. Reachability

A vertex `v` is **reachable** from a vertex `u` when a valid path exists from `u` to `v`.

In an undirected graph, connectivity is symmetric.

In a directed graph:

```text
u can reach v
```

does not necessarily imply:

```text
v can reach u
```

Reachability is one of the principal questions solved by BFS and DFS.

---

# 33. Source and Destination

In a directed edge:

```text
u → v
```

`u` is the:

```text
source
```

and `v` is the:

```text
destination
```

These terms are also used in path-finding problems.

Example:

```text
Find the shortest path from source A to destination D.
```

---

# 34. Neighbourhood

The **neighbours** of a vertex are the vertices directly connected to it.

Example:

```text
    B
    |
A ─ C ─ D
```

The neighbours of `C` are:

```text
A, B, D
```

In directed graphs, it may be necessary to distinguish:

- incoming neighbours;
- outgoing neighbours.

---

# 35. Graph Order and Size

The **order** of a graph is the number of vertices.

```text
Order = |V|
```

The **size** of a graph is the number of edges.

```text
Size = |E|
```

For example:

```text
V = {A, B, C, D}
E = {(A, B), (B, C), (C, D)}
```

Therefore:

```text
|V| = 4
|E| = 3
```

In algorithm analysis, `V` and `E` are commonly used to express graph complexity.

---

# 36. Graph Classification Summary

| Classification | Meaning |
|---|---|
| Directed | Edges have direction |
| Undirected | Edges have no direction |
| Weighted | Edges contain costs or values |
| Unweighted | Edges are treated equally |
| Connected | Every vertex is reachable from every other |
| Disconnected | Multiple separate components exist |
| Cyclic | Contains at least one cycle |
| Acyclic | Contains no cycles |
| Sparse | Contains relatively few edges |
| Dense | Contains many possible edges |
| Simple | Contains no self-loops or parallel edges |
| Complete | Every pair of vertices is connected |

A graph may belong to several classifications simultaneously.

For example:

```text
Directed
Weighted
Acyclic
Sparse
```

---

# 37. Engineering Perspective

Precise graph terminology is necessary because small differences in graph properties can change which algorithms are valid.

For example:

- topological sorting requires a directed acyclic graph;
- Dijkstra's algorithm requires appropriate edge-weight conditions;
- minimum spanning trees apply to weighted undirected graphs;
- connectivity has different meanings in directed and undirected graphs;
- representation efficiency depends on whether the graph is sparse or dense.

Before selecting or implementing an algorithm, the graph model must therefore be defined clearly.

---

# 38. Relationship with the Next Document

This document established the vocabulary required to describe graphs.

The next document introduces **graph representations**, explaining how vertices and edges can be stored in memory using:

- edge lists;
- adjacency matrices;
- adjacency lists.

The selected representation directly influences memory usage, traversal efficiency, and algorithm complexity.

---

# 39. Key Takeaways

After completing this document, the learner should understand that:

- vertices represent entities and edges represent relationships;
- graphs may be directed, undirected, weighted, or unweighted;
- degree measures the number of connections associated with a vertex;
- paths describe valid sequences of connected vertices;
- cycles return to their starting vertex;
- connected components identify independent regions of a graph;
- DAGs represent directed relationships without cycles;
- sparse and dense graphs require different representation strategies;
- trees are connected, acyclic graphs;
- `|V|` and `|E|` represent the number of vertices and edges;
- precise graph classification is essential before selecting an algorithm or implementation.