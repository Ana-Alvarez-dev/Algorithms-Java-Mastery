# Minimum Spanning Trees

## Algorithms Java Mastery

A **Minimum Spanning Tree (MST)** is a subset of the edges of a connected, weighted, undirected graph that connects every vertex with the minimum possible total edge weight.

Unlike shortest-path algorithms, which minimize the cost of travelling between selected vertices, a Minimum Spanning Tree minimizes the total cost required to connect the complete graph.

This distinction is essential.

```text
Shortest Path
        ↓
Minimizes a route between vertices
```

```text
Minimum Spanning Tree
        ↓
Minimizes the total cost of connecting all vertices
```

Minimum Spanning Trees are fundamental in network design, telecommunications, transportation planning, electrical infrastructure, clustering, and many optimization problems.

The two classical algorithms studied for constructing an MST are:

- Kruskal's algorithm;
- Prim's algorithm.

---

# 1. Purpose

The purpose of this document is to:

- define spanning trees and Minimum Spanning Trees;
- explain the conditions required for an MST;
- distinguish MSTs from shortest-path trees;
- introduce Kruskal's and Prim's algorithms;
- understand the cut and cycle principles;
- analyse correctness and computational complexity;
- identify practical applications and implementation considerations.

---

# 2. Spanning Tree

A **spanning tree** of an undirected graph is a subgraph that:

- contains every vertex of the original graph;
- remains connected;
- contains no cycles.

Consider:

```text
A ─── B
|\   /|
| \ / |
| / \ |
|/   \|
C ─── D
```

A possible spanning tree is:

```text
A ─── B
|
C ─── D
```

It includes all four vertices while using only the edges required to preserve connectivity.

---

# 3. Fundamental Properties

For a connected graph with:

```text
V vertices
```

every spanning tree contains exactly:

```text
V - 1 edges
```

This property follows from the relationship between connectivity and cycles.

- Fewer than `V - 1` edges cannot connect all vertices.
- More than `V - 1` edges in a connected subgraph must create at least one cycle.

Therefore:

```text
Connected
        +
Acyclic
        +
All Vertices
        ↓
Spanning Tree
```

---

# 4. Minimum Spanning Tree

A **Minimum Spanning Tree** is a spanning tree whose total edge weight is less than or equal to the total weight of every other spanning tree of the graph.

Suppose a graph contains several possible spanning trees.

```text
Spanning Tree A

Total Weight = 18
```

```text
Spanning Tree B

Total Weight = 12
```

```text
Spanning Tree C

Total Weight = 15
```

The Minimum Spanning Tree is:

```text
Spanning Tree B
```

because:

```text
12 < 15 < 18
```

---

# 5. Required Graph Conditions

The classical Minimum Spanning Tree problem applies to a graph that is:

```text
Undirected
        +
Weighted
        +
Connected
```

## Undirected

Connections have no traversal direction.

```text
A ─── B
```

## Weighted

Every edge has an associated cost.

```text
A ──5── B
```

## Connected

Every vertex can be reached from every other vertex.

If the graph is disconnected, a single spanning tree cannot include all vertices.

Instead, the result is a:

```text
Minimum Spanning Forest
```

---

# 6. Minimum Spanning Forest

A disconnected graph contains multiple connected components.

Example:

```text
Component 1

A ─── B ─── C
```

```text
Component 2

D ─── E
```

No edge connects the components.

A spanning tree for the complete graph does not exist.

However, a minimum spanning tree can be constructed independently for each component.

The collection of these trees is called a:

```text
Minimum Spanning Forest
```

---

# 7. Example Weighted Graph

Consider:

```text
        4
    A ───── B
    │ \     │
  2 │  3\   │ 5
    │     \ │
    C ───── D
        1
```

Edges:

```text
(A, B, 4)
(A, C, 2)
(A, D, 3)
(B, D, 5)
(C, D, 1)
```

One possible spanning tree is:

```text
(A, C, 2)
(C, D, 1)
(A, B, 4)
```

Total weight:

```text
2 + 1 + 4 = 7
```

If no other spanning tree has a smaller total cost, this is an MST.

---

# 8. MST May Not Be Unique

A graph may have more than one Minimum Spanning Tree.

This commonly occurs when several edges have equal weights.

Example:

```text
A ──1── B
|       |
1       1
|       |
C ──1── D
```

Different edge combinations may connect every vertex with the same total cost.

Therefore:

```text
Minimum Total Weight
```

does not always imply:

```text
Unique Edge Set
```

