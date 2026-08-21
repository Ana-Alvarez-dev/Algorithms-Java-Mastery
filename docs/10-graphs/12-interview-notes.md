# Interview Notes

## Algorithms Java Mastery

Graph-related questions are common in technical interviews because they evaluate several abilities at the same time:

- problem modeling;
- data-structure selection;
- traversal reasoning;
- complexity analysis;
- correctness arguments;
- Java collection usage;
- communication of engineering trade-offs.

Interviewers usually do not expect only a memorized implementation of BFS, DFS, Dijkstra, or Prim. They also evaluate whether the candidate can identify the graph model, verify the algorithm's preconditions, explain its complexity, and justify why a particular strategy is appropriate.

This document summarizes the most important concepts, comparisons, problem patterns, and Java considerations developed throughout the **Graphs** module.

---

# 1. Purpose

The purpose of this document is to:

- review the essential graph concepts;
- summarize the principal graph algorithms;
- compare their use cases and complexities;
- identify common technical-interview questions;
- provide a structured checklist for problem solving;
- reinforce clear engineering communication.

---

# 2. Core Graph Definition

A graph is commonly represented as:

```text
G = (V, E)
```

where:

- `V` is the set of vertices;
- `E` is the set of edges.

Vertices represent entities.

Edges represent relationships between those entities.

Example:

```text
Person = Vertex
Friendship = Edge
```

```text
City = Vertex
Road = Edge
```

```text
Task = Vertex
Dependency = Directed Edge
```

A strong interview answer begins by explaining what the vertices and edges represent in the problem.

---

# 3. Graph Classifications

Before selecting an algorithm, identify the graph's properties.

## Directed or Undirected

Directed:

```text
A → B
```

does not imply:

```text
B → A
```

Undirected:

```text
A ─── B
```

represents a bidirectional relationship.

---

## Weighted or Unweighted

Weighted:

```text
A ──5── B
```

The value may represent distance, cost, time, or another metric.

Unweighted:

```text
A ─── B
```

All edges are treated equally.

---

## Cyclic or Acyclic

A cyclic graph contains a path that returns to its starting vertex.

A Directed Acyclic Graph is commonly called a:

```text
DAG
```

DAGs are fundamental for dependency ordering.

---

## Connected or Disconnected

A connected undirected graph contains a path between every pair of vertices.

A disconnected graph contains multiple components.

One traversal from a single source does not necessarily visit the complete graph.

---

## Sparse or Dense

Sparse:

```text
E ≪ V²
```

Dense:

```text
E approaches V²
```

Graph density influences representation and performance.

---

# 4. Essential Terminology

The following terms should be clearly understood.

| Term | Meaning |
|---|---|
| Vertex | Entity represented in the graph |
| Edge | Relationship between vertices |
| Adjacent vertices | Vertices connected directly |
| Degree | Number of incident edges in an undirected graph |
| In-degree | Number of incoming edges |
| Out-degree | Number of outgoing edges |
| Path | Sequence of connected vertices |
| Cycle | Path that returns to its starting vertex |
| Component | Connected region of a graph |
| Reachability | Existence of a path between vertices |
| Source | Starting vertex or edge origin |
| Destination | Target vertex or edge endpoint |
| DAG | Directed Acyclic Graph |

---

# 5. Graph Representations

The three principal representations are:

- edge list;
- adjacency matrix;
- adjacency list.

## Edge List

Stores edges directly.

```text
(A, B)
(A, C)
(B, D)
```

Suitable for:

- edge-oriented algorithms;
- Kruskal's algorithm;
- graph import and export.

Typical space:

```text
O(V + E)
```

---

## Adjacency Matrix

Uses a `V × V` matrix.

Suitable for:

- dense graphs;
- constant-time edge lookup;
- stable and manageable vertex sets.

Space:

```text
O(V²)
```

Edge lookup:

```text
O(1)
```

Neighbour traversal:

```text
O(V)
```

---

## Adjacency List

Stores each vertex with its neighbours.

```text
A → B, C
B → A, D
C → A
D → B
```

Suitable for:

- sparse graphs;
- BFS;
- DFS;
- most traversal-oriented algorithms.

Space:

```text
O(V + E)
```

---

# 6. Common Representation Question

### When would you use an adjacency matrix instead of an adjacency list?

A strong answer should mention:

- the graph is dense;
- the number of vertices is manageable;
- frequent constant-time edge checks are required;
- `O(V²)` memory is acceptable.

An adjacency list is generally preferable for sparse graphs and efficient neighbour traversal.

---

# 7. Breadth-First Search

BFS explores vertices level by level.

