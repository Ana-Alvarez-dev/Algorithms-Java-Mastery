# Benchmarking

## Algorithms Java Mastery

Understanding an algorithm's theoretical complexity is an essential part of Computer Science, but it is not sufficient for evaluating its real-world performance. Two algorithms with the same asymptotic complexity may exhibit significantly different execution times because of implementation details, compiler optimizations, memory behavior, and hardware characteristics.

**Benchmarking** is the discipline of measuring software performance through controlled and reproducible experiments. Instead of reasoning only about asymptotic growth, benchmarking evaluates how algorithms behave during actual execution under realistic conditions.

In the Java ecosystem, reliable benchmarking requires a deep understanding of the **Java Virtual Machine (JVM)**, including **Just-In-Time (JIT) compilation**, **JVM warm-up**, **Garbage Collection (GC)**, and runtime optimizations. Measuring execution time using simple approaches—such as `System.nanoTime()` inside a loop—often produces misleading results because modern JVMs dynamically optimize running code.

To address these challenges, Java developers use **JMH (Java Microbenchmark Harness)**, the official benchmarking framework developed by the OpenJDK team. JMH provides the tools necessary to design statistically sound benchmarks while minimizing measurement bias introduced by the runtime environment.

This module introduces the theoretical and practical foundations of benchmarking, preparing the learner to design experiments, collect reliable performance data, interpret benchmark results, and evaluate Java algorithms using professional engineering practices.

---

# Learning Objectives

After completing this module, the learner should be able to:

- explain the purpose of software benchmarking;
- distinguish theoretical complexity analysis from empirical performance analysis;
- understand the influence of JVM warm-up and JIT compilation;
- analyze the impact of Garbage Collection on benchmark results;
- use JMH to create reliable Java microbenchmarks;
- configure benchmark execution parameters;
- interpret benchmark reports correctly;
- identify invalid benchmarking practices;
- evaluate algorithm performance using reproducible experiments;
- apply professional benchmarking techniques to Java applications.

---

# Module Structure

```text
17-benchmarking/
│── README.md
│── 01-benchmarking-fundamentals.md
│── 02-empirical-analysis.md
│── 03-jvm-warmup.md
│── 04-jit-compilation.md
│── 05-garbage-collection-effects.md
│── 06-jmh-fundamentals.md
│── 07-benchmark-design.md
│── 08-benchmark-parameters.md
│── 09-result-interpretation.md
└── 10-common-mistakes.md
```

---

# Module Contents

The [Arrays empirical research report](results/arrays/README.md) consolidates
105 recorded means from 11 JMH templates. It includes the extracted CSV,
methodology, theoretical comparisons, preliminary conclusions and limitations.
Error margins were not retained in the source tables and remain unknown.

The module includes a concrete
[Searching benchmark evidence record](results/searching/README.md), with the
first reported execution, all 65 transcribed means and errors, preliminary
interpretation, provenance and limitations. It connects the concepts below
to an observed experiment without treating timing as a complexity proof.

## 01. Benchmarking Fundamentals

Introduces benchmarking as a software engineering discipline.

Topics include:

- performance measurement;
- benchmarking principles;
- repeatability;
- reproducibility;
- experimental evaluation.

---

## 02. Empirical Analysis

Explains the relationship between theoretical complexity and experimental performance.

Topics include:

- asymptotic analysis;
- empirical measurements;
- performance evaluation;
- scalability;
- experimental methodology.

---

## 03. JVM Warmup

Studies the warm-up phase of the Java Virtual Machine.

Topics include:

- JVM startup;
- adaptive optimization;
- warm-up iterations;
- runtime stabilization;
- benchmark preparation.

---

## 04. JIT Compilation

Introduces the Java Just-In-Time compiler and its impact on performance.

Topics include:

- runtime compilation;
- adaptive optimization;
- inlining;
- dead code elimination;
- execution efficiency.

---

## 05. Garbage Collection Effects

Explains how automatic memory management influences benchmark results.

Topics include:

- object allocation;
- heap management;
- garbage collection pauses;
- memory pressure;
- measurement variability.

---

## 06. JMH Fundamentals

Introduces Java Microbenchmark Harness, the standard benchmarking framework for Java.

Topics include:

- benchmark annotations;
- benchmark lifecycle;
- execution modes;
- Blackhole;
- benchmark organization.

---

## 07. Benchmark Design

Studies how reliable benchmarking experiments are constructed.

Topics include:

