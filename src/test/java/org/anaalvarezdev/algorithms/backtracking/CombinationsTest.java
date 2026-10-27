package org.anaalvarezdev.algorithms.backtracking;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class CombinationsTest {
    @Test void rejectsInvalidSizesAndRanksBeforeExploration() {
        for (int[] pair : new int[][]{{-1,0},{19,0},{3,-1},{3,4}}) {
            assertThatIllegalArgumentException().isThrownBy(() -> Combinations.choose(pair[0],pair[1]));
            assertThatIllegalArgumentException().isThrownBy(() -> Combinations.countWithPruning(pair[0],pair[1]));
            assertThatIllegalArgumentException().isThrownBy(() -> Combinations.countWithoutPruning(pair[0],pair[1]));
        }
    }

    @Test void zeroAndFullSelectionHaveOneImmutableRow() {
        assertThat(Combinations.choose(0,0)).isEqualTo(List.of(List.of()));
        assertThat(Combinations.choose(4,0)).isEqualTo(List.of(List.of()));
        assertThat(Combinations.choose(3,3)).isEqualTo(List.of(List.of(0,1,2)));
        var result = Combinations.choose(3,2);
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> result.clear());
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> result.get(0).clear());
    }

    @Test void enumeratesOnlyIncreasingRowsInLexicographicOrder() {
        assertThat(Combinations.choose(4,2)).isEqualTo(List.of(List.of(0,1),List.of(0,2),List.of(0,3),List.of(1,2),List.of(1,3),List.of(2,3)));
    }

    @Test void allSizesThroughTwelveMatchBitmaskSubsets() {
        for (int n = 0; n <= 12; n++) {
            for (int k = 0; k <= n; k++) {
                List<List<Integer>> expected = byMasks(n,k);
                assertThat(Combinations.choose(n,k)).isEqualTo(expected);
                assertThat(Combinations.countWithPruning(n,k)).isEqualTo(expected.size());
                assertThat(Combinations.countWithoutPruning(n,k)).isEqualTo(expected.size());
            }
        }
    }

    @Test void boundaryCountsMatchBigIntegerBinomialsAndMaterialiseTheLargestPlan() {
        for (int k = 0; k <= 18; k++) {
            long expected = binomial(18,k);
            assertThat(Combinations.countWithPruning(18,k)).isEqualTo(expected);
            assertThat(Combinations.countWithoutPruning(18,k)).isEqualTo(expected);
        }
        var largest = Combinations.choose(18,9);
        assertThat(largest).hasSize(48620);
        assertThat(largest.get(0)).isEqualTo(List.of(0,1,2,3,4,5,6,7,8));
        assertThat(largest.get(largest.size() - 1)).isEqualTo(List.of(9,10,11,12,13,14,15,16,17));
    }

    @Test void countsSatisfyPascalIdentityAndCallsDoNotShareState() {
        for (int n = 2; n <= 12; n++) {
            for (int k = 1; k < n; k++) {
                assertThat(Combinations.countWithPruning(n,k)).isEqualTo(
                        Combinations.countWithoutPruning(n - 1,k - 1) + Combinations.countWithoutPruning(n - 1,k));
            }
        }
        var before = Combinations.choose(5,3);
        Combinations.choose(0,0);
        assertThat(Combinations.choose(5,3)).isEqualTo(before);
    }

    private static List<List<Integer>> byMasks(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        for (int mask = 0; mask < 1 << n; mask++) {
            if (Integer.bitCount(mask) != k) { continue; }
            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < n; i++) { if ((mask & (1 << i)) != 0) { row.add(i); } }
            result.add(row);
        }
        result.sort((a,b) -> {
            for (int i = 0; i < a.size(); i++) {
                int comparison = Integer.compare(a.get(i),b.get(i)); if (comparison != 0) { return comparison; }
            }
            return 0;
        });
        return result;
    }

    private static long binomial(int n, int k) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= k; i++) { result = result.multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i)); }
        return result.longValueExact();
    }
}
