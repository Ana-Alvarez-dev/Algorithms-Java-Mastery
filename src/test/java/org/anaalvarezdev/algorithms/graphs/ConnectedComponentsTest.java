package org.anaalvarezdev.algorithms.graphs;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConnectedComponentsTest {
    @Test void shouldLabelAllComponentsIncludingIsolatedAndLoopVertices() {
        for (var graph : SmallGraphs.both(6,false,new int[][]{{0,2},{1,3},{4,4}})) {
            var result = ConnectedComponents.find(graph); assertThat(result.count()).isEqualTo(4);
            assertThat(result.labels()).containsExactly(0,1,0,1,2,3); result.labels()[0] = 99;
            assertThat(result.componentOf(0)).isZero();
            assertThatThrownBy(() -> result.componentOf(6)).isInstanceOf(IndexOutOfBoundsException.class);
        }
    }
    @Test void shouldHandleEmptyAndRejectDirectedOrNull() {
        assertThat(ConnectedComponents.find(new AdjacencyListGraph(0,false,new int[0][])).count()).isZero();
        assertThatThrownBy(() -> ConnectedComponents.find(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> ConnectedComponents.find(new AdjacencyListGraph(0,true,new int[0][]))).isInstanceOf(IllegalArgumentException.class);
    }
    @Test void shouldAgreeWithReachabilityOnEverySmallUndirectedGraph() {
        for (int mask = 0; mask < 64; mask++) {
            int[][] edges = SmallGraphs.edges(4,mask,false); int[][] distances = SmallGraphs.distances(4,false,edges);
            for (var graph : SmallGraphs.both(4,false,edges)) {
                var result = ConnectedComponents.find(graph);
                for (int a = 0; a < 4; a++) for (int b = 0; b < 4; b++) {
                    assertThat(result.componentOf(a) == result.componentOf(b)).isEqualTo(distances[a][b] < 999);
                }
            }
        }
    }
}
