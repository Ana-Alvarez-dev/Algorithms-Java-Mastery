package org.anaalvarezdev.algorithms.graphs;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;
import java.util.SplittableRandom;
import static org.anaalvarezdev.algorithms.graphs.WeightedGraph.Edge;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DijkstraTest {
    @Test void shouldSkipStaleEntriesAndReturnDistancesAndIndependentPaths() {
        var graph = new WeightedGraph(5,true,List.of(new Edge(0,1,10),new Edge(0,2,1),new Edge(2,1,1),new Edge(1,3,2),new Edge(2,3,20)));
        var result = Dijkstra.shortestPaths(graph,0); assertThat(result.distanceTo(3)).hasValue(4);
        assertThat(result.pathTo(3)).containsExactly(0,2,1,3); assertThat(result.pathTo(4)).isEmpty(); assertThat(result.distanceTo(4)).isEmpty();
        result.pathTo(3)[0] = 99; assertThat(result.pathTo(3)).containsExactly(0,2,1,3);
        assertThat(result.pathTo(0)).containsExactly(0); assertThat(result.distanceTo(0)).hasValue(0);
    }
    @Test void shouldHandleZeroCyclesTiesAndUndirectedEdgesWithoutParentCycles() {
        var graph = new WeightedGraph(4,false,List.of(new Edge(0,1,0),new Edge(1,2,0),new Edge(0,2,0),new Edge(2,3,1),new Edge(1,3,1)));
        var result = Dijkstra.shortestPaths(graph,0); assertThat(result.distanceTo(3)).hasValue(1);
        assertThat(result.pathTo(3)).containsExactly(0,1,3); assertThat(result.pathTo(2)).containsExactly(0,2);
    }
    @Test void shouldUseLongDistancesBeyondTheIntRange() {
        var graph = new WeightedGraph(3,true,List.of(new Edge(0,1,Integer.MAX_VALUE),new Edge(1,2,Integer.MAX_VALUE)));
        assertThat(Dijkstra.shortestPaths(graph,0).distanceTo(2)).hasValue(4294967294L);
    }
    @Test void shouldRejectNegativeWeightsGloballyAndValidateSourceAndResults() {
        assertThatThrownBy(() -> Dijkstra.shortestPaths(null,0)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> Dijkstra.shortestPaths(new WeightedGraph(0,true,List.of()),0)).isInstanceOf(IndexOutOfBoundsException.class);
        var negative = new WeightedGraph(3,true,List.of(new Edge(1,2,-1)));
        assertThatThrownBy(() -> Dijkstra.shortestPaths(negative,0)).isInstanceOf(IllegalArgumentException.class);
        var result = Dijkstra.shortestPaths(new WeightedGraph(1,true,List.of()),0);
        assertThatThrownBy(() -> result.distanceTo(1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> result.pathTo(-1)).isInstanceOf(IndexOutOfBoundsException.class);
    }
    @Test void shouldMatchRepeatedRelaxationOracleAcrossSeededGraphsAndAllSources() {
        var random = new SplittableRandom(20261020);
        for (boolean directed : new boolean[]{true,false}) for (int trial = 0; trial < 200; trial++) {
            List<Edge> edges = new ArrayList<>(); int n = 6;
            for (int from = 0; from < n; from++) for (int to = directed ? 0 : from; to < n; to++) {
                if (random.nextInt(3) == 0) { edges.add(new Edge(from,to,random.nextInt(10))); }
            }
            var graph = new WeightedGraph(n,directed,edges);
            for (int source = 0; source < n; source++) {
                long[] expected = new long[n]; Arrays.fill(expected,999999); expected[source] = 0;
                for (int pass = 0; pass < n-1; pass++) {
                    long[] next = expected.clone();
                    for (Edge edge : edges) {
                        next[edge.to()] = Math.min(next[edge.to()],expected[edge.from()] + edge.weight());
                        if (!directed) { next[edge.from()] = Math.min(next[edge.from()],expected[edge.to()] + edge.weight()); }
                    }
                    expected = next;
                }
                var result = Dijkstra.shortestPaths(graph,source);
                for (int target = 0; target < n; target++) {
                    assertThat(result.distanceTo(target)).isEqualTo(expected[target] == 999999 ? OptionalLong.empty() : OptionalLong.of(expected[target]));
                    int[] path = result.pathTo(target); if (path.length == 0) { continue; }
                    assertThat(path[0]).isEqualTo(source); assertThat(path[path.length-1]).isEqualTo(target);
                    assertThat(path).doesNotHaveDuplicates(); long sum = 0;
                    for (int i = 1; i < path.length; i++) {
                        int destination = path[i]; sum += graph.outgoing(path[i-1]).stream().filter(e -> e.to() == destination).findFirst().orElseThrow().weight();
                    }
                    assertThat(sum).isEqualTo(expected[target]);
                }
            }
        }
    }
}
