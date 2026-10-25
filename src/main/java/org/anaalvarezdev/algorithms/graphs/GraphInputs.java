package org.anaalvarezdev.algorithms.graphs;

import java.util.Objects;
import java.util.TreeSet;

/** Validation, canonical unweighted input and private path reconstruction. */
final class GraphInputs {
    private GraphInputs() { }
    static void count(int vertices) {
        if (vertices < 0) { throw new IllegalArgumentException("negative vertex count"); }
    }
    static void vertex(int vertices, int vertex) {
        if (vertex < 0 || vertex >= vertices) { throw new IndexOutOfBoundsException("vertex outside [0, V)"); }
    }
    record Data(int[][] rows, long edges) { }
    static Data normalise(int vertices, boolean directed, int[][] edges) {
        count(vertices); Objects.requireNonNull(edges, "edges must not be null");
        java.util.List<TreeSet<Integer>> rows = new java.util.ArrayList<>(vertices);
        for (int vertex = 0; vertex < vertices; vertex++) { rows.add(new TreeSet<>()); }
        for (int[] edge : edges) {
            Objects.requireNonNull(edge, "edge must not be null");
            if (edge.length != 2) { throw new IllegalArgumentException("edge must have two endpoints"); }
            vertex(vertices, edge[0]); vertex(vertices, edge[1]);
            rows.get(edge[0]).add(edge[1]);
            if (!directed) { rows.get(edge[1]).add(edge[0]); }
        }
        int[][] result = new int[vertices][]; long count = 0;
        for (int from = 0; from < vertices; from++) {
            result[from] = rows.get(from).stream().mapToInt(Integer::intValue).toArray();
            for (int to : result[from]) { if (directed || from <= to) { count++; } }
        }
        return new Data(result, count);
    }
    static int[] path(int[] parent, int source, int target, boolean reachable) {
        vertex(parent.length, target);
        if (!reachable) { return new int[0]; }
        int length = 1;
        for (int node = target; node != source; node = parent[node]) { length++; }
        int[] result = new int[length];
        for (int node = target, index = length - 1; index >= 0; index--, node = parent[node]) { result[index] = node; }
        return result;
    }
}
