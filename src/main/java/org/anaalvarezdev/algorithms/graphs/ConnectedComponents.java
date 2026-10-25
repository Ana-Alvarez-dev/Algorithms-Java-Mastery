package org.anaalvarezdev.algorithms.graphs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;

/** Undirected components, including isolated vertices; labels follow ascending roots. */
public final class ConnectedComponents {
    private ConnectedComponents() { }
    public static final class Result {
        private final int[] labels;
        private final int count;
        private Result(int[] labels, int count) { this.labels = labels; this.count = count; }
        public int count() { return count; }
        public int componentOf(int vertex) { GraphInputs.vertex(labels.length, vertex); return labels[vertex]; }
        public int[] labels() { return labels.clone(); }
    }
    public static Result find(IntGraph graph) {
        Objects.requireNonNull(graph, "graph must not be null");
        if (graph.directed()) { throw new IllegalArgumentException("requires an undirected graph"); }
        int[] labels = new int[graph.vertexCount()]; Arrays.fill(labels, -1); int count = 0;
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int root = 0; root < labels.length; root++) {
            if (labels[root] >= 0) { continue; }
            labels[root] = count; queue.add(root);
            while (!queue.isEmpty()) {
                var neighbours = graph.adjacent(queue.remove());
                while (neighbours.hasNext()) {
                    int to = neighbours.nextInt();
                    if (labels[to] < 0) { labels[to] = count; queue.add(to); }
                }
            }
            count++;
        }
        return new Result(labels, count);
    }
}