If all edge weights are distinct, the MST is unique.

The converse is not always required: a graph with repeated weights may still have a unique MST.

---

# 9. MST and Shortest-Path Tree

A Minimum Spanning Tree and a shortest-path tree solve different problems.

## Minimum Spanning Tree

Objective:

```text
Minimize total weight of all selected edges
```

## Shortest-Path Tree

Objective:

```text
Minimize distance from one source
to every reachable vertex
```

An MST does not guarantee the shortest route from a selected source to every vertex.

A shortest-path tree does not necessarily minimize the total weight of the complete tree.

---

# 10. Comparison Example

Suppose:

```text
A ──2── B
A ──2── C
B ──1── C
C ──2── D
A ──4── D
```

An MST may select:

```text
B ──1── C
A ──2── B
C ──2── D
```

Total:

```text
5
```

However, the path from `A` to `C` inside this tree may differ from the direct shortest route depending on the selected edges.

The MST optimizes the network as a whole, not every individual route.

---

# 11. Greedy Strategy

Both Kruskal's and Prim's algorithms are **greedy algorithms**.

A greedy algorithm repeatedly makes a locally optimal decision.

Conceptually:

```text
Select a Safe Minimum Edge
        ↓
Preserve the Possibility of an MST
        ↓
Repeat
```

The challenge is not simply selecting the globally smallest available edge.

The algorithm must ensure that the selected edge is **safe** and does not invalidate the spanning-tree requirements.

---

# 12. Safe Edge

A **safe edge** is an edge that can be added to the current partial solution while preserving the possibility of completing it into a Minimum Spanning Tree.

The concept of safety is justified through properties such as:

- the cut property;
- the cycle property.

These principles explain why the greedy choices made by Kruskal's and Prim's algorithms are correct.

---

# 13. Cut

A **cut** divides the vertices of a graph into two disjoint sets.

Conceptually:

```text
Set A | Set B
```

Every vertex belongs to exactly one side.

An edge **crosses the cut** when its endpoints belong to different sets.

Example:

```text
Set 1 = {A, B}

Set 2 = {C, D}
```

Edges such as:

```text
(A, C)
(B, D)
```

cross the cut.

---

# 14. Cut Property

The **cut property** states that a minimum-weight edge crossing a valid cut is safe for some Minimum Spanning Tree, provided it is consistent with the edges already selected.

Conceptually:

```text
Divide Vertices into Two Groups
        ↓
Find Lightest Edge Crossing the Cut
        ↓
Select It Safely
```

Prim's algorithm applies this principle repeatedly by separating:

- vertices already included in the tree;
- vertices not yet included.

---

# 15. Cycle Property

The **cycle property** provides another useful principle.

For any cycle, an edge with strictly greater weight than every other edge in that cycle cannot belong to an MST.

Suppose:

```text
A ──2── B
 \      |
  7     3
   \    |
      C
```

Cycle edges:

```text
2, 3, 7
```

The edge with weight `7` is unnecessary because removing it preserves connectivity while reducing total cost.

This principle helps explain why cycles must be avoided.

---

# 16. Kruskal's Algorithm

**Kruskal's algorithm** constructs an MST by processing edges from smallest to largest weight.

An edge is selected only when it connects two previously separate components.

Conceptually:

```text
Sort All Edges by Weight
        ↓
Select Smallest Edge
        ↓
Would It Create a Cycle?
        ↓
No → Add It
Yes → Reject It
        ↓
Repeat Until V - 1 Edges Are Selected
```

Kruskal's algorithm focuses primarily on edges rather than vertices.

---

# 17. Kruskal Example

Consider the edges:

```text
(A, B, 4)
(A, C, 2)
(A, D, 3)
(B, D, 5)
(C, D, 1)
```

Sort by weight:

```text
(C, D, 1)
(A, C, 2)
(A, D, 3)
(A, B, 4)
(B, D, 5)
```

---

## Select (C, D, 1)

No cycle is created.

```text
Selected = {(C, D)}
```

---

## Select (A, C, 2)

No cycle is created.

```text
Selected = {
    (C, D),
    (A, C)
}
```

---

## Consider (A, D, 3)

`A`, `C`, and `D` are already connected.

Adding `(A, D)` creates a cycle.

Reject it.

---

## Select (A, B, 4)

This connects `B` to the existing component.

```text
Selected = {
    (C, D),
    (A, C),
    (A, B)
}
```

The graph contains four vertices, and the algorithm selected:

```text
V - 1 = 3 edges
```

Total weight:

