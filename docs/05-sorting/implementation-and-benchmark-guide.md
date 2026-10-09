# Sorting Implementation and Benchmark Guide

## Implemented Scope

The Java 21 Sorting package implements the five algorithms studied in this
module. They are independent, stateless utility classes with the same public
entry-point shape:

```java
int[] values = {29, 10, 14, 37, 13};
InsertionSort.sort(values);
// values now contains {10, 13, 14, 29, 37}.
```

The common contract is:

- input is a non-null `int[]`;
- the method returns `void` and writes ascending order into the supplied array;
- length and every value's multiplicity are preserved;
- empty and singleton arrays are accepted;
- null input throws `NullPointerException` with `values must not be null`;
- comparisons use relational operators rather than overflow-prone subtraction.

Writing into the caller's array does not imply constant auxiliary space.
Merge Sort uses an additional array. Generic object/comparator sorting, descending
order, and alternative partition implementations remain outside this first
implementation scope.

## Traceability

All production and test files use the package
`org.anaalvarezdev.algorithms.sorting`.

| Analysis | Implementation | Test class | JMH experiment |
|---|---|---|---|
| [Selection Sort](04-selection-sort.md) | `SelectionSort` | `SelectionSortTest` | `ElementarySortingBenchmark.selectionSort` |
| [Bubble Sort](05-bubble-sort.md) | `BubbleSort` | `BubbleSortTest` | `ElementarySortingBenchmark.bubbleSort` |
| [Insertion Sort](06-insertion-sort.md) | `InsertionSort` | `InsertionSortTest` | `ElementarySortingBenchmark.insertionSort` |
| [Merge Sort](07-merge-sort.md) | `MergeSort` | `MergeSortTest` | `EfficientSortingBenchmark.mergeSort` |
| [Quick Sort](08-quick-sort.md) | `QuickSort` | `QuickSortTest` | `EfficientSortingBenchmark.quickSort` |
| Java 21 library reference | `Arrays.sort(int[])` | Reference oracle in the contract suite | `EfficientSortingBenchmark.javaArraysSort` |

The [source package README](../../src/main/java/org/anaalvarezdev/algorithms/sorting/README.md)
provides the implementation inventory. Tests live under `src/test/java/` and
experiments under `src/jmh/java/`, with the same package path.

## Exact Algorithm Choices

| Algorithm | Selected implementation | Time for nontrivial inputs | Auxiliary space |
|---|---|---|---|
| Selection | Minimum selection, avoiding self-exchanges | Θ(n²) in every case | Θ(1) |
| Bubble | Shrinking active prefix and early exit after a pass without exchanges | Θ(n) sorted; Θ(n²) worst case | Θ(1) |
| Insertion | Shift strictly greater values and insert the saved key | Θ(n) sorted; Θ(n²) worst case | Θ(1) |
| Merge | Top-down recursion; one reusable workspace; left value wins equality | Θ(n log n) best and worst case | Θ(n) workspace and O(log n) stack |
| Quick | Hoare partition; middle-value pivot; recurse on smaller side and loop over larger side | Θ(n log n) balanced; Θ(n²) worst case | O(log n) stack; Θ(1) partition state |

For Bubble, Insertion, and Quick Sort, expected-time statements assume a
uniformly random permutation of distinct keys. A fixed seeded benchmark dataset
is a concrete workload rather than a proof of that expectation.

Quick Sort uses inclusive intervals. Hoare returns a **boundary**, with recursive
regions `[left, boundary]` and `[boundary + 1, right]`; this differs from the
Lomuto final-pivot-index example in the algorithm document. Equal values do not
stall either scan. Processing only the smaller region recursively bounds stack
growth even when partitions are unbalanced. It does not provide a worst-case
logarithmic-time guarantee or randomise the pivot.

Bubble, Insertion, and Merge follow stable movement rules: strict comparisons
for exchanges/shifts, and left-first merge ties. Selection and Quick do not
guarantee stability. With primitive integers, equal-value identity cannot be
observed by a test. Stability is justified by the movement rules here; object
identity stability tests require a later object/comparator implementation.

## Correctness and Automated Evidence

Class Javadocs connect the implemented loops and recursion with the invariants
and termination arguments in the numbered documents. Sorting must establish
both ordered output and permutation preservation.

`SortingContractTest` supplies thirteen inherited JUnit Jupiter tests to each
of the five concrete test classes. The suite covers empty and singleton input,
two-element intervals, ordinary ordering, duplicates, extreme integers, sorted
and reverse input, equal values, idempotence, and null rejection. It also compares
results against `Arrays.sort` for:

- 110 seeded cases per algorithm across 22 boundary lengths;
- every array of length zero through six over keys `{-1, 0, 1}`: 1,093 cases per algorithm.

`MergeSortTest` adds a large odd-length case. `QuickSortTest` adds large sorted,
reverse, equal, random, and repeated-key inputs. These checks verify observable
output rather than private partition methods or a particular swap sequence.

On 2026-10-09, Java 21 verification passed **257 tests**, including **69 Sorting
tests**, and the executable JMH jar built successfully. A separate verification
probe exercised all 160 configured benchmark combinations twice and checked
source preservation, fresh copies, output agreement, and input distributions.
JMH discovery and a short forked smoke run verify that the generated harness
can execute; smoke scores are not recorded as research results.

