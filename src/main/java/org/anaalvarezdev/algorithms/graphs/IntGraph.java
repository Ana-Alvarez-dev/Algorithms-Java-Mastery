package org.anaalvarezdev.algorithms.graphs;

import java.util.PrimitiveIterator;

/**
 * Immutable fixed-vertex unweighted graph. Vertices are [0,V); loops are allowed,
 * parallel input edges collapse. Undirected edge count counts each pair/loop once.
 * Neighbours are ascending independent arrays. No mutation or iterator is public.
 */
public abstract sealed class IntGraph permits AdjacencyListGraph, AdjacencyMatrixGraph {
    private final int vertices;
    private final boolean directed;
    private final long edges;
    IntGraph(int vertices, boolean directed, long edges) {
        this.vertices = vertices; this.directed = directed; this.edges = edges;
    }
    public final int vertexCount() { return vertices; }
    public final boolean directed() { return directed; }
    public final long edgeCount() { return edges; }
    public final boolean hasEdge(int from, int to) {
        GraphInputs.vertex(vertices, from); GraphInputs.vertex(vertices, to); return contains(from, to);
    }
    public final int[] neighbours(int vertex) {
        GraphInputs.vertex(vertices, vertex);
        int count = 0; var iterator = adjacent(vertex);
        while (iterator.hasNext()) { iterator.nextInt(); count++; }
        int[] result = new int[count]; iterator = adjacent(vertex);
        for (int index = 0; index < count; index++) { result[index] = iterator.nextInt(); }
        return result;
    }
    abstract boolean contains(int from, int to);
    abstract PrimitiveIterator.OfInt adjacent(int vertex);
}
