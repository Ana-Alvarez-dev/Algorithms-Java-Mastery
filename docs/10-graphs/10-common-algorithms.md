# Common Algorithms

## Algorithms Java Mastery

Graph algorithms solve problems involving relationships, connectivity, reachability, ordering, routing, and optimization.

The previous documents introduced the principal techniques of this module:

- Breadth-First Search;
- Depth-First Search;
- Topological Sorting;
- shortest-path algorithms;
- Minimum Spanning Tree algorithms.

This document connects those techniques with common problem patterns and practical software engineering applications.

The objective is not to introduce unrelated algorithms, but to help the learner recognize when a problem can be modeled as a graph and which previously studied strategy is appropriate.

---

# 1. Purpose

The purpose of this document is to:

- connect graph algorithms with common problem types;
- identify the graph model behind practical scenarios;
- compare appropriate algorithmic strategies;
- strengthen graph-problem recognition;
- relate academic concepts to backend and software engineering systems.

---

# 2. Recognizing Graph Problems

A problem may be suitable for graph modeling when it contains:

```text
Entities
        +
Relationships
```

Typical questions include:

- Can one entity reach another?
- What is the minimum-cost route?
- In what order should dependent tasks be processed?
- Are circular dependencies present?
- How many independent groups exist?
- How can all locations be connected at minimum total cost?
- Which entities are closely related?
- Which state transitions lead to a solution?

These questions usually correspond to vertices, edges, paths, components, or graph optimization problems.

---

# 3. Reachability

A reachability problem asks whether a path exists from one vertex to another.

Example:

```text
Can service A communicate with service D?
```

Graph model:

```text
Service = Vertex
Communication Link = Edge
```

Appropriate algorithms:

- BFS;
- DFS.

Conceptually:

```text
Start at Source
        ↓
Traverse Reachable Vertices
        ↓
Target Found?
```

Both algorithms can determine reachability.

The choice depends on additional requirements, such as shortest unweighted distance or depth-oriented exploration.

---

# 4. Connected Components

A connected-component problem identifies separate groups within an undirected graph.

Example:

```text
A ─── B ─── C

D ─── E

F
```

Components:

```text
{A, B, C}

{D, E}

{F}
```

Appropriate algorithms:

- repeated BFS;
- repeated DFS.

General process:

```text
Select an Unvisited Vertex
        ↓
Traverse Its Complete Component
        ↓
Assign Component Identifier
        ↓
Repeat
```

Applications include:

- network partition analysis;
- user-community grouping;
- isolated infrastructure detection;
- image-region analysis;
- clustering foundations.

---

# 5. Cycle Detection

Cycle detection determines whether a graph contains a path that returns to its starting vertex.

Appropriate strategy:

```text
Depth-First Search
```

For an undirected graph, the parent edge must be distinguished from a genuine cycle.

For a directed graph, DFS may use:

```text
UNDISCOVERED
ACTIVE
FINISHED
```

An edge toward an active vertex indicates a directed cycle.

Applications include:

- dependency validation;
- deadlock detection;
- recursive configuration checks;
- workflow verification;
- build-system analysis.

---

# 6. Dependency Ordering

A dependency-ordering problem requires arranging tasks so that every prerequisite appears before the task that depends on it.

Example:

```text
Compile Core
        ↓
Compile Service
        ↓
Package Application
```

Graph model:

```text
Task = Vertex
Dependency = Directed Edge
```

Appropriate algorithm:

```text
Topological Sorting
```

The graph must be a:

```text
Directed Acyclic Graph
```

Applications include:

- build systems;
- package managers;
- course prerequisites;
- deployment pipelines;
- database migrations;
- workflow orchestration.

---

# 7. Unweighted Shortest Paths

When every edge has the same cost, the shortest path is the path with the minimum number of edges.

Appropriate algorithm:

```text
Breadth-First Search
```

Example:

```text
A ─── B ─── D
 \         /
  └── C ──┘
```

BFS explores by distance levels:

```text
Distance 0
Distance 1
Distance 2
```

Applications include:

- minimum moves in a board game;
- social-network degrees of separation;
- shortest route through an unweighted maze;
- minimum number of dependency steps;
- state transitions with equal cost.

---

# 8. Non-Negative Weighted Shortest Paths

When edges have different non-negative costs, BFS is no longer sufficient.

Appropriate algorithm:

```text
Dijkstra's Algorithm
```

Example graph model:

```text
Router = Vertex
Connection = Edge
Latency = Weight
```

Dijkstra repeatedly processes the vertex with the smallest known distance and relaxes its outgoing edges.

Applications include:

- network routing;
- navigation;
- delivery planning;
- latency optimization;
- game-map movement;
- resource-cost analysis.

---

