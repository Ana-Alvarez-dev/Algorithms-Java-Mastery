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
 * First author run; source fe370a1953c089a55f8851399acc42a59e3d37b7 supplied
 * separately. JMH 1.37/JDK 21.0.9, one thread, 3/5 x 500 ms warmup/measurement,
 * two forks (Cnt=10), complete combined run 3 min 35 sec. Full means/errors,
 * 240 measured and 144 warmup averages: docs/17-benchmarking/results/recursion/README.md.
 * All nine Factorial cells retained. At n=20: loop 7.134 +/- 1.620, ordinary
 * recursion 24.741 +/- 4.252, tail recursion 25.186 +/- 5.213 ns/op.
 * Interpretation:
 * Loop means are lower; ordinary/tail intervals overlap at every selected n.
 * Loop errors at n=5/10 reach 43.8%/26.7%. Recursive helpers do n products,
 * including multiplication by one; loop does max(n-1,0). This does not isolate
 * call overhead. Similar tail/ordinary means do not establish tail-call
 * elimination, equivalence or constant stack use; bounded inputs/JIT limit generalisation.
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
