package org.anaalvarezdev.algorithms.graphs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;

/** FIFO shortest edge-count paths from one valid source; unreachable distance -1. */
public final class BreadthFirstSearch {
    private BreadthFirstSearch() { }
    public static final class Result {
        private final int source;
        private final int[] distance;
        private final int[] parent;
        private final int[] order;
        private Result(int source, int[] distance, int[] parent, int[] order) {
            this.source = source; this.distance = distance; this.parent = parent; this.order = order;
        }
        public int distanceTo(int vertex) { GraphInputs.vertex(distance.length, vertex); return distance[vertex]; }
        public int[] order() { return order.clone(); }
        public int[] pathTo(int vertex) { return GraphInputs.path(parent, source, vertex, distanceTo(vertex) >= 0); }
    }
    public static Result search(IntGraph graph, int source) {
        Objects.requireNonNull(graph, "graph must not be null"); GraphInputs.vertex(graph.vertexCount(), source);
        int[] distance = new int[graph.vertexCount()]; int[] parent = new int[distance.length]; int[] order = new int[distance.length];
        Arrays.fill(distance, -1); Arrays.fill(parent, -1);
        ArrayDeque<Integer> queue = new ArrayDeque<>(); queue.add(source); distance[source] = 0; int count = 0;
        while (!queue.isEmpty()) {
            int from = queue.remove(); order[count++] = from; var neighbours = graph.adjacent(from);
            while (neighbours.hasNext()) {
                int to = neighbours.nextInt();
                if (distance[to] < 0) { distance[to] = distance[from] + 1; parent[to] = from; queue.add(to); }
            }
        }
        return new Result(source, distance, parent, Arrays.copyOf(order, count));
    }
}
