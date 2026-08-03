# Shortest Paths

## Algorithms Java Mastery

A **shortest-path problem** asks for the minimum-cost route between vertices in a graph.

The meaning of *cost* depends on the graph model. In an unweighted graph, the cost of a path is usually the number of edges it contains. In a weighted graph, the cost is calculated by adding the weights of the edges along the path.

Shortest-path algorithms are fundamental in navigation systems, network routing, logistics, dependency analysis, communication infrastructure, and many backend applications.

Different graph conditions require different algorithms. No single shortest-path algorithm is appropriate for every graph.

---

# 1. Purpose

The purpose of this document is to:

- define the shortest-path problem;
- distinguish distance, path length, and path cost;
- identify the principal shortest-path problem variants;
- explain when BFS, Dijkstra, Bellman–Ford, and Floyd–Warshall should be used;
- understand the effect of negative edge weights;
- analyse correctness and computational complexity;
- prepare the learner for Java implementations.

---

# 2. Path Cost

Consider the weighted path:

```text
A ──4──▶ B ──3──▶ C
```

Its total cost is:

```text
4 + 3 = 7
```

More generally, for a path:

```text
v₀ → v₁ → v₂ → ... → vₖ
```

the path cost is the sum of its edge weights:

```text
w(v₀, v₁)
+
w(v₁, v₂)
+
...
+
w(vₖ₋₁, vₖ)
```

The shortest path is the valid path with the minimum total cost.

---

# 3. Path Length and Path Cost

These concepts should not be confused.

## Path length

The number of edges contained in the path.

```text
A → B → C → D
```

Path length:

```text
3
```

## Path cost

The sum of the edge weights.

```text
A ──2──▶ B ──5──▶ C
```

Path cost:

```text
7
```

In an unweighted graph, every edge may be treated as having cost one. In that case, path length and path cost are equivalent.

---

# 4. Shortest-Path Variants

Shortest-path problems are commonly classified according to the required sources and destinations.

## Single-Source Shortest Paths

Find the shortest paths from one source to every reachable vertex.

```text
Source
   ↓
All Other Vertices
```

Examples:

- BFS;
- Dijkstra's algorithm;
- Bellman–Ford algorithm.

---

## Single-Pair Shortest Path

Find the shortest path between one source and one destination.

```text
Source → Destination
```

A single-source algorithm may stop early when the required destination has been finalized.

---

## Single-Destination Shortest Paths

Find the shortest paths from every vertex to one destination.

This problem can sometimes be transformed by reversing the graph's edges and running a single-source algorithm from the destination.

---

## All-Pairs Shortest Paths

Find the shortest path between every pair of vertices.

```text
Every Vertex
        ↓
Every Other Vertex
```

A classical solution is:

```text
Floyd–Warshall
```

---

# 5. Unweighted Graphs

In an unweighted graph, every edge has equal cost.

Example:

```text
A ─── B ─── D
 \         /
  └── C ──┘
```

The shortest path from `A` to `D` contains the minimum number of edges.

Breadth-First Search is appropriate because it explores vertices level by level.

```text
Distance 0 → Source
Distance 1 → Immediate Neighbours
Distance 2 → Next Level
```

Therefore:

```text
BFS shortest-path complexity = O(V + E)
```

with an adjacency list.

---

# 6. Weighted Graphs

In weighted graphs, paths with fewer edges are not necessarily cheaper.

Example:

```text
A ──10──▶ B
│
2
│
▼
C ──3──▶ B
```

Direct path:

```text
A → B

Cost = 10
```

Alternative path:

```text
A → C → B

Cost = 2 + 3 = 5
```

Although the alternative contains more edges, it has a lower total cost.

This is why BFS cannot solve general weighted shortest-path problems.

---

# 7. Relaxation

The central operation used by many shortest-path algorithms is called **relaxation**.

Suppose an edge connects:

```text
u → v
```

with weight:

```text
w(u, v)
```

If reaching `v` through `u` is cheaper than the currently known route, the distance is updated.

Conceptually:

```text
Known Distance to u
        +
Edge Weight
        <
Known Distance to v
```

Then:

```text
distance[v] =
distance[u] + weight(u, v)
```

The predecessor of `v` is also updated:

```text
predecessor[v] = u
```

