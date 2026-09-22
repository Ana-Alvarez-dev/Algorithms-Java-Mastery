package org.anaalvarezdev.algorithms.arrays;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayTraversalTest {

    @Test
    void shouldVisitAllElementsInOrder() {
        int[] values = {10, 20, 30, 40, 50};
        List<Integer> visited = new ArrayList<>();

        ArrayTraversal.traverse(values, visited::add);

        assertThat(visited)
                .containsExactly(10, 20, 30, 40, 50);
    }
    @Test
    void shouldVisitSingleElement() {
        int[] values = {42};
        List<Integer> visited = new ArrayList<>();

        ArrayTraversal.traverse(values, visited::add);

        assertThat(visited)
                .containsExactly(42);
    }
    @Test
    void shouldNotVisitAnyElementWhenArrayIsEmpty() {
        int[] values = {};
        List<Integer> visited = new ArrayList<>();

        ArrayTraversal.traverse(values, visited::add);

        assertThat(visited).isEmpty();
    }
    @Test
    void shouldThrowExceptionWhenArrayIsNull() {

        assertThatThrownBy(() ->
                ArrayTraversal.traverse(null, value -> {
                }))
                .isInstanceOf(NullPointerException.class);
    }

}
