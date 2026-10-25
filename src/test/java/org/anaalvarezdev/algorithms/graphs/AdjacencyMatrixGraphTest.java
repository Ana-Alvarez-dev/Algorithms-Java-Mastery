package org.anaalvarezdev.algorithms.graphs;
class AdjacencyMatrixGraphTest extends GraphContractTest {
    @Override IntGraph create(int n, boolean directed, int[][] edges) { return new AdjacencyMatrixGraph(n,directed,edges); }
}
