# Topological Sorting

## Algorithms Java Mastery

**Topological Sorting** is a graph algorithm that produces a linear ordering of the vertices in a **Directed Acyclic Graph (DAG)**.

The resulting order respects every directed dependency in the graph. For each edge:

```text
u → v
```

vertex `u` must appear before vertex `v`.

This makes topological sorting especially useful for problems involving prerequisites, dependency resolution, build order, task scheduling, workflow execution, and compilation.

Unlike BFS and DFS, topological sorting is not a traversal of arbitrary graphs. It applies only when the graph is:

```text
Directed
        +
Acyclic
```

If the graph contains a directed cycle, no valid topological ordering exists.

---

# 1. Purpose

The purpose of this document is to:

- define topological sorting;
- explain why it applies only to DAGs;
- understand dependency ordering;
- study DFS-based and in-degree-based strategies;
- reason about correctness;
- analyse computational complexity;
- identify common applications and mistakes.

---

# 2. What Is Topological Sorting?

A **topological ordering** is a sequence of all vertices in a directed graph such that every dependency appears before the vertex that depends on it.

Consider:

```text
A → C
B → C
C → D
```

Valid topological orders include:

```text
A → B → C → D
```

and:

```text
B → A → C → D
```

Both are valid because:

- `A` appears before `C`;
- `B` appears before `C`;
- `C` appears before `D`.

The relative order between `A` and `B` is not constrained.

---

# 3. Dependency Interpretation

A directed edge:

```text
u → v
```

may be interpreted as:

```text
u must be completed before v
```

Examples:

```text
Course A → Course B
```

Course A is a prerequisite for Course B.

```text
Compile Module A → Compile Module B
```

Module B depends on Module A.

```text
Task A → Task B
```

Task B cannot begin before Task A is complete.

Topological sorting transforms these partial dependencies into one valid linear sequence.

---

# 4. Why the Graph Must Be Directed

Dependency relationships have direction.

For example:

```text
A → B
```

means:

```text
A precedes B
```

It does not imply:

```text
B precedes A
```

An undirected edge does not express this ordering relationship.

Therefore, topological sorting is defined for directed graphs.

---

# 5. Why the Graph Must Be Acyclic

Consider:

```text
A → B
B → C
C → A
```

The dependencies imply:

```text
A before B
B before C
C before A
```

No vertex can be placed first without violating another dependency.

Therefore:

```text
Directed Cycle
        ↓
No Topological Ordering
```

A valid topological ordering exists if and only if the directed graph is acyclic.

---

# 6. Topological Order Is Usually Not Unique

A DAG may have multiple valid topological orders.

Example:

```text
A → C
B → C
```

Both sequences are valid:

```text
A → B → C
```

```text
B → A → C
```

The graph constrains only the pairs connected through dependencies.

Independent vertices may appear in different relative positions.

An implementation should not assume that one specific valid order is the only correct result.

---

# 7. Example DAG

Consider:

```text
A → C
B → C
B → D
C → E
D → E
```

Graphically:

```text
A ───▶ C ───▶ E
        ▲       ▲
        │       │
B ──────┘       │
└──────▶ D ─────┘
```

Possible topological order:

```text
A → B → C → D → E
```

Another valid order:

```text
B → A → D → C → E
```

The essential requirement is that every source of an edge appears before its destination.

---

# 8. Principal Strategies

Two common algorithms are used for topological sorting.

```text
Topological Sorting
├── DFS Finishing Order
└── Kahn's Algorithm
```

The first uses:

```text
Depth-First Search
```

The second uses:

```text
In-degree
        +
Queue
```

Both can achieve:

```text
O(V + E)
```

with an adjacency-list representation.

---

# 9. DFS-Based Strategy

The DFS-based approach relies on the principle that a vertex should be added to the result only after all vertices reachable through its outgoing dependencies have been fully explored.

Conceptually:

```text
Visit Vertex
        ↓
Explore All Outgoing Neighbours
        ↓
Finish Vertex
        ↓
Add to Stack
```

After all vertices have been processed, the finishing order is reversed.

---

# 10. Why Finishing Order Works

Suppose:

```text
A → B
```

DFS starting from `A` first explores `B`.

```text
DFS(A)
    ↓
DFS(B)
```

