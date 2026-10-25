package org.anaalvarezdev.algorithms.graphs;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Optional;

/** Kahn FIFO order for directed graphs; empty Optional means a cycle, not an empty DAG. */
public final class TopologicalSort {
    private TopologicalSort() { }
    public static Optional<int[]> sort(IntGraph graph) {
        Objects.requireNonNull(graph, "graph must not be null");
        if (!graph.directed()) { throw new IllegalArgumentException("requires a directed graph"); }
        int[] indegree = new int[graph.vertexCount()]; int[] result = new int[indegree.length];
        for (int from = 0; from < indegree.length; from++) {
            var neighbours = graph.adjacent(from);
            while (neighbours.hasNext()) { indegree[neighbours.nextInt()]++; }
        }
        ArrayDeque<Integer> ready = new ArrayDeque<>();
        for (int vertex = 0; vertex < indegree.length; vertex++) { if (indegree[vertex] == 0) { ready.add(vertex); } }
        int count = 0;
        while (!ready.isEmpty()) {
            int from = ready.remove(); result[count++] = from; var neighbours = graph.adjacent(from);
            while (neighbours.hasNext()) { int to = neighbours.nextInt(); if (--indegree[to] == 0) { ready.add(to); } }
        }
        return count == result.length ? Optional.of(result) : Optional.empty();
    }
}
