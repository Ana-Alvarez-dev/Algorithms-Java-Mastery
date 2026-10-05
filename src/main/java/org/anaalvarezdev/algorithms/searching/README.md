# Searching Implementations

## Algorithms Java Mastery

This package contains the Java implementations associated with
`docs/04-searching/`.

The package follows the repository traceability model:

```text
CLRS / Academic Source
        ↓
docs/01-foundations/
        ↓
docs/02-complexity/
        ↓
docs/03-arrays/
        ↓
docs/04-searching/
        ↓
src/main/java/org/anaalvarezdev/algorithms/searching/
        ↓
src/test/java/org/anaalvarezdev/algorithms/searching/
        ↓
src/jmh/java/org/anaalvarezdev/algorithms/searching/
```

## Current Implementation

| Algorithm | Documentation | Test | Benchmark |
|---|---|---|---|
| `LinearSearch` | `03-linear-search.md` | `LinearSearchTest` | `LinearSearchBenchmark` |
| `BinarySearch` | `04-binary-search.md` | `BinarySearchTest` | `BinarySearchBenchmark` |
| `RecursiveBinarySearch` | `05-recursive-binary-search.md` | `RecursiveBinarySearchTest` | `BinarySearchVariantsBenchmark` |
| `FirstOccurrence` | `07-search-patterns.md` | `FirstOccurrenceTest` | Not required |
| `LastOccurrence` | `07-search-patterns.md` | `LastOccurrenceTest` | Not required |
| `SearchInsertPosition` | `07-search-patterns.md` | `SearchInsertPositionTest` | Not required |
| `SearchRange` | `08-common-search-problems.md` | `SearchRangeTest` | Not required |
| `ClosestValueSearch` | `08-common-search-problems.md` | `ClosestValueSearchTest` | Not required |

Linear Search was first introduced while studying array traversal. Its canonical
implementation now belongs to this package so that Searching owns search
behaviour without duplicating code.

The currently approved implementation scope is complete.

Future implementations should be added only when they introduce a distinct
search problem or reasoning pattern and when their contracts, correctness
arguments, and complexity analyses are already defined in the module
documentation.

## Engineering Rule

Not every documented search pattern requires a Java class, and not every Java
class requires a benchmark.

Implementation and empirical evaluation should exist only when they add clear
learning or engineering value.


## Verification Status

Every implementation in this package has a corresponding JUnit + AssertJ test
class.

Benchmark coverage is intentionally selective.

At this stage, repository documentation records the expected verification
commands, but this README does not claim that Maven or JMH execution has been
performed for the current branch.

The implementation should be verified with the Maven Wrapper before final
integration.