---

# 8. Initial Distance Values

At the beginning of a single-source shortest-path algorithm:

```text
distance[source] = 0
```

For every other vertex:

```text
distance[vertex] = ∞
```

This means that no route to those vertices is known yet.

Example:

| Vertex | Initial Distance |
|---|---:|
| A | 0 |
| B | ∞ |
| C | ∞ |
| D | ∞ |

Relaxation progressively replaces infinite or expensive distances with better values.

---

# 9. Predecessor Tracking

Distances indicate the minimum cost but do not directly describe the path.

To reconstruct the route, the algorithm stores a predecessor for every improved vertex.

Example:

```text
predecessor[B] = A
predecessor[C] = B
predecessor[D] = C
```

To reconstruct the path from `A` to `D`:

```text
D
↓
C
↓
B
↓
A
```

Reverse the sequence:

```text
A → B → C → D
```

---

# 10. Dijkstra's Algorithm

**Dijkstra's algorithm** solves single-source shortest-path problems in graphs whose edge weights are non-negative.

Its central strategy is:

```text
Select Unprocessed Vertex
with Minimum Known Distance
        ↓
Finalize Its Distance
        ↓
Relax Its Outgoing Edges
        ↓
Repeat
```

A priority queue is commonly used to select the next vertex efficiently.

---

# 11. Dijkstra Example

Consider:

```text
A ──4──▶ B
│        │
1        2
│        │
▼        ▼
C ──1──▶ D
```

Starting from `A`:

```text
distance[A] = 0
distance[B] = ∞
distance[C] = ∞
distance[D] = ∞
```

Relax edges from `A`:

```text
distance[B] = 4
distance[C] = 1
```

The smallest tentative distance belongs to `C`.

Relax:

```text
distance[D] = 2
```

Next process `D`, followed by `B`.

Final distances:

| Vertex | Distance from A |
|---|---:|
| A | 0 |
| B | 4 |
| C | 1 |
| D | 2 |

---

# 12. Why Dijkstra Requires Non-Negative Weights

Dijkstra assumes that once the smallest tentative distance is selected, that distance cannot later be improved.

Negative edges invalidate this assumption.

Example:

```text
A ──2──▶ B
│
5
│
▼
C ──-10──▶ B
```

The algorithm may initially accept:

```text
A → B = 2
```

but another route produces:

```text
A → C → B = 5 + (-10) = -5
```

A previously finalized distance would need to change.

Therefore, standard Dijkstra must not be used with negative edge weights.

---

# 13. Dijkstra with a Priority Queue

Java commonly uses:

```text
PriorityQueue<NodeDistance<V>>
```

The priority queue orders entries by tentative distance.

Conceptually:

```text
Smallest Distance
        ↓
Highest Priority
```

Because Java's `PriorityQueue` does not efficiently support arbitrary priority updates, implementations often insert a new entry when a shorter path is discovered.

Old entries may remain in the queue and must be ignored when removed.

---

# 14. Stale Priority-Queue Entries

Suppose the queue contains:

```text
(B, 10)
```

Later, a better path is found:

```text
(B, 4)
```

The new entry is added:

```text
(B, 4)
```

When `(B, 10)` is eventually removed, it is outdated.

Conceptual validation:

```text
if queuedDistance > knownDistance
    ignore entry
```

This strategy preserves correctness while avoiding the need to modify an existing priority-queue entry.

---

# 15. Dijkstra Complexity

With an adjacency list and binary heap:

```text
O((V + E) log V)
```

This is commonly simplified to:

```text
O(E log V)
```

for connected graphs where the number of edges is at least proportional to the number of vertices.

Additional space:

```text
O(V + E)
```

including:

- graph representation;
- distance map;
- predecessor map;
- priority queue.

With an adjacency matrix and linear minimum selection:

```text
O(V²)
```

---

# 16. Bellman–Ford Algorithm

The **Bellman–Ford algorithm** computes single-source shortest paths even when the graph contains negative edge weights.

Its strategy is to relax every edge repeatedly.

```text
Relax All Edges
        ↓
Repeat V - 1 Times
```

Why `V - 1`?

A simple shortest path can contain at most:

```text
V - 1 edges
```

because repeating a vertex would create a cycle.

---

# 17. Bellman–Ford Process

