package org.anaalvarezdev.algorithms.graphs;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BreadthFirstSearchTest {
    @Test void shouldFindDeterministicShortestPathsAndUnreachableVertices() {
        for (var graph : SmallGraphs.both(6,true,new int[][]{{0,2},{0,1},{1,3},{2,3},{3,0},{3,3}})) {
            var result = BreadthFirstSearch.search(graph,0); assertThat(result.order()).containsExactly(0,1,2,3);
            assertThat(result.pathTo(3)).containsExactly(0,1,3); assertThat(result.pathTo(0)).containsExactly(0);
            assertThat(result.distanceTo(5)).isEqualTo(-1); assertThat(result.pathTo(5)).isEmpty();
        }
    }
    @Test void shouldValidateNullEmptyAndInvalidVertices() {
        assertThatThrownBy(() -> BreadthFirstSearch.search(null,0)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> BreadthFirstSearch.search(new AdjacencyListGraph(0,true,new int[0][]),0)).isInstanceOf(IndexOutOfBoundsException.class);
        var graph = new AdjacencyListGraph(1,true,new int[0][]);
        assertThatThrownBy(() -> BreadthFirstSearch.search(graph,-1)).isInstanceOf(IndexOutOfBoundsException.class);
        var result = BreadthFirstSearch.search(graph,0);
        assertThatThrownBy(() -> result.pathTo(1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> result.distanceTo(-1)).isInstanceOf(IndexOutOfBoundsException.class);
    }
    @Test void shouldReturnIndependentSnapshots() {
        var result = BreadthFirstSearch.search(new AdjacencyListGraph(2,false,new int[][]{{0,1}}),0);
        result.order()[0] = 9; result.pathTo(1)[0] = 9;
        assertThat(result.order()).containsExactly(0,1); assertThat(result.pathTo(1)).containsExactly(0,1);
    }
    @Test void shouldMatchAllPairsOracleOnEveryThreeVertexDirectedGraphWithLoops() {
        for (int mask = 0; mask < 512; mask++) {
            int[][] edges = SmallGraphs.edges(3,mask,true); int[][] expected = SmallGraphs.distances(3,true,edges);
            for (var graph : SmallGraphs.both(3,true,edges)) for (int source = 0; source < 3; source++) {
                var result = BreadthFirstSearch.search(graph,source);
                for (int target = 0; target < 3; target++) {
                    int d = expected[source][target]; assertThat(result.distanceTo(target)).isEqualTo(d == 999 ? -1 : d);
                    int[] path = result.pathTo(target); assertThat(path.length).isEqualTo(d == 999 ? 0 : d+1);
                    for (int i = 1; i < path.length; i++) { assertThat(graph.hasEdge(path[i-1],path[i])).isTrue(); }
                }
            }
        }
    }
    @Test void shouldMatchEveryFourVertexUndirectedGraph() {
        for (int mask = 0; mask < 64; mask++) {
            int[][] edges = SmallGraphs.edges(4,mask,false); int[][] expected = SmallGraphs.distances(4,false,edges);
            for (var graph : SmallGraphs.both(4,false,edges)) for (int source = 0; source < 4; source++) {
                var result = BreadthFirstSearch.search(graph,source);
                for (int target = 0; target < 4; target++) { assertThat(result.distanceTo(target)).isEqualTo(expected[source][target] == 999 ? -1 : expected[source][target]); }
            }
        }
    }
}
