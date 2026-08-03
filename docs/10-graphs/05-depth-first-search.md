# Depth-First Search

## Algorithms Java Mastery

**Depth-First Search (DFS)** is a graph traversal algorithm that explores one path as deeply as possible before returning to examine alternative paths.

Unlike Breadth-First Search, which processes vertices level by level, DFS follows a branch until it reaches a vertex with no unvisited neighbours. The algorithm then **backtracks** to the most recent vertex that still has unexplored connections.

Conceptually:

```text
Select Source
        ↓
Follow One Path
        ↓
Continue Deeper
        ↓
Reach a Dead End
        ↓
Backtrack
        ↓
Explore Another Path
```

DFS may be implemented using:

- recursion;
- an explicit stack.

Its depth-oriented behaviour makes it fundamental for cycle detection, topological sorting, connected-component analysis, path exploration, dependency processing, and many backtracking problems.

---

# 1. Purpose

The purpose of this document is to:

- introduce Depth-First Search;
- explain its branch-by-branch exploration strategy;
- understand the relationship between DFS, recursion, and stacks;
- define its principal invariants;
- analyse its computational complexity;
- identify its common applications;
- prepare the learner for a Java implementation.

---

# 2. What Is Depth-First Search?

Depth-First Search is an algorithm that explores a graph by visiting an unvisited neighbour and continuing from that vertex before returning to explore alternatives.

Consider:

```text
        A
       / \
      B   C
     / \   \
    D   E   F
```

Starting from `A`, one possible DFS traversal is:

```text
A → B → D → E → C → F
```

The exact order depends on how neighbours are stored.

The central behaviour remains:

```text
Explore deeply
        ↓
Backtrack when necessary
        ↓
Continue with remaining branches
```

---

# 3. Central Strategy

The general DFS process is:

```text
Visit Current Vertex
        ↓
Mark It as Visited
        ↓
Select an Unvisited Neighbour
        ↓
Continue from That Neighbour
        ↓
Repeat Until No Unvisited Neighbour Exists
        ↓
Backtrack
```

The algorithm continues until every reachable vertex has been visited.

---

# 4. Why DFS Uses a Stack

DFS must remember the vertices from which unexplored branches remain.

A **stack** naturally supports this requirement because it follows the:

```text
Last In, First Out
```

principle.

The most recently entered vertex is the first one revisited during backtracking.

Conceptually:

```text
A
↓
B
↓
D
```

When `D` has no unvisited neighbours:

```text
Return to B
```

If `B` still has an unexplored neighbour, DFS continues from there.

The stack may be:

- explicit, using a Java collection;
- implicit, through the recursive call stack.

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

One possible traversal is:

```text
A → B → D → E → F → C
```

This order assumes that neighbours are examined from left to right according to the adjacency list.

---

# 6. Manual Recursive Execution

## Step 1 — Visit A

```text
Visited = {A}
```

The first unvisited neighbour is:

```text
B
```

DFS continues recursively from `B`.

---

## Step 2 — Visit B

```text
Visited = {A, B}
```

Neighbours:

```text
A, D, E
```

`A` is already visited.

The next unvisited neighbour is:

```text
D
```

---

## Step 3 — Visit D

```text
Visited = {A, B, D}
```

The only neighbour is:

```text
B
```

Since `B` is already visited, `D` has no unexplored neighbours.

DFS backtracks to:

```text
B
```

---

## Step 4 — Continue from B

The next unvisited neighbour of `B` is:

```text
E
```

Visit `E`.

```text
Visited = {A, B, D, E}
```

---

## Step 5 — Visit F from E

Neighbours of `E`:

```text
B, F
```

`B` is already visited.

DFS continues to:

```text
F
```

```text
Visited = {A, B, D, E, F}
```

---

## Step 6 — Visit C from F

Neighbours of `F`:

```text
C, E
```

`E` is already visited.

Visit:

```text
C
```

```text
Visited = {A, B, C, D, E, F}
```

The remaining neighbours have already been visited.

Final traversal:

```text
A → B → D → E → F → C
```

---

