package org.anaalvarezdev.algorithms.backtracking;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SplittableRandom;
import java.util.TreeSet;
import static org.assertj.core.api.Assertions.*;

class PermutationsTest {
    private static final Comparator<List<Integer>> LEX = (a,b) -> {
        for (int i = 0; i < Math.min(a.size(),b.size()); i++) {
            int order = Integer.compare(a.get(i),b.get(i)); if (order != 0) { return order; }
        }
        return Integer.compare(a.size(),b.size());
    };

    @Test void rejectsNullAndDistinctOutputWorkCaps() {
        assertThatNullPointerException().isThrownBy(() -> Permutations.unique(null));
        assertThatNullPointerException().isThrownBy(() -> Permutations.countUnique(null));
        assertThatNullPointerException().isThrownBy(() -> Permutations.countUsingFrequencies(null));
        assertThatIllegalArgumentException().isThrownBy(() -> Permutations.unique(new int[10]));
        assertThatIllegalArgumentException().isThrownBy(() -> Permutations.countUnique(new int[11]));
        assertThatIllegalArgumentException().isThrownBy(() -> Permutations.countUsingFrequencies(new int[11]));
    }

    @Test void emptyPermutationAndDeepImmutabilityAreExplicit() {
        assertThat(Permutations.unique(new int[0])).isEqualTo(List.of(List.of()));
        assertThat(Permutations.countUnique(new int[0])).isEqualTo(1);
        assertThat(Permutations.countUsingFrequencies(new int[0])).isEqualTo(1);
        var result = Permutations.unique(new int[]{1,2});
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> result.clear());
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> result.get(0).set(0,99));
    }

    @Test void repeatedValuesAreIndistinguishableAndOrderIsLexicographic() {
        assertThat(Permutations.unique(new int[]{2,1,1})).isEqualTo(List.of(List.of(1,1,2),List.of(1,2,1),List.of(2,1,1)));
        check(new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE});
    }

    @Test void everySmallMultisetMatchesAllIndexPermutationsThenDeduplication() {
        for (int size = 0; size <= 7; size++) { multisets(new int[size],0,-1); }
    }

    @Test void seededUnsortedInputsMatchIndependentIndexEnumeration() {
        SplittableRandom random = new SplittableRandom(20261026);
        for (int sample = 0; sample < 100; sample++) {
            check(random.ints(random.nextInt(8),-2,3).toArray());
        }
    }

    @Test void countBoundaryFitsLongAndEnumerationCapAcceptsRepeatedValues() {
        int[] distinct = {0,1,2,3,4,5,6,7,8,9};
        assertThat(Permutations.countUnique(distinct)).isEqualTo(3_628_800);
        assertThat(Permutations.countUsingFrequencies(distinct)).isEqualTo(3_628_800);
        assertThat(Permutations.unique(new int[9])).hasSize(1);
    }

    @Test void branchRestorationAndRepeatedCallsDoNotShareState() {
        int[] values = {3,1,3,2}; int[] before = values.clone();
        var first = Permutations.unique(values);
        Permutations.unique(new int[]{9,9});
        assertThat(Permutations.unique(values)).isEqualTo(first);
        assertThat(Permutations.countUnique(values)).isEqualTo(first.size());
        assertThat(Permutations.countUsingFrequencies(values)).isEqualTo(first.size());
        assertThat(values).containsExactly(before);
    }

    private static void multisets(int[] values, int index, int minimum) {
        if (index == values.length) { check(values); return; }
        for (int value = minimum; value <= 1; value++) {
            values[index] = value; multisets(values,index + 1,value);
        }
    }

    private static void check(int[] values) {
        int[] before = values.clone();
        Set<List<Integer>> expected = new TreeSet<>(LEX);
        allPositions(values.clone(),0,expected);
        var result = Permutations.unique(values);
        assertThat(result).isEqualTo(new ArrayList<>(expected));
        long count = multinomial(values).longValueExact();
        assertThat(result).hasSize(Math.toIntExact(count));
        assertThat(Permutations.countUnique(values)).isEqualTo(count);
        assertThat(Permutations.countUsingFrequencies(values)).isEqualTo(count);
        assertThat(values).containsExactly(before);
    }

    private static void allPositions(int[] values, int from, Set<List<Integer>> output) {
        if (from == values.length) { output.add(Arrays.stream(values).boxed().toList()); return; }
        for (int i = from; i < values.length; i++) {
            int temporary = values[from]; values[from] = values[i]; values[i] = temporary;
            allPositions(values,from + 1,output);
            temporary = values[from]; values[from] = values[i]; values[i] = temporary;
        }
    }

    private static BigInteger multinomial(int[] values) {
        Map<Integer,Integer> frequencies = new HashMap<>();
        for (int value : values) { frequencies.merge(value,1,Integer::sum); }
        BigInteger result = factorial(values.length);
        for (int count : frequencies.values()) { result = result.divide(factorial(count)); }
        return result;
    }
    private static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) { result = result.multiply(BigInteger.valueOf(i)); }
        return result;
    }
}