- experimental design;
- input selection;
- avoiding measurement bias;
- repeatability;
- benchmark isolation.

---

## 08. Benchmark Parameters

Explains how benchmark execution is configured.

Topics include:

- warm-up iterations;
- measurement iterations;
- forks;
- threads;
- benchmark configuration.

---

## 09. Result Interpretation

Studies how benchmark results should be analyzed.

Topics include:

- execution time;
- throughput;
- latency;
- statistical variation;
- confidence in measurements.

---

## 10. Common Mistakes

Reviews conceptual and practical errors frequently encountered during performance measurement.

Topics include:

- measuring without warm-up;
- benchmarking incorrect code;
- ignoring JVM optimizations;
- drawing conclusions from single executions;
- confusing theoretical complexity with empirical performance.

---

# Learning Progression

This module follows the workflow used by professional performance engineers when evaluating Java software.

```text
Algorithm
        ↓
Complexity Analysis
        ↓
Empirical Measurement
        ↓
JVM Understanding
        ↓
JIT Compilation
        ↓
Garbage Collection
        ↓
JMH
        ↓
Benchmark Design
        ↓
Benchmark Configuration
        ↓
Result Interpretation
        ↓
Performance Evaluation
```

Each document builds upon the previous one, progressing from theoretical concepts to practical benchmarking methodology.

---

# Relationship with Previous Modules

This module extends the engineering workflow established throughout the repository.

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

While previous modules demonstrate that an algorithm is **correct**, Benchmarking evaluates **how efficiently that algorithm performs under real execution conditions**.

Testing answers:

> **Does the implementation work correctly?**

Benchmarking answers:

> **How efficiently does the implementation execute?**

Together, they provide a complete engineering evaluation of software quality.

---

# Java Perspective

Modern Java benchmarking commonly relies on:

- Java Microbenchmark Harness (JMH);
- Maven;
- OpenJDK;
- IntelliJ IDEA;
- GitHub Actions;
- Java Flight Recorder (JFR) for advanced performance analysis.

JMH provides statistically reliable measurements while accounting for JVM optimizations that invalidate naïve timing techniques.

---

# Engineering Perspective

Professional software engineers recognize that performance measurement requires scientific methodology.

Before trusting benchmark results, they typically ask:

- Has the JVM completed warm-up?
- Is the JIT compiler influencing execution?
- Has Garbage Collection affected the measurements?
- Are multiple iterations producing consistent results?
- Is the benchmark measuring the intended behavior?

Reliable performance evaluation requires carefully designed experiments rather than isolated timing measurements.

Benchmarking therefore represents a disciplined engineering practice that complements algorithm analysis, automated testing, and software optimization.

---

# Execution Environment and Reproducibility

Benchmark results are empirical observations produced under a particular execution environment.

They should therefore record or control relevant conditions such as:

- JDK version;
- operating system;
- hardware;
- JVM warm-up;
- JMH configuration;
- forks;
- measurement iterations;
- benchmark parameters.

The repository may execute benchmarks from Windows or Linux.

The expected relationship is:

```text
Complexity Analysis
        ↓
Theoretical Growth Expectation
        ↓
Correct Java Implementation
        ↓
Automated Tests
        ↓
Controlled JMH Experiment
        ↓
Environment-Aware Interpretation
```

A change in operating system or hardware may change concrete measurements such as throughput or nanoseconds per operation.

It does not by itself change an asymptotic result such as:

```text
Θ(n)
```

This preserves the boundary:

```text
Asymptotic Model
        ≠
Empirical Measurement
```

The Maven Wrapper should be used to build the benchmark profile.

```text
Windows
.\mvnw.cmd -Pbenchmark clean package

Linux
./mvnw -Pbenchmark clean package
```

The benchmark artefact is then executed according to the JMH configuration defined by the project.

---

# Traceability with the Engineering Environment

```text
docs/02-complexity/
        ↓
Algorithmic Expectation
        ↓
src/main/
        ↓
src/test/
        ↓
src/jmh/
        ↓
Maven Benchmark Profile
        ↓
Windows / Linux Execution
        ↓
Measured Result
        ↓
Engineering Interpretation
```

Linux is therefore relevant to benchmarking as an execution environment, not as a source of algorithmic complexity.

---

# Sorting Experiments

