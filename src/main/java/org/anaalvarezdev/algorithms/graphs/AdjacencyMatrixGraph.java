package org.anaalvarezdev.algorithms.graphs;

import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;

/** Immutable boolean matrix; an internal neighbour iterator scans one row. */
public final class AdjacencyMatrixGraph extends IntGraph {
    private final boolean[][] rows;
    public AdjacencyMatrixGraph(int vertices, boolean directed, int[][] edges) {
        this(vertices, directed, GraphInputs.normalise(vertices, directed, edges));
    }
    private AdjacencyMatrixGraph(int vertices, boolean directed, GraphInputs.Data data) {
        super(vertices, directed, data.edges()); rows = new boolean[vertices][vertices];
        for (int from = 0; from < vertices; from++) for (int to : data.rows()[from]) { rows[from][to] = true; }
    }
    @Override boolean contains(int from, int to) { return rows[from][to]; }
    @Override PrimitiveIterator.OfInt adjacent(int vertex) {
        return new PrimitiveIterator.OfInt() {
            private int next;
            public boolean hasNext() {
                while (next < rows.length && !rows[vertex][next]) { next++; }
                return next < rows.length;
            }
            public int nextInt() {
                if (!hasNext()) { throw new NoSuchElementException(); }
                return next++;
            }
        };
    }
}
