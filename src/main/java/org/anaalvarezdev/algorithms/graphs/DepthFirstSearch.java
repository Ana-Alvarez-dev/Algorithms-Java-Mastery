package org.anaalvarezdev.algorithms.graphs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Objects;
import java.util.PrimitiveIterator;

/** Iterative recursive-order DFS, ascending neighbours; visits source reachability only. */
public final class DepthFirstSearch {
    private record Frame(PrimitiveIterator.OfInt neighbours) { }
    private DepthFirstSearch() { }
    public static int[] traverse(IntGraph graph, int source) {
        Objects.requireNonNull(graph, "graph must not be null"); GraphInputs.vertex(graph.vertexCount(), source);
        boolean[] seen = new boolean[graph.vertexCount()]; int[] order = new int[seen.length]; int count = 0;
        ArrayDeque<Frame> stack = new ArrayDeque<>(); seen[source] = true; order[count++] = source;
        stack.push(new Frame(graph.adjacent(source)));
        while (!stack.isEmpty()) {
            var neighbours = stack.peek().neighbours();
            if (!neighbours.hasNext()) { stack.pop(); continue; }
            int to = neighbours.nextInt();
            if (!seen[to]) { seen[to] = true; order[count++] = to; stack.push(new Frame(graph.adjacent(to))); }
        }
        return Arrays.copyOf(order, count);
    }
}
