package org.anaalvarezdev.algorithms.graphs;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.PrimitiveIterator;

/** Immutable sorted primitive adjacency rows; construction normalises input. */
public final class AdjacencyListGraph extends IntGraph {
    private final int[][] rows;
    public AdjacencyListGraph(int vertices, boolean directed, int[][] edges) {
        this(vertices, directed, GraphInputs.normalise(vertices, directed, edges));
    }
    private AdjacencyListGraph(int vertices, boolean directed, GraphInputs.Data data) {
        super(vertices, directed, data.edges()); rows = data.rows();
    }
    @Override boolean contains(int from, int to) { return Arrays.binarySearch(rows[from], to) >= 0; }
    @Override PrimitiveIterator.OfInt adjacent(int vertex) {
        return new PrimitiveIterator.OfInt() {
            private int index;
            public boolean hasNext() { return index < rows[vertex].length; }
            public int nextInt() {
                if (!hasNext()) { throw new NoSuchElementException(); }
                return rows[vertex][index++];
            }
        };
    }
}
