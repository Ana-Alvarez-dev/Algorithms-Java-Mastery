package org.anaalvarezdev.algorithms.dynamicprogramming;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;
import static org.assertj.core.api.Assertions.*;

class LongestCommonSubsequenceTest {
    @Test void validatesBothStringsAndSharedCapsBeforeAllocation() {
        for (int v = 0; v < 4; v++) {
            int variant = v;
            assertThatNullPointerException().isThrownBy(() -> run(variant, null, ""));
            assertThatNullPointerException().isThrownBy(() -> run(variant, "", null));
            assertThatIllegalArgumentException().isThrownBy(() -> run(variant, "A".repeat(513), ""));
            assertThatIllegalArgumentException().isThrownBy(() -> run(variant, "", "A".repeat(513)));
        }
    }

    @Test void emptyEqualDisjointAndNoncontiguousInputsHaveCorrectLengths() {
        check("", "");
        check("", "ABC");
        check("ABC", "ABC");
        check("AAA", "BBB");
        check("ABC", "AC");
        assertThat(LongestCommonSubsequence.sequence("ABC", "AC")).isEqualTo("AC");
    }

    @Test void reconstructionUsesDocumentedUpwardTieWithoutClaimingUniqueOptimum() {
        assertThat(LongestCommonSubsequence.sequence("ABCBDAB", "BDCABA")).isEqualTo("BCBA");
        assertThat(LongestCommonSubsequence.sequence("AB", "BA")).isEqualTo("A");
        assertThat(LongestCommonSubsequence.sequence("BA", "AB")).isEqualTo("B");
    }

    @Test void allBinaryStringsThroughFiveMatchIndependentSubsequenceEnumeration() {
        List<String> strings = new ArrayList<>();
        for (int n = 0; n <= 5; n++) {
            for (int mask = 0; mask < (1 << n); mask++) {
                StringBuilder value = new StringBuilder();
                for (int i = 0; i < n; i++) { value.append((mask & (1 << i)) == 0 ? 'A' : 'B'); }
                strings.add(value.toString());
            }
        }
        for (String first : strings) { for (String second : strings) { check(first, second); } }
    }

    @Test void seededUnequalLengthsAndRepeatedUnitsMatchSubsequenceOracle() {
        SplittableRandom random = new SplittableRandom(20261026);
        for (int sample = 0; sample < 100; sample++) {
            check(randomString(random), randomString(random));
        }
    }

    @Test void utf16UnitsIncludeSurrogatesAndDoNotApplyNormalisation() {
        String smile = "\uD83D\uDE00";
        check(smile, smile);
        assertThat(LongestCommonSubsequence.sequence(smile, smile)).isEqualTo(smile);
        check(smile, "\uD83D\uDE01");
        assertThat(LongestCommonSubsequence.sequence(smile, "\uD83D\uDE01")).isEqualTo("\uD83D");
        check("é", "e\u0301");
        assertThat(LongestCommonSubsequence.lengthRolling("é", "e\u0301")).isZero();
    }

    @Test void maximumAcceptedLengthsCoverDenseFailuresAndDiagonalSuccess() {
        String first = "A".repeat(512);
        String second = "B".repeat(512);
        for (int v = 0; v < 4; v++) {
            assertThat(run(v, first, second)).isZero();
            assertThat(run(v, first, first)).isEqualTo(512);
            assertThat(run(v, first, "")).isZero();
        }
    }

    @Test void symmetryCommonSuffixAndRepeatedCallsRespectLengthIdentity() {
        for (String first : List.of("", "AB", "ABBA", "CAB")) {
            for (String second : List.of("", "AC", "BAAB", "BC")) {
                int before = LongestCommonSubsequence.lengthMemoised(first, second);
                assertThat(LongestCommonSubsequence.lengthRolling(second, first)).isEqualTo(before);
                assertThat(LongestCommonSubsequence.lengthTabulated(first + "Z", second + "Z")).isEqualTo(before + 1);
                String sequence = LongestCommonSubsequence.sequence(first, second);
                LongestCommonSubsequence.sequence("DIFFERENT", "INPUT");
                assertThat(LongestCommonSubsequence.sequence(first, second)).isEqualTo(sequence);
            }
        }
    }

    private static String randomString(SplittableRandom random) {
        StringBuilder result = new StringBuilder();
        int length = random.nextInt(13);
        for (int i = 0; i < length; i++) { result.append("ABCabc".charAt(random.nextInt(6))); }
        return result.toString();
    }

    private static void check(String first, String second) {
        String shorter = first.length() <= second.length() ? first : second;
        String other = first.length() <= second.length() ? second : first;
        int best = 0;
        for (int mask = 0; mask < (1 << shorter.length()); mask++) {
            StringBuilder candidate = new StringBuilder();
            for (int i = 0; i < shorter.length(); i++) { if ((mask & (1 << i)) != 0) { candidate.append(shorter.charAt(i)); } }
            if (isSubsequence(candidate.toString(), other)) { best = Math.max(best, candidate.length()); }
        }
        for (int variant = 0; variant < 4; variant++) { assertThat(run(variant, first, second)).isEqualTo(best); }
        String sequence = LongestCommonSubsequence.sequence(first, second);
        assertThat(isSubsequence(sequence, first)).isTrue();
        assertThat(isSubsequence(sequence, second)).isTrue();
    }

    private static boolean isSubsequence(String candidate, String input) {
        int next = 0;
        for (int i = 0; i < input.length() && next < candidate.length(); i++) {
            if (input.charAt(i) == candidate.charAt(next)) { next++; }
        }
        return next == candidate.length();
    }

    private static int run(int variant, String first, String second) {
        return switch (variant) {
            case 0 -> LongestCommonSubsequence.lengthMemoised(first, second);
            case 1 -> LongestCommonSubsequence.lengthTabulated(first, second);
            case 2 -> LongestCommonSubsequence.lengthRolling(first, second);
            default -> LongestCommonSubsequence.sequence(first, second).length();
        };
    }
}
