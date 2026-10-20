package org.anaalvarezdev.algorithms.graphs;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.anaalvarezdev.algorithms.graphs.WeightedGraph.Edge;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WeightedGraphTest {
    @Test void shouldCopyInputAndExposeImmutableSortedCanonicalEdges() {
        List<Edge> input = new ArrayList<>(List.of(new Edge(2,0,0),new Edge(1,2,-3),new Edge(1,1,8)));
        var graph = new WeightedGraph(4,false,input); input.clear();
        assertThat(graph.outgoing(2)).containsExactly(new Edge(2,0,0),new Edge(2,1,-3));
        assertThat(graph.edges()).containsExactly(new Edge(0,2,0),new Edge(1,1,8),new Edge(1,2,-3));
        assertThat(graph.outgoing(3)).isEmpty();
        assertThatThrownBy(() -> graph.edges().clear()).isInstanceOf(UnsupportedOperationException.class);
        assertThatThrownBy(() -> graph.outgoing(2).clear()).isInstanceOf(UnsupportedOperationException.class);
    }
    @Test void shouldRespectDirectionZeroWeightsAndLoops() {
        var graph = new WeightedGraph(2,true,List.of(new Edge(1,0,0),new Edge(1,1,-1)));
        assertThat(graph.outgoing(0)).isEmpty(); assertThat(graph.edges()).hasSize(2); assertThat(graph.directed()).isTrue();
    }
    @Test void shouldRejectDuplicatePairsIncludingReverseUndirectedPairs() {
        assertThatThrownBy(() -> new WeightedGraph(2,true,List.of(new Edge(0,1,1),new Edge(0,1,2)))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new WeightedGraph(2,false,List.of(new Edge(0,1,1),new Edge(1,0,1)))).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new WeightedGraph(1,false,List.of(new Edge(0,0,1),new Edge(0,0,1)))).isInstanceOf(IllegalArgumentException.class);
        assertThat(new WeightedGraph(2,true,List.of(new Edge(0,1,1),new Edge(1,0,1))).edges()).hasSize(2);
    }
    @Test void shouldValidateAllConstructionAndQueryInputs() {
        assertThatThrownBy(() -> new WeightedGraph(-1,true,List.of())).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new WeightedGraph(1,true,null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new WeightedGraph(1,true,Arrays.asList((Edge)null))).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new WeightedGraph(1,true,List.of(new Edge(0,1,0)))).isInstanceOf(IndexOutOfBoundsException.class);
        var empty = new WeightedGraph(0,false,List.of()); assertThat(empty.vertexCount()).isZero();
        assertThatThrownBy(() -> empty.outgoing(0)).isInstanceOf(IndexOutOfBoundsException.class);
    }
}
