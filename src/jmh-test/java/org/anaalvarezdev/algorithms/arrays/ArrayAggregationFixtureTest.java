package org.anaalvarezdev.algorithms.arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openjdk.jmh.annotations.Param;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/** Untimed contract checks for the actual JMH fixture, active only in -Pbenchmark. */
class ArrayAggregationFixtureTest {
    @ParameterizedTest(name = "default size={0}: bounded sum and preserved input")
    @MethodSource("defaultSizes")
    void defaultFixturesRespectTheSumContract(int size) throws ReflectiveOperationException {
        checkFixture(size);
    }

    static IntStream defaultSizes() throws ReflectiveOperationException {
        return Arrays.stream(field("size").getAnnotation(Param.class).value())
                .mapToInt(Integer::parseInt);
    }

    @ParameterizedTest(name = "boundary size={0}")
    @ValueSource(ints = {0, 1, 99, 103})
    void emptyAndPartialCyclesRespectTheSameContract(int size) throws ReflectiveOperationException {
        checkFixture(size);
    }

    @ParameterizedTest(name = "unsupported size={0}")
    @ValueSource(ints = {-1, 1_000_001, Integer.MAX_VALUE})
    void unsupportedSizesFailBeforeAllocation(int size) throws ReflectiveOperationException {
        var benchmark = fixture(size);
        assertThatThrownBy(benchmark::setup)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("aggregation fixture requires size 0..1000000");
    }

    @Test
    void trialPreparationCreatesFreshIndependentArrays() throws ReflectiveOperationException {
        var first = fixture(101);
        first.setup();
        int[] previousTrial = values(first);
        previousTrial[0] = -1;
        first.setup();
        var second = fixture(101);
        second.setup();
        assertThat(values(first)).isNotSameAs(previousTrial).startsWith(0, 1, 2);
        assertThat(values(first)).isNotSameAs(values(second)).containsExactly(values(second));
    }

    private static void checkFixture(int size) throws ReflectiveOperationException {
        var benchmark = fixture(size);
        benchmark.setup();
        int[] values = values(benchmark);
        int[] original = values.clone();
        long cycles = size / 100;
        long remainder = size % 100;
        long formula = cycles * 4_950 + remainder * (remainder - 1) / 2;
        long oracle = Arrays.stream(values).asLongStream().sum();
        assertThat(values).hasSize(size);
        assertThat(Arrays.stream(values).allMatch(v -> v >= 0 && v <= 99))
                .as("all prepared values are bounded, n=%d", size).isTrue();
        assertThat(oracle).as("independent cycle sum, n=%d", size).isEqualTo(formula);
        assertThat(oracle).isBetween(0L, (long) Integer.MAX_VALUE);
        for (int call = 0; call < 3; call++) {
            assertThat((long) ArrayAggregation.sum(values)).isEqualTo(oracle);
            assertThat(Arrays.equals(values, original))
                    .as("input preserved after call %d, n=%d", call, size).isTrue();
        }
    }

    private static ArrayAggregationBenchmark fixture(int size) throws ReflectiveOperationException {
        var benchmark = new ArrayAggregationBenchmark();
        field("size").setInt(benchmark, size);
        return benchmark;
    }

    private static int[] values(ArrayAggregationBenchmark benchmark) throws ReflectiveOperationException {
        return (int[]) field("array").get(benchmark);
    }

    private static Field field(String name) throws NoSuchFieldException {
        Field field = ArrayAggregationBenchmark.class.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }
}
