package org.anaalvarezdev.algorithms.hashtables;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HashingTest {
    @Test void shouldRoundSupportedCapacitiesWithoutOverflow() {
        assertThat(Hashing.capacity(1)).isEqualTo(4); assertThat(Hashing.capacity(5)).isEqualTo(8);
        assertThat(Hashing.capacity(Hashing.MAXIMUM_CAPACITY)).isEqualTo(Hashing.MAXIMUM_CAPACITY);
        assertThat(Hashing.grow(1<<29)).isEqualTo(Hashing.MAXIMUM_CAPACITY);
        assertThatThrownBy(() -> Hashing.grow(Hashing.MAXIMUM_CAPACITY)).isInstanceOf(IllegalStateException.class);
    }
    @Test void shouldRejectInvalidRequestsBeforeAllocating() {
        for(int value:new int[]{0,-1,Integer.MAX_VALUE}) {
            assertThatThrownBy(() -> new SeparateChainingHashTable<>(value)).isInstanceOf(IllegalArgumentException.class);
            assertThatThrownBy(() -> new LinearProbingHashTable<>(value)).isInstanceOf(IllegalArgumentException.class);
        }
        assertThatThrownBy(() -> Hashing.grow(3)).isInstanceOf(IllegalArgumentException.class);
    }
}
