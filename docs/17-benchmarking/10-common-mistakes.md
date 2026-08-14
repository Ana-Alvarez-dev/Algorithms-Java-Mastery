# Common Mistakes

## Algorithms Java Mastery

Benchmarking is a scientific process whose objective is to measure software performance accurately and objectively. However, obtaining reliable benchmark results is considerably more difficult than simply measuring execution time. Modern Java applications execute inside the **Java Virtual Machine (JVM)**, which continuously performs runtime optimizations such as **JIT compilation**, **Garbage Collection**, and adaptive optimization. These mechanisms can easily invalidate naïve benchmarking techniques.

Many performance studies published by inexperienced developers contain methodological errors that lead to incorrect conclusions. Common mistakes include benchmarking without JVM warm-up, comparing algorithms under different conditions, relying on a single execution, ignoring statistical variation, or drawing conclusions from benchmarks that measure something other than the intended code.

Professional software engineers understand that benchmarking is an experimental discipline. Every measurement must be carefully designed, executed, analyzed, and interpreted before it can support engineering decisions.

This document summarizes the most common mistakes encountered in Java benchmarking and presents the principles required to avoid them.

---

# 1. Purpose

The purpose of this document is to:

- identify common benchmarking mistakes;
- explain why these mistakes invalidate measurements;
- establish good benchmarking practices;
- improve the reliability of performance experiments;
- reinforce the concepts studied throughout this module.

---

# 2. Measuring Without Warm-up

One of the most frequent mistakes is measuring code immediately after starting the JVM.

Incorrect workflow:

```text
Start JVM

↓

Measure Immediately
```

The first executions include interpretation, class loading, and runtime optimization.

Correct workflow:

```text
Warm-up

↓

Steady State

↓

Measure
```

Reliable benchmarks always allow the JVM to complete its warm-up phase.

---

# 3. Ignoring JIT Compilation

The Just-In-Time compiler continuously optimizes frequently executed code.

Incorrect assumption:

```text
First Execution

=

Final Performance
```

The first execution often represents interpreted bytecode rather than optimized native code.

Benchmarks should measure steady-state execution after JIT optimization.

---

# 4. Using System.nanoTime() Incorrectly

Many beginners attempt to benchmark algorithms using:

```text
Start Timer

↓

Execute Code

↓

Stop Timer
```

Although useful for simple timing, this approach ignores:

- JVM warm-up;
- compiler optimizations;
- garbage collection;
- statistical variation.

Professional Java benchmarking relies on **JMH** rather than manual timing loops.

---

# 5. Performing Only One Measurement

A single execution provides little useful information.

Incorrect:

```text
Run Once

↓

Draw Conclusion
```

Correct:

```text
Multiple Executions

↓

Statistical Analysis
```

Repeated measurements reduce random variation and improve reliability.

---

# 6. Comparing Different Workloads

Algorithms must process identical input data.

Incorrect:

```text
Algorithm A

↓

Random Data
```

```text
Algorithm B

↓

Sorted Data
```

The comparison is unfair because the workloads differ.

Professional benchmarks compare implementations under identical conditions.

---

# 7. Ignoring Garbage Collection

Garbage Collection affects execution time.

Incorrect assumption:

```text
Measured Time

↓

Algorithm Only
```

Actual execution time may also include:

- memory allocation;
- garbage collection pauses;
- heap management.

These factors should be considered when interpreting benchmark results.

---

# 8. Ignoring Statistical Variation

Performance naturally varies.

Incorrect assumption:

```text
Every Run

↓

Same Result
```

Correct understanding:

```text
Many Runs

↓

Small Variation
```

Professional benchmarking always considers averages, deviations, and confidence intervals.

---

# 9. Chasing Small Differences

Not every performance difference is meaningful.

Example:

```text
10.01 ms

↓

10.03 ms
```

Such small differences may simply reflect measurement noise.

Optimization efforts should focus on statistically significant improvements.

---

# 10. Confusing Benchmarking with Complexity Analysis

Benchmarking and complexity analysis answer different questions.

Complexity:

