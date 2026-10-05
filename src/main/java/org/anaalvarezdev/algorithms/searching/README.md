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

Linear Search was first introduced while studying array traversal. Its canonical
implementation now belongs to this package so that Searching owns search
behaviour without duplicating code.

Future implementations should be added only when their contracts, correctness
arguments, and complexity analyses are already defined in the module
documentation.

## Engineering Rule

Not every documented search pattern requires a Java class, and not every Java
class requires a benchmark.

Implementation and empirical evaluation should exist only when they add clear
learning or engineering value.
