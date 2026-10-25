package org.anaalvarezdev.algorithms.graphs;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TopologicalSortTest {
    @Test void shouldDistinguishEmptyDagFromCycleAndRejectUndirected() {
        assertThat(TopologicalSort.sort(new AdjacencyListGraph(0,true,new int[0][]))).hasValueSatisfying(a -> assertThat(a).isEmpty());
        assertThatThrownBy(() -> TopologicalSort.sort(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> TopologicalSort.sort(new AdjacencyListGraph(0,false,new int[0][]))).isInstanceOf(IllegalArgumentException.class);
    }
    @Test void shouldIncludeIsolatedVerticesAndUseDeterministicFifoOrder() {
        for (var graph : SmallGraphs.both(5,true,new int[][]{{0,2},{1,2},{2,3}})) {
            assertThat(TopologicalSort.sort(graph).orElseThrow()).containsExactly(0,1,4,2,3);
        }
    }
    @Test void shouldRejectCyclesIncludingDisconnectedCyclesAndSelfLoops() {
        for (int[][] edges : new int[][][]{{{0,0}},{{1,2},{2,1}},{{0,1},{1,2},{2,0}}}) {
            for (var graph : SmallGraphs.both(3,true,edges)) { assertThat(TopologicalSort.sort(graph)).isEmpty(); }
        }
    }
    @Test void shouldMatchIndependentCycleOracleForEverySmallDirectedGraph() {
        for (int mask = 0; mask < 512; mask++) {
            int[][] edges = SmallGraphs.edges(3,mask,true); boolean cycle = false;
            boolean[][] reach = new boolean[3][3]; for (int[] edge : edges) { reach[edge[0]][edge[1]] = true; }
            for (int via = 0; via < 3; via++) for (int a = 0; a < 3; a++) for (int b = 0; b < 3; b++) { reach[a][b] |= reach[a][via] && reach[via][b]; }
            for (int v = 0; v < 3; v++) { cycle |= reach[v][v]; }
            for (var graph : SmallGraphs.both(3,true,edges)) {
                var result = TopologicalSort.sort(graph); assertThat(result.isEmpty()).isEqualTo(cycle);
                if (result.isPresent()) {
                    int[] order = result.orElseThrow(); assertThat(order).containsExactlyInAnyOrder(0,1,2); int[] position = new int[3];
                    for (int i = 0; i < 3; i++) { position[order[i]] = i; }
                    for (int[] edge : edges) { assertThat(position[edge[0]]).isLessThan(position[edge[1]]); }
                }
            }
        }
    }
}
