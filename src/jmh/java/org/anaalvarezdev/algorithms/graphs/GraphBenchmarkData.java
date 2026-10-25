package org.anaalvarezdev.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;
import java.util.TreeMap;

/** Connected, deterministic fixtures; construction is outside timed invocations. */
final class GraphBenchmarkData {
    private GraphBenchmarkData() { }
    static List<WeightedGraph.Edge> edges(int n, String shape) {
        var random = new SplittableRandom(20261020); var pairs = new TreeMap<Long,WeightedGraph.Edge>();
        for (int from = 0; from < n-1; from++) { add(pairs,from,from+1,random); }
        if (shape.equals("DENSE")) {
            for (int from = 0; from < n; from++) for (int to = from+1; to < n; to++) { add(pairs,from,to,random); }
        } else if (shape.equals("SPARSE")) {
            for (int edge = 0; edge < 3*n; edge++) { add(pairs,random.nextInt(n),random.nextInt(n),random); }
        }
        return new ArrayList<>(pairs.values());
    }
    private static void add(TreeMap<Long,WeightedGraph.Edge> pairs, int from, int to, SplittableRandom random) {
        if (from == to) { return; }
        int a = Math.min(from,to); int b = Math.max(from,to); long key = ((long)a << 32) | b;
        if (!pairs.containsKey(key)) { pairs.put(key,new WeightedGraph.Edge(a,b,random.nextInt(1,1001))); }
    }
}