Tests support selected executions. The correctness reasoning supplies the
general argument; successful compilation or benchmark execution alone is not
a correctness proof.

## Benchmark Design

There are two comparative experiment groups:

| Group | Methods | Default sizes | Configurations |
|---|---|---|---:|
| `ElementarySortingBenchmark` | Selection, Bubble, Insertion, copy-only control | 100; 1,000; 10,000 | 60 |
| `EfficientSortingBenchmark` | Merge, Quick, `Arrays.sort`, copy-only control | 100; 1,000; 10,000; 100,000; 1,000,000 | 100 |

Each group uses five distributions generated by `SortingBenchmarkInputs`:

| Distribution | Construction |
|---|---|
| `RANDOM` | Seeded values spanning the `int` domain |
| `SORTED` | Distinct ascending values `0..n-1` |
| `REVERSE_SORTED` | Distinct descending values `n-1..0` |
| `NEARLY_SORTED` | Ascending values, then `max(1, floor(n/100))` seeded adjacent exchanges; exchanges may overlap or cancel |
| `DUPLICATE_HEAVY` | Seeded values among the sixteen integers `-8..7` |

The seed is `20261009`. Generation happens once in trial setup. Matching
size/distribution pairs receive identical source values across algorithms and
groups. Every measured method clones its source, and sort methods order only
the clone. They return the result for JMH to consume.

**Measured cost is allocation/copy plus sorting, in microseconds per operation
(`us/op`).** Merge Sort's internal workspace allocation is included. The source
is never repeatedly sorted in place. The copy-only methods measure the same
clone operation without sorting; their scores should be presented as controls,
not mechanically subtracted to claim isolated sorting cost.

This design follows the reasoning in OpenJDK's
[JMH per-invocation sorting example](https://github.com/openjdk/jmh/blob/master/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_38_PerInvokeSetup.java),
which illustrates input-reuse errors and timer overhead from invocation-level
fixtures. Including the copy in the measured operation makes that cost explicit
and avoids a separate timer around each sorting call.

Defaults are one thread, three warmup iterations and five measurement iterations
of 500 ms each, and two forks. Command-line overrides must be recorded. Large
arrays are confined to the efficient group to avoid the elementary algorithms'
quadratic work. These groups describe the implemented experiments, not universal
performance classifications.

## Run from Windows PowerShell

Use a Java 21 JDK and run these commands from the repository root:

```powershell
.\mvnw.cmd test
.\mvnw.cmd -Pbenchmark clean package
java -jar target\benchmarks.jar -l ".*sorting.*"
```

For a short harness check, without publishing its scores:

```powershell
java -jar target\benchmarks.jar ".*sorting.*" -p size=100 -wi 1 -w 50ms -i 1 -r 50ms -f 1 -t 1 -foe true
```

For a first controlled subset, covering all eight methods on three distributions:

```powershell
java -jar target\benchmarks.jar ".*sorting.*" -p size=100,1000 -p distribution=RANDOM,SORTED,REVERSE_SORTED -foe true -rf json -rff sorting-first-run.json -o sorting-first-run.log
```

To execute each complete default group separately:

```powershell
java -jar target\benchmarks.jar ".*sorting.ElementarySortingBenchmark.*" -foe true -rf json -rff sorting-elementary.json -o sorting-elementary.log
java -jar target\benchmarks.jar ".*sorting.EfficientSortingBenchmark.*" -foe true -rf json -rff sorting-efficient.json -o sorting-efficient.log
```

The full matrix contains 160 configurations and may take tens of minutes. Start
with the subset if you want to inspect results before running the complete matrix.
Avoid overriding the elementary group to million-element inputs. Export names
should be changed for subsequent runs to preserve each experiment's provenance.

On Linux, build with `bash mvnw -Pbenchmark clean package` and use
`target/benchmarks.jar` in the Java commands.

## Recording and Interpretation

The benchmark templates intentionally leave `Result` and `Interpretation`
pending a recorded controlled execution. For each research run, retain:

- the complete JMH JSON and log, including headers and reported uncertainty;
- the source commit (`git rev-parse HEAD`) and exact command;
- `java -version`, CPU, operating system, JVM arguments, and effective JMH settings;
- distribution definitions, seed, and the fact that copying is timed.

Compare matching size/distribution pairs. Check variability before discussing
precise ratios, and consider allocation or GC profiling for later research.
The current protocol measures repeated copies of one deterministic source per
pair; it does not sample many seeds or measure a pure sorting operation.
It does not establish memory-space bounds, object stability, worst-case safety,
or mathematical asymptotic growth.

## Academic Connection

The supplied CLRS **Third Edition (2009)** supports the conceptual reasoning:
Section 2.1 for insertion and prefix invariants; Section 2.3.1 for divide-and-conquer
merge sorting; Chapter 7 for quick sorting and partitioning. Selection and Bubble
Sort correspond to the exercises in Section 2.2 and Problem 2-2. This repository's
zero-based arrays, shared workspace, middle-pivot Hoare choice, and bounded-stack
control are documented implementation decisions. The module's fourth-edition
bibliography remains a separate reference; no pagination from that edition is
claimed here.