`B` finishes before `A`.

Finishing sequence:

```text
B, A
```

Reversing it gives:

```text
A, B
```

which respects the dependency.

More generally, for every edge:

```text
u → v
```

a valid DFS-based topological sort ensures that `u` appears before `v` in the reversed finishing order.

---

# 11. DFS-Based Conceptual Pseudocode

```text
TOPOLOGICAL-SORT(graph)

    create state map
    create empty stack

    for each vertex in graph

        if vertex is undiscovered

            DFS-VISIT(vertex)

    return reverse(stack)
```

DFS helper:

```text
DFS-VISIT(vertex)

    mark vertex as active

    for each outgoing neighbour

        if neighbour is active

            report cycle

        if neighbour is undiscovered

            DFS-VISIT(neighbour)

    mark vertex as finished
    push vertex onto stack
```

Cycle detection is necessary because finishing order alone is not sufficient when the graph contains a cycle.

---

# 12. DFS Vertex States

The DFS strategy commonly uses three states.

```text
WHITE
```

The vertex has not been discovered.

```text
GRAY
```

The vertex is currently active in the recursion stack.

```text
BLACK
```

The vertex and all its outgoing dependencies have been processed.

A directed cycle exists when the algorithm finds an edge toward a `GRAY` vertex.

---

# 13. DFS Cycle Detection

Consider:

```text
A → B → C
↑       |
└───────┘
```

During DFS:

```text
A = GRAY
B = GRAY
C = GRAY
```

From `C`, the edge points to `A`, which is still active.

```text
C → A
```

Therefore:

```text
Edge to GRAY Vertex
        ↓
Back Edge
        ↓
Directed Cycle
```

The algorithm must reject the graph because no topological ordering exists.

---

# 14. DFS Manual Example

Consider:

```text
A → C
B → C
C → D
```

Start DFS from `A`.

```text
A = GRAY
```

Explore `C`.

```text
C = GRAY
```

Explore `D`.

```text
D = GRAY
```

`D` has no outgoing neighbours.

```text
D = BLACK
Stack = [D]
```

Return to `C`.

```text
C = BLACK
Stack = [D, C]
```

Return to `A`.

```text
A = BLACK
Stack = [D, C, A]
```

Then process unvisited `B`.

```text
B = BLACK
Stack = [D, C, A, B]
```

Reverse the finishing order:

```text
B → A → C → D
```

This is a valid topological ordering.

---

# 15. Kahn's Algorithm

Kahn's algorithm constructs a topological ordering by repeatedly selecting vertices with no unresolved incoming dependencies.

A vertex with:

```text
in-degree = 0
```

has no prerequisite remaining.

It can therefore be placed next in the ordering.

Conceptually:

```text
Find Vertices with In-Degree 0
        ↓
Add Them to Queue
        ↓
Remove One Vertex
        ↓
Add It to Result
        ↓
Remove Its Outgoing Edges
        ↓
Update In-Degrees
        ↓
Add Newly Available Vertices
        ↓
Repeat
```

---

# 16. In-Degree

The **in-degree** of a vertex is the number of incoming edges.

Example:

```text
A → C ← B
```

Then:

```text
in-degree(A) = 0
in-degree(B) = 0
in-degree(C) = 2
```

Vertices `A` and `B` can appear before `C`.

When both incoming edges to `C` are conceptually removed, its in-degree becomes zero.

---

# 17. Kahn's Algorithm Pseudocode

```text
KAHN-TOPOLOGICAL-SORT(graph)

    calculate in-degree for every vertex
    create queue

    for each vertex

        if in-degree(vertex) = 0

            enqueue vertex

    create empty result

    while queue is not empty

        current = dequeue
        add current to result

        for each outgoing neighbour

            decrease in-degree(neighbour)

            if in-degree(neighbour) = 0

                enqueue neighbour

    if result contains fewer than V vertices

        report cycle

    return result
```

The queue determines which currently available vertex is processed next.

---

# 18. Kahn's Algorithm Manual Example

Consider:

```text
A → C
B → C
B → D
C → E
D → E
```

Initial in-degrees:

| Vertex | In-degree |
|---|---:|
| A | 0 |
| B | 0 |
| C | 2 |
| D | 1 |
| E | 2 |

Initial queue:

