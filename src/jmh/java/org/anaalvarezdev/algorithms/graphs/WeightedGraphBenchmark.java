package org.anaalvarezdev.algorithms.graphs;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How do sparse versus complete connected fixtures affect this lazy-queue
 * Dijkstra and sorting/union-based Kruskal implementation at two populations?
 *
 * Theoretical Expectation:
 * Lazy Dijkstra includes validation, O(V+E) state/queue bound and O(E log(E+1))
 * queue work. Kruskal includes O(E log(E+1)) sorting and disjoint-set operations.
 * They solve different problems, so equal input does not imply comparable output.
 *
 * Measured Work:
 * Trial setup builds one undirected graph with positive int weights (seed
 * 20261020). Dijkstra times validation, result arrays, lazy queue and relaxation
 * from source 0. Kruskal times copied edge sorting, union state and immutable
 * forest result. Input construction is excluded; work/output allocation is timed.
 *
 * Result:
 * Author run reported 2026-10-24; source 00fc9de90757eab77fbf6f824132d9bbea3f37e2
 * supplied with outputs. JMH 1.37/JDK 21.0.9, one thread, 3/5 x 500 ms warmup/
 * measurement, two forks (Cnt=10), completed 1 min 14 sec. All 8 cells/errors,
 * 80 measured and 48 warmup averages: docs/17-benchmarking/results/graphs/weighted-first-run.md.
 * DENSE n=1000: Dijkstra 5395.066 +/- 479.629; Kruskal 163948.138 +/- 19262.711 us/op.
 *
 * Interpretation:
 * DENSE has 4950/499500 canonical edges at n=100/1000. Current Kruskal sorts and
 * scans every edge: 4950/499500 union attempts, 99/999 accepted. It does not stop
 * when connected. These are source counts, not profiler attribution of elapsed
 * time. Both SPARSE Dijkstra errors exceed 25% (34.5%/28.3%). Dense Kruskal mean
 * is about 164 ms against a 500 ms iteration target; actual invocation counts
 * are not supplied. No data removed, significance, cache or allocation claim.
 * Source distances and minimum spanning forest remain different problems;
 * compare methods with themselves, without fitting an exponent from two sizes.
 * Protocol: docs/10-graphs/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class WeightedGraphBenchmark {
    @Param({"100","1000"}) private int size;
    @Param({"SPARSE","DENSE"}) private String shape;
    private WeightedGraph graph;
    @Setup(Level.Trial) public void setup() { graph = new WeightedGraph(size,false,GraphBenchmarkData.edges(size,shape)); }
    @Benchmark public Dijkstra.Result dijkstra() { return Dijkstra.shortestPaths(graph,0); }
    @Benchmark public Kruskal.Forest kruskalForest() { return Kruskal.minimumSpanningForest(graph); }
}
