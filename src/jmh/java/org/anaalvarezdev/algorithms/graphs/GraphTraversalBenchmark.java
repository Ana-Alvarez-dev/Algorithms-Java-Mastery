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
 * Author run reported 2026-10-24; source 00fc9de90757eab77fbf6f824132d9bbea3f37e2
 * supplied with outputs. JMH 1.37/JDK 21.0.9, one thread, 3/5 x 500 ms warmup/
 * measurement, two forks (Cnt=10), completed 3 min 36 sec. All 24 cells/errors,
 * 240 measured and 144 warmup averages: docs/17-benchmarking/results/graphs/README.md.
 * SPARSE n=1000 BFS list/matrix: 34.781 +/- 0.360 / 624.284 +/- 12.492 us/op;
 * DFS list/matrix: 40.952 +/- 0.774 / 404.327 +/- 17.054 us/op.
 *
 * Interpretation:
 * PATH/SPARSE means favour list scans in this subset. DENSE has quadratic edge
 * population (4950/499500 at n=100/1000), so list O(V+E) also grows quadratically
 * in V. Dense size-100 BFS intervals overlap. Three errors reach 25% of means,
 * largest 41.7%; no formal significance or universal representation ranking.
 * Row-position counts/source paths explain work, not CPU/cache causes or exact
 * ratios. BFS returns distances/parents/order while DFS only order; their means
 * do not isolate queue-versus-stack cost. No memory/allocation/asymptotic proof.
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