```text
[A, B]
```

---

## Process A

Add `A` to result.

```text
Result = [A]
```

Decrease in-degree of `C`.

```text
in-degree(C) = 1
```

Queue:

```text
[B]
```

---

## Process B

Add `B`.

```text
Result = [A, B]
```

Update:

```text
in-degree(C) = 0
in-degree(D) = 0
```

Queue:

```text
[C, D]
```

---

## Process C

Add `C`.

```text
Result = [A, B, C]
```

Update:

```text
in-degree(E) = 1
```

Queue:

```text
[D]
```

---

## Process D

Add `D`.

```text
Result = [A, B, C, D]
```

Update:

```text
in-degree(E) = 0
```

Queue:

```text
[E]
```

---

## Process E

Final result:

```text
A → B → C → D → E
```

All vertices were processed, so the graph is acyclic.

---

# 19. Cycle Detection with Kahn's Algorithm

Suppose the graph contains:

```text
A → B
B → C
C → A
```

Initial in-degrees:

```text
A = 1
B = 1
C = 1
```

No vertex has in-degree zero.

Therefore:

```text
Queue = []
```

The algorithm processes zero vertices.

Since:

```text
processed vertices < total vertices
```

a directed cycle exists.

This cycle-detection mechanism is one of Kahn's algorithm's main advantages.

---

# 20. Correctness Intuition for Kahn's Algorithm

A vertex with in-degree zero has no unresolved predecessor.

Therefore, placing it next cannot violate any incoming dependency.

When the vertex is processed, its outgoing edges are removed conceptually.

This may make other vertices eligible.

The algorithm repeats until:

- every vertex has been ordered; or
- no eligible vertex exists.

If vertices remain but no in-degree-zero vertex exists, those remaining vertices participate in or depend on a directed cycle.

---

# 21. DFS versus Kahn's Algorithm

| Characteristic | DFS-Based | Kahn's Algorithm |
|---|---|---|
| Main structure | Recursion or stack | Queue |
| Core concept | Reverse finishing order | Repeated in-degree-zero removal |
| Cycle detection | Edge to active vertex | Fewer than `V` vertices processed |
| Additional data | State map and result stack | In-degree map and queue |
| Natural relation | DFS | BFS-like processing |
| Recursion risk | Possible | None |
| Incremental dependency view | Less explicit | Very clear |

Both are valid and efficient.

The appropriate choice depends on implementation requirements and problem context.

---

# 22. Preconditions

Topological sorting requires:

- a directed graph;
- a well-defined vertex set;
- outgoing adjacency information;
- no malformed edges;
- cycle detection if acyclicity is not already guaranteed.

A topological sorting method should not silently return a misleading order for a cyclic graph.

---

# 23. Postconditions

For a valid DAG, the returned sequence must satisfy:

- every vertex appears exactly once;
- the result contains all vertices;
- for every edge `u → v`, `u` appears before `v`.

For a cyclic graph, the method should:

- report the cycle;
- return an explicit failure result;
- or throw a documented exception.

The behaviour must be part of the algorithm contract.

---

# 24. Topological Order Validation

A candidate ordering can be validated by storing the position of every vertex.

Example order:

```text
A, B, C, D
```

Position map:

```text
A → 0
B → 1
C → 2
D → 3
```

For every edge:

```text
u → v
```

verify:

```text
position(u) < position(v)
```

If this property holds for every edge, the ordering is valid.

---

# 25. Complexity with an Adjacency List

For both DFS-based topological sorting and Kahn's algorithm:

```text
Time = O(V + E)
```

Reasoning:

- each vertex is processed a constant number of times;
- every edge is examined once.

Additional space:

```text
O(V)
```

for structures such as:

- state map;
- recursion or explicit stack;
- in-degree map;
- queue;
- result list.

The graph representation itself requires:

```text
O(V + E)
```

space.

---

# 26. Complexity with an Adjacency Matrix

With an adjacency matrix, every vertex may require scanning a complete row.

Therefore:

```text
Time = O(V²)
```

The additional algorithmic space remains:

```text
O(V)
```

This again demonstrates the impact of representation on graph algorithm complexity.

---

# 27. Java-Oriented Kahn Design

A Java implementation may use:

