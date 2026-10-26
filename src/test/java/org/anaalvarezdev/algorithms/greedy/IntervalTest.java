package org.anaalvarezdev.algorithms.greedy;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class IntervalTest {
    @Test void acceptsNegativeTimesAndExtremeEndpointsWithoutSubtraction() {
        assertThat(new Interval(Integer.MIN_VALUE,Integer.MAX_VALUE).start()).isEqualTo(Integer.MIN_VALUE);
        assertThat(new Interval(-10,-1).finish()).isEqualTo(-1);
    }
    @Test void rejectsEmptyAndReversedIntervals() {
        assertThatIllegalArgumentException().isThrownBy(() -> new Interval(2,2));
        assertThatIllegalArgumentException().isThrownBy(() -> new Interval(Integer.MAX_VALUE,Integer.MIN_VALUE));
    }
}
