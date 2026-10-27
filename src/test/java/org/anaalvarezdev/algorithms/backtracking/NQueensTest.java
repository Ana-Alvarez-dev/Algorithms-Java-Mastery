package org.anaalvarezdev.algorithms.backtracking;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class NQueensTest {
    @Test void validatesBeforeBitShiftsOrAllocation() {
        for (int n : new int[]{-1,13,64,Integer.MAX_VALUE}) {
            assertThatIllegalArgumentException().isThrownBy(() -> NQueens.solutions(n));
            assertThatIllegalArgumentException().isThrownBy(() -> NQueens.countWithFlags(n));
            assertThatIllegalArgumentException().isThrownBy(() -> NQueens.countWithBitMasks(n));
        }
    }

    @Test void emptyBoardSmallFailuresAndFourQueenOrderAreExplicit() {
        assertThat(NQueens.solutions(0)).isEqualTo(List.of(List.of()));
        assertThat(NQueens.solutions(1)).isEqualTo(List.of(List.of(0)));
        assertThat(NQueens.solutions(2)).isEmpty();
        assertThat(NQueens.solutions(3)).isEmpty();
        assertThat(NQueens.solutions(4)).isEqualTo(List.of(List.of(1,3,0,2),List.of(2,0,3,1)));
    }

    @Test void resultsAndRowsAreImmutableSnapshots() {
        var result = NQueens.solutions(4);
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> result.clear());
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> result.get(0).set(0,0));
        assertThat(result.get(0)).isNotSameAs(result.get(1));
        assertThat(result.get(0)).isEqualTo(List.of(1,3,0,2));
    }

    @Test void allBoardsThroughNineMatchIndependentCompleteColumnPermutations() {
        for (int n = 0; n <= 9; n++) {
            int[] columns = new int[n]; for (int i = 0; i < n; i++) { columns[i] = i; }
            List<List<Integer>> expected = new ArrayList<>();
            permutations(columns,0,expected);
            expected.sort(NQueensTest::lex);
            assertThat(NQueens.solutions(n)).isEqualTo(expected);
            assertThat(NQueens.countWithFlags(n)).isEqualTo(expected.size());
            assertThat(NQueens.countWithBitMasks(n)).isEqualTo(expected.size());
        }
    }

    @Test void everySupportedCountMatchesPublishedOrdinaryBoardCounts() {
        // OEIS A000170, offset 0; rotations/reflections are counted separately.
        long[] expected = {1,1,0,0,2,10,4,40,92,352,724,2680,14200};
        for (int n = 0; n < expected.length; n++) {
            assertThat(NQueens.countWithFlags(n)).isEqualTo(expected[n]);
            assertThat(NQueens.countWithBitMasks(n)).isEqualTo(expected[n]);
        }
        assertThat(NQueens.solutions(12)).hasSize(14200);
    }

    @Test void reflectionIsPresentRatherThanCollapsedBySymmetryReduction() {
        for (int n = 4; n <= 8; n++) {
            var solutions = NQueens.solutions(n);
            for (var row : solutions) {
                List<Integer> reflected = new ArrayList<>();
                for (int column : row) { reflected.add(n - 1 - column); }
                assertThat(solutions).contains(reflected);
            }
        }
    }

    @Test void branchRestorationAndDifferentBoardCallsAreRepeatable() {
        var before = NQueens.solutions(8);
        NQueens.countWithFlags(10);
        NQueens.solutions(3);
        assertThat(NQueens.solutions(8)).isEqualTo(before);
        assertThat(NQueens.countWithFlags(8)).isEqualTo(92);
        assertThat(NQueens.countWithBitMasks(8)).isEqualTo(92);
    }

    // Enumerate every complete column permutation before checking diagonals;
    // no partial diagonal pruning or shared flag/mask state from production.
    private static void permutations(int[] columns, int row, List<List<Integer>> output) {
        if (row == columns.length) {
            for (int a = 0; a < columns.length; a++) {
                for (int b = a + 1; b < columns.length; b++) {
                    if (Math.abs(columns[a] - columns[b]) == b - a) { return; }
                }
            }
            output.add(Arrays.stream(columns).boxed().toList());
            return;
        }
        for (int i = row; i < columns.length; i++) {
            int temporary = columns[row]; columns[row] = columns[i]; columns[i] = temporary;
            permutations(columns,row + 1,output);
            temporary = columns[row]; columns[row] = columns[i]; columns[i] = temporary;
        }
    }

    private static int lex(List<Integer> a, List<Integer> b) {
        for (int i = 0; i < a.size(); i++) {
            int comparison = Integer.compare(a.get(i),b.get(i)); if (comparison != 0) { return comparison; }
        }
        return 0;
    }
}