# 7. Recursive DFS

Recursion naturally expresses the structure of DFS.

Conceptual pseudocode:

```text
DEPTH-FIRST-SEARCH(graph, current, visited)

    mark current as visited
    process current

    for each neighbour of current

        if neighbour is not visited

            DEPTH-FIRST-SEARCH(
                graph,
                neighbour,
                visited
            )
```

Each recursive call explores one branch.

When the call completes, execution returns automatically to the previous vertex.

---

# 8. Recursive Call Stack

Suppose the traversal follows:

```text
A → B → D
```

The call stack becomes:

```text
DFS(A)
DFS(B)
DFS(D)
```

When `D` has no unvisited neighbours:

```text
DFS(D) completes
```

Execution returns to:

```text
DFS(B)
```

This automatic return is the mechanism of backtracking.

---

# 9. Iterative DFS

DFS can also be implemented using an explicit stack.

Conceptual pseudocode:

```text
DEPTH-FIRST-SEARCH(graph, source)

    create empty stack
    create empty visited set

    push source onto stack

    while stack is not empty

        current = pop stack

        if current is not visited

            mark current as visited
            process current

            for each neighbour of current

                if neighbour is not visited

                    push neighbour onto stack
```

This implementation makes the stack behaviour visible rather than relying on recursion.

---

# 10. Recursive and Iterative Differences

Both approaches follow the same depth-first principle, but their observable traversal orders may differ.

| Recursive DFS | Iterative DFS |
|---|---|
| Uses the call stack | Uses an explicit stack |
| Concise implementation | Greater control over execution |
| Natural backtracking | Avoids deep recursion |
| May cause stack overflow | Uses heap-allocated collection storage |
| Order follows recursive neighbour iteration | Order depends on push order |

To reproduce a recursive left-to-right traversal iteratively, neighbours may need to be pushed in reverse order.

---

# 11. Visited-State Management

Graphs may contain cycles.

Example:

```text
A ─── B
|     |
D ─── C
```

Without a visited structure, DFS could repeatedly follow the cycle:

```text
A → B → C → D → A → ...
```

A visited set ensures that every vertex is processed at most once.

Possible representations include:

```text
boolean[]
```

```text
Set<V>
```

```text
Map<V, State>
```

A simple visited set is sufficient for standard traversal.

More advanced DFS algorithms may require multiple states.

---

# 12. Vertex States

Some DFS applications distinguish three states.

```text
WHITE
```

The vertex has not been discovered.

```text
GRAY
```

The vertex has been discovered, but its exploration is not complete.

```text
BLACK
```

The vertex and all its outgoing edges have been completely processed.

Conceptually:

```text
Undiscovered
      ↓
Discovered and Active
      ↓
Finished
```

These states are especially useful for:

- cycle detection;
- edge classification;
- topological sorting;
- discovery and finishing times.

---

# 13. Mark Before Exploring

In recursive DFS, a vertex should be marked before exploring its neighbours.

Correct sequence:

```text
Mark Current
        ↓
Process Current
        ↓
Explore Neighbours
```

Incorrect sequence:

```text
Explore Neighbours
        ↓
Mark Current
```

In cyclic graphs, delaying the mark may cause infinite recursion.

---

# 14. Preconditions

A DFS execution commonly requires:

- a valid graph;
- a source vertex belonging to the graph;
- a representation that supports neighbour retrieval;
- a consistent directed or undirected graph model.

The implementation should also define how invalid or absent sources are handled.

---

# 15. Postconditions

After DFS completes from a source:

- every reachable vertex has been visited;
- no unreachable vertex has been processed;
- every visited vertex appears only once;
- the traversal has completely explored the source component;
- all recursive calls or stack entries have been resolved.

If predecessor data is stored, DFS also produces a depth-first traversal tree or forest.

---

# 16. DFS Invariants

## Visited invariant

Every vertex marked as visited has been reached through a valid path from the source.

## Uniqueness invariant

A visited vertex is never fully processed again.

## Active-path invariant

In recursive DFS, the call stack represents the current path from the source to the active vertex.