Primary data structure:

```text
Queue
```

General behaviour:

```text
Source
        ↓
Immediate Neighbours
        ↓
Next Distance Level
```

With an adjacency list:

```text
Time = O(V + E)

Additional Space = O(V)
```

---

# 8. When to Use BFS

Use BFS for:

- reachability;
- connected components;
- level-order traversal;
- shortest paths in unweighted graphs;
- minimum number of transitions;
- degrees of separation;
- equal-cost maze navigation.

Key guarantee:

> BFS discovers vertices in nondecreasing distance from the source.

---

# 9. Frequent BFS Question

### Why does BFS find shortest paths in an unweighted graph?

Because BFS processes vertices level by level.

All vertices at distance `d` from the source are discovered before vertices at distance `d + 1`.

Therefore, the first time a vertex is discovered, the path used contains the minimum possible number of edges.

---

# 10. Important BFS Detail

Vertices should normally be marked as visited when they are enqueued.

Correct:

```text
Discover
        ↓
Mark
        ↓
Enqueue
```

Marking only when dequeued may allow the same vertex to enter the queue multiple times.

---

# 11. Depth-First Search

DFS explores one branch as deeply as possible before backtracking.

Primary mechanism:

```text
Recursion
```

or:

```text
Explicit Stack
```

With an adjacency list:

```text
Time = O(V + E)

Additional Space = O(V)
```

---

# 12. When to Use DFS

Use DFS for:

- reachability;
- connected components;
- cycle detection;
- topological sorting;
- dependency exploration;
- exhaustive search;
- backtracking;
- strongly connected components;
- articulation points and bridges.

DFS naturally models recursive exploration and backtracking.

---

# 13. BFS versus DFS

| Characteristic | BFS | DFS |
|---|---|---|
| Main structure | Queue | Stack or recursion |
| Exploration | Level by level | Branch by branch |
| Unweighted shortest path | Guaranteed | Not guaranteed |
| Cycle detection | Possible | Natural with states |
| Topological sorting | Through in-degree strategy | Through finishing order |
| Backtracking | Not central | Natural |
| Typical memory concern | Wide frontier | Deep recursion |

A good interview answer should choose according to the required guarantee, not personal preference.

---

# 14. Cycle Detection

## Undirected Graph

During DFS, an already visited neighbour indicates a cycle only when it is not the current vertex's parent.

```text
Visited Neighbour
        +
Not Parent
        ↓
Cycle
```

## Directed Graph

Use three states:

```text
UNDISCOVERED
ACTIVE
FINISHED
```

An edge toward an `ACTIVE` vertex indicates a directed cycle.

---

# 15. Topological Sorting

Topological sorting produces a linear order of the vertices in a DAG.

For every edge:

```text
u → v
```

the result must satisfy:

```text
u appears before v
```

Common strategies:

- DFS reversed finishing order;
- Kahn's algorithm using in-degree.

With an adjacency list:

```text
O(V + E)
```

---

# 16. Topological Sorting Questions

### Why does a cycle prevent topological sorting?

A directed cycle creates circular ordering requirements.

Example:

```text
A before B
B before C
C before A
```

No vertex can legally appear first.

---

### Is a topological order unique?

Not necessarily.

Independent vertices may appear in different valid relative orders.

Tests should validate dependency constraints rather than one arbitrary sequence.

---

# 17. Kahn's Algorithm

Kahn's algorithm repeatedly processes vertices with:

```text
in-degree = 0
```

General process:

```text
Calculate In-Degrees
        ↓
Enqueue All Zero-In-Degree Vertices
        ↓
Process One Vertex
        ↓
Decrease Neighbour In-Degrees
        ↓
Enqueue Newly Available Vertices
```

Cycle detection:

```text
processed vertices < V
```

implies that the graph contains a directed cycle.

---

# 18. Shortest-Path Problem

A shortest-path problem asks for the minimum-cost path between vertices.

The cost may represent:

- number of edges;
- distance;
- time;
- financial cost;
- latency;
- risk.

The algorithm depends on the graph's weight conditions.

---

# 19. Shortest-Path Algorithm Selection

| Condition | Algorithm |
|---|---|
| Unweighted graph | BFS |
| Equal edge weights | BFS |
| Non-negative weights | Dijkstra |
| Negative weights permitted | Bellman–Ford |
| Detect reachable negative cycles | Bellman–Ford |
| All-pairs shortest paths | Floyd–Warshall |

Selecting an algorithm before checking edge-weight conditions is a common interview mistake.

---

# 20. Relaxation

Relaxation attempts to improve the known distance to a vertex.

For edge:

