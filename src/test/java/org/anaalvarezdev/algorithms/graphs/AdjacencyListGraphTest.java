package org.anaalvarezdev.algorithms.graphs;
class AdjacencyListGraphTest extends GraphContractTest {
    @Override IntGraph create(int n, boolean directed, int[][] edges) { return new AdjacencyListGraph(n,directed,edges); }
}