## Backtracking invariant

When a recursive call returns, every vertex reachable through that explored branch has been processed.

## Completion invariant

When a vertex becomes finished, all its outgoing neighbours have been examined.

These invariants support correctness reasoning.

---

# 17. Correctness Intuition

DFS only moves through valid edges.

Therefore, every visited vertex is reachable from the source.

The algorithm examines all outgoing edges of every discovered vertex. Whenever an unvisited neighbour exists, DFS eventually visits it.

If a reachable vertex were never visited, there would have to be an edge from an already reached path to that unvisited vertex. However, DFS examines that edge and would discover it.

Therefore, DFS visits every vertex reachable from the source.

---

# 18. DFS Tree

When DFS first discovers a vertex, the edge used to reach it may be recorded as a tree edge.

These relationships form a **DFS tree**.

Example:

```text
Original Graph

A ─── B
| \   |
C ─── D
```

One possible DFS tree from `A`:

```text
A
|
B
|
D
|
C
```

The original graph may contain additional edges not included in the DFS tree.

---

# 19. DFS Forest

If a graph is disconnected, running DFS from one source visits only its connected component.

To process the complete graph, DFS must be restarted from every unvisited vertex.

Conceptual pseudocode:

```text
DEPTH-FIRST-SEARCH-ALL(graph)

    create empty visited set

    for each vertex in graph

        if vertex is not visited

            run DFS from vertex
```

The result is a collection of DFS trees called a:

```text
DFS forest
```

Each tree represents one traversal component.

---

# 20. Connected Components

In an undirected graph, DFS can identify connected components.

Example:

```text
Component 1

A ─── B ─── C
```

```text
Component 2

D ─── E
```

DFS starting from `A` visits:

```text
A, B, C
```

The next unvisited vertex begins another traversal:

```text
D, E
```

The number of separate DFS executions equals the number of connected components.

---

# 21. Path Finding

DFS can determine whether a path exists between two vertices.

General strategy:

```text
Start at Source
        ↓
Explore Recursively
        ↓
Stop if Target Is Found
```

If the target is discovered, it is reachable.

DFS can also store predecessors to reconstruct one valid path.

However, the path found is not necessarily the shortest.

---

# 22. Path Reconstruction

Suppose DFS records:

```text
predecessor[B] = A
predecessor[D] = B
predecessor[F] = D
```

To reconstruct the path from `A` to `F`:

```text
F
↓
D
↓
B
↓
A
```

Reverse the result:

```text
A → B → D → F
```

DFS produces a valid path, but another shorter path may exist.

---

# 23. DFS Does Not Guarantee Shortest Paths

Consider:

```text
A ─── B ─── C ─── D
 \_______________/
```

There is a direct edge:

```text
A → D
```

but DFS may first follow:

```text
A → B → C → D
```

Therefore, DFS does not guarantee the minimum number of edges.

For shortest paths in unweighted graphs, BFS is generally appropriate.

---

# 24. Cycle Detection in Undirected Graphs

In an undirected graph, encountering an already visited neighbour does not always indicate a cycle because each edge appears in both directions.

Example:

```text
A ─── B
```

From `B`, the neighbour `A` is already visited because it is the parent.

A cycle exists when DFS encounters a visited neighbour that is not the current vertex's parent.

Conceptually:

```text
Visited Neighbour
        ↓
Is It the Parent?
        ↓
No
        ↓
Cycle Detected
```

---

# 25. Cycle Detection in Directed Graphs

In directed graphs, DFS often uses the three-state model.

A cycle exists when the algorithm finds an edge to a vertex that is currently:

```text
GRAY
```

This means the edge points to an active ancestor in the recursion stack.

Such an edge is commonly called a:

```text
back edge
```

Conceptually:

```text
Current Vertex
        ↓
Edge to Active Ancestor
        ↓
Directed Cycle
```

---

# 26. Topological Sorting

Topological sorting applies to a:

```text
Directed Acyclic Graph
```

DFS supports topological ordering by recording vertices when their exploration finishes.

Conceptual process:

```text
Explore Vertex Completely
        ↓
Add Vertex to Result
        ↓
Reverse Finishing Order
```

A vertex is added only after all vertices that depend on it through explored paths have been processed.

This topic is developed in:

```text
06-topological-sorting.md
```

---

# 27. Discovery and Finishing Times

Advanced DFS implementations may record two timestamps.

## Discovery time

The moment a vertex is first visited.

## Finishing time

The moment all its outgoing edges have been fully explored.

Example:

```text
discover(A) = 1
finish(A) = 8
```

These values help analyse:

- ancestor relationships;
- edge types;
- topological ordering;
- strongly connected components.

---

# 28. Edge Classification

In directed graphs, DFS can classify edges.

## Tree edge

Used to discover a new vertex.

## Back edge

Points to an active ancestor and indicates a cycle.

## Forward edge

Points from a vertex to a descendant that was already discovered.

## Cross edge

Connects vertices belonging to different completed branches.

This classification depends on discovery and finishing states.

---

# 29. Complexity with an Adjacency List

With an adjacency-list representation:

```text
Time = O(V + E)
```

Reasoning:

- every reachable vertex is visited once;
- every adjacency entry is inspected once;
- each edge contributes a constant amount of work.

Additional space:

```text
O(V)
```

for:

- the visited structure;
- the explicit or recursive stack;
- optional predecessor and state information.

---

# 30. Complexity with an Adjacency Matrix

With an adjacency matrix, every visited vertex requires scanning a complete row.

Therefore:

```text
Time = O(V²)
```

Additional traversal space remains:

```text
O(V)
```

This difference reinforces the importance of selecting an appropriate graph representation.

---

# 31. Recursion Depth

Recursive DFS may create one call per vertex along the current path.

Worst-case recursion depth:

```text
O(V)
```

Example:

```text
A → B → C → D → E
```

For very deep graphs, recursion may exceed the Java call-stack limit and produce:

```text
StackOverflowError
```

An iterative implementation may be safer when graph depth is large or unpredictable.

---

# 32. Complexity Summary

| Representation | DFS Time | Additional Space |
|---|---:|---:|
| Adjacency List | O(V + E) | O(V) |
| Adjacency Matrix | O(V²) | O(V) |

The additional space includes the visited structure and the recursion or explicit stack.

---

# 33. Java-Oriented Recursive Design

A recursive Java implementation may use:

```java
Set<V> visited = new HashSet<>();
List<V> traversalOrder = new ArrayList<>();
```

Conceptual method:

```java
private static <V> void dfs(
        Map<V, List<V>> graph,
        V current,
        Set<V> visited,
        List<V> traversalOrder) {

    visited.add(current);
    traversalOrder.add(current);

    for (V neighbour : graph.getOrDefault(current, List.of())) {
        if (!visited.contains(neighbour)) {
            dfs(graph, neighbour, visited, traversalOrder);
        }
    }
}
```

A public method can initialize the required state and validate the source.

---

# 34. Illustrative Recursive Java Implementation

```java
public static <V> List<V> depthFirstSearch(
        Map<V, List<V>> graph,
        V source) {

    Objects.requireNonNull(graph, "graph must not be null");
    Objects.requireNonNull(source, "source must not be null");

    if (!graph.containsKey(source)) {
        throw new IllegalArgumentException(
                "source must belong to the graph"
        );
    }

    Set<V> visited = new HashSet<>();
    List<V> traversalOrder = new ArrayList<>();

    depthFirstSearch(
            graph,
            source,
            visited,
            traversalOrder
    );

    return List.copyOf(traversalOrder);
}
```

Helper method:

```java
private static <V> void depthFirstSearch(
        Map<V, List<V>> graph,
        V current,
        Set<V> visited,
        List<V> traversalOrder) {

    visited.add(current);
    traversalOrder.add(current);

    for (V neighbour : graph.getOrDefault(current, List.of())) {
        if (!visited.contains(neighbour)) {
            depthFirstSearch(
                    graph,
                    neighbour,
                    visited,
                    traversalOrder
            );
        }
    }
}
```

---

