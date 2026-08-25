# JMH Fundamentals

## Algorithms Java Mastery

Measuring the performance of Java applications is considerably more complex than recording execution time with methods such as `System.nanoTime()`. The **Java Virtual Machine (JVM)** continuously performs runtime optimizations—including **JIT compilation**, **JVM warm-up**, and **Garbage Collection**—that significantly influence execution speed.

As a result, naïve benchmarking techniques frequently produce inaccurate or misleading measurements. A benchmark that appears correct may actually measure startup overhead, compiler optimizations, or memory management instead of the algorithm itself.

To solve this problem, the OpenJDK project developed the **Java Microbenchmark Harness (JMH)**.

JMH is the official framework for building reliable Java microbenchmarks. It automates many complex aspects of performance measurement, including warm-up iterations, multiple executions, process isolation, statistical analysis, and protection against JVM optimizations that could invalidate benchmark results.

Today, JMH is considered the industry standard for benchmarking Java code and is widely used by JVM engineers, library developers, framework maintainers, and enterprise software teams.

---

# 1. Purpose

The purpose of this document is to:

- introduce JMH;
- explain why JMH is necessary;
- understand the role of microbenchmarking;
- study the execution model of JMH;
- prepare for designing reliable Java benchmarks.

---

# 2. What Is JMH?

**Java Microbenchmark Harness (JMH)** is the official benchmarking framework developed by the **OpenJDK** project.

Its purpose is to measure the performance of small Java code fragments accurately and reproducibly.

Conceptually:

```text
Java Code
        ↓
JMH
        ↓
Controlled Execution
        ↓
Reliable Measurements
```

---

# 3. Why JMH Exists

Simple timing techniques are often incorrect.

Example:

```text
Start Timer

↓

Execute Code

↓

Stop Timer
```

This approach ignores:

- JVM Warm-up;
- JIT Compilation;
- Garbage Collection;
- compiler optimizations.

JMH was designed specifically to handle these challenges.

---

# 4. Microbenchmarking

JMH focuses on **microbenchmarks**.

A microbenchmark measures:

```text
Single Method

↓

Small Algorithm

↓

Individual Operation
```

It is intended for precise evaluation of small units of code.

---

# 5. Benchmark Lifecycle

A typical JMH execution follows this sequence.

```text
Initialize Benchmark
        ↓
Warm-up Iterations
        ↓
JIT Optimization
        ↓
Measurement Iterations
        ↓
Statistical Analysis
        ↓
Benchmark Report
```

Each phase contributes to reliable measurements.

---

# 6. Warm-up Phase

Before recording results, JMH executes the benchmark multiple times.

Conceptually:

```text
Warm-up

↓

Ignore Results

↓

Optimize JVM
```

These executions prepare the JVM for steady-state performance.

---

# 7. Measurement Phase

Only after warm-up does JMH begin collecting measurements.

Conceptually:

```text
Optimized Execution

↓

Measure

↓

Record Results
```

The reported values represent optimized application behavior.

---

# 8. Multiple Iterations

A benchmark is executed repeatedly.

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

Repeated execution reduces the influence of random variation.

---

# 9. Forks

JMH can execute the benchmark in multiple independent JVM processes.

Conceptually:

```text
JVM Process 1

JVM Process 2

JVM Process 3
```

Each process begins with a fresh JVM.

Forking reduces bias caused by previous executions.

---

# 10. Benchmark Modes

JMH supports multiple measurement modes.

Common modes include:

- Throughput;
- Average Time;
- Sample Time;
- Single Shot Time;
- All Modes.

Each mode emphasizes a different performance characteristic.

---

# 11. Benchmark State

Benchmarks frequently require shared data.

Conceptually:

```text
Benchmark

↓

Shared State

↓

Execution
```

JMH provides controlled mechanisms for managing benchmark state safely.

---

# 12. Blackhole

Modern JIT compilers eliminate unused computations.

Example:

```text
Compute Result

↓

Result Ignored

↓

Optimization Removes Code
```

JMH provides a **Blackhole** object.

Conceptually:

```text
Compute Result

↓

Blackhole

↓

Prevent Optimization
```

This ensures the benchmark measures real work rather than optimized-away code.

---

# 13. Dead Code Elimination

Without Blackhole:

```text
Unused Result

↓

Dead Code Elimination
```

The benchmark may measure almost nothing.

Blackhole prevents this optimization.

---

# 14. Repeatability

Reliable benchmarks should produce similar results under identical conditions.

Conceptually:

```text
Run

↓

Run Again

↓

Comparable Results
```

JMH automates this process.

---

# 15. Reproducibility

Another engineer should be able to execute the benchmark and obtain comparable measurements.

Conceptually:

```text
Benchmark

↓

Different Computer

↓

Comparable Behaviour
```

JMH encourages reproducible experimental methodology.

---

# 16. Typical Workflow

Professional benchmarking generally follows this process.

```text
Write Benchmark
        ↓
Warm-up
        ↓
Measure
        ↓
Analyze Results
        ↓
Optimize
        ↓
Benchmark Again
```

Performance improvements should always be verified experimentally.

---

# 17. Relationship with Previous Topics

JMH incorporates the concepts studied previously.

```text
JVM Warm-up
        ↓
JIT Compilation
        ↓
Garbage Collection
        ↓
Reliable Measurement
```

Rather than ignoring JVM behavior, JMH is specifically designed to work with it.

---

# 18. Java Perspective

Modern Java projects commonly integrate JMH with:

- Maven;
- Gradle;
- IntelliJ IDEA;
- OpenJDK.

JMH benchmarks are typically maintained separately from production code and executed independently of unit tests.

This separation ensures that performance evaluation does not interfere with functional testing.

---

# 19. Engineering Perspective

Professional software engineers rarely trust manual timing code.

Instead, they ask:

```text
Was the JVM
properly
warmed up?
```

```text
Could compiler
optimizations
invalidate the
measurement?
```

```text
Are the
results
statistically
reliable?
```

JMH provides a framework that answers these questions through scientifically designed benchmarking procedures.

---

# 20. Relationship with the Next Document

This document introduced **JMH Fundamentals**, explaining why Java requires specialized benchmarking tools and how JMH produces reliable performance measurements.

The next document studies **Benchmark Design**, focusing on how benchmarking experiments should be structured, how workloads should be selected, and how to avoid experimental bias when comparing algorithm implementations.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- JMH (Java Microbenchmark Harness) is the official OpenJDK framework for benchmarking Java code;
- JMH addresses JVM-specific challenges such as warm-up, JIT compilation, and Garbage Collection;
- microbenchmarks measure small units of code under controlled conditions;
- warm-up iterations are excluded from measurements to obtain steady-state performance;
- repeated iterations and multiple JVM forks improve measurement reliability;
- Blackhole prevents dead code elimination and ensures meaningful benchmark execution;
- JMH produces reproducible, statistically meaningful benchmark results suitable for professional performance evaluation;
- modern Java developers use JMH as the standard tool for validating performance improvements and comparing algorithm implementations.