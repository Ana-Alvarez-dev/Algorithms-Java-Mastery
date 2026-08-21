# Breadth-First Search

## Algorithms Java Mastery

**Breadth-First Search (BFS)** is a graph traversal algorithm that explores vertices according to their distance from a selected starting point.

Instead of following one path as deeply as possible, BFS visits all immediate neighbours of the source before continuing to vertices located farther away.

Conceptually:

```text
Source
   ↓
Immediate Neighbours
   ↓
Next Level
   ↓
Following Levels
```

This level-by-level exploration makes BFS especially useful for discovering reachability, calculating distances in unweighted graphs, identifying connected components, and finding paths containing the minimum number of edges.

The algorithm relies on a **queue**, whose First In, First Out behaviour preserves the correct processing order.

---

# 1. Purpose

The purpose of this document is to:

- introduce Breadth-First Search;
- explain its level-by-level traversal strategy;
- understand why BFS uses a queue;
- define its principal invariants;
- analyse its computational complexity;
- identify its most common applications;
- prepare the learner for a Java implementation.

---

# 2. What Is Breadth-First Search?

Breadth-First Search is an algorithm that systematically explores a graph from a selected source vertex.

The source is visited first. Its unvisited neighbours are then discovered and scheduled for processing. Afterward, the algorithm continues with the neighbours of those vertices.

Example:

```text
        A
       / \
      B   C
     / \   \
    D   E   F
```

Starting from `A`, one possible BFS order is:

```text
A → B → C → D → E → F
```

The algorithm processes the graph by levels:

```text
Level 0: A

Level 1: B, C

Level 2: D, E, F
```

The exact order among vertices on the same level depends on the order in which their neighbours are stored.

---

# 3. Central Strategy

The central idea of BFS is:

> Visit vertices in nondecreasing order of their distance from the source.

The general process is:

```text
Select Source
        ↓
Mark Source as Discovered
        ↓
Add Source to Queue
        ↓
Remove Next Vertex
        ↓
Inspect Its Neighbours
        ↓
Discover Unvisited Neighbours
        ↓
Add Them to Queue
        ↓
Repeat Until Queue Is Empty
```

The queue ensures that earlier-discovered vertices are processed before later-discovered vertices.

---

# 4. Why BFS Uses a Queue

A queue follows the **First In, First Out (FIFO)** principle.

```text
First Discovered
        ↓
First Processed
```

Suppose the source discovers `B` and `C`.

```text
Queue:

Front → B, C ← Rear
```

Vertex `B` is processed before `C` because it entered the queue first.

If processing `B` discovers `D` and `E`, they are added behind `C`.

```text
Queue:

Front → C, D, E ← Rear
```

This behaviour prevents the algorithm from moving too deeply into one branch before processing the remaining vertices at the current level.

Using a stack instead would produce depth-first behaviour.

---

# 5. Example Graph

Consider the following undirected graph:

```text
        A
       / \
      B   C
     / \   \
    D   E---F
```

Adjacency list:

```text
A → B, C
B → A, D, E
C → A, F
D → B
E → B, F
F → C, E
```

Starting vertex:

```text
A
```

The algorithm begins with:

```text
Visited = {A}

Queue = [A]
```

---

# 6. Manual Execution

## Step 1 — Process A

Remove `A` from the queue.

Neighbours:

```text
B, C
```

Both are unvisited, so they are marked and added.

```text
Visited = {A, B, C}

Queue = [B, C]
```

Traversal so far:

```text
A
```

---

## Step 2 — Process B

Remove `B`.

Neighbours:

```text
A, D, E
```

`A` is already visited.

`D` and `E` are discovered and added.

```text
Visited = {A, B, C, D, E}

Queue = [C, D, E]
```

Traversal:

```text
A → B
```

---

## Step 3 — Process C

Remove `C`.

Neighbours:

```text
A, F
```

`A` is already visited.

`F` is discovered and added.

```text
Visited = {A, B, C, D, E, F}

Queue = [D, E, F]
```

Traversal:

```text
A → B → C
```

---

## Step 4 — Process D

`D` has only one neighbour:

```text
B
```

It is already visited.

```text
Queue = [E, F]
```

