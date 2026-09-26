package org.anaalvarezdev.algorithms.arrays;


import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;


/*
 * Result:
 *
 * Input Size (n)   Build Prefix (ns/op)   Direct Query (ns/op)   Prefix Query (ns/op)
 *
 * 100              227.298                1.385                  1.890
 * 1,000            2,253.308              68.508                 1.627
 * 10,000           19,826.184             648.226                1.816
 * 100,000          193,249.154            11,748.563             1.615
 * 1,000,000        3,075,827.204          150,525.519            1.295
 *
 * Interpretation:
 *
 * The experimental results demonstrate the preprocessing
 * trade-off introduced by the prefix-sum technique.
 *
 * BUILD PREFIX:
 *
 * Prefix construction processes every input element exactly
 * once and creates an auxiliary array of size n + 1.
 *
 * Time Complexity:
 * T(n) = Θ(n)
 *
 * Space Complexity:
 * S(n) = Θ(n)
 *
 * The measured execution time increases as the input size grows,
 * which is consistent with linear preprocessing.
 *
 * DIRECT RANGE QUERY:
 *
 * The direct approach traverses every element in the requested
 * range. Since the benchmark queries the entire array, the
 * number of processed elements grows with n.
 *
 * T(n) = Θ(n)
 *
 * The measured execution time therefore increases substantially
 * as the input size grows.
 *
 * PREFIX RANGE QUERY:
 *
 * Once preprocessing has been completed, the range sum is
 * computed using:
 *
 * prefix[right + 1] - prefix[left]
 *
 * This requires only two indexed accesses, one subtraction,
 * and a return operation.
 *
 * T(n) = Θ(1)
 *
 * The measured prefix-query time remains approximately constant
 * across input sizes ranging from 100 to 1,000,000 elements.
 *
 * The experiment therefore illustrates an important algorithmic
 * trade-off:
 *
 * Without preprocessing:
 * q range queries -> Θ(qn)
 *
 * With prefix sums:
 * preprocessing   -> Θ(n)
 * q queries       -> Θ(q)
 *
 * Total:
 * Θ(n + q)
 *
 * Prefix sums trade additional preprocessing time and Θ(n)
 * auxiliary memory for constant-time range queries.
 *
 * The empirical measurements are consistent with the theoretical
 * analysis but do not constitute a mathematical proof of
 * asymptotic complexity.
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Warmup(
        iterations = 5,
        time = 1,
        timeUnit = TimeUnit.SECONDS
)
@Measurement(
        iterations = 10,
        time = 1,
        timeUnit = TimeUnit.SECONDS
)
@Fork(2)
public class PrefixSumBenchmark {
    @Param({
            "100",
            "1000",
            "10000",
            "100000",
            "1000000"
    })
    private int size;

    private int[] array;
    private long[] prefix;

    private int left;
    private int right;

    @Setup(Level.Trial)
    public void setup() {

        array = new int[size];

        for (int index = 0; index < size; index++) {
            array[index] = index + 1;
        }

        prefix = PrefixSum.build(array);

        left = 0;
        right = size - 1;
    }

    @Benchmark
    public void buildPrefix(Blackhole blackhole) {

        long[] result =
                PrefixSum.build(array);

        blackhole.consume(result);
    }

    @Benchmark
    public void directRangeQuery(Blackhole blackhole) {

        long sum = 0;

        for (int index = left; index <= right; index++) {
            sum += array[index];
        }

        blackhole.consume(sum);
    }

    @Benchmark
    public void prefixRangeQuery(Blackhole blackhole) {

        long result =
                PrefixSum.rangeSum(
                        prefix,
                        left,
                        right
                );

        blackhole.consume(result);
    }

}