# 9. Shortest Paths with Negative Edges

Some graphs may contain negative edge weights.

Appropriate algorithm:

```text
Bellman–Ford
```

Bellman–Ford:

- supports negative edges;
- computes single-source shortest paths;
- detects reachable negative cycles.

Applications may include:

- financial transformation models;
- constraint systems;
- theoretical optimization problems;
- graph models where transitions may reduce accumulated cost.

Dijkstra must not be used when negative edges are present.

---

# 10. All-Pairs Shortest Paths

Some systems require the minimum distance between every pair of vertices.

Appropriate algorithm:

```text
Floyd–Warshall
```

The result is a distance matrix.

Applications include:

- small transportation networks;
- repeated route queries;
- network analysis;
- relationship-distance analysis;
- system-wide cost comparisons.

Its cubic time complexity makes it more appropriate for relatively small graphs or situations with many pairwise queries.

---

# 11. Minimum-Cost Network Design

A network-design problem may require connecting every vertex while minimizing total connection cost.

Appropriate algorithms:

- Kruskal's algorithm;
- Prim's algorithm.

Graph model:

```text
Location = Vertex
Possible Connection = Edge
Construction Cost = Weight
```

The objective is not to minimize every individual route.

Instead:

```text
Connect All Vertices
        +
Avoid Cycles
        +
Minimize Total Edge Weight
```

Applications include:

- network cabling;
- road construction;
- electrical infrastructure;
- pipeline design;
- telecommunications planning.

---

# 12. Task Scheduling

Tasks may contain prerequisite relationships.

Example:

```text
Validate Input
        ↓
Process Data
        ↓
Generate Report
```

A directed acyclic graph represents the workflow.

Appropriate techniques include:

- topological sorting for valid execution order;
- Kahn's algorithm for identifying currently available tasks;
- critical-path analysis in more advanced scheduling models.

When multiple tasks have in-degree zero, they may be candidates for parallel execution.

---

# 13. Package and Module Dependencies

Software systems frequently contain dependencies between packages or modules.

Graph model:

```text
Module = Vertex
Dependency = Directed Edge
```

Graph algorithms can answer:

- Is a dependency cycle present?
- In what order should modules be built?
- Which modules depend on a selected component?
- Which dependencies are reachable?
- Which modules can be processed independently?

Appropriate algorithms:

- DFS for cycle detection;
- topological sorting for build order;
- BFS or DFS for dependency reachability.

---

# 14. Web Crawling

The web can be modeled as a directed graph.

```text
Web Page = Vertex
Hyperlink = Directed Edge
```

A crawler begins from one or more pages and visits reachable pages.

Possible traversal strategies:

- BFS for level-oriented discovery;
- DFS for deep exploration.

A visited structure is essential because pages may reference one another cyclically.

Engineering concerns include:

- duplicate URLs;
- crawl depth;
- rate limiting;
- unreachable pages;
- external-domain rules;
- memory usage.

---

# 15. Social Networks

A social network may be represented as:

```text
Person = Vertex
Relationship = Edge
```

The graph may be:

- undirected for mutual relationships;
- directed for followers;
- weighted for interaction strength.

Common problems include:

| Problem | Possible Technique |
|---|---|
| Degrees of separation | BFS |
| Reachability | BFS or DFS |
| Communities | Connected components or clustering |
| Influence paths | Directed traversal |
| Mutual relationships | Adjacency analysis |
| Recommendations | Common-neighbour analysis |

Large social graphs generally require sparse representations such as adjacency lists.

---

# 16. Recommendation Systems

A simple recommendation model may use a bipartite graph.

```text
Users
        ↕
Products
```

One vertex group represents users, while another represents products.

Edges may represent:

- purchases;
- views;
- ratings;
- preferences.

Possible graph-based reasoning includes:

- finding users with similar connections;
- identifying products connected to related users;
- counting shared neighbours;
- exploring multi-step relationships.

Production recommendation systems are more complex, but graph modeling provides an important conceptual foundation.

---

# 17. Network Routing

Computer networks are naturally represented as weighted graphs.

```text
Router = Vertex
Network Link = Edge
```

Weights may represent:

- latency;
- cost;
- congestion;
- bandwidth penalty;
- administrative preference.

Possible algorithms:

- Dijkstra for non-negative routing costs;
- Bellman–Ford for models that permit more general edge conditions;
- BFS when every connection is treated equally.

The selected weight determines what the algorithm considers the best route.

---

# 18. Maze Solving

A maze may be converted into a graph.

```text
Valid Position = Vertex
Legal Movement = Edge
```

Use BFS when:

```text
Minimum Number of Moves Is Required
```

Use DFS when:

```text
Any Valid Path Is Sufficient
```