Traversal:

```text
A → B → C → D
```

---

## Step 5 — Process E

Neighbours:

```text
B, F
```

Both are already visited.

```text
Queue = [F]
```

Traversal:

```text
A → B → C → D → E
```

---

## Step 6 — Process F

Neighbours:

```text
C, E
```

Both have already been discovered.

```text
Queue = []
```

Final traversal:

```text
A → B → C → D → E → F
```

---

# 7. Visited-State Management

Graphs may contain cycles.

Example:

```text
A ─── B
|     |
D ─── C
```

Without tracking visited vertices, the algorithm could repeat indefinitely:

```text
A → B → C → D → A → ...
```

BFS therefore maintains a visited structure.

Possible implementations include:

```text
boolean array
```

```text
set of vertices
```

```text
map from vertex to traversal state
```

Typical states include:

```text
Undiscovered
Discovered
Processed
```

For many implementations, a single visited set is sufficient.

---

# 8. Mark Before Enqueueing

A vertex should normally be marked as visited when it is added to the queue, not when it is later removed.

Correct sequence:

```text
Discover Vertex
        ↓
Mark as Visited
        ↓
Add to Queue
```

Suppose two vertices both connect to `D`.

```text
B ─── D ─── C
```

If `D` is marked only when removed from the queue, both `B` and `C` may enqueue it.

Incorrect result:

```text
Queue = [D, D]
```

Marking `D` immediately when discovered prevents duplicate scheduling.

---

# 9. Conceptual Pseudocode

```text
BREADTH-FIRST-SEARCH(graph, source)

    create empty queue
    create empty visited set

    mark source as visited
    enqueue source

    while queue is not empty

        current = dequeue

        process current

        for each neighbour of current

            if neighbour is not visited

                mark neighbour as visited
                enqueue neighbour
```

The `process` operation depends on the problem.

It may:

- collect traversal order;
- search for a target;
- calculate distances;
- assign parents;
- count components;
- build a traversal tree.

---

# 10. Preconditions

A BFS execution commonly requires:

- a valid graph;
- a source vertex belonging to the graph;
- a representation that allows neighbour retrieval;
- a clear definition of directed or undirected edges.

Additional preconditions may depend on the application.

For example, an unweighted shortest-path calculation assumes that all edges have equal cost.

---

# 11. Postconditions

After BFS completes from a source vertex:

- every reachable vertex has been discovered;
- no unreachable vertex has been visited;
- each reachable vertex has been processed according to BFS order;
- vertices are visited in nondecreasing distance from the source;
- the queue is empty;
- the visited structure contains the reachable component.

If predecessor information is stored, BFS also produces a traversal tree rooted at the source.

---

# 12. BFS Invariants

Several properties remain true throughout execution.

## Queue invariant

Every vertex in the queue has been discovered but may not yet have been fully processed.

## Visited invariant

A vertex marked as visited is never enqueued again.

## Reachability invariant

Every discovered vertex is reachable from the source.

## Level-order invariant

Vertices are removed from the queue in nondecreasing order of distance from the source.

## Predecessor invariant

If a predecessor is recorded for a discovered vertex, that predecessor lies on a valid path from the source.

These invariants provide the foundation for reasoning about correctness.

---

# 13. Correctness Intuition

BFS is correct for reachability because it begins at the source and expands only through existing edges.

Therefore, every discovered vertex is reachable.

It is complete for the source component because whenever a reachable vertex is processed, all its unvisited neighbours are discovered.

Repeated expansion eventually reaches every vertex connected to the source.

Conceptually:

```text
Source Reachable
        ↓
Its Neighbours Reachable
        ↓
Their Neighbours Reachable
        ↓
Continue Until No New Vertices Exist
```

---

# 14. Shortest Paths in Unweighted Graphs

One of the most important properties of BFS is that it calculates shortest-path distances in an unweighted graph.

Here, shortest means:

```text
Minimum number of edges
```

Suppose:

```text
A ─── B ─── D
 \         /
  └── C ──┘
```

Possible paths from `A` to `D`:

```text
A → B → D
```

```text
A → C → D
```

Both contain two edges.

