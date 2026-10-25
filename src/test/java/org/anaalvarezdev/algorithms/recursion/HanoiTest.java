package org.anaalvarezdev.algorithms.recursion;

import org.junit.jupiter.api.Test;
import java.util.ArrayDeque;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HanoiTest {
    @Test void shouldReturnDefinedEmptyAndSmallPlans() {
        assertThat(Hanoi.moves(0)).isEmpty(); assertThat(Hanoi.moves(1)).containsExactly(new Hanoi.Move(1,0,2));
        assertThat(Hanoi.moves(2)).containsExactly(new Hanoi.Move(1,0,1),new Hanoi.Move(2,0,2),new Hanoi.Move(1,1,2));
    }
    @Test void shouldProduceLegalCompleteOptimalLengthPlansThroughTwelveDisks() {
        for (int disks = 0; disks <= 12; disks++) { simulate(disks,Hanoi.moves(disks)); }
    }
    @Test void shouldSupportTheSelectedOutputBoundary() { simulate(16,Hanoi.moves(16)); }
    @Test void shouldRejectInputsOutsideTheMaterialisedOutputDomain() {
        for (int disks : new int[]{Integer.MIN_VALUE,-1,17,Integer.MAX_VALUE}) {
            assertThatThrownBy(() -> Hanoi.moves(disks)).isInstanceOf(IllegalArgumentException.class);
        }
    }
    @Test void shouldReturnIndependentImmutablePlans() {
        var plan = Hanoi.moves(3); assertThatThrownBy(plan::clear).isInstanceOf(UnsupportedOperationException.class);
        assertThat(Hanoi.moves(1)).hasSize(1); assertThat(plan).hasSize(7);
    }
    private static void simulate(int disks, List<Hanoi.Move> moves) {
        List<ArrayDeque<Integer>> pegs = List.of(new ArrayDeque<>(),new ArrayDeque<>(),new ArrayDeque<>());
        for (int disk = disks; disk >= 1; disk--) { pegs.get(0).push(disk); }
        assertThat(moves).hasSize((1 << disks)-1);
        for (var move : moves) {
            assertThat(move.from()).isBetween(0,2); assertThat(move.to()).isBetween(0,2); assertThat(move.from()).isNotEqualTo(move.to());
            assertThat(pegs.get(move.from()).pop()).isEqualTo(move.disk());
            if (!pegs.get(move.to()).isEmpty()) { assertThat(pegs.get(move.to()).peek()).isGreaterThan(move.disk()); }
            pegs.get(move.to()).push(move.disk());
        }
        assertThat(pegs.get(0)).isEmpty(); assertThat(pegs.get(1)).isEmpty(); assertThat(pegs.get(2)).hasSize(disks);
        for (int disk = 1; disk <= disks; disk++) { assertThat(pegs.get(2).pop()).isEqualTo(disk); }
    }
}
