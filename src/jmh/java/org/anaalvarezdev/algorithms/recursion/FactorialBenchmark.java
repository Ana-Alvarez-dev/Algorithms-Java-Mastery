package org.anaalvarezdev.algorithms.recursion;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

/*
 * Research Question:
 * How do ordinary recursion, accumulator tail recursion and a loop compare for
 * exact long factorial on the same bounded input domain?
 * Theoretical Expectation:
 * All perform linear word work; recursive forms retain O(n) logical call depth,
 * loop O(1) local state. Java tail position does not promise constant stack use.
 * Measured Work:
 * Each invocation validates the trial-state n and computes/returns n! from
 * scratch. No precomputed result or setup allocation is subtracted. Small
 * bounded workloads and JIT inlining/specialisation restrict generalisation.
 * Result:
 * Pending author measurements; smoke execution checks the harness only.
 * Interpretation:
 * Pending source/runtime/settings and every mean/error. Timing cannot recover
 * actual frame counts, establish tail-call elimination or a universal ranking.
 * Protocol: docs/11-recursion/implementation-and-benchmark-guide.md.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Threads(1)
@Warmup(iterations=3,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Measurement(iterations=5,time=500,timeUnit=TimeUnit.MILLISECONDS)
@Fork(2)
public class FactorialBenchmark {
    @Param({"5","10","20"}) private int n;
    @Benchmark public long recursive() { return Factorial.recursive(n); }
    @Benchmark public long tailRecursive() { return Factorial.tailRecursive(n); }
    @Benchmark public long iterative() { return Factorial.iterative(n); }
}
