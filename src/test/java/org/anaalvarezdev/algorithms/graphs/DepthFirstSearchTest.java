package org.anaalvarezdev.algorithms.graphs;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DepthFirstSearchTest {
    @Test void shouldMatchRecursiveNeighbourOrderWithCrossEdges() {
        for (var graph : SmallGraphs.both(5,true,new int[][]{{0,1},{0,2},{1,2},{1,3},{2,0},{2,4}})) {
            assertThat(DepthFirstSearch.traverse(graph,0)).containsExactly(0,1,2,4,3);
        }
    }
    @Test void shouldValidateNullAndInvalidSource() {
        assertThatThrownBy(() -> DepthFirstSearch.traverse(null,0)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> DepthFirstSearch.traverse(new AdjacencyListGraph(0,true,new int[0][]),0)).isInstanceOf(IndexOutOfBoundsException.class);
    }
    @Test void shouldMatchRecursiveOracleForEverySmallDirectedGraphAndSource() {
        for (int mask = 0; mask < 512; mask++) for (var graph : SmallGraphs.both(3,true,SmallGraphs.edges(3,mask,true))) {
            for (int source = 0; source < 3; source++) {
                List<Integer> expected = new ArrayList<>(); visit(graph,source,new boolean[3],expected);
                assertThat(DepthFirstSearch.traverse(graph,source)).containsExactly(expected.stream().mapToInt(Integer::intValue).toArray());
            }
        }
    }
    @Test void shouldTraverseDeepPathsWithoutCallStackRecursion() {
        int n = 100000; int[][] edges = new int[n-1][2];
        for (int i = 0; i < n-1; i++) { edges[i] = new int[]{i,i+1}; }
        var graph = new AdjacencyListGraph(n,true,edges); int[] order = DepthFirstSearch.traverse(graph,0);
        assertThat(order).hasSize(n); for (int i = 0; i < n; i++) { assertThat(order[i]).isEqualTo(i); }
        assertThat(BreadthFirstSearch.search(graph,0).distanceTo(n-1)).isEqualTo(n-1);
    }
    private static void visit(IntGraph graph, int node, boolean[] seen, List<Integer> order) {
        seen[node] = true; order.add(node);
        for (int to : graph.neighbours(node)) { if (!seen[to]) { visit(graph,to,seen,order); } }
    }
}