BFS discovers vertices level by level, so the first time a vertex is reached, the algorithm has found a path using the minimum number of edges.

---

# 15. Distance Calculation

The source begins with distance zero.

```text
distance[source] = 0
```

When an unvisited neighbour is discovered:

```text
distance[neighbour] = distance[current] + 1
```

Example:

```text
        A
       / \
      B   C
     /
    D
```

Distances from `A`:

| Vertex | Distance |
|---|---:|
| A | 0 |
| B | 1 |
| C | 1 |
| D | 2 |

The values represent the minimum number of edges from the source.

---

# 16. Predecessor Tracking

BFS can record how each vertex was first reached.

Example:

```text
predecessor[B] = A
predecessor[C] = A
predecessor[D] = B
```

This information forms a **BFS tree**.

```text
        A
       / \
      B   C
     /
    D
```

The predecessor structure can reconstruct a shortest path from the source to any reachable vertex.

---

# 17. Path Reconstruction

To reconstruct a path from source `A` to destination `D`, follow predecessors backward.

```text
D
↓
B
↓
A
```

Reverse the sequence:

```text
A → B → D
```

Conceptual process:

```text
Start at Destination
        ↓
Follow Predecessors
        ↓
Reach Source
        ↓
Reverse Collected Path
```

If no predecessor chain reaches the source, the destination is not reachable from it.

---

# 18. Directed Graphs

BFS also works with directed graphs.

However, traversal must respect edge direction.

Example:

```text
A → B → C
↑
D
```

Starting from `A`:

```text
A → B → C
```

Vertex `D` is not reachable from `A`, even though an edge connects `D` to `A`.

Direction determines valid movement.

---

# 19. Disconnected Graphs

Running BFS from one source visits only the vertices reachable from that source.

Example:

```text
Component 1:

A ─── B ─── C
```

```text
Component 2:

D ─── E
```

Starting from `A` visits:

```text
A, B, C
```

It does not visit:

```text
D, E
```

To traverse the entire disconnected graph, BFS must be restarted from every still-unvisited vertex.

---

# 20. Traversing All Components

Conceptual pseudocode:

```text
BREADTH-FIRST-SEARCH-ALL(graph)

    create empty visited set

    for each vertex in graph

        if vertex is not visited

            run BFS from vertex
```

Each execution discovers one connected component in an undirected graph.

This technique can be used to:

- count connected components;
- classify vertices by component;
- determine whether the complete graph is connected.

---

# 21. BFS Tree

The predecessor relationships created by BFS form a tree rooted at the source.

This tree contains:

- the source as root;
- one predecessor for each other reachable vertex;
- shortest unweighted paths from the source.

The BFS tree is not necessarily identical to the original graph because the original graph may contain additional edges.

Example:

```text
Original Graph

A ─── B
| \   |
C ─── D
```

A possible BFS tree from `A`:

```text
    A
   /|\
  B C D
```

Only the edges used for first discovery belong to the traversal tree.

---

# 22. Complexity with an Adjacency List

When the graph is represented with an adjacency list:

```text
Time = O(V + E)
```

Reasoning:

- every reachable vertex is enqueued and dequeued at most once;
- every adjacency entry is inspected once;
- undirected edges appear twice, but constant factors are ignored.

Space complexity:

```text
O(V)
```

for:

- the queue;
- the visited structure;
- optional distance and predecessor data.

The graph representation itself requires:

```text
O(V + E)
```

space.

---

# 23. Complexity with an Adjacency Matrix

With an adjacency matrix, retrieving the neighbours of a vertex requires scanning an entire row.

For every visited vertex:

```text
O(V)
```

positions are inspected.

Therefore:

```text
Time = O(V²)
```

The additional traversal space remains:

```text
O(V)
```

This difference demonstrates why representation choices affect algorithm complexity.

---

# 24. Complexity Summary

| Representation | BFS Time | Additional Space |
|---|---:|---:|
| Adjacency List | O(V + E) | O(V) |
| Adjacency Matrix | O(V²) | O(V) |

For sparse graphs, adjacency lists generally provide better performance.

For dense graphs:

```text
E ≈ V²
```

so both forms approach quadratic processing.

---

# 25. Java-Oriented Design

