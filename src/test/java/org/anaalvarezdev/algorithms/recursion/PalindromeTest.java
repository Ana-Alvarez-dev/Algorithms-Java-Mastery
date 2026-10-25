package org.anaalvarezdev.algorithms.recursion;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PalindromeTest {
    @Test void shouldHandleEmptyOddEvenAndExtremeValues() {
        for (int[] values : new int[][]{{},{1},{1,2,1},{Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MIN_VALUE}}) {
            assertThat(Palindrome.recursive(values)).isTrue(); assertThat(Palindrome.iterative(values)).isTrue();
        }
        assertThat(Palindrome.recursive(new int[]{1,2})).isFalse();
    }
    @Test void shouldMatchIndependentReversedCopyOracleForEverySmallArray() {
        for (int[] values : SmallArrays.all()) {
            int[] reversed = new int[values.length]; for (int i = 0; i < values.length; i++) { reversed[i] = values[values.length-1-i]; }
            int[] before = values.clone(); boolean expected = Arrays.equals(values,reversed);
            assertThat(Palindrome.recursive(values)).isEqualTo(expected); assertThat(Palindrome.iterative(values)).isEqualTo(expected);
            assertThat(values).containsExactly(before);
        }
    }
    @Test void shouldRejectNullForBothVariants() {
        assertThatThrownBy(() -> Palindrome.recursive(null)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> Palindrome.iterative(null)).isInstanceOf(NullPointerException.class);
    }
    @Test void shouldHandleLargeSymmetryAndEarlyOrLateMismatchWithBalancedDepth() {
        int[] values = new int[1000000]; Arrays.fill(values,7);
        assertThat(Palindrome.recursive(values)).isTrue(); assertThat(Palindrome.iterative(values)).isTrue();
        values[0] = 8; assertThat(Palindrome.recursive(values)).isFalse(); values[0] = 7;
        values[values.length/2] = 8; assertThat(Palindrome.recursive(values)).isFalse(); assertThat(Palindrome.iterative(values)).isFalse();
    }
}