Conceptual pseudocode:

```text
initialize distances

repeat V - 1 times

    for each edge (u, v)

        relax(u, v)
```

Afterward, the algorithm performs one additional pass.

If any distance can still be improved:

```text
Reachable Negative Cycle Exists
```

---

# 18. Negative Cycles

A **negative cycle** is a cycle whose total weight is negative.

Example:

```text
A ──1──▶ B
▲        │
│       -4
│        ▼
└──2──── C
```

Cycle cost:

```text
1 + (-4) + 2 = -1
```

Every traversal of the cycle lowers the total path cost further.

```text
-1
-2
-3
...
```

Therefore, no finite shortest path exists for vertices affected by a reachable negative cycle.

---

# 19. Bellman–Ford Complexity

Bellman–Ford performs:

```text
V - 1 passes
```

over:

```text
E edges
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

Bellman–Ford is slower than Dijkstra but supports negative weights and negative-cycle detection.

---

# 20. Floyd–Warshall Algorithm

The **Floyd–Warshall algorithm** computes shortest paths between every pair of vertices.

It uses dynamic programming.

The central idea is to progressively allow more vertices to be used as intermediate points.

Conceptually:

```text
Can the route from i to j
be improved by passing through k?
```

Update rule:

```text
distance[i][j] =
min(
    distance[i][j],
    distance[i][k] + distance[k][j]
)
```

---

# 21. Floyd–Warshall Matrix

The algorithm maintains a distance matrix.

Example:

```text
      A   B   C
   ┌───────────
A  │ 0   4   ∞
B  │ ∞   0   2
C  │ 1   ∞   0
```

As intermediate vertices are considered, shorter routes replace larger values.

This representation makes Floyd–Warshall suitable for relatively small or dense graphs requiring many pairwise distance queries.

---

# 22. Floyd–Warshall Complexity

The algorithm uses three nested iterations over the vertices.

```text
Time = O(V³)
```

Space:

```text
O(V²)
```

It can support negative edges, but it cannot produce meaningful finite shortest paths when a relevant negative cycle exists.

A negative cycle may be detected when:

```text
distance[v][v] < 0
```

after the algorithm completes.

---

# 23. Algorithm Selection

| Graph Condition | Recommended Algorithm |
|---|---|
| Unweighted graph | BFS |
| All edge weights equal | BFS |
| Non-negative weighted graph | Dijkstra |
| Negative edges, no negative cycle | Bellman–Ford |
| Detect reachable negative cycle | Bellman–Ford |
| All-pairs shortest paths | Floyd–Warshall |
| Dense graph with many pair queries | Floyd–Warshall may be appropriate |

Selecting the algorithm requires identifying the graph's properties before implementation.

---

# 24. Complexity Comparison

| Algorithm | Problem Type | Time Complexity |
|---|---|---:|
| BFS | Single-source, unweighted | O(V + E) |
| Dijkstra with binary heap | Single-source, non-negative weights | O((V + E) log V) |
| Dijkstra with matrix | Single-source, non-negative weights | O(V²) |
| Bellman–Ford | Single-source, negative weights allowed | O(VE) |
| Floyd–Warshall | All-pairs | O(V³) |

No algorithm is universally optimal.

---

# 25. Shortest-Path Correctness Conditions

A shortest-path implementation should satisfy:

- the source has distance zero;
- unreachable vertices retain infinite distance;
- every recorded predecessor corresponds to an actual edge;
- the reconstructed path begins at the source;
- the reconstructed path ends at the destination;
- the path cost equals the recorded distance;
- no valid cheaper path exists under the algorithm's assumptions.

Algorithm-specific conditions must also be respected.

For example:

```text
Dijkstra → no negative edges
```

---

# 26. Triangle Inequality for Shortest Paths

After a correct shortest-path computation, every edge:

```text
u → v
```

should satisfy:

```text
distance[v]
≤
distance[u] + weight(u, v)
```

If the right-hand side were smaller, relaxing the edge would improve `v`.

This property is useful for reasoning about the final result.

---

# 27. Unreachable Vertices

A vertex may not be reachable from the source.

Example:

```text
A ─── B

