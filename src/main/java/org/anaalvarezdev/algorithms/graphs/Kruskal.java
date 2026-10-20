package org.anaalvarezdev.algorithms.graphs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/** Minimum spanning forest of an undirected graph; negative edges allowed, loops ignored. */
public final class Kruskal {
    public record Forest(List<WeightedGraph.Edge> edges, long totalWeight, int components) {
        public Forest { edges = List.copyOf(edges); }
    }
    private Kruskal() { }
    public static Forest minimumSpanningForest(WeightedGraph graph) {
        Objects.requireNonNull(graph, "graph must not be null");
        if (graph.directed()) { throw new IllegalArgumentException("requires an undirected graph"); }
        List<WeightedGraph.Edge> sorted = new ArrayList<>(graph.edges());
        sorted.sort(Comparator.comparingInt(WeightedGraph.Edge::weight).thenComparingInt(WeightedGraph.Edge::from)
                .thenComparingInt(WeightedGraph.Edge::to));
        DisjointSet sets = new DisjointSet(graph.vertexCount()); List<WeightedGraph.Edge> selected = new ArrayList<>(); long total = 0;
        for (var edge : sorted) {
            if (sets.union(edge.from(), edge.to())) { selected.add(edge); total += edge.weight(); }
        }
        return new Forest(selected, total, sets.count());
    }
}
