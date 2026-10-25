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
 * Pending author measurements; smoke is not research timing.
 *
 * Interpretation:
 * Pending source/runtime/settings and all means/errors. Compare a method with
 * itself across matching fixtures; do not rank shortest paths versus minimum
 * spanning forest as interchangeable algorithms. No profiler, memory-footprint,
 * allocation-rate, worst-case guarantee or asymptotic exponent is inferred.
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
