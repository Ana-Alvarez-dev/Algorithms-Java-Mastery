package org.anaalvarezdev.algorithms.graphs;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How do immutable list/matrix representations affect complete BFS and recursive-
 * order iterative DFS over the same connected path, sparse and complete graphs?
 *
 * Theoretical Expectation:
 * List traversal is O(V+E); matrix scans are O(V^2) here because source 0 reaches
 * all vertices. Dense E already is Theta(V^2), so both bounds are quadratic.
 *
 * Measured Work:
 * Trial setup builds both graphs from identical edges. Source 0 reaches the entire
 * undirected backbone. Timed calls allocate fresh visited/distance/parent/output
 * state and frontiers, scan neighbours and return results. No construction,
 * defensive public neighbour snapshots or mutation is timed. Memory layout and
 * allocation are part of each implementation, not isolated factors.
 *
 * Result:
 * Pending author measurements; smoke checks are harness evidence only.
 *
 * Interpretation:
 * Pending actual source SHA, runtime/settings and all means/errors. Compare only
 * matching size/shape/traversal; BFS returns paths/distances and DFS only order,
 * so their difference is not an isolated queue-versus-stack cost. No cache cause,
 * memory footprint, allocation rate or asymptotic proof follows from means.
 * Protocol: docs/10-graphs/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class GraphTraversalBenchmark {
    @Param({"100","1000"}) private int size;
    @Param({"PATH","SPARSE","DENSE"}) private String shape;
    private AdjacencyListGraph list;
    private AdjacencyMatrixGraph matrix;
    @Setup(Level.Trial) public void setup() {
        int[][] edges = GraphBenchmarkData.edges(size,shape).stream().map(e -> new int[]{e.from(),e.to()}).toArray(int[][]::new);
        list = new AdjacencyListGraph(size,false,edges); matrix = new AdjacencyMatrixGraph(size,false,edges);
    }
    @Benchmark public BreadthFirstSearch.Result listBfs() { return BreadthFirstSearch.search(list,0); }
    @Benchmark public BreadthFirstSearch.Result matrixBfs() { return BreadthFirstSearch.search(matrix,0); }
    @Benchmark public int[] listDfs() { return DepthFirstSearch.traverse(list,0); }
    @Benchmark public int[] matrixDfs() { return DepthFirstSearch.traverse(matrix,0); }
}