The graph may be created explicitly or explored implicitly by generating valid neighbouring positions during execution.

---

# 19. State-Space Exploration

Many problems can be represented as states and transitions.

```text
State = Vertex
Valid Transition = Edge
```

Examples include:

- puzzles;
- game configurations;
- workflow states;
- protocol states;
- configuration search;
- robotic movement.

BFS is useful when each transition has equal cost and the minimum number of steps is required.

DFS and backtracking are useful when exploring possible solutions deeply and reversing decisions after failure.

---

# 20. Compiler and Expression Processing

Compilers use graph-like structures for:

- dependency analysis;
- control flow;
- data flow;
- syntax relationships;
- optimization.

A control-flow graph may represent:

```text
Basic Block = Vertex
Possible Control Transfer = Edge
```

Algorithms may determine:

- reachability;
- cycles;
- execution paths;
- strongly connected regions;
- ordering constraints.

This demonstrates that graph algorithms are essential beyond explicit network applications.

---

# 21. File and Directory Relationships

A normal directory hierarchy is usually modeled as a tree.

However, file systems may also contain graph-like relationships through:

- symbolic links;
- shortcuts;
- shared references;
- dependency links.

Traversal must account for possible cycles.

A visited structure prevents repeated processing when the structure is no longer a strict tree.

---

# 22. Version-Control History

A version-control history may be modeled as a directed acyclic graph.

```text
Commit = Vertex
Parent Relationship = Directed Edge
```

Branches and merges create a structure more general than a simple linked list.

Graph operations can support:

- ancestor discovery;
- reachability;
- merge-base analysis;
- history traversal;
- dependency ordering.

The absence of cycles is essential to the commit-history model.

---

# 23. Database Relationships

Relational data may be interpreted as a graph when entities contain interconnected references.

```text
Record = Vertex
Relationship = Edge
```

Graph reasoning may support:

- dependency analysis;
- traversal of related records;
- hierarchy processing;
- path queries;
- impact analysis.

Graph databases make this model explicit by storing vertices and edges as primary concepts.

---

# 24. Strongly Connected Components

In directed graphs, a strongly connected component contains vertices that can all reach one another.

Applications include:

- identifying cyclic dependency groups;
- analysing mutual reachability;
- condensing a graph into a DAG;
- detecting tightly connected regions.

Classical algorithms include:

- Kosaraju's algorithm;
- Tarjan's algorithm.

These algorithms extend DFS concepts introduced earlier.

They may be studied in a future advanced graph module.

---

# 25. Bipartite Graph Problems

A graph is bipartite when its vertices can be divided into two groups such that every edge connects vertices from different groups.

Common applications include:

- user-product graphs;
- student-course assignments;
- worker-task matching;
- resource allocation.

BFS or DFS can test bipartiteness by assigning alternating groups or colours.

Conceptually:

```text
Group A
        ↕
Group B
```

An edge connecting two vertices assigned to the same group indicates that the graph is not bipartite.

---

# 26. Graph Colouring

Graph colouring assigns labels or colours to vertices subject to constraints.

A common condition is:

```text
Adjacent Vertices Must Have Different Colours
```

Applications include:

- examination scheduling;
- register allocation;
- frequency assignment;
- map colouring;
- conflict-free resource planning.

General graph colouring can be computationally difficult.

Greedy strategies may provide practical solutions without always producing an optimal colouring.

---

# 27. Articulation Points and Bridges

An **articulation point** is a vertex whose removal increases the number of connected components.

A **bridge** is an edge whose removal disconnects part of the graph.

Applications include:

- infrastructure vulnerability analysis;
- network fault detection;
- identifying critical services;
- transportation resilience;
- communication planning.

These problems are commonly solved using advanced DFS information such as discovery times and low-link values.

---

# 28. Common Algorithm Selection Table

| Problem | Graph Condition | Recommended Technique |
|---|---|---|
| Reachability | Any graph | BFS or DFS |
| Connected components | Undirected graph | Repeated BFS or DFS |
| Cycle detection | Directed or undirected | DFS with appropriate state |
| Minimum edge-count path | Unweighted graph | BFS |
| Non-negative shortest path | Weighted graph | Dijkstra |
| Negative-edge shortest path | Weighted graph | Bellman–Ford |
| All-pairs shortest paths | Weighted graph | Floyd–Warshall |
| Dependency ordering | DAG | Topological sorting |
| Minimum total network cost | Weighted undirected graph | Kruskal or Prim |
| Bipartite validation | Undirected graph | BFS or DFS colouring |
| Deep state exploration | State graph | DFS or backtracking |

---

# 29. Problem-Solving Method

When facing a graph problem, use the following sequence.

