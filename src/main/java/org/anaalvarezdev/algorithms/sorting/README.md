# Sorting Implementations

## Algorithms Java Mastery

This Java 21 package implements the five algorithms studied in
[the Sorting module](../../../../../../../docs/05-sorting/README.md).
The [implementation and benchmark guide](../../../../../../../docs/05-sorting/implementation-and-benchmark-guide.md)
defines the contracts, exact variants, test coverage, and execution protocol.

| Class | Strategy | Test | Benchmark |
|---|---|---|---|
| `SelectionSort` | Repeated minimum selection | `SelectionSortTest` | `ElementarySortingBenchmark.selectionSort` |
| `BubbleSort` | Adjacent exchanges with early exit | `BubbleSortTest` | `ElementarySortingBenchmark.bubbleSort` |
| `InsertionSort` | Insert into sorted prefix | `InsertionSortTest` | `ElementarySortingBenchmark.insertionSort` |
| `MergeSort` | Top-down merge, shared auxiliary array | `MergeSortTest` | `EfficientSortingBenchmark.mergeSort` |
| `QuickSort` | Hoare partition, middle pivot, smaller-side recursion | `QuickSortTest` | `EfficientSortingBenchmark.quickSort` |

Call `AlgorithmName.sort(int[])` to order the supplied array ascending. Each
method preserves all element multiplicities, accepts empty and singleton arrays,
and rejects null input. Merge Sort writes into the original array but requires
linear auxiliary storage. Quick Sort bounds stack growth by processing the
larger partition iteratively; its worst-case running time remains quadratic.

The five test classes inherit the same observable contract tests and add
algorithm-specific large-input checks where appropriate. Equal primitive values
do not expose identity, so these tests do not claim to verify object stability.

The two JMH groups measure **copy plus sort**, use deterministic fresh inputs,
and include copy-only controls; the efficient group also includes `Arrays.sort`.
The benchmark profile builds `target/benchmarks.jar`. The
[first Sorting evidence record](../../../../../../../docs/17-benchmarking/results/sorting/README.md)
preserves the 48-configuration summary supplied on 2026-10-12 and findings from
the original 480 measurement-iteration averages. JSON/log exports were removed
from the current tree on 2026-10-19; individual arrays are not reproduced in
Markdown. The runtime was JDK 25.0.1, while the project targets Java 21.
Interpretation is preliminary because several cases and copy controls have
wide uncertainty. The supplied subset does not cover the full default matrix.