Sorting now provides two comparative JMH groups, covering five academic
algorithms, a Java library reference, and copy-only controls. They measure copy
plus sorting on five deterministic distributions. Quadratic algorithms are
limited to 10,000 elements; Merge, Quick, and the library reference extend to
1,000,000. The [Sorting implementation and benchmark guide](../05-sorting/implementation-and-benchmark-guide.md)
defines the complete protocol, Windows commands, and evidence-recording rules.
The [first reported Sorting results](results/sorting/README.md) preserve the
complete 48-configuration summary and JMH metadata. The original 480 iteration
averages were checked when that report was prepared; JSON/log exports were
removed from the current tree on 2026-10-19 at the author's request. The run used
JDK 25.0.1 and completes both templates' result sections.
Interpretation remains preliminary because of wide errors and fork variation.

# Linked Lists Experiment

`LinkedListOperationsBenchmark` compares three manual generic topologies,
`ArrayList` and the executing JDK's `LinkedList` for traversal, fixed middle
access, and a prepend/remove pair that restores the original state. The default
matrix has fifteen methods and four sizes. The
[Linked Lists implementation and benchmark guide](../06-linked-lists/implementation-and-benchmark-guide.md)
defines timed work, setup exclusions, checksums, scope and Windows run commands.
The [first reported Linked Lists results](results/linked-lists/README.md) preserve
all 30 console means and errors supplied on 2026-10-19 and complete the template's
Result and Interpretation sections. Supplied headers confirm JMH 1.37 and
JDK 21.0.9; 50 measurement and 30 warmup iteration values are retained for five
configurations. The author identified the executed source as commit `a16a431`.
Two results have errors
larger than their means. No JSON/log files are versioned for this record.

# Stacks and Queues Experiments

`StackOperationsBenchmark` compares restored push/pop pairs across four
representations. `QueueOperationsBenchmark` compares FIFO rotation across six
representations, including a two-stack queue whose transfers remain timed.
The [module guide](../07-stacks-and-queues/implementation-and-benchmark-guide.md)
defines setup exclusions, reserved capacity, exact paired operations and the
20-configuration initial subset (40 configurations by default).

OpenJDK 21 verification passed all 418 repository tests, including 80 new tests.
The ten JMH methods were discovered and smoke-checked; a separate correctness
probe checked all 40 method/size combinations. The
[first reported measurement subset](results/stacks-and-queues/README.md) now
completes Result and Interpretation: 20 configurations, 200 measurement and 120
warmup averages, JDK 21.0.9/JMH 1.37, author-reported source `804f69f`. Eleven
reported errors reach at least 25% of their means. Conclusions remain preliminary;
all printed evidence is retained in Markdown without JSON/log artifacts.

# Hash Table Experiments

`HashTableOperationsBenchmark` compares chained/probed manual tables and JDK
HashMap for successful lookup, absent lookup and a remove/restore pair. DISTINCT_HASH
and CONSTANT_HASH fixtures separate ideal distinct homes from a single collision
class. JDK keys are Comparable; it is a library reference with different collision
management, not a pure academic chain. Construction and growth are excluded.

The [module guide](../08-hash-tables/implementation-and-benchmark-guide.md)
defines nine methods, 54 default configurations and the 18-configuration initial
subset. OpenJDK 21 verification passed 465 tests, including 47 new tests; JMH
smoke and an independent probe checked the harness. Result and Interpretation
are now completed by the [first reported subset](results/hash-tables/README.md):
18 DISTINCT_HASH configurations on Oracle JDK 21.0.9, with all means/errors,
180 measurement and 108 warmup iteration averages. The author confirmed execution
source `03e6ed5e5649b4018b47f054319e0495676b7fcf`.
The source trace explains how tombstone-safe reinsertion scans the contiguous
occupied suffix despite distinct home hashes; no exact timing cause is inferred.
Evidence remains in Markdown without JSON/log artifacts. CONSTANT_HASH and size
10,000 require separate measurements.

# Key Takeaways

After completing this module, the learner should understand that:

- benchmarking measures the real execution performance of software through controlled experiments;
- theoretical complexity analysis and empirical benchmarking provide complementary perspectives on algorithm efficiency;
- JVM warm-up, JIT compilation, and Garbage Collection significantly influence Java performance measurements;
- JMH is the standard framework for creating reliable Java microbenchmarks;
- properly designed benchmarks require reproducible methodology and statistically meaningful execution;
- benchmark parameters strongly affect measurement accuracy;
- benchmark results must be interpreted carefully rather than accepted at face value;
- professional software engineers use benchmarking to validate performance claims, compare implementations, and guide optimization decisions.
