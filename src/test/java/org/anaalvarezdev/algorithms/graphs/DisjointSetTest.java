package org.anaalvarezdev.algorithms.graphs;

import org.junit.jupiter.api.Test;
import java.util.SplittableRandom;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DisjointSetTest {
    @Test void shouldMergeExactlyOnceAndTrackComponentCount() {
        var sets = new DisjointSet(4); assertThat(sets.count()).isEqualTo(4);
        assertThat(sets.union(0,1)).isTrue(); assertThat(sets.union(1,0)).isFalse();
        sets.union(2,3); sets.union(1,3); assertThat(sets.count()).isEqualTo(1);
        assertThat(sets.find(0)).isEqualTo(sets.find(3));
    }
    @Test void shouldRejectInvalidCountsAndEndpoints() {
        assertThatThrownBy(() -> new DisjointSet(-1)).isInstanceOf(IllegalArgumentException.class);
        var sets = new DisjointSet(0); assertThat(sets.count()).isZero();
        assertThatThrownBy(() -> sets.find(0)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> new DisjointSet(2).union(-1,0)).isInstanceOf(IndexOutOfBoundsException.class);
    }
    @Test void shouldMatchNaiveLabelsAcrossSeededUnions() {
        int n = 20; int[] labels = java.util.stream.IntStream.range(0,n).toArray(); var sets = new DisjointSet(n); var random = new SplittableRandom(20261020);
        for (int step = 0; step < 1000; step++) {
            int a = random.nextInt(n); int b = random.nextInt(n); int old = labels[b]; int replacement = labels[a];
            assertThat(sets.union(a,b)).isEqualTo(old != replacement);
            for (int i = 0; i < n; i++) { if (labels[i] == old) { labels[i] = replacement; } }
            assertThat(sets.count()).isEqualTo((int)ArraysSupport.distinct(labels));
            for (int i = 0; i < n; i++) { assertThat(sets.find(i) == sets.find(a)).isEqualTo(labels[i] == labels[a]); }
        }
    }
    private static final class ArraysSupport { static long distinct(int[] values) { return java.util.Arrays.stream(values).distinct().count(); } }
}