# 35. Java-Oriented Iterative Design

An iterative implementation may use:

```java
Deque<V> stack = new ArrayDeque<>();
Set<V> visited = new HashSet<>();
```

Conceptual structure:

```text
stack.push(source);

while (!stack.isEmpty()) {
    V current = stack.pop();

    if (visited.add(current)) {
        process(current);

        for (V neighbour : neighbours) {
            if (!visited.contains(neighbour)) {
                stack.push(neighbour);
            }
        }
    }
}
```

The neighbour push order determines the traversal order.

---

# 36. Illustrative Iterative Java Implementation

```java
public static <V> List<V> iterativeDepthFirstSearch(
        Map<V, List<V>> graph,
        V source) {

    Objects.requireNonNull(graph, "graph must not be null");
    Objects.requireNonNull(source, "source must not be null");

    if (!graph.containsKey(source)) {
        throw new IllegalArgumentException(
                "source must belong to the graph"
        );
    }

    Deque<V> stack = new ArrayDeque<>();
    Set<V> visited = new HashSet<>();
    List<V> traversalOrder = new ArrayList<>();

    stack.push(source);

    while (!stack.isEmpty()) {
        V current = stack.pop();

        if (!visited.add(current)) {
            continue;
        }

        traversalOrder.add(current);

        List<V> neighbours =
                graph.getOrDefault(current, List.of());

        for (int index = neighbours.size() - 1;
             index >= 0;
             index--) {

            V neighbour = neighbours.get(index);

            if (!visited.contains(neighbour)) {
                stack.push(neighbour);
            }
        }
    }

    return List.copyOf(traversalOrder);
}
```

Pushing neighbours in reverse preserves a left-to-right order similar to the recursive implementation when the neighbour collection is ordered.

---

# 37. Input Validation

A robust DFS implementation should define behaviour for:

- null graph;
- null source;
- absent source;
- unknown neighbour vertices;
- duplicate adjacency entries;
- null neighbour collections;
- malformed directed or undirected relationships.

Prefer validating graph invariants during graph construction rather than repeatedly repairing invalid input inside traversal algorithms.

---

# 38. Traversal Order Stability

DFS does not guarantee one universal traversal sequence.

For:

```text
A → B, C
```

one neighbour order may produce:

```text
A → B → ...
```

Another may produce:

```text
A → C → ...
```

Both can be valid DFS traversals.

Deterministic traversal requires a deterministic neighbour order, such as:

- ordered list;
- insertion-ordered set;
- sorted collection.

Tests should not assume an unspecified ordering.

---

# 39. Common Applications

DFS is commonly used for:

- reachability;
- connected components;
- cycle detection;
- topological sorting;
- path existence;
- maze exploration;
- strongly connected components;
- articulation points;
- bridge detection;
- dependency analysis;
- backtracking;
- state-space exploration.

Many advanced graph algorithms are extensions of DFS.

---

# 40. Maze Exploration

A maze can be represented as:

```text
Cell = Vertex
Valid Movement = Edge
```

DFS explores one route until reaching:

- the destination;
- a dead end.

At a dead end, it backtracks and tries another route.

DFS can find a valid path, although not necessarily the shortest one.

---

# 41. Dependency Analysis

In a software dependency graph:

```text
Module = Vertex
Dependency = Directed Edge
```

DFS can:

- detect circular dependencies;
- determine processing order;
- identify reachable dependencies;
- support build sequencing.

This makes DFS valuable in build systems, package managers, compilers, and workflow engines.

---

# 42. Backtracking Relationship

Backtracking algorithms often follow a DFS structure.

Conceptually:

```text
Choose Option
        ↓
Continue Recursively
        ↓
Valid Solution?
        ↓
No
        ↓
Undo Choice
        ↓
Try Another Option
```

Applications include:

- maze solving;
- Sudoku;
- permutations;
- combinations;
- constraint satisfaction;
- game-state exploration.

DFS provides the traversal strategy, while backtracking adds state reversal and constraint checking.

---

# 43. Common Mistakes

## Forgetting the visited structure

Cycles may cause infinite recursion or repeated processing.

