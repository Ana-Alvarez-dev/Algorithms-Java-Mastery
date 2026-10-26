package org.anaalvarezdev.algorithms.greedy;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SplittableRandom;
import static org.assertj.core.api.Assertions.*;

class HuffmanCodingTest {
    @Test void rejectsNullSymbolsValuesArraysAndNonpositiveFrequencies() {
        assertThatNullPointerException().isThrownBy(() -> HuffmanCoding.build(null));
        assertThatNullPointerException().isThrownBy(() -> HuffmanCoding.costWithHeap(null));
        assertThatNullPointerException().isThrownBy(() -> HuffmanCoding.costWithTwoQueues(null));
        Map<Integer,Integer> bad = new HashMap<>(); bad.put(null,1);
        assertThatNullPointerException().isThrownBy(() -> HuffmanCoding.build(bad));
        bad.clear(); bad.put(1,null);
        assertThatNullPointerException().isThrownBy(() -> HuffmanCoding.build(bad));
        for (int invalid : new int[]{0,-1,Integer.MIN_VALUE}) {
            assertThatIllegalArgumentException().isThrownBy(() -> HuffmanCoding.build(Map.of(1,invalid)));
            assertThatIllegalArgumentException().isThrownBy(() -> HuffmanCoding.costWithHeap(new int[]{1,invalid}));
            assertThatIllegalArgumentException().isThrownBy(() -> HuffmanCoding.costWithTwoQueues(new int[]{invalid}));
        }
    }

    @Test void emptyAlphabetHasEmptyCodebookAndZeroCost() {
        check(new int[0],BigInteger.ZERO);
        assertThat(HuffmanCoding.build(Map.of()).codes()).isEmpty();
    }

    @Test void singletonUsesOneNonemptyBitPerOccurrence() {
        var result = HuffmanCoding.build(Map.of(Integer.MIN_VALUE,7));
        assertThat(result.codes()).isEqualTo(Map.of(Integer.MIN_VALUE,"0"));
        assertThat(result.weightedBitLength()).isEqualTo(BigInteger.valueOf(7));
        check(new int[]{7},BigInteger.valueOf(7));
    }

    @Test void classicalSixFrequenciesGiveTheDeterministicOptimalCode() {
        int[] frequencies = {45,13,12,16,9,5};
        check(frequencies,BigInteger.valueOf(224));
        assertThat(HuffmanCoding.build(asMap(frequencies)).codes()).isEqualTo(
                Map.of(0,"0",1,"101",2,"100",3,"111",4,"1101",5,"1100"));
    }

    @Test void allSmallPositiveWeightsMatchAllBinaryRootPartitions() {
        for (int length = 0; length <= 6; length++) { enumerate(new int[length],0); }
    }

    @Test void seededLargeSkewedAndExtremeWeightsPreserveInputAndExactCosts() {
        SplittableRandom random = new SplittableRandom(20261026);
        for (int sample = 0; sample < 100; sample++) {
            int[] values = random.ints(random.nextInt(2,129),1,Integer.MAX_VALUE).toArray();
            check(values,HuffmanCoding.build(asMap(values)).weightedBitLength());
        }
        check(new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
                BigInteger.valueOf(Integer.MAX_VALUE).multiply(BigInteger.valueOf(8)));
        int[] equal = new int[1000]; Arrays.fill(equal,1);
        check(equal,BigInteger.valueOf(9976));
        int[] fibonacci = new int[46]; long previous = 0, current = 1;
        for (int i = 0; i < fibonacci.length; i++) {
            fibonacci[i] = Math.toIntExact(current);
            long next = previous + current; previous = current; current = next;
        }
        check(fibonacci,HuffmanCoding.build(asMap(fibonacci)).weightedBitLength());
    }

    @Test void symbolTiesAreIndependentOfMapOrderAndCodebooksAreImmutable() {
        Map<Integer,Integer> first = new LinkedHashMap<>();
        first.put(Integer.MAX_VALUE,1); first.put(-1,1); first.put(Integer.MIN_VALUE,1);
        Map<Integer,Integer> second = new LinkedHashMap<>();
        List<Integer> keys = new ArrayList<>(first.keySet()); Collections.reverse(keys);
        for (int key : keys) { second.put(key,first.get(key)); }
        var result = HuffmanCoding.build(first);
        assertThat(HuffmanCoding.build(second)).isEqualTo(result);
        first.clear();
        assertThat(result.codes()).hasSize(3);
        assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() -> result.codes().clear());
        Map<Integer,String> mutableCodes = new HashMap<>(result.codes());
        var copy = new HuffmanCoding.Codebook(mutableCodes,result.weightedBitLength());
        mutableCodes.clear();
        assertThat(copy).isEqualTo(result);
    }

    private static void enumerate(int[] weights, int index) {
        if (index == weights.length) {
            check(weights,BigInteger.valueOf(optimum(weights)));
            return;
        }
        for (int value = 1; value <= 3; value++) { weights[index] = value; enumerate(weights,index + 1); }
    }

    private static void check(int[] weights, BigInteger expected) {
        int[] before = weights.clone();
        Map<Integer,Integer> frequencies = asMap(weights);
        var result = HuffmanCoding.build(frequencies);
        assertThat(result.weightedBitLength()).isEqualTo(expected);
        assertThat(HuffmanCoding.costWithHeap(weights)).isEqualTo(expected);
        assertThat(HuffmanCoding.costWithTwoQueues(weights)).isEqualTo(expected);
        assertThat(weights).containsExactly(before);
        assertThat(frequencies).isEqualTo(asMap(before));
        assertThat(result.codes()).hasSize(weights.length);
        BigInteger recovered = BigInteger.ZERO;
        for (var entry : result.codes().entrySet()) {
            String code = entry.getValue();
            assertThat(code).matches("[01]+");
            recovered = recovered.add(BigInteger.valueOf(frequencies.get(entry.getKey())).multiply(BigInteger.valueOf(code.length())));
            for (var other : result.codes().entrySet()) {
                if (!entry.getKey().equals(other.getKey())) { assertThat(other.getValue().startsWith(code)).isFalse(); }
            }
        }
        assertThat(recovered).isEqualTo(expected);
    }

    private static Map<Integer,Integer> asMap(int[] frequencies) {
        Map<Integer,Integer> result = new LinkedHashMap<>();
        for (int i = 0; i < frequencies.length; i++) { result.put(i,frequencies[i]); }
        return result;
    }

    // Independent exhaustive full-binary-tree oracle: enumerate all root splits,
    // rather than assuming the two-smallest merge rule being tested.
    private static long optimum(int[] weights) {
        if (weights.length == 0) { return 0; }
        if (weights.length == 1) { return weights[0]; }
        long[] costs = new long[1 << weights.length];
        long[] sums = new long[costs.length];
        for (int mask = 1; mask < costs.length; mask++) {
            for (int i = 0; i < weights.length; i++) {
                if ((mask & (1 << i)) != 0) { sums[mask] += weights[i]; }
            }
            if (Integer.bitCount(mask) < 2) { continue; }
            long best = Long.MAX_VALUE;
            int first = Integer.lowestOneBit(mask);
            for (int subset = (mask - 1) & mask; subset > 0; subset = (subset - 1) & mask) {
                if ((subset & first) != 0) { best = Math.min(best,costs[subset] + costs[mask ^ subset]); }
            }
            costs[mask] = best + sums[mask];
        }
        return costs[costs.length - 1];
    }
}