A generic BFS implementation may use:

```java
Queue<V> queue = new ArrayDeque<>();
Set<V> visited = new HashSet<>();
```

A graph represented as an adjacency map may use:

```java
Map<V, List<V>> adjacencyList;
```

The principal operations are:

```text
queue.offer(source);
queue.poll();
visited.add(vertex);
visited.contains(vertex);
```

The implementation should depend on interfaces where appropriate:

```text
Queue
Set
Map
List
```

rather than unnecessarily coupling the algorithm to concrete collection classes.

---

# 26. Illustrative Java Structure

```text
public static <V> List<V> breadthFirstSearch(
        Map<V, List<V>> graph,
        V source) {

    Queue<V> queue = new ArrayDeque<>();
    Set<V> visited = new HashSet<>();
    List<V> traversalOrder = new ArrayList<>();

    visited.add(source);
    queue.offer(source);

    while (!queue.isEmpty()) {
        V current = queue.poll();
        traversalOrder.add(current);

        for (V neighbour : graph.getOrDefault(current, List.of())) {
            if (visited.add(neighbour)) {
                queue.offer(neighbour);
            }
        }
    }

    return traversalOrder;
}
```

The expression:

```text
visited.add(neighbour)
```

returns `true` only when the neighbour was not already present.

This allows marking and checking to occur in one operation.

---

# 27. Input Validation

A robust implementation should define how it handles:

- a null graph;
- a null source;
- a source absent from the graph;
- null neighbour collections;
- edges pointing to unknown vertices;
- duplicate adjacency entries.

The appropriate behaviour may include:

- rejecting invalid input;
- returning an empty traversal;
- treating absent neighbour lists as empty;
- enforcing graph invariants during construction.

The contract should remain explicit and consistent.

---

# 28. Traversal Order Stability

BFS guarantees level-based exploration, but it does not always guarantee one exact traversal order.

Consider:

```text
A → B, C
```

If neighbours are stored as:

```text
[B, C]
```

the order may be:

```text
A, B, C
```

If stored as:

```text
[C, B]
```

the order may be:

```text
A, C, B
```

Both are valid BFS traversals.

Tests should avoid assuming an order that the graph representation does not guarantee.

When deterministic traversal is required, neighbour collections should preserve or define an order.

---

# 29. Common Applications

BFS is commonly used for:

- reachability analysis;
- shortest paths in unweighted graphs;
- level-order tree traversal;
- connected-component discovery;
- social-network distance;
- web crawling;
- network broadcasting;
- dependency distance;
- maze solving;
- state-space exploration.

Its usefulness comes from its systematic expansion by distance.

---

# 30. Social Network Distance

In a social graph:

```text
Person = Vertex
Friendship = Edge
```

BFS can determine the minimum number of social connections between two people.

Example:

```text
Ana → Bruno → Carla → Diego
```

Distance from Ana to Diego:

```text
3 edges
```

This type of calculation is sometimes described as degrees of separation.

---

# 31. Maze Solving

A maze may be represented as a graph.

```text
Cell = Vertex
Valid Movement = Edge
```

BFS can find a route with the minimum number of movements when each movement has equal cost.

The algorithm expands positions level by level until reaching the destination.

---

# 32. Level-Order Tree Traversal

A tree is a specialized graph.

BFS applied to a tree produces level-order traversal.

Example:

```text
        A
       / \
      B   C
     / \
    D   E
```

Traversal:

```text
A → B → C → D → E
```

The queue preserves the left-to-right processing order when children are enqueued consistently.

---

# 33. Multi-Source BFS

Some problems begin from multiple source vertices simultaneously.

Conceptually:

```text
Source A
Source B
Source C
```

All sources are placed into the queue initially with distance zero.

The algorithm then expands outward from all of them.

Applications include:

- finding the nearest service location;
- modelling simultaneous spread;
- calculating distance to the closest source;
- analysing multiple entry points.

The same BFS principles remain valid.

---

# 34. Early Termination

If the objective is only to find one target, BFS may stop when the target is discovered or removed from the queue, depending on the contract.

For shortest unweighted paths, the first discovery of the target establishes its minimum distance.

Early termination can reduce unnecessary work.

