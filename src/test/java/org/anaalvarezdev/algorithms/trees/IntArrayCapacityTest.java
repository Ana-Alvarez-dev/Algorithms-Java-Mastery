package org.anaalvarezdev.algorithms.trees;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class IntArrayCapacityTest {
    @Test void shouldCheckBoundariesWithoutAllocatingHugeArrays() {
        assertThat(IntArrayCapacity.initial(0)).isEqualTo(1);
        assertThat(IntArrayCapacity.initial(IntArrayCapacity.MAXIMUM)).isEqualTo(IntArrayCapacity.MAXIMUM);
        assertThat(IntArrayCapacity.grow(1)).isEqualTo(2);
        assertThat(IntArrayCapacity.grow(IntArrayCapacity.MAXIMUM / 2 + 1)).isEqualTo(IntArrayCapacity.MAXIMUM);
        assertThatThrownBy(() -> IntArrayCapacity.grow(IntArrayCapacity.MAXIMUM)).isInstanceOf(IllegalStateException.class);
    }
    @Test void shouldRejectInvalidArithmeticInputsBeforeAllocation() {
        for (int value : new int[]{-1,IntArrayCapacity.MAXIMUM + 1}) {
            assertThatThrownBy(() -> IntArrayCapacity.initial(value)).isInstanceOf(IllegalArgumentException.class);
        }
        for (int value : new int[]{0,-1,IntArrayCapacity.MAXIMUM + 1}) {
            assertThatThrownBy(() -> IntArrayCapacity.grow(value)).isInstanceOf(IllegalArgumentException.class);
        }
    }
}
