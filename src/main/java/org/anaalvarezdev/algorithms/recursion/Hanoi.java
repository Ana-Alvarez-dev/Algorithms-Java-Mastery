package org.anaalvarezdev.algorithms.recursion;

import java.util.ArrayList;
import java.util.List;

/** Materialised three-peg Hanoi plan: source 0, target 2, spare 1; smallest disk is 1. */
public final class Hanoi {
    public record Move(int disk, int from, int to) { }
    private Hanoi() { }
    /** Returns an immutable optimal plan; 0..16 disks bounds exponential output. */
    public static List<Move> moves(int disks) {
        if (disks < 0 || disks > 16) { throw new IllegalArgumentException("Hanoi requires 0 <= disks <= 16"); }
        List<Move> result = new ArrayList<>((1 << disks)-1); transfer(disks,0,2,1,result); return List.copyOf(result);
    }
    private static void transfer(int disks, int from, int to, int spare, List<Move> output) {
        if (disks == 0) { return; }
        transfer(disks-1,from,spare,to,output); output.add(new Move(disks,from,to)); transfer(disks-1,spare,to,from,output);
    }
}