```text
1 + 2 + 4 = 7
```

---

# 18. Cycle Detection in Kruskal

Kruskal's algorithm must efficiently determine whether two vertices already belong to the same connected component.

If they do:

```text
Adding the edge creates a cycle
```

If they do not:

```text
The edge merges two components
```

The standard data structure used for this operation is:

```text
Disjoint Set Union
```

also known as:

```text
Union-Find
```

---

# 19. Disjoint Set Union

A Disjoint Set Union structure maintains groups of connected vertices.

It supports two principal operations.

## Find

Determines which component contains a vertex.

```text
find(A)
```

## Union

Combines two separate components.

```text
union(A, B)
```

During Kruskal's algorithm:

```text
if find(u) != find(v)
    select edge
    union(u, v)
else
    reject edge
```

---

# 20. Union-Find Optimizations

Two important optimizations are commonly used.

## Path Compression

Flattens the internal structure during `find`.

## Union by Rank or Size

Attaches the smaller or shallower component beneath the larger one.

Together, these techniques make Union-Find operations extremely efficient.

The amortized complexity is commonly expressed using the inverse Ackermann function:

```text
O(α(V))
```

which grows so slowly that it behaves almost like constant time for practical inputs.

---

# 21. Kruskal Pseudocode

```text
KRUSKAL(graph)

    result = empty edge collection

    create one disjoint set for every vertex

    sort all edges by nondecreasing weight

    for each edge (u, v) in sorted order

        if find(u) != find(v)

            add edge to result
            union(u, v)

        if result contains V - 1 edges

            stop

    if result contains fewer than V - 1 edges

        graph is disconnected

    return result
```

---

# 22. Kruskal Complexity

Sorting the edges requires:

```text
O(E log E)
```

Union-Find operations require approximately:

```text
O(E α(V))
```

Therefore, the sorting step dominates.

Overall complexity:

```text
O(E log E)
```

Since:

```text
E ≤ V²
```

this is also frequently related to:

```text
O(E log V)
```

depending on the analysis convention.

Additional space:

```text
O(V + E)
```

for:

- edge collection;
- Union-Find structure;
- result.

---

# 23. When Kruskal Is Appropriate

Kruskal's algorithm is particularly suitable when:

- the graph is sparse;
- edges are already available as an edge list;
- global edge sorting is convenient;
- the graph may be disconnected;
- a minimum spanning forest is acceptable.

Because it naturally processes separate components, Kruskal can construct a minimum spanning forest without significant modification.

---

# 24. Prim's Algorithm

**Prim's algorithm** constructs an MST by growing one connected tree from a selected starting vertex.

At every step, it selects the minimum-weight edge connecting the current tree to a vertex outside it.

Conceptually:

```text
Select Starting Vertex
        ↓
Current Tree Contains One Vertex
        ↓
Find Lightest Edge Leaving the Tree
        ↓
Add New Vertex and Edge
        ↓
Repeat Until Every Vertex Is Included
```

Prim's algorithm focuses on expanding a vertex set rather than globally sorting every edge.

---

# 25. Prim Example

Consider:

```text
        4
    A ───── B
    │ \     │
  2 │  3\   │ 5
    │     \ │
    C ───── D
        1
```

Start from:

```text
A
```

Initial tree:

```text
Vertices = {A}
```

Candidate edges:

```text
(A, B, 4)
(A, C, 2)
(A, D, 3)
```

Select:

```text
(A, C, 2)
```

---

Current tree:

```text
Vertices = {A, C}
```

Candidate crossing edges:

```text
(A, B, 4)
(A, D, 3)
(C, D, 1)
```

Select:

```text
(C, D, 1)
```

---

Current tree:

```text
Vertices = {A, C, D}
```

Candidate crossing edges:

```text
(A, B, 4)
(D, B, 5)
```

Select:

```text
(A, B, 4)
```

All vertices are included.

Total weight:

```text
2 + 1 + 4 = 7
```

---

# 26. Priority Queue in Prim

Prim's algorithm commonly uses a min-priority queue.

The queue stores candidate edges or candidate vertex costs.

Conceptually:

```text
Lowest Crossing Edge
        ↓
Highest Priority
```

When a vertex is added to the tree, its outgoing edges are examined.

Edges leading to already included vertices are ignored.

This is similar to Dijkstra's algorithm structurally, but the meaning of the priority is different.

---

# 27. Prim and Dijkstra Are Different

Both algorithms may use:

- adjacency lists;
- priority queues;
- greedy selection.

