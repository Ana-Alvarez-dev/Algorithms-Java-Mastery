package org.anaalvarezdev.algorithms.graphs;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import java.util.SplittableRandom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

abstract class GraphContractTest {
    abstract IntGraph create(int vertices, boolean directed, int[][] edges);
    @Test void shouldRepresentEmptyAndIsolatedVertices() {
        var empty = create(0,true,new int[0][]); assertThat(empty.vertexCount()).isZero(); assertThat(empty.edgeCount()).isZero();
        var graph = create(3,false,new int[0][]); assertThat(graph.neighbours(2)).isEmpty(); assertThat(graph.hasEdge(0,1)).isFalse();
    }
    @Test void shouldRespectDirectionAndCountLoopsOnce() {
        int[][] edges = {{2,0},{0,2},{2,2},{2,2},{2,1}};
        var directed = create(3,true,edges); assertThat(directed.edgeCount()).isEqualTo(4); assertThat(directed.hasEdge(1,2)).isFalse();
        var undirected = create(3,false,edges); assertThat(undirected.edgeCount()).isEqualTo(3);
        assertThat(undirected.neighbours(2)).containsExactly(0,1,2); assertThat(undirected.hasEdge(1,2)).isTrue();
    }
    @Test void shouldCopyInputAndReturnIndependentSortedNeighbours() {
        int[][] edges = {{0,3},{0,1},{0,2}}; var graph = create(4,true,edges); edges[0][1] = 0;
        int[] result = graph.neighbours(0); assertThat(result).containsExactly(1,2,3); result[0] = 99;
        assertThat(graph.neighbours(0)).containsExactly(1,2,3); assertThat(graph.neighbours(3)).isEmpty();
    }
    @Test void shouldRejectInvalidConstruction() {
        assertThatThrownBy(() -> create(-1,true,new int[0][])).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> create(2,true,null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> create(2,true,new int[][]{null})).isInstanceOf(NullPointerException.class);
        for (int[] edge : new int[][]{{},{0},{0,1,2}}) {
            assertThatThrownBy(() -> create(2,true,new int[][]{edge})).isInstanceOf(IllegalArgumentException.class);
        }
        for (int[] edge : new int[][]{{-1,0},{0,2}}) {
            assertThatThrownBy(() -> create(2,true,new int[][]{edge})).isInstanceOf(IndexOutOfBoundsException.class);
        }
    }
    @Test void shouldValidateEveryPublicQueryEndpoint() {
        var graph = create(2,true,new int[][]{{0,1}});
        for (int bad : new int[]{-1,2}) {
            assertThatThrownBy(() -> graph.neighbours(bad)).isInstanceOf(IndexOutOfBoundsException.class);
            assertThatThrownBy(() -> graph.hasEdge(bad,0)).isInstanceOf(IndexOutOfBoundsException.class);
            assertThatThrownBy(() -> graph.hasEdge(0,bad)).isInstanceOf(IndexOutOfBoundsException.class);
        }
        assertThat(graph.edgeCount()).isEqualTo(1);
    }
    @Test void shouldTerminateInternalIteratorsAndRejectRemoval() {
        var graph = create(4,true,new int[][]{{0,3},{0,1}}); var it = graph.adjacent(0);
        assertThat(it.hasNext()).isTrue(); assertThat(it.hasNext()).isTrue(); assertThat(it.nextInt()).isEqualTo(1);
        assertThatThrownBy(it::remove).isInstanceOf(UnsupportedOperationException.class);
        assertThat(it.nextInt()).isEqualTo(3); assertThat(it.hasNext()).isFalse();
        assertThatThrownBy(it::nextInt).isInstanceOf(NoSuchElementException.class);
    }
    @Test void shouldKeepInstancesIndependent() {
        var a = create(2,false,new int[][]{{0,1}}); var b = create(2,false,new int[0][]);
        assertThat(a.hasEdge(0,1)).isTrue(); assertThat(b.hasEdge(0,1)).isFalse();
    }
    @Test void shouldMatchBooleanModelForSeededDuplicateAndLoopInputs() {
        var random = new SplittableRandom(20261020);
        for (boolean directed : new boolean[]{true,false}) for (int trial = 0; trial < 100; trial++) {
            boolean[][] model = new boolean[10][10]; int[][] edges = new int[75][2];
            for (int[] edge : edges) { edge[0] = random.nextInt(10); edge[1] = random.nextInt(10); model[edge[0]][edge[1]] = true; if (!directed) { model[edge[1]][edge[0]] = true; } }
            var graph = create(10,directed,edges); long count = 0;
            for (int from = 0; from < 10; from++) {
                java.util.List<Integer> expected = new java.util.ArrayList<>();
                for (int to = 0; to < 10; to++) { assertThat(graph.hasEdge(from,to)).isEqualTo(model[from][to]); if (model[from][to]) { expected.add(to); if (directed || from <= to) { count++; } } }
                assertThat(graph.neighbours(from)).containsExactly(expected.stream().mapToInt(Integer::intValue).toArray());
            }
            assertThat(graph.edgeCount()).isEqualTo(count);
        }
    }
}