```text
u → v
```

the update occurs when:

```text
distance[u] + weight(u, v)
<
distance[v]
```

Then:

```text
distance[v] =
distance[u] + weight(u, v)
```

and:

```text
predecessor[v] = u
```

Relaxation is central to Dijkstra and Bellman–Ford.

---

# 21. Dijkstra's Algorithm

Dijkstra solves single-source shortest paths when all edge weights are non-negative.

Primary structure:

```text
Min-Priority Queue
```

With adjacency list and binary heap:

```text
O((V + E) log V)
```

The queue prioritizes the vertex with the smallest tentative distance.

---

# 22. Why Dijkstra Rejects Negative Weights

Dijkstra assumes that once the smallest tentative distance is selected, that distance cannot later improve.

A negative edge can violate this assumption.

Therefore:

```text
Negative Edge
        ↓
Dijkstra Correctness Invalid
```

This is one of the most frequently asked graph questions.

---

# 23. Stale Queue Entries in Dijkstra

Java's `PriorityQueue` does not provide a direct efficient decrease-key operation.

A common implementation inserts a new entry when a shorter distance is found.

The queue may contain:

```text
(B, 10)
(B, 4)
```

When `(B, 10)` is removed, it must be ignored because it is stale.

---

# 24. Bellman–Ford

Bellman–Ford supports negative edge weights.

General strategy:

```text
Relax All Edges
        ↓
Repeat V - 1 Times
```

Complexity:

```text
O(VE)
```

An additional pass determines whether a reachable negative cycle exists.

---

# 25. Negative Cycle

A negative cycle has total weight below zero.

Repeated traversal continuously reduces total path cost.

Therefore:

```text
No finite shortest path exists
```

for affected vertices.

An algorithm should report this condition rather than returning a misleading numeric result.

---

# 26. Floyd–Warshall

Floyd–Warshall computes shortest paths between every pair of vertices.

Complexity:

```text
Time = O(V³)

Space = O(V²)
```

It is appropriate when:

- all-pairs distances are required;
- the graph is relatively small;
- a matrix-based solution is acceptable.

---

# 27. Minimum Spanning Tree

A spanning tree:

- contains all vertices;
- is connected;
- contains no cycles;
- contains exactly `V - 1` edges.

A Minimum Spanning Tree minimizes the total weight of those selected edges.

Classical requirements:

```text
Weighted
        +
Undirected
        +
Connected
```

---

# 28. MST versus Shortest-Path Tree

## Minimum Spanning Tree

Minimizes:

```text
Total weight of the complete connecting structure
```

## Shortest-Path Tree

Minimizes:

```text
Distance from one source to each vertex
```

These objectives are different.

An MST does not guarantee shortest routes from one source.

---

# 29. Kruskal's Algorithm

Kruskal processes edges in nondecreasing weight order.

General process:

```text
Sort Edges
        ↓
Select Next Lightest Edge
        ↓
Would It Create a Cycle?
        ↓
No → Add
Yes → Reject
```

Primary supporting structure:

```text
Disjoint Set Union
```

Complexity:

```text
O(E log E)
```

---

# 30. Union-Find

Union-Find supports:

```text
find(vertex)
```

to identify a component, and:

```text
union(a, b)
```

to merge components.

With path compression and union by rank or size:

```text
O(α(V)) amortized
```

per operation.

This behaves almost like constant time for practical inputs, but the formal bound should remain explicit.

---

# 31. Prim's Algorithm

Prim grows one connected tree from a selected starting vertex.

At each step, it chooses the minimum-weight edge crossing from the current tree to an unvisited vertex.

With adjacency list and binary heap:

```text
O(E log V)
```

Primary structure:

```text
Min-Priority Queue
```

---

# 32. Prim versus Dijkstra

Both may use a priority queue, but the priority means something different.

## Prim

```text
Cheapest edge connecting a new vertex to the tree
```

## Dijkstra

```text
Cheapest total path from the source
```

Their similar implementation structure should not obscure their different objectives.

---

# 33. Kruskal versus Prim

| Characteristic | Kruskal | Prim |
|---|---|---|
| Focus | Globally sorted edges | Growing connected tree |
| Main structure | Edge list and Union-Find | Adjacency structure and priority queue |
| Start vertex | Not required | Required |
| Cycle control | Component comparison | Visited set |
| Disconnected graph | Produces forest naturally | Requires restart or failure |
| Typical complexity | O(E log E) | O(E log V) |

---

# 34. Complexity Summary