However, their objectives differ.

## Prim

Priority represents:

```text
Cheapest edge connecting a vertex to the growing tree
```

## Dijkstra

Priority represents:

```text
Shortest total known distance from the source
```

Prim minimizes:

```text
Total tree weight
```

Dijkstra minimizes:

```text
Individual path distances from one source
```

Their implementations may look similar, but they must not be confused.

---

# 28. Prim Pseudocode

```text
PRIM(graph, start)

    create empty visited set
    create minimum priority queue
    create empty result

    add start to visited
    add outgoing edges from start to queue

    while queue is not empty
        and result contains fewer than V - 1 edges

        edge = remove minimum edge

        if edge destination is already visited

            continue

        add edge to result
        mark destination as visited

        for each outgoing edge from destination

            if edge leads to unvisited vertex

                add edge to queue

    if not every vertex was visited

        graph is disconnected

    return result
```

---

# 29. Prim Complexity

With an adjacency list and binary heap:

```text
O(E log V)
```

Additional space:

```text
O(V + E)
```

With an adjacency matrix and linear minimum selection:

```text
O(V²)
```

The matrix version can be appropriate for dense graphs.

---

# 30. When Prim Is Appropriate

Prim's algorithm is often suitable when:

- the graph is connected;
- an adjacency-list representation is available;
- the tree should grow from a specific starting vertex;
- neighbour-based processing is natural;
- the graph is dense when using a matrix implementation.

The final MST weight does not depend on the starting vertex, although the selected edge set may differ when multiple MSTs exist.

---

# 31. Kruskal versus Prim

| Characteristic | Kruskal | Prim |
|---|---|---|
| Main focus | Edges | Growing vertex set |
| Main structure | Sorted edge list and Union-Find | Priority queue |
| Starting vertex | Not required | Required |
| Cycle prevention | Disjoint Set Union | Visited vertex set |
| Sparse graphs | Often very suitable | Also efficient |
| Dense graphs | Sorting many edges may be expensive | Matrix version can be effective |
| Disconnected graph | Produces spanning forest naturally | Must restart per component |
| Typical complexity | O(E log E) | O(E log V) |

Both algorithms are correct greedy approaches.

---

# 32. Correctness Intuition for Kruskal

Kruskal repeatedly selects the smallest edge connecting two distinct components.

These components define a cut.

The selected edge is one of the lightest edges crossing that cut.

By the cut property, the edge is safe.

Because edges that create cycles are rejected, the result remains acyclic.

When `V - 1` safe edges have been selected in a connected graph, the result is a Minimum Spanning Tree.

---

# 33. Correctness Intuition for Prim

Prim maintains one connected partial tree.

At every step, the visited vertices and unvisited vertices define a cut.

Prim selects the minimum-weight edge crossing that cut.

By the cut property, this edge is safe.

The selected edge adds exactly one new vertex, so no cycle is introduced.

Repeating this process until every vertex is included produces an MST.

---

# 34. Preconditions

An MST algorithm should clearly define its requirements.

Typical preconditions include:

- graph is undirected;
- graph edges have defined weights;
- edge endpoints belong to the graph;
- the graph is connected if one spanning tree is required;
- self-loops are ignored or rejected;
- parallel edges follow an explicit policy.

Negative edge weights are permitted in MST algorithms.

Unlike Dijkstra's algorithm, MST correctness does not require non-negative weights.

---

# 35. Negative Edge Weights

A Minimum Spanning Tree may contain negative edges.

Example:

```text
A ──-3── B
B ── 2── C
A ── 5── C
```

Selecting the negative edge reduces the total tree weight.

Kruskal's and Prim's algorithms remain valid because they compare edge weights rather than relying on finalized path distances.

This is an important distinction from Dijkstra's algorithm.

---

# 36. Self-Loops

A self-loop connects a vertex to itself.

```text
A ↺
```

A self-loop can never help connect two different vertices.

It also immediately forms a cycle.

Therefore, self-loops are excluded from a spanning tree.

An implementation may:

- reject them during graph construction;
- store but ignore them during MST processing.

---

# 37. Parallel Edges

A multigraph may contain multiple edges between the same pair of vertices.

Example:

```text
A ──3── B
A ──7── B
```

An MST algorithm may consider both, but the heavier edge is normally unnecessary unless graph constraints create a special case.

The implementation should ensure that parallel edges are represented and compared correctly.

---

# 38. MST Result Model

A complete result may contain:

- selected edges;
- total weight;
- connected status;
- number of components;
- source vertex for Prim;
- spanning forest information.