C ─── D
```

Starting from `A`, the distances may be:

```text
A = 0
B = 1
C = ∞
D = ∞
```

An implementation should represent unreachable vertices explicitly.

Possible representations include:

- a large sentinel value;
- `Double.POSITIVE_INFINITY`;
- absence from the distance map;
- an optional result type.

The chosen representation must avoid arithmetic overflow and ambiguity.

---

# 28. Overflow Considerations

When distances use integer types, adding a weight to an infinite sentinel may overflow.

Incorrect:

```text
Integer.MAX_VALUE + 10
```

This may wrap into a negative number.

Before relaxation, verify that the source distance is finite.

Conceptually:

```text
if distance[u] is finite
    attempt relaxation
```

Using `long` may reduce but does not eliminate the need for careful validation.

---

# 29. Java-Oriented Data Model

A weighted adjacency list may use:

```text
Map<V, List<WeightedEdge<V>>>
```

An edge may be represented as:

```java
public record WeightedEdge<V>(
        V destination,
        long weight
) {
}
```

A priority-queue entry may be represented as:

```text
public record NodeDistance<V>(
        V vertex,
        long distance
) {
}
```

The records separate graph data from algorithm state.

---

# 30. Illustrative Dijkstra Structure

```text
public static <V> Map<V, Long> dijkstra(
        Map<V, List<WeightedEdge<V>>> graph,
        V source) {

    Map<V, Long> distances = new HashMap<>();

    for (V vertex : graph.keySet()) {
        distances.put(vertex, Long.MAX_VALUE);
    }

    distances.put(source, 0L);

    PriorityQueue<NodeDistance<V>> queue =
            new PriorityQueue<>(
                    Comparator.comparingLong(NodeDistance::distance)
            );

    queue.offer(new NodeDistance<>(source, 0L));

    while (!queue.isEmpty()) {
        NodeDistance<V> entry = queue.poll();

        if (entry.distance() != distances.get(entry.vertex())) {
            continue;
        }

        for (WeightedEdge<V> edge :
                graph.getOrDefault(entry.vertex(), List.of())) {

            if (edge.weight() < 0) {
                throw new IllegalArgumentException(
                        "Dijkstra requires non-negative weights"
                );
            }

            long candidate =
                    entry.distance() + edge.weight();

            if (candidate < distances.get(edge.destination())) {
                distances.put(edge.destination(), candidate);

                queue.offer(new NodeDistance<>(
                        edge.destination(),
                        candidate
                ));
            }
        }
    }

    return Map.copyOf(distances);
}
```

A production implementation should also validate overflow, unknown destinations, null values, and graph invariants.

---

# 31. Returning More Than Distances

A complete shortest-path result may contain:

```text
Distances
Predecessors
Source
Reachability
Negative-Cycle Status
```

Conceptually:

```java
public record ShortestPathResult<V>(
        Map<V, Long> distances,
        Map<V, V> predecessors
) {
}
```

This supports both distance queries and path reconstruction.

Returning only a traversal order is not sufficient.

---

# 32. Early Termination

For a single destination, some algorithms may stop early.

## BFS

May stop when the destination is first discovered.

## Dijkstra

May stop when the destination is removed from the priority queue with its current minimum distance.

Bellman–Ford generally requires the complete relaxation process unless stronger problem-specific conditions are available.

Early termination should preserve the algorithm's correctness assumptions.

---

# 33. Directed and Undirected Graphs

Shortest-path algorithms apply to both directed and undirected graphs.

For an undirected edge:

```text
A ──5── B
```

the adjacency representation generally stores:

```text
A → B with cost 5
B → A with cost 5
```

For a directed edge:

```text
A ──5──▶ B
```

only movement from `A` to `B` is permitted.

Incorrectly adding the reverse edge changes the problem.

---

# 34. Common Applications

Shortest-path algorithms are used in:

- GPS navigation;
- internet routing;
- logistics;
- public transportation;
- game movement;
- network latency optimization;
- social-network distance;
- dependency chains;
- robotic motion planning;
- resource-allocation systems.

In each case, vertices represent states or locations, while edge weights represent movement or transition cost.

---

# 35. Network Routing

In a communication network:

```text
Router = Vertex
Connection = Edge
Latency or Cost = Weight
```

A shortest-path algorithm can determine the least expensive route for transmitting data.

The selected weight may represent:

- delay;
- bandwidth cost;
- congestion;
- administrative preference.

The definition of *shortest* depends on the chosen metric.

---

# 36. Navigation Systems

In a road network:

```text
Intersection = Vertex
Road Segment = Edge
```

Weights may represent:

- distance;
- expected travel time;
- toll cost;
- fuel consumption.

A route minimizing distance may differ from one minimizing travel time.

The graph model must therefore reflect the actual optimization objective.

---

# 37. Common Mistakes

## Using BFS on unequal weighted edges

BFS minimizes edge count, not general path cost.

## Using Dijkstra with negative weights

Its correctness guarantee no longer applies.

## Ignoring negative cycles

A finite shortest path may not exist.

## Confusing path length with path cost

More edges can still produce a cheaper route.

## Forgetting predecessor tracking

Distances alone cannot reconstruct paths.

## Treating unreachable vertices as having distance zero

Zero has a valid mathematical meaning and should not represent absence.

## Ignoring stale priority-queue entries

Vertices may be processed unnecessarily or incorrectly.

## Using an insufficient numeric type

Large path costs may overflow.

## Assuming the graph is undirected

Edge direction must be preserved.

## Selecting an algorithm before analysing graph conditions

Algorithm choice depends on weights, cycles, density, and required query type.

---

# 38. Automated Testing Strategy

Shortest-path implementations should be tested with:

## Single vertex

Expected source distance:

```text
0
```

## Direct connection

Verify one-edge paths.

## Alternative routes

Confirm that the cheaper route is selected.

## More edges but lower cost

Verify that weighted cost—not edge count—determines the result.

## Unreachable vertex

Confirm infinite or absent distance.

## Directed graph

Verify that reverse movement is not assumed.

## Zero-weight edge

Confirm that zero is treated as a valid weight.

## Negative edge

Reject it in Dijkstra or process it with Bellman–Ford.

## Negative cycle

Confirm detection where supported.

## Multiple equal shortest paths

Accept any valid minimum-cost predecessor structure unless deterministic tie-breaking is required.

---

# 39. Representative Test Properties

Useful properties include:

- source distance equals zero;
- all reachable distances are finite;
- unreachable distances remain infinite;
- every predecessor edge exists;
- reconstructed path cost equals recorded distance;
- every edge satisfies the shortest-path triangle inequality;
- Dijkstra rejects negative weights;
- Bellman–Ford detects reachable negative cycles;
- BFS distance equals the minimum number of edges in an unweighted graph;
- no returned path repeats a vertex unless problem conditions justify it.

---

# 40. Engineering Perspective

Shortest-path algorithms demonstrate why algorithm selection must follow problem analysis.

Before choosing an implementation, the engineer must determine:

```text
Is the graph weighted?

