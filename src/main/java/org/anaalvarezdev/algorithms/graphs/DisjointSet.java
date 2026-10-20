package org.anaalvarezdev.algorithms.graphs;

/** Union by size and iterative path halving; internal to Kruskal. */
final class DisjointSet {
    private final int[] parent;
    private final int[] sizes;
    private int count;
    DisjointSet(int vertices) {
        GraphInputs.count(vertices); parent = new int[vertices]; sizes = new int[vertices]; count = vertices;
        for (int vertex = 0; vertex < vertices; vertex++) { parent[vertex] = vertex; sizes[vertex] = 1; }
    }
    int count() { return count; }
    int find(int vertex) {
        GraphInputs.vertex(parent.length, vertex);
        while (parent[vertex] != vertex) { parent[vertex] = parent[parent[vertex]]; vertex = parent[vertex]; }
        return vertex;
    }
    boolean union(int left, int right) {
        int a = find(left); int b = find(right);
        if (a == b) { return false; }
        if (sizes[a] < sizes[b]) { int temp = a; a = b; b = temp; }
        parent[b] = a; sizes[a] += sizes[b]; count--; return true;
    }
}