```text
Identify Entities
        ↓
Identify Relationships
        ↓
Determine Edge Direction
        ↓
Determine Whether Weights Exist
        ↓
Identify Graph Constraints
        ↓
Define Required Result
        ↓
Select Representation
        ↓
Select Algorithm
        ↓
Verify Preconditions
        ↓
Analyse Complexity
```

This process avoids selecting an algorithm before understanding the graph model.

---

# 30. Questions to Ask Before Choosing an Algorithm

Ask:

- Is the graph directed or undirected?
- Is it weighted or unweighted?
- Can weights be negative?
- Can the graph contain cycles?
- Is it connected?
- Is it sparse or dense?
- Is one source involved or every pair?
- Is any valid path sufficient?
- Is the shortest path required?
- Must every vertex be connected at minimum total cost?
- Is deterministic output required?
- Can the graph change during execution?

These answers determine the correct solution strategy.

---

# 31. Representation Selection

Use an adjacency list when:

- the graph is sparse;
- neighbour traversal is frequent;
- BFS or DFS is central.

Use an adjacency matrix when:

- the graph is dense;
- constant-time edge lookup is important;
- the vertex count is manageable.

Use an edge list when:

- algorithms process edges globally;
- Kruskal's algorithm is used;
- importing or sorting relationships is central.

Representation is part of the algorithmic decision.

---

# 32. Combining Algorithms

Complex graph problems may require several algorithms.

Example dependency platform:

```text
DFS
        ↓
Detect Cycles
```

```text
Topological Sorting
        ↓
Produce Build Order
```

```text
BFS
        ↓
Measure Dependency Distance
```

Example infrastructure platform:

```text
Minimum Spanning Tree
        ↓
Design Minimum-Cost Network
```

```text
Dijkstra
        ↓
Find Efficient Routes Within Network
```

Each algorithm solves a different question.

---

# 33. Common Mistakes

## Treating every relationship problem as a tree

Graphs may contain cycles, multiple parents, and disconnected components.

## Selecting BFS or DFS without defining the objective

Both traverse graphs, but they provide different guarantees.

## Using BFS for unequal weighted edges

It minimizes edge count, not weighted cost.

## Using Dijkstra with negative edges

Its correctness conditions are violated.

## Applying topological sorting to a cyclic graph

No valid dependency order exists.

## Confusing MST and shortest paths

One minimizes total network cost; the other minimizes route cost.

## Ignoring disconnected components

One traversal may not process the complete graph.

## Choosing a representation by habit

The graph's density and required operations should determine the representation.

---

# 34. Automated Testing Perspective

Graph algorithms should be tested according to mathematical properties rather than only one expected sequence.

Examples:

## Traversal

Verify:

- every reachable vertex appears;
- no vertex appears twice;
- unreachable vertices do not appear.

## Topological sorting

Verify:

```text
For every edge u → v:
position(u) < position(v)
```

## Shortest paths

Verify:

- reconstructed path uses real edges;
- recorded distance equals path cost;
- no cheaper route exists in the test graph.

## Minimum Spanning Tree

Verify:

- all vertices are connected;
- no cycle exists;
- exactly `V - 1` edges are selected;
- total weight is minimal.

This approach produces stronger tests than checking one incidental output order.

---

# 35. Engineering Perspective

Graph algorithms are valuable because they transform relationship-based problems into precise computational models.

In professional software engineering, the difficult step is often not implementing BFS, DFS, Dijkstra, or Prim. The greater challenge is correctly defining:

- what each vertex represents;
- what each edge represents;
- whether direction matters;
- what a weight means;
- which property must be optimized;
- which assumptions are valid.

A correct graph model simplifies algorithm selection.

An incorrect model can make even a correctly implemented algorithm produce meaningless results.

---

# 36. Relationship with the Next Document

This document connected the algorithms of the module with common problem patterns.

The next document examines **Common Mistakes**, focusing on errors involving:

- graph modeling;
- representation choices;
- traversal state;
- algorithm preconditions;
- complexity assumptions;
- Java collection usage.

The objective is to prevent both conceptual and implementation defects.

---

# 37. Key Takeaways

After completing this document, the learner should understand that:

- graph algorithms solve relationship-based problems;
- BFS and DFS support reachability and component discovery;
- BFS finds shortest paths in unweighted graphs;
- DFS supports cycles, dependencies, and exhaustive exploration;
- topological sorting orders directed acyclic dependencies;
- Dijkstra solves non-negative weighted shortest paths;
- Bellman–Ford supports negative edges;
- Floyd–Warshall solves all-pairs shortest paths;
- Kruskal and Prim minimize total network connection cost;
- graph modeling must precede algorithm selection;
- representation, graph properties, and required output determine the appropriate strategy;
- many backend, network, compiler, scheduling, and infrastructure problems are graph problems in disguise.