Conceptually:

```java
public record MinimumSpanningTreeResult<V>(
        List<WeightedEdge<V>> edges,
        long totalWeight,
        boolean connected
) {
}
```

For disconnected graphs, a separate forest-oriented result may be clearer.

---

# 39. Java Edge Representation

A weighted undirected edge may be represented as:

```java
public record WeightedEdge<V>(
        V first,
        V second,
        long weight
) {
}
```

The graph contract should define whether:

```text
(A, B)
```

and:

```text
(B, A)
```

represent the same logical edge.

For an undirected edge list, each logical edge should generally appear only once.

For an adjacency list, it may appear in both endpoint collections.

---

# 40. Illustrative Kruskal Structure

```java
public static <V> List<WeightedEdge<V>> kruskal(
        Set<V> vertices,
        List<WeightedEdge<V>> edges) {

    List<WeightedEdge<V>> sortedEdges =
            edges.stream()
                    .sorted(Comparator.comparingLong(
                            WeightedEdge::weight
                    ))
                    .toList();

    DisjointSet<V> disjointSet =
            new DisjointSet<>(vertices);

    List<WeightedEdge<V>> result =
            new ArrayList<>();

    for (WeightedEdge<V> edge : sortedEdges) {
        V firstRoot = disjointSet.find(edge.first());
        V secondRoot = disjointSet.find(edge.second());

        if (!firstRoot.equals(secondRoot)) {
            result.add(edge);

            disjointSet.union(
                    edge.first(),
                    edge.second()
            );

            if (result.size() == vertices.size() - 1) {
                break;
            }
        }
    }

    if (result.size() != vertices.size() - 1) {
        throw new IllegalArgumentException(
                "graph is disconnected"
        );
    }

    return List.copyOf(result);
}
```

The correctness of this implementation depends on a valid Union-Find structure and consistent edge data.

---

# 41. Illustrative Prim Structure

A candidate edge may be stored in a priority queue.

```java
PriorityQueue<WeightedEdge<V>> queue =
        new PriorityQueue<>(
                Comparator.comparingLong(
                        WeightedEdge::weight
                )
        );
```

General process:

```text
visited.add(start);
queue.addAll(graph.getOrDefault(start, List.of()));

while (!queue.isEmpty()
        && result.size() < graph.size() - 1) {

    WeightedEdge<V> edge = queue.poll();

    V next = determineUnvisitedEndpoint(edge, visited);

    if (next == null) {
        continue;
    }

    result.add(edge);
    visited.add(next);

    for (WeightedEdge<V> candidate :
            graph.getOrDefault(next, List.of())) {

        if (!bothEndpointsVisited(candidate, visited)) {
            queue.offer(candidate);
        }
    }
}
```

The exact representation influences how the unvisited endpoint is identified.

---

# 42. Total Weight and Overflow

The total MST weight is calculated by summing selected edge weights.

```text
totalWeight =
sum of selected edge weights
```

Large weights or large graphs may cause numeric overflow.

Using:

```text
long
```

is generally safer than `int`, but the implementation should still consider:

- input limits;
- overflow validation;
- negative totals;
- exact arithmetic requirements.

---

# 43. Common Applications

Minimum Spanning Trees are used in:

- telecommunications networks;
- electrical grid design;
- road and railway planning;
- computer network cabling;
- pipeline construction;
- clustering;
- image segmentation;
- approximation algorithms;
- infrastructure optimization.

The shared objective is:

```text
Connect all required entities
while minimizing total connection cost
```

---

# 44. Network Design

Suppose several offices must be connected by communication links.

```text
Office = Vertex
Possible Cable = Edge
Installation Cost = Weight
```

An MST identifies a set of links that:

- connects every office;
- avoids redundant cycles;
- minimizes total installation cost.

This does not necessarily minimize communication distance between every pair after construction.

---

# 45. Transportation Planning

In a simplified planning model:

```text
City = Vertex
Possible Road = Edge
Construction Cost = Weight
```

An MST can identify the least expensive network connecting every city.

Real infrastructure planning may require additional constraints, such as:

- capacity;
- geographic restrictions;
- resilience;
- legal requirements;
- multiple routes for fault tolerance.

The classical MST models only the minimum total edge cost.

---

# 46. Clustering

Minimum Spanning Trees can support clustering.

Conceptual process:

```text
Construct MST
        ↓
Remove One or More Heavy Edges
        ↓
Obtain Separate Components
        ↓
Interpret Components as Clusters
```

