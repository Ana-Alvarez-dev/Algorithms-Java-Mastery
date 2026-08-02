# Benchmark Parameters

## Algorithms Java Mastery

A benchmark is only as reliable as the methodology used to execute it. Even a well-designed benchmark can produce misleading results if it is configured with inappropriate execution parameters. For this reason, **JMH (Java Microbenchmark Harness)** provides a rich set of configurable parameters that allow developers to control how benchmarks are executed and measured.

These parameters determine fundamental aspects of the benchmarking process, including **how many warm-up iterations are performed, how long measurements last, how many independent JVM processes are created, how many threads execute the benchmark, and which performance metric is reported**.

Proper parameter selection improves measurement stability, minimizes JVM-related bias, and increases the statistical reliability of benchmark results. Conversely, poor configuration may cause measurements to reflect JVM startup costs, insufficient optimization, or environmental noise rather than the actual performance of the code under evaluation.

Understanding benchmark parameters is therefore essential for producing meaningful and reproducible performance experiments in Java.

---

# 1. Purpose

The purpose of this document is to:

- introduce the most important JMH benchmark parameters;
- explain how benchmark configuration influences measurements;
- understand warm-up and measurement iterations;
- study forks, threads, and execution modes;
- establish best practices for reliable Java benchmarking.

---

# 2. Why Benchmark Parameters Matter

Every benchmark execution involves decisions that affect measurement quality.

Conceptually:

```text
Benchmark

↓

Configuration

↓

Execution

↓

Results
```

Changing benchmark parameters may significantly influence the reported performance.

---

# 3. Warm-up Iterations

Warm-up iterations prepare the JVM before measurements begin.

Conceptually:

```text
Warm-up

↓

JIT Optimization

↓

Ignore Results
```

Warm-up iterations are **not** included in the final benchmark report.

Their objective is to allow the JVM to reach steady-state performance.

---

# 4. Measurement Iterations

After warm-up, JMH performs measurement iterations.

Conceptually:

```text
Optimized JVM

↓

Execute Benchmark

↓

Record Results
```

Only these executions contribute to the reported benchmark statistics.

---

# 5. Iteration Duration

Each iteration executes for a configurable period.

Conceptually:

```text
Iteration

↓

Fixed Duration

↓

Measurement
```

Longer iterations generally reduce measurement noise by collecting more execution samples.

---

# 6. Number of Iterations

Benchmark reliability improves through repeated measurements.

Conceptually:

```text
Iteration 1

↓

Iteration 2

↓

Iteration 3

↓

...
```

More iterations provide stronger statistical confidence.

---

# 7. Forks

A **fork** starts a completely new JVM process.

Conceptually:

```text
Fork 1

↓

Fresh JVM
```

```text
Fork 2

↓

Fresh JVM
```

Each fork begins with a clean execution environment.

This minimizes the influence of previous benchmark executions.

---

# 8. Why Forks Are Important

Without forks:

```text
Benchmark A

↓

Benchmark B
```

The second benchmark may inherit JVM optimizations from the first.

With forks:

```text
Benchmark A

↓

Fresh JVM

↓

Benchmark B
```

Each benchmark executes independently.

---

# 9. Threads

JMH allows benchmarks to execute using different numbers of threads.

Conceptually:

```text
1 Thread

↓

2 Threads

↓

4 Threads

↓

8 Threads
```

This enables the evaluation of concurrent algorithms and scalability.

---

# 10. Benchmark Modes

JMH supports several execution modes.

Common modes include:

### Throughput

```text
Operations

↓

Per Second
```

Measures how much work is completed over time.

---

### Average Time

```text
Execution Time

↓

Average
```

Reports the average execution time for an operation.

---

### Sample Time

Measures randomly sampled execution times.

Useful for latency analysis.

---

### Single Shot Time

Measures a single execution.

Typically used for expensive operations that cannot be repeated many times.

---

### All Modes

Executes every supported measurement mode.

---

# 11. Time Units

Benchmark results may be reported using different units.

Examples include:

- nanoseconds;
- microseconds;
- milliseconds;
- seconds.

Selecting appropriate units improves readability without changing the underlying measurements.

---

# 12. Benchmark State

Many benchmarks require shared data.

JMH provides configurable benchmark states.

Conceptually:

```text
Shared Data

↓

Benchmark

↓

Execution
```

Proper state management ensures fair and consistent measurements.

---

# 13. Benchmark Scope

Benchmark state may exist at different scopes.

Examples include:

- per benchmark;
- per thread;
- per execution.

Selecting the correct scope prevents unintended sharing and synchronization effects.

---

# 14. Parameterized Benchmarks

JMH supports configurable benchmark parameters.

Conceptually:

```text
Input Size

↓

100

↓

1000

↓

10000
```

The same benchmark can automatically execute using multiple parameter values.

This simplifies scalability analysis.

---

# 15. Synchronization Effects

Concurrent benchmarks may introduce synchronization overhead.

Examples include:

- locks;
- atomic operations;
- shared resources.

Benchmark parameters should accurately reflect the intended concurrency model.

---

# 16. Relationship with Statistical Reliability

Benchmark parameters directly influence result quality.

Generally:

```text
More Iterations

↓

More Samples

↓

Higher Confidence
```

Proper configuration reduces random variation and improves reproducibility.

---

# 17. Choosing Reasonable Parameters

There is no universal configuration suitable for every benchmark.

Professional engineers choose parameters according to:

- benchmark duration;
- workload size;
- JVM behavior;
- desired statistical confidence;
- available hardware resources.

Configuration should match the benchmarking objective.

---

# 18. Java Perspective

Typical JMH benchmarks configure:

- warm-up iterations;
- measurement iterations;
- forks;
- threads;
- benchmark mode;
- time unit;
- benchmark parameters.

These settings define how the JVM executes and measures the benchmark.

---

# 19. Engineering Perspective

Professional software engineers rarely ask:

```text
How many
milliseconds
did it take?
```

Instead, they ask:

```text
Was the JVM
fully warmed up?
```

```text
Were enough
iterations
performed?
```

```text
Were multiple
forks used?
```

```text
Are the
measurements
statistically
reliable?
```

Understanding benchmark parameters allows engineers to distinguish between meaningful performance data and accidental measurement artifacts.

---

# 20. Relationship with the Next Document

This document introduced **Benchmark Parameters**, explaining how JMH configuration influences benchmark execution and result quality.

The next document studies **Result Interpretation**, demonstrating how to analyze benchmark reports, understand statistical metrics, compare implementations fairly, and draw reliable engineering conclusions from empirical performance measurements.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- benchmark parameters control how JMH executes and measures Java benchmarks;
- warm-up iterations prepare the JVM and are excluded from final measurements;
- measurement iterations provide the data used to generate benchmark statistics;
- forks create independent JVM processes that improve experimental reliability;
- threads enable performance evaluation under concurrent workloads;
- benchmark modes measure different aspects of performance, including throughput, average time, and latency;
- parameterized benchmarks simplify performance evaluation across multiple input sizes;
- careful parameter selection is essential for producing reproducible, statistically meaningful, and professionally valid benchmark results.