## Marking vertices after recursion

A cycle may be entered before the current vertex is protected.

## Assuming DFS finds the shortest path

DFS finds a path, not necessarily the minimum one.

## Ignoring disconnected components

One execution visits only the source component.

## Confusing iterative push order

Pushing neighbours in natural order reverses their processing order.

## Using recursion without considering graph depth

Large graphs may cause stack overflow.

## Ignoring direction

Directed edges must be followed only in their valid direction.

## Treating every visited neighbour as a cycle in an undirected graph

The parent edge must be distinguished.

## Assuming one exact traversal order

Neighbour ordering may produce multiple valid results.

---

# 44. Automated Testing Strategy

A DFS implementation should be tested with:

## Empty graph

Verify the defined contract.

## Single vertex

Expected traversal:

```text
A
```

## Linear graph

Verify deep traversal.

## Branching graph

Verify backtracking.

## Cyclic graph

Confirm termination and unique visitation.

## Disconnected graph

Confirm that source-based DFS remains within one component.

## Directed graph

Verify that direction is respected.

## Deep graph

Evaluate whether recursive implementation limits are acceptable.

## Multiple valid traversal orders

Avoid overconstraining tests when neighbour order is unspecified.

---

# 45. Representative Test Properties

Useful properties include:

- the source is visited first;
- every result vertex is reachable from the source;
- no vertex appears more than once;
- every reachable vertex appears exactly once;
- every predecessor relationship corresponds to an edge;
- predecessor chains lead back to the source;
- an empty recursion or explicit stack remains after completion;
- complete-graph traversal produces one tree per component;
- directed-cycle detection identifies back edges correctly.

---

# 46. DFS versus BFS

| Characteristic | DFS | BFS |
|---|---|---|
| Exploration | Branch by branch | Level by level |
| Main structure | Stack or recursion | Queue |
| Unweighted shortest path | Not guaranteed | Guaranteed |
| Cycle detection | Natural with states | Possible |
| Topological sorting | Commonly used | Possible through in-degree strategy |
| Memory pattern | Depends on depth | Depends on frontier width |
| Backtracking | Natural | Not central |
| Common use | Dependencies, cycles, exhaustive exploration | Distances, levels, shortest unweighted paths |

The correct traversal depends on the problem rather than preference.

---

# 47. Engineering Perspective

Depth-First Search demonstrates how recursion and stack behaviour can systematically explore complex relationships.

Its importance extends beyond simple traversal. DFS provides the structural foundation for:

- topological sorting;
- cycle detection;
- strongly connected components;
- dependency validation;
- bridge and articulation-point algorithms;
- backtracking systems.

A professional implementation must also consider:

- graph representation;
- recursion depth;
- neighbour ordering;
- cycle behaviour;
- directed versus undirected semantics;
- deterministic testing;
- memory constraints.

---

# 48. Relationship with the Next Document

This document introduced depth-oriented graph exploration.

The next document studies **Topological Sorting**, an ordering algorithm for directed acyclic graphs.

DFS supports topological sorting through vertex finishing order.

The progression is:

```text
Depth-First Search
        ↓
Complete Dependencies First
        ↓
Record Finishing Order
        ↓
Reverse the Result
        ↓
Topological Ordering
```

---

# 49. Key Takeaways

After completing this document, the learner should understand that:

- DFS explores one branch deeply before backtracking;
- the algorithm uses recursion or an explicit stack;
- visited-state management prevents repeated processing and infinite cycles;
- recursive calls implicitly represent the current traversal path;
- DFS visits every vertex reachable from the source;
- disconnected graphs require multiple DFS executions;
- DFS can find a valid path but does not guarantee the shortest one;
- cycle detection depends on graph direction and traversal state;
- DFS supports topological sorting and many advanced graph algorithms;
- adjacency-list DFS requires `O(V + E)` time;
- adjacency-matrix DFS requires `O(V²)` time;
- recursion depth may reach `O(V)`;
- traversal order depends on neighbour ordering;
- iterative DFS provides greater control for large or deeply nested graphs.