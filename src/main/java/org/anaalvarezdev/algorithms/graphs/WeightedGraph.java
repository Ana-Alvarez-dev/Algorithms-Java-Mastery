package org.anaalvarezdev.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

/**
 * Immutable weighted adjacency lists. Fixed int vertices; loops and any int weight
 * allowed. Repeated endpoint pairs are rejected, including reversed undirected
 * duplicates. Zero weight is an edge. Neighbours sort by destination; canonical
 * undirected edges use {@code from <= to}. Input/output lists cannot mutate the graph.
 */
public final class WeightedGraph {
    public record Edge(int from, int to, int weight) { }
    private final boolean directed;
    private final List<List<Edge>> rows;
    private final List<Edge> edges;
    public WeightedGraph(int vertices, boolean directed, List<Edge> input) {
        GraphInputs.count(vertices); Objects.requireNonNull(input, "edges must not be null");
        List<TreeMap<Integer,Integer>> maps = new ArrayList<>(vertices);
        for (int v = 0; v < vertices; v++) { maps.add(new TreeMap<>()); }
        for (Edge edge : input) {
            Objects.requireNonNull(edge, "edge must not be null");
            GraphInputs.vertex(vertices, edge.from()); GraphInputs.vertex(vertices, edge.to());
            if (maps.get(edge.from()).containsKey(edge.to())) { throw new IllegalArgumentException("duplicate endpoint pair"); }
            maps.get(edge.from()).put(edge.to(), edge.weight());
            if (!directed) { maps.get(edge.to()).put(edge.from(), edge.weight()); }
        }
        List<List<Edge>> built = new ArrayList<>(vertices); List<Edge> canonical = new ArrayList<>();
        for (int from = 0; from < vertices; from++) {
            List<Edge> row = new ArrayList<>();
            for (var entry : maps.get(from).entrySet()) {
                Edge edge = new Edge(from, entry.getKey(), entry.getValue()); row.add(edge);
                if (directed || edge.from() <= edge.to()) { canonical.add(edge); }
            }
            built.add(List.copyOf(row));
        }
        this.directed = directed; rows = List.copyOf(built); edges = List.copyOf(canonical);
    }
    public int vertexCount() { return rows.size(); }
    public boolean directed() { return directed; }
    public List<Edge> edges() { return edges; }
    public List<Edge> outgoing(int vertex) { GraphInputs.vertex(rows.size(), vertex); return rows.get(vertex); }
}
