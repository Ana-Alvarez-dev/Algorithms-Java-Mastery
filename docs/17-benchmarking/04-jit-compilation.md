# JIT Compilation

## Algorithms Java Mastery

One of the defining characteristics of Java is that programs are **not compiled directly into native machine code before execution**. Instead, Java source code is first compiled into **bytecode**, an intermediate platform-independent representation executed by the **Java Virtual Machine (JVM)**.

If every instruction were interpreted throughout the lifetime of the application, Java programs would execute considerably slower than applications compiled directly to machine code. To overcome this limitation, modern JVMs employ the **Just-In-Time (JIT) compiler**.

The JIT compiler continuously monitors program execution, identifies frequently executed methods, and compiles them into optimized native machine code while the application is running. This adaptive optimization enables Java applications to achieve performance comparable to traditionally compiled languages while preserving platform independence.

Understanding JIT Compilation is fundamental to benchmarking because the execution speed of a Java program changes dynamically as the JVM applies increasingly sophisticated optimizations. Consequently, reliable performance measurements must account for the JIT compilation process.

---

# 1. Purpose

The purpose of this document is to:

- introduce the Just-In-Time compiler;
- explain how JIT compilation works;
- understand adaptive runtime optimization;
- study the relationship between JIT and benchmarking;
- prepare for performance measurement using JMH.

---

# 2. What Is JIT Compilation?

**Just-In-Time (JIT) Compilation** is the process of translating Java bytecode into optimized native machine code during program execution.

Conceptually:

```text
Java Source Code
        ↓
Java Compiler
        ↓
Bytecode
        ↓
JVM
        ↓
JIT Compiler
        ↓
Native Machine Code
```

This process occurs automatically while the application is running.

---

# 3. Why JIT Exists

Interpreting every bytecode instruction repeatedly is inefficient.

Instead, the JVM follows this strategy:

```text
Observe Execution

↓

Identify Hot Code

↓

Compile

↓

Reuse Native Code
```

Frequently executed code becomes progressively faster.

---

# 4. Bytecode Execution

Initially, Java programs execute as bytecode.

Conceptually:

```text
Bytecode

↓

Interpreter

↓

Execution
```

Interpretation enables portability but is slower than optimized native execution.

---

# 5. Hot Methods

The JVM continuously monitors execution frequency.

Conceptually:

```text
Method

↓

Executed Many Times

↓

Hot Method
```

Only hot methods become candidates for JIT compilation.

This selective strategy prevents unnecessary compilation.

---

# 6. Runtime Profiling

Before compiling a method, the JVM gathers execution statistics.

Examples include:

- execution frequency;
- branch behavior;
- method invocation patterns;
- object allocation.

This information allows the compiler to make better optimization decisions.

---

# 7. Adaptive Optimization

The JVM does not optimize everything immediately.

Instead:

```text
Observe

↓

Profile

↓

Optimize

↓

Observe Again
```

Optimization evolves as the application continues running.

---

# 8. Native Machine Code

Once compiled, a hot method executes directly as machine code.

Conceptually:

```text
Bytecode

↓

JIT Compilation

↓

Native Code

↓

Faster Execution
```

Future executions bypass interpretation.

---

# 9. Common Optimizations

The JIT compiler performs numerous runtime optimizations.

Examples include:

- method inlining;
- dead code elimination;
- loop optimization;
- constant propagation;
- escape analysis.

These optimizations improve execution speed without changing program behavior.

---

# 10. Method Inlining

One of the most important optimizations is **method inlining**.

Instead of:

```text
Method A

↓

Call

↓

Method B
```

the JVM may transform execution into:

```text
Method A

↓

Body of Method B
```

Removing method-call overhead often improves performance.

---

# 11. Dead Code Elimination

The compiler may remove computations whose results are never used.

Conceptually:

```text
Unused Computation

↓

Removed
```

This optimization can significantly affect benchmarking if results are ignored.

For this reason, JMH uses **Blackhole** objects to prevent dead code elimination.

---

# 12. Constant Propagation

Known constant values may be substituted during compilation.

Conceptually:

```text
Constant Value

↓

Compile-Time Replacement

↓

Simpler Code
```

Reducing unnecessary calculations improves execution efficiency.

---

# 13. Escape Analysis

The JVM analyzes whether objects remain confined to a single method.

If an object does not escape:

```text
Object

↓

No Escape

↓

Allocation Optimization
```

Some allocations may even be eliminated entirely.

---

# 14. Deoptimization

Runtime assumptions are not always permanent.

If an optimization becomes invalid:

```text
Optimized Code

↓

Assumption Changes

↓

Deoptimization

↓

Recompile
```

The JVM can safely replace optimized code with a more appropriate version.

---

# 15. Relationship with JVM Warm-up

Warm-up prepares the JVM for optimization.

Conceptually:

```text
Warm-up

↓

Runtime Profiling

↓

JIT Compilation

↓

Optimized Execution
```

Without warm-up, the JIT compiler has insufficient information for advanced optimization.

---

# 16. Relationship with Benchmarking

Reliable benchmarks must account for JIT compilation.

Incorrect workflow:

```text
Start JVM

↓

Measure Immediately
```

Correct workflow:

```text
Warm-up

↓

JIT Optimization

↓

Measurement
```

Only steady-state performance should be measured.

---

# 17. Relationship with Complexity Analysis

JIT compilation improves execution speed but does **not** change asymptotic complexity.

Example:

```text
O(n)
```

remains:

```text
O(n)
```

before and after optimization.

JIT affects constant factors, not algorithmic growth.

---

# 18. Java Perspective

Modern JVM implementations continuously balance interpretation and compilation.

Typical execution lifecycle:

```text
Java Source

↓

Bytecode

↓

Interpreter

↓

Runtime Profiling

↓

Hot Method

↓

JIT Compilation

↓

Optimized Native Code
```

This adaptive execution model is one of the primary reasons why long-running Java applications achieve excellent performance.

---

# 19. Engineering Perspective

Professional Java developers recognize that runtime optimization is dynamic.

Typical questions include:

```text
Has this
method
been JIT
compiled?
```

```text
Are we
measuring
interpreted
or optimized
execution?
```

```text
Could compiler
optimizations
invalidate this
benchmark?
```

Understanding JIT Compilation enables engineers to design reliable benchmarks and correctly interpret performance measurements.

---

# 20. Relationship with the Next Document

This document introduced **Just-In-Time (JIT) Compilation**, explaining how the JVM transforms frequently executed bytecode into optimized native machine code.

The next document studies **Garbage Collection Effects**, examining how Java's automatic memory management influences application performance and why garbage collection activity must be considered when interpreting benchmark results.

---

# 21. Key Takeaways

After completing this document, the learner should understand that:

- the JIT compiler translates frequently executed Java bytecode into optimized native machine code during program execution;
- the JVM profiles application behavior before deciding which methods to optimize;
- only **hot methods** are typically selected for JIT compilation;
- common optimizations include method inlining, dead code elimination, constant propagation, and escape analysis;
- the JVM can deoptimize and recompile methods as execution characteristics change;
- JIT compilation significantly improves execution speed but does not alter an algorithm's asymptotic complexity;
- reliable Java benchmarks must allow sufficient warm-up for JIT optimizations to occur before measurements are collected;
- understanding JIT Compilation is essential for analyzing Java performance, interpreting benchmark results, and designing scientifically valid performance experiments.