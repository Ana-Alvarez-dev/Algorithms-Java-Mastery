package org.anaalvarezdev.algorithms.graphs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.OptionalLong;
import java.util.PriorityQueue;

/** Nonnegative weighted paths with lazy priority-queue entries, not decrease-key. */
public final class Dijkstra {
    private record Entry(int vertex, long distance) { }
    private Dijkstra() { }
    public static final class Result {
        private final int source;
        private final long[] distances;
        private final int[] parents;
        private Result(int source, long[] distances, int[] parents) { this.source = source; this.distances = distances; this.parents = parents; }
        public OptionalLong distanceTo(int vertex) {
            GraphInputs.vertex(distances.length, vertex);
            return distances[vertex] == Long.MAX_VALUE ? OptionalLong.empty() : OptionalLong.of(distances[vertex]);
        }
        public int[] pathTo(int vertex) { return GraphInputs.path(parents, source, vertex, distanceTo(vertex).isPresent()); }
    }
    /** Rejects any negative edge, including edges unreachable from source. */
    public static Result shortestPaths(WeightedGraph graph, int source) {
        Objects.requireNonNull(graph, "graph must not be null"); GraphInputs.vertex(graph.vertexCount(), source);
        for (var edge : graph.edges()) { if (edge.weight() < 0) { throw new IllegalArgumentException("negative edge weight"); } }
        long[] distance = new long[graph.vertexCount()]; int[] parent = new int[distance.length];
        Arrays.fill(distance, Long.MAX_VALUE); Arrays.fill(parent, -1); distance[source] = 0;
        var queue = new PriorityQueue<Entry>(Comparator.comparingLong(Entry::distance).thenComparingInt(Entry::vertex));
        queue.add(new Entry(source, 0));
        while (!queue.isEmpty()) {
            Entry entry = queue.remove();
            if (entry.distance() != distance[entry.vertex()]) { continue; }
            for (var edge : graph.outgoing(entry.vertex())) {
                long candidate = entry.distance() + edge.weight();
                if (candidate < distance[edge.to()]) {
                    distance[edge.to()] = candidate; parent[edge.to()] = entry.vertex(); queue.add(new Entry(edge.to(), candidate));
                }
            }
        }
        return new Result(source, distance, parent);
    }
}