However, it should not be used when the algorithm must:

- visit the complete component;
- calculate every distance;
- count components;
- build a full BFS tree.

---

# 35. Common Mistakes

## Forgetting the visited structure

Cycles may cause repeated processing or nontermination.

## Marking vertices too late

The same vertex may be enqueued multiple times.

## Using a stack

The algorithm becomes depth-first rather than breadth-first.

## Ignoring disconnected components

A single BFS execution may not visit the complete graph.

## Assuming weighted shortest paths

Standard BFS is not generally correct when edges have different weights.

## Assuming one fixed traversal order

Vertices on the same level may be processed in different valid orders.

## Ignoring edge direction

A directed graph must be traversed only along valid outgoing edges.

## Using an inefficient representation

An adjacency matrix may make sparse-graph traversal unnecessarily expensive.

---

# 36. Automated Testing Strategy

A BFS implementation should be tested with several graph structures.

## Empty graph

Verify the defined behaviour when no vertices exist.

## Single vertex

Expected traversal:

```text
A
```

## Simple connected graph

Verify level-order exploration.

## Cyclic graph

Confirm that every reachable vertex is processed once.

## Disconnected graph

Confirm that one-source BFS remains within its component.

## Directed graph

Confirm that edge direction is respected.

## Multiple valid orders

Avoid overconstraining tests when neighbour order is unspecified.

## Shortest path

Verify calculated distances and reconstructed paths.

---

# 37. Representative Test Properties

Useful properties include:

- the source is the first visited vertex;
- every returned vertex is reachable from the source;
- no vertex appears more than once;
- every reachable vertex appears once;
- distances never decrease during queue removal;
- each non-source predecessor is adjacent to its vertex;
- predecessor paths lead back to the source;
- calculated distances equal the number of edges in reconstructed paths.

These properties test more than one specific traversal example.

---

# 38. BFS versus DFS

| Characteristic | BFS | DFS |
|---|---|---|
| Processing strategy | Level by level | Branch by branch |
| Primary structure | Queue | Stack or recursion |
| Unweighted shortest path | Yes | Not guaranteed |
| Memory pattern | May store wide frontier | Depends on depth |
| Common applications | Distances, levels, reachability | Cycles, ordering, backtracking |

Neither algorithm is universally superior.

The appropriate choice depends on the problem.

---

# 39. Engineering Perspective

Breadth-First Search demonstrates how combining a simple data structure with a precise traversal policy produces a powerful general algorithm.

The queue is not merely an implementation detail. Its FIFO behaviour is responsible for the level-order property and the shortest-path guarantee in unweighted graphs.

In professional systems, BFS appears in network services, dependency analysis, recommendation systems, web crawlers, routing tools, workflow engines, and many applications that need to measure or explore relationships.

Correct use requires understanding:

- graph direction;
- edge cost assumptions;
- representation complexity;
- visited-state management;
- deterministic-order requirements;
- expected graph scale.

---

# 40. Relationship with the Next Document

This document introduced level-by-level graph exploration.

The next document studies **Depth-First Search (DFS)**, which follows one branch as deeply as possible before backtracking.

The comparison is:

```text
Breadth-First Search
        ↓
Queue
        ↓
Level-by-Level Exploration
```

```text
Depth-First Search
        ↓
Stack or Recursion
        ↓
Deep Branch Exploration
```

Together, BFS and DFS form the foundation of many graph algorithms.

---

# 41. Key Takeaways

After completing this document, the learner should understand that:

- BFS explores a graph level by level;
- a queue preserves the required FIFO processing order;
- vertices should be marked when they are enqueued;
- a visited structure prevents repetition and infinite traversal;
- BFS visits every vertex reachable from the selected source;
- one execution does not necessarily cover a disconnected graph;
- BFS calculates shortest paths in unweighted graphs;
- predecessor information allows path reconstruction;
- adjacency-list BFS requires `O(V + E)` time;
- adjacency-matrix BFS requires `O(V²)` time;
- traversal order within the same level depends on neighbour ordering;
- standard BFS does not solve general weighted shortest-path problems;
- BFS is fundamental for reachability, connectivity, levels, path finding, and network exploration.