```java
Map<V, Integer> inDegree;
Queue<V> available;
List<V> ordering;
```

Typical concrete collections:

```text
HashMap
ArrayDeque
ArrayList
```

The algorithm should calculate in-degree from the graph rather than trusting inconsistent external values unless the graph abstraction guarantees correctness.

---

# 28. Illustrative Kahn Implementation

```java
public static <V> List<V> topologicalSort(
        Map<V, List<V>> graph) {

    Objects.requireNonNull(graph, "graph must not be null");

    Map<V, Integer> inDegree = new HashMap<>();

    for (V vertex : graph.keySet()) {
        inDegree.put(vertex, 0);
    }

    for (Map.Entry<V, List<V>> entry : graph.entrySet()) {
        for (V neighbour : entry.getValue()) {
            if (!inDegree.containsKey(neighbour)) {
                throw new IllegalArgumentException(
                        "every edge destination must belong to the graph"
                );
            }

            inDegree.merge(neighbour, 1, Integer::sum);
        }
    }

    Queue<V> available = new ArrayDeque<>();

    for (Map.Entry<V, Integer> entry : inDegree.entrySet()) {
        if (entry.getValue() == 0) {
            available.offer(entry.getKey());
        }
    }

    List<V> ordering = new ArrayList<>();

    while (!available.isEmpty()) {
        V current = available.poll();
        ordering.add(current);

        for (V neighbour : graph.getOrDefault(current, List.of())) {
            int updatedDegree = inDegree.merge(
                    neighbour,
                    -1,
                    Integer::sum
            );

            if (updatedDegree == 0) {
                available.offer(neighbour);
            }
        }
    }

    if (ordering.size() != graph.size()) {
        throw new IllegalArgumentException(
                "graph contains a directed cycle"
        );
    }

    return List.copyOf(ordering);
}
```

---

# 29. Deterministic Ordering

A DAG may have multiple valid orders.

Using:

```text
HashMap
```

or unordered neighbour collections may produce different valid results across executions or implementations.

When deterministic ordering is required, use structures such as:

```text
LinkedHashMap
```

```text
LinkedHashSet
```

or a priority queue with a comparator.

For example, Kahn's algorithm may use:

```text
PriorityQueue<V>
```

to always select the smallest currently available vertex.

This produces a lexicographically minimal valid ordering when the comparator defines that order.

---

# 30. Java-Oriented DFS Design

A DFS-based implementation may use:

```text
Map<V, State> states;
Deque<V> result;
```

Possible enum:

```text
private enum State {
    UNDISCOVERED,
    ACTIVE,
    FINISHED
}
```

When a neighbour is `ACTIVE`, a cycle exists.

When a vertex finishes, it is added to the front of the result or pushed onto a stack.

---

# 31. Illustrative DFS Helper

```text
private static <V> void visit(
        Map<V, List<V>> graph,
        V current,
        Map<V, State> states,
        Deque<V> ordering) {

    states.put(current, State.ACTIVE);

    for (V neighbour : graph.getOrDefault(current, List.of())) {
        State neighbourState = states.get(neighbour);

        if (neighbourState == State.ACTIVE) {
            throw new IllegalArgumentException(
                    "graph contains a directed cycle"
            );
        }

        if (neighbourState == State.UNDISCOVERED) {
            visit(graph, neighbour, states, ordering);
        }
    }

    states.put(current, State.FINISHED);
    ordering.addFirst(current);
}
```

Adding each finished vertex to the front avoids a separate reversal step.

---

# 32. Common Applications

Topological sorting is used in:

- build systems;
- package managers;
- course prerequisite planning;
- task scheduling;
- workflow orchestration;
- compiler dependency analysis;
- spreadsheet recalculation;
- database migration ordering;
- service initialization;
- deployment pipelines.

The common pattern is:

```text
Some operations depend on others
```

---

# 33. Build Systems

Suppose:

```text
Compile Core
        ↓
Compile Service
        ↓
Package Application
```

A build system must respect these dependencies.

Topological sorting produces a valid execution order.

Independent modules may be compiled in different relative orders or even in parallel.

---

# 34. Course Prerequisites

Consider:

```text
Programming I → Data Structures
Data Structures → Algorithms
Discrete Mathematics → Algorithms
```

A valid course order must place every prerequisite before the dependent course.

