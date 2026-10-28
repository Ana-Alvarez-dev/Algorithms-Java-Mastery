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

On 2026-10-09, the LinearSearch correction was verified with Java 21 and Maven:
188 tests passed, including all 9 LinearSearch tests. The JMH executable was
also built successfully and discovered all four Searching benchmark methods.
That verification applies to the correction integrated through PR #63.

The author subsequently supplied a complete first JMH summary with 65
configurations. The benchmark templates now record the reported results and
preliminary interpretation. The full transcription, provenance and limitations
are available in the
[Searching benchmark evidence](../../../../../../../docs/17-benchmarking/results/searching/README.md).

Wide error margins in several cases and incomplete run metadata limit precision.
A more stable follow-up run remains useful; the current evidence can already
support the research documentation with those limits stated explicitly.

## Testing Module Follow-up

`ClosestValueSearch` now enforces the lowest-index nearest-value tie even with
duplicates, reusing `SearchInsertPosition` rather than copying lower-bound code.
Empty/minimum fast paths are constant; worst time is Theta(log(n+1)), auxiliary
space O(1). Input is preserved and distances widen before subtraction. The
[Testing guide](../../../../../../../docs/16-testing/implementation-and-verification-guide.md)
and [verification report](../../../../../../../docs/16-testing/verification-report.md)
link six pre-fix failures to twelve named cases plus four oracle/property/
isolation tests. Searching has 101 executed tests (85 existing +16 new).
Historical Searching benchmark scores remain unchanged; no closest-value timing
question is added merely because its contract was fixed.

## Applied Documentation Review (2026-10-28)

The [current contract and proof map](../../../../../../../docs/04-searching/implementation-contracts.md)
links all eight APIs to their results, validation, interval variants and cost
models. The [dated review](../../../../../../../docs/04-searching/documentation-review.md#ordered-documentation-review-follow-up-2026-10-28)
records documentation corrections and independent bounded model checks.
Production Java, tests and benchmark bodies are unchanged in that pass; the
101 hosted Searching invocations above remain historical execution evidence.