The approach groups vertices connected through relatively low-cost relationships.

This demonstrates that MSTs are useful beyond physical network design.

---

# 47. Common Mistakes

## Applying MST algorithms to directed graphs

The classical problem assumes an undirected graph.

## Confusing MST with shortest paths

The objectives are different.

## Assuming negative weights are invalid

MST algorithms can process negative edge weights.

## Adding the globally smallest edges without cycle detection

The result may contain cycles and fail to be a tree.

## Forgetting graph connectivity

A disconnected graph has no single spanning tree.

## Assuming the MST is always unique

Equal weights may produce multiple valid MSTs.

## Treating self-loops as useful edges

They cannot connect different vertices.

## Using Dijkstra instead of Prim

Their implementations may look similar, but their priorities represent different quantities.

## Storing undirected edges inconsistently

Duplicated or missing reverse relationships may produce incorrect processing.

## Comparing only the number of edges

Every spanning tree already contains `V - 1` edges; optimization concerns total weight.

---

# 48. Automated Testing Strategy

MST implementations should be tested with:

## Single vertex

Expected result:

```text
No edges
Total weight = 0
```

## Two connected vertices

The only edge must be selected.

## Simple triangle

Verify that the heaviest unnecessary edge is excluded.

## Equal weights

Accept multiple valid edge sets with the same minimum total cost.

## Negative edge

Confirm that the algorithm may select it.

## Disconnected graph

Verify failure or minimum spanning forest behaviour.

## Self-loop

Confirm that it is ignored or rejected.

## Parallel edges

Verify that the better connection is considered.

## Dense graph

Confirm correct total weight across many alternatives.

## Sparse graph

Verify efficient edge processing.

---

# 49. MST Validation Properties

A result should satisfy:

- every selected edge belongs to the original graph;
- all graph vertices are included;
- the result is connected;
- the result contains no cycle;
- the result contains exactly `V - 1` edges;
- the reported total equals the sum of selected weights;
- no alternative spanning tree has a smaller total weight.

The final property is difficult to test directly for large graphs.

For small test graphs, all spanning trees may be enumerated to verify the minimum.

---

# 50. Testing without Requiring One Exact MST

When multiple MSTs exist, tests should not require one arbitrary edge set unless deterministic tie-breaking is part of the contract.

Prefer validating:

```text
Correct edge count
        +
Connectivity
        +
Acyclicity
        +
Expected total weight
```

This accepts every mathematically valid MST.

---

# 51. Engineering Perspective

Minimum Spanning Trees illustrate how local greedy decisions can produce a globally optimal structure when supported by the correct mathematical properties.

Kruskal and Prim solve the same problem through different perspectives:

```text
Kruskal
        ↓
Combine Components Using Safe Edges
```

```text
Prim
        ↓
Grow One Tree Using Safe Crossing Edges
```

A professional implementation must consider:

- graph representation;
- graph density;
- disconnected-input policy;
- deterministic tie-breaking;
- weight numeric type;
- duplicate-edge handling;
- result validation;
- memory constraints.

The choice between Kruskal and Prim should follow the structure and operations of the graph rather than personal preference.

---

# 52. Relationship with the Next Document

This document completes the study of the principal graph algorithms in this module.

The next document presents **Complexity Analysis**, comparing:

- graph representations;
- BFS;
- DFS;
- topological sorting;
- shortest-path algorithms;
- Minimum Spanning Tree algorithms.

The objective is to understand how the number of vertices and edges affects the performance of each strategy.

---

# 53. Key Takeaways

After completing this document, the learner should understand that:

- a spanning tree connects all vertices without cycles;
- every spanning tree of a connected graph contains exactly `V - 1` edges;
- an MST minimizes the total weight of the selected edges;
- classical MST algorithms apply to weighted, undirected graphs;
- disconnected graphs produce minimum spanning forests;
- an MST is different from a shortest-path tree;
- Minimum Spanning Trees may not be unique;
- Kruskal processes edges globally in increasing weight order;
- Kruskal uses Union-Find to prevent cycles;
- Prim grows one connected tree through minimum crossing edges;
- Prim commonly uses a priority queue;
- the cut property explains why selected minimum crossing edges are safe;
- the cycle property helps identify edges that cannot belong to an MST;
- Kruskal generally requires `O(E log E)` time;
- Prim with an adjacency list and binary heap requires `O(E log V)` time;
- negative edge weights are permitted;
- correctness requires connectivity, acyclicity, complete vertex coverage, and minimum total weight.