```text
How Does It Scale?
```

Benchmarking:

```text
How Fast Does It Execute?
```

A benchmark cannot replace asymptotic analysis, and asymptotic analysis cannot replace benchmarking.

Both techniques are complementary.

---

# 11. Benchmarking Unrealistic Inputs

Artificial datasets often produce misleading conclusions.

Poor example:

```text
Always

10 Elements
```

Better example:

```text
Small

↓

Medium

↓

Large

↓

Production-Like Data
```

Benchmark inputs should represent realistic application scenarios.

---

# 12. Ignoring Memory Allocation

Execution time is only one aspect of performance.

Algorithms that allocate many temporary objects may:

- increase GC activity;
- reduce throughput;
- increase latency.

Memory allocation should be evaluated alongside execution time.

---

# 13. Benchmarking Too Much Code

Microbenchmarks should isolate the behavior under study.

Incorrect:

```text
Algorithm

+

Logging

+

File I/O

+

Network
```

Correct:

```text
Algorithm Only
```

External operations hide the true cost of the algorithm.

---

# 14. Optimizing Without Measurement

Optimization should always be evidence-based.

Incorrect workflow:

```text
Guess

↓

Optimize
```

Correct workflow:

```text
Measure

↓

Identify Bottleneck

↓

Optimize

↓

Measure Again
```

Performance improvements should always be verified experimentally.

---

# 15. Ignoring Benchmark Documentation

Benchmark reports should document:

- hardware;
- operating system;
- JVM version;
- benchmark parameters;
- workload characteristics.

Without documentation, experiments become difficult to reproduce.

---

# 16. Misinterpreting Benchmark Results

Common interpretation mistakes include:

- comparing benchmarks executed under different configurations;
- ignoring error margins;
- assuming averages tell the complete story;
- overlooking outliers;
- confusing throughput with latency.

Accurate interpretation requires statistical reasoning.

---

# 17. Overgeneralizing Results

A benchmark measures performance only under the tested conditions.

Incorrect conclusion:

```text
Algorithm A

↓

Always Faster
```

Correct conclusion:

```text
Algorithm A

↓

Faster

Under

These Conditions
```

Professional engineers avoid broad conclusions that exceed the evidence.

---

# 18. Java Perspective

Reliable Java benchmarking typically combines:

- JMH;
- JVM warm-up;
- multiple iterations;
- multiple forks;
- statistical analysis;
- documented experimental conditions.

This methodology minimizes measurement bias and improves reproducibility.

---

# 19. Engineering Perspective

Professional software engineers routinely ask:

```text
Was the
benchmark
fair?
```

```text
Were JVM
optimizations
considered?
```

```text
Can another
developer
reproduce
these results?
```

```text
Do the
measurements
justify the
conclusion?
```

These questions reflect the scientific mindset required for trustworthy performance evaluation.

---

# 20. Relationship with the Repository

This document concludes the **Benchmarking** module.

At this stage, the learner has completed the full engineering workflow developed throughout the repository.

```text
Problem Understanding
        ↓
Algorithm Design
        ↓
Correctness Reasoning
        ↓
Complexity Analysis
        ↓
Java Implementation
        ↓
Automated Testing
        ↓
Benchmarking
```

Together, these stages provide a complete methodology for developing, validating, analyzing, and evaluating algorithms using modern Java engineering practices.

The knowledge acquired in this module prepares the learner for future studies in **performance optimization**, **profiling**, **concurrent programming**, and **high-performance backend systems**, where benchmark-driven decision making becomes an essential engineering skill.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- benchmarking requires scientific methodology rather than simple execution timing;
- measuring without JVM warm-up produces misleading results;
- JIT compilation and Garbage Collection significantly influence Java performance measurements;
- repeated measurements and statistical analysis are essential for reliable benchmarking;
- algorithms must be compared using identical workloads and execution conditions;
- benchmark results should never be generalized beyond the conditions under which they were obtained;
- optimization should always be guided by empirical evidence rather than intuition;
- professional software engineers use JMH, controlled experiments, and careful result interpretation to make reliable performance decisions.