| Algorithm | Typical Complexity |
|---|---:|
| BFS | O(V + E) |
| DFS | O(V + E) |
| Topological Sort | O(V + E) |
| Dijkstra | O((V + E) log V) |
| Bellman–Ford | O(VE) |
| Floyd–Warshall | O(V³) |
| Kruskal | O(E log E) |
| Prim | O(E log V) |

These results assume suitable graph representations and supporting data structures.

---

# 35. Common Interview Problems

Typical graph problems include:

- determine whether a path exists;
- count connected components;
- detect a cycle;
- determine whether a graph is bipartite;
- clone a graph;
- find the shortest unweighted path;
- solve a maze;
- order tasks with dependencies;
- detect circular dependencies;
- calculate network delay;
- connect all points at minimum cost;
- find critical connections;
- group related entities.

The first step should be identifying the graph model and required guarantee.

---

# 36. Problem-Recognition Table

| Problem Clue | Likely Strategy |
|---|---|
| Minimum number of steps | BFS |
| Explore every possible branch | DFS |
| Dependency order | Topological sorting |
| Circular dependency | Directed cycle detection |
| Cheapest route with non-negative costs | Dijkstra |
| Negative edge costs | Bellman–Ford |
| Every pair distance | Floyd–Warshall |
| Minimum total connection cost | Kruskal or Prim |
| Independent groups | Connected components |
| Two-group assignment | Bipartite check |

---

# 37. Java Collections to Remember

Common graph implementations use:

```text
Map<V, List<V>>
```

for adjacency lists.

```text
Set<V>
```

for visited-state tracking.

```java
Queue<V> queue = new ArrayDeque<>();
```

for BFS.

```text
Deque<V> stack = new ArrayDeque<>();
```

for iterative DFS.

```text
PriorityQueue<T>
```

for Dijkstra and Prim.

```text
Map<V, Integer>
```

for in-degree values.

The selected collection affects both clarity and complexity.

---

# 38. Java-Specific Questions

### Why use `ArrayDeque` instead of `LinkedList` for BFS?

`ArrayDeque` provides efficient queue operations and avoids the node-allocation overhead of a linked structure for this use case.

---

### Why prefer `Deque` over Java's legacy `Stack` class?

`Deque` provides modern stack operations such as:

```text
push()
pop()
peek()
```

and is generally preferred for stack behaviour.

---

### Does `PriorityQueue` iterate in priority order?

No.

Only the head is guaranteed to have the highest priority.

Use repeated:

```text
poll()
```

to process elements in priority order.

---

### Why can mutable graph vertices be dangerous as hash keys?

Changing fields involved in `equals()` or `hashCode()` may make a vertex inaccessible inside `HashMap` or `HashSet`.

Prefer stable, immutable keys.

---

# 39. Common Interview Mistakes

Candidates frequently:

- begin coding before defining vertices and edges;
- ignore edge direction;
- forget disconnected components;
- omit visited-state tracking;
- confuse BFS and DFS guarantees;
- use Dijkstra with negative weights;
- assume topological order is unique;
- confuse MSTs with shortest paths;
- state complexity without naming the representation;
- overfit tests to one traversal order;
- fail to explain why the selected algorithm is correct.

Avoiding these mistakes demonstrates stronger algorithmic maturity.

---

# 40. How to Explain a Solution

A clear graph interview answer can follow this structure.

## 1. Model

```text
Vertices represent ...
Edges represent ...
```

## 2. Graph Properties

```text
Directed or undirected
Weighted or unweighted
Cyclic or acyclic
Sparse or dense
```

## 3. Required Result

```text
Reachability
Shortest path
Ordering
Component count
Minimum total connection cost
```

## 4. Algorithm Choice

Explain why the selected algorithm provides the required guarantee.

## 5. Data Structures

Identify:

- adjacency representation;
- queue;
- stack;
- priority queue;
- visited set;
- distance or predecessor map.

## 6. Complexity

State time and space complexity with assumptions.

## 7. Edge Cases

Discuss:

- empty graph;
- isolated vertices;
- disconnected components;
- cycles;
- duplicate edges;
- unreachable destination;
- negative weights.

---

# 41. Example Interview Reasoning

Problem:

```text
Find the minimum number of flights required
to travel from one airport to another.
```

Reasoning:

```text
Airport = Vertex
Direct Flight = Directed Edge
```

Every flight has equal cost in terms of flight count.

Therefore:

```text
Unweighted Shortest Path
        ↓
Breadth-First Search
```

Use:

```text
Adjacency List
Queue
Visited Set
Distance Map
```

Complexity:

```text
O(V + E)
```

This explanation is stronger than simply stating, “Use BFS.”

---

# 42. Correctness Questions

Be prepared to explain why an algorithm works.

