package org.anaalvarezdev.algorithms.greedy;

/** Nonempty half-open interval [start,finish); negative times are accepted. */
public record Interval(int start, int finish) {
    public Interval {
        if (start >= finish) {
            throw new IllegalArgumentException("interval requires start < finish");
        }
    }
}