Topological sorting can produce one acceptable study sequence.

---

# 35. Workflow Scheduling

In a workflow:

```text
Validate Data
      ↓
Process Data
      ↓
Generate Report
```

Topological ordering identifies which tasks are ready and which remain blocked by dependencies.

Kahn's algorithm is particularly intuitive for this use because in-degree represents the number of unresolved prerequisites.

---

# 36. Parallel Processing

Topological sorting can also reveal opportunities for parallel execution.

If multiple vertices currently have in-degree zero:

```text
A
B
C
```

they have no unresolved dependencies among the remaining tasks.

They may be eligible for concurrent processing, subject to external resource constraints.

The topological order itself is sequential, but the dependency levels can support parallel scheduling.

---

# 37. Common Mistakes

## Applying the algorithm to an undirected graph

Topological order requires directed dependencies.

## Ignoring cycles

A cyclic graph has no valid ordering.

## Assuming the result is unique

Many DAGs have multiple correct orders.

## Reversing the edge meaning

Misinterpreting:

```text
A → B
```

may produce the opposite order.

## Forgetting isolated vertices

Vertices with no incoming or outgoing edges still belong in the result.

## Modifying shared in-degree state unintentionally

If the in-degree map belongs to the graph object, Kahn's algorithm may corrupt reusable graph state.

## Using DFS without active-state cycle detection

A cyclic graph may produce an invalid result or infinite recursion.

## Overconstraining automated tests

A test should validate dependencies rather than require one arbitrary order unless deterministic ordering is part of the contract.

---

# 38. Automated Testing Strategy

Representative test cases include:

## Empty graph

Expected result:

```text
empty ordering
```

## Single vertex

Expected:

```text
[A]
```

## Linear dependency

```text
A → B → C
```

Only one valid ordering exists:

```text
A, B, C
```

## Independent vertices

Multiple valid orders should be accepted.

## Diamond dependency

```text
A → B
A → C
B → D
C → D
```

Verify:

```text
A before B and C
B and C before D
```

## Disconnected DAG

All vertices must appear.

## Directed cycle

The implementation must reject the graph.

## Self-loop

```text
A → A
```

This is a cycle and must be rejected.

---

# 39. Property-Based Validation

Rather than checking one exact sequence, tests can validate these properties:

- result size equals `V`;
- every vertex appears once;
- every graph vertex is present;
- no unknown vertex appears;
- for every edge `u → v`, `u` appears before `v`;
- cyclic graphs produce failure;
- isolated vertices appear in the result.

This approach is more robust when multiple valid topological orders exist.

---

# 40. Engineering Perspective

Topological sorting is an example of how graph structure directly models real engineering constraints.

The algorithm does not merely arrange data. It produces a valid execution sequence from a partial dependency relation.

Professional implementations should define:

- edge direction semantics;
- cycle-handling policy;
- deterministic-order requirements;
- concurrency opportunities;
- graph mutation rules;
- scalability expectations.

For large dependency systems, adjacency lists provide efficient processing in:

```text
O(V + E)
```

time.

---

# 41. Relationship with the Next Document

Topological sorting orders vertices according to dependencies.

The next document studies **Shortest Paths**, where the objective changes from dependency ordering to minimizing the cost of travelling from one vertex to another.

The progression is:

```text
Traversal
        ↓
Dependency Ordering
        ↓
Path Optimization
```

Different shortest-path algorithms are required depending on:

- whether the graph is weighted;
- whether edge weights are negative;
- whether one source or every pair is involved.

---

# 42. Key Takeaways

After completing this document, the learner should understand that:

- topological sorting produces a linear ordering of a DAG;
- for every edge `u → v`, `u` must appear before `v`;
- a valid topological order exists only for directed acyclic graphs;
- the result is not necessarily unique;
- DFS can produce an order through reversed finishing times;
- DFS cycle detection requires active-vertex state;
- Kahn's algorithm repeatedly processes in-degree-zero vertices;
- Kahn's algorithm detects cycles when fewer than `V` vertices are processed;
- both principal strategies require `O(V + E)` time with adjacency lists;
- tests should validate dependency constraints rather than one arbitrary order;
- topological sorting is fundamental for dependency resolution, scheduling, build systems, workflows, and compilation.