Examples:

### BFS

Vertices are processed in nondecreasing distance from the source.

### DFS Reachability

Every reachable neighbour is eventually explored through recursive expansion.

### Topological Sort

Every dependency appears before its destination through in-degree removal or reversed finishing order.

### Dijkstra

With non-negative weights, selecting the smallest tentative distance safely finalizes it.

### Kruskal and Prim

The cut property guarantees that selected minimum crossing edges are safe.

Correctness reasoning distinguishes understanding from memorization.

---

# 43. Testing Questions

A strong candidate should test graph algorithms using properties.

## BFS and DFS

- all reachable vertices appear;
- no vertex appears more than once;
- unreachable vertices are excluded.

## Topological Sorting

For every edge:

```text
position(u) < position(v)
```

## Shortest Paths

- path edges exist;
- path cost matches the recorded distance;
- unreachable vertices are represented correctly.

## MST

- all vertices are connected;
- no cycle exists;
- exactly `V - 1` edges are selected;
- total weight is minimal.

---

# 44. Quick Comparison Questions

### BFS or DFS for shortest unweighted path?

```text
BFS
```

### DFS structure?

```text
Stack or recursion
```

### BFS structure?

```text
Queue
```

### Topological sorting input?

```text
Directed Acyclic Graph
```

### Dijkstra weight restriction?

```text
Non-negative edge weights
```

### Algorithm for negative edges?

```text
Bellman–Ford
```

### All-pairs shortest paths?

```text
Floyd–Warshall
```

### MST algorithms?

```text
Kruskal and Prim
```

### Kruskal supporting structure?

```text
Union-Find
```

### Prim supporting structure?

```text
Priority Queue
```

---

# 45. Quick Complexity Review

```text
BFS
O(V + E)
```

```text
DFS
O(V + E)
```

```text
Topological Sort
O(V + E)
```

```text
Dijkstra
O((V + E) log V)
```

```text
Bellman–Ford
O(VE)
```

```text
Floyd–Warshall
O(V³)
```

```text
Kruskal
O(E log E)
```

```text
Prim
O(E log V)
```

---

# 46. Review Checklist

Before a technical interview, confirm that you can answer:

- [ ] What are vertices and edges?
- [ ] What is the difference between directed and undirected graphs?
- [ ] What is the difference between weighted and unweighted graphs?
- [ ] When should an adjacency list be preferred?
- [ ] When is an adjacency matrix useful?
- [ ] Why does BFS use a queue?
- [ ] Why does DFS use a stack or recursion?
- [ ] Why does BFS find shortest unweighted paths?
- [ ] How are cycles detected in directed and undirected graphs?
- [ ] What conditions are required for topological sorting?
- [ ] Why can topological orders differ?
- [ ] What is relaxation?
- [ ] Why does Dijkstra reject negative weights?
- [ ] What does Bellman–Ford add?
- [ ] What problem does Floyd–Warshall solve?
- [ ] What is the difference between an MST and a shortest-path tree?
- [ ] How do Kruskal and Prim differ?
- [ ] What role does Union-Find play?
- [ ] How do graph representations affect complexity?
- [ ] Which edge cases should be tested?

---

# 47. Engineering Perspective

Graph interview questions are rarely only about syntax.

They evaluate whether the candidate can transform a relationship-based problem into a precise computational model.

A strong engineer should be able to move through the following sequence:

```text
Understand the Problem
        ↓
Define Vertices and Edges
        ↓
Identify Graph Properties
        ↓
Select a Representation
        ↓
Choose a Valid Algorithm
        ↓
Explain Correctness
        ↓
Analyse Complexity
        ↓
Handle Edge Cases
        ↓
Implement and Test
```

This reasoning process is more valuable than memorizing isolated code templates.

---

# 48. Key Takeaways

After reviewing this document, the learner should remember that:

- graph modeling must precede implementation;
- edge direction and weight conditions determine algorithm validity;
- adjacency lists are generally appropriate for sparse traversal problems;
- BFS provides shortest paths in unweighted graphs;
- DFS supports deep exploration, cycles, and dependency analysis;
- topological sorting applies only to DAGs;
- Dijkstra requires non-negative weights;
- Bellman–Ford supports negative edges and detects reachable negative cycles;
- Floyd–Warshall computes all-pairs shortest paths;
- Kruskal and Prim construct Minimum Spanning Trees;
- MSTs and shortest-path trees solve different problems;
- graph complexity should be expressed using both `V` and `E`;
- Java collection choices influence implementation quality;
- interview answers should explain modeling, assumptions, correctness, complexity, and edge cases.