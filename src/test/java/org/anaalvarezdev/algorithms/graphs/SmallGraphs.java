package org.anaalvarezdev.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;

final class SmallGraphs {
    private SmallGraphs() { }
    static int[][] edges(int n, int mask, boolean directed) {
        List<int[]> edges = new ArrayList<>(); int bit = 0;
        for (int from = 0; from < n; from++) for (int to = directed ? 0 : from + 1; to < n; to++) {
            if ((mask & (1 << bit)) != 0) { edges.add(new int[]{from,to}); } bit++;
        }
        return edges.toArray(int[][]::new);
    }
    static IntGraph[] both(int n, boolean directed, int[][] edges) {
        return new IntGraph[]{new AdjacencyListGraph(n,directed,edges),new AdjacencyMatrixGraph(n,directed,edges)};
    }
    static int[][] distances(int n, boolean directed, int[][] edges) {
        int[][] d = new int[n][n];
        for (int from = 0; from < n; from++) for (int to = 0; to < n; to++) { d[from][to] = from == to ? 0 : 999; }
        for (int[] edge : edges) { d[edge[0]][edge[1]] = Math.min(d[edge[0]][edge[1]],1); if (!directed) { d[edge[1]][edge[0]] = Math.min(d[edge[1]][edge[0]],1); } }
        for (int via = 0; via < n; via++) for (int from = 0; from < n; from++) for (int to = 0; to < n; to++) { d[from][to] = Math.min(d[from][to],d[from][via]+d[via][to]); }
        return d;
    }
}
