package org.anaalvarezdev.algorithms.graphs;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.anaalvarezdev.algorithms.graphs.WeightedGraph.Edge;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KruskalTest {
    @Test void shouldReturnForestWithNegativeWeightsLoopsAndIsolatedVertices() {
        var graph = new WeightedGraph(5,false,List.of(new Edge(0,0,Integer.MIN_VALUE),new Edge(0,1,-4),new Edge(1,2,2),new Edge(0,2,8),new Edge(3,3,1)));
        var result = Kruskal.minimumSpanningForest(graph); assertThat(result.totalWeight()).isEqualTo(-2); assertThat(result.components()).isEqualTo(3);
        assertThat(result.edges()).containsExactly(new Edge(0,1,-4),new Edge(1,2,2));
        assertThatThrownBy(() -> result.edges().clear()).isInstanceOf(UnsupportedOperationException.class);
    }
    @Test void shouldHandleEmptyAndLongTotalsAndRejectDirectedOrNull() {
        assertThat(Kruskal.minimumSpanningForest(new WeightedGraph(0,false,List.of())).components()).isZero();
        var graph = new WeightedGraph(3,false,List.of(new Edge(0,1,Integer.MAX_VALUE),new Edge(1,2,Integer.MAX_VALUE)));
        assertThat(Kruskal.minimumSpanningForest(graph).totalWeight()).isEqualTo(4294967294L);
        assertThatThrownBy(() -> Kruskal.minimumSpanningForest(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> Kruskal.minimumSpanningForest(new WeightedGraph(0,true,List.of()))).isInstanceOf(IllegalArgumentException.class);
    }
    @Test void shouldUseDeterministicWeightAndEndpointTies() {
        var graph = new WeightedGraph(3,false,List.of(new Edge(2,1,0),new Edge(2,0,0),new Edge(1,0,0)));
        assertThat(Kruskal.minimumSpanningForest(graph).edges()).containsExactly(new Edge(0,1,0),new Edge(0,2,0));
    }
    @Test void shouldMatchExhaustiveForestOracleOnEveryFourVertexGraphAndThreeWeightAssignments() {
        for (int mask = 0; mask < 64; mask++) for (int assignment = 0; assignment < 3; assignment++) {
            int[][] pairs = SmallGraphs.edges(4,mask,false); List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < pairs.length; i++) { edges.add(new Edge(pairs[i][0],pairs[i][1],assignment == 0 ? 1 : assignment == 1 ? i-3 : 3-i)); }
            var graph = new WeightedGraph(4,false,edges); var result = Kruskal.minimumSpanningForest(graph);
            int[][] full = SmallGraphs.distances(4,false,pairs); int components = 0;
            for (int v = 0; v < 4; v++) { boolean first = true; for (int u = 0; u < v; u++) { first &= full[v][u] == 999; } if (first) { components++; } }
            long best = Long.MAX_VALUE;
            for (int subset = 0; subset < (1 << edges.size()); subset++) {
                if (Integer.bitCount(subset) != 4-components) { continue; }
                List<int[]> chosen = new ArrayList<>(); long weight = 0;
                for (int i = 0; i < edges.size(); i++) if ((subset & (1 << i)) != 0) { Edge e = edges.get(i); chosen.add(new int[]{e.from(),e.to()}); weight += e.weight(); }
                int[][] reach = SmallGraphs.distances(4,false,chosen.toArray(int[][]::new)); boolean matches = true;
                for (int a = 0; a < 4; a++) for (int b = 0; b < 4; b++) { matches &= (full[a][b] < 999) == (reach[a][b] < 999); }
                if (matches) { best = Math.min(best,weight); }
            }
            assertThat(result.totalWeight()).isEqualTo(best); assertThat(result.components()).isEqualTo(components);
            assertThat(result.edges()).hasSize(4-components);
            int[][] selected = result.edges().stream().map(e -> new int[]{e.from(),e.to()}).toArray(int[][]::new);
            int[][] actual = SmallGraphs.distances(4,false,selected);
            for (int a = 0; a < 4; a++) for (int b = 0; b < 4; b++) { assertThat(actual[a][b] < 999).isEqualTo(full[a][b] < 999); }
        }
    }
}