Can weights be negative?

Is one source involved?

Are all pairs required?

Is the graph sparse or dense?

Must the actual path be reconstructed?

Can vertices be unreachable?
```

These questions determine both the algorithm and the representation.

A correct implementation is not simply one that returns a plausible route. It must preserve the assumptions under which the selected algorithm is mathematically valid.

---

# 41. Relationship with the Next Document

Shortest-path algorithms minimize the cost of travelling between vertices.

The next document introduces **Minimum Spanning Trees**, which solve a different optimization problem.

```text
Shortest Path
        ↓
Minimize the route between selected vertices
```

```text
Minimum Spanning Tree
        ↓
Connect all vertices with minimum total edge cost
```

Although both topics use weighted graphs and priority structures, their objectives are not interchangeable.

---

# 42. Key Takeaways

After completing this document, the learner should understand that:

- shortest paths minimize path cost between vertices;
- path length and weighted path cost are different concepts;
- BFS solves shortest paths in unweighted graphs;
- Dijkstra solves non-negative weighted single-source problems;
- Bellman–Ford supports negative edges and detects reachable negative cycles;
- Floyd–Warshall computes all-pairs shortest paths;
- relaxation improves known distances through candidate routes;
- predecessor information is required for path reconstruction;
- unreachable vertices require an explicit representation;
- Dijkstra commonly uses a priority queue and may contain stale entries;
- algorithm selection depends on graph weights, cycle conditions, density, and query requirements;
- correctness requires respecting each algorithm's mathematical preconditions.