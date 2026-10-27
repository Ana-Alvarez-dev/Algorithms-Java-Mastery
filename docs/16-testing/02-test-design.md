# Test Design

Start from a contract, partition the domain, then choose cases likely to
distinguish correct and incorrect behaviour. Arrange inputs, perform the action,
and assert the full observable result. Do not derive the expected answer from
the implementation under test.

| Obligation | Selected evidence |
|---|---|
| Minimum distance and minimum index | Exact duplicates, repeated lower/upper values and tied groups |
| Empty/singleton and outside range | Named partitions and exhaustive oracle |
| Full int target/value range | Long-distance extreme cases and seeded inputs |
| Input preservation | Fresh copy compared after each query |
| Null policy | Existing focused exception test and explicit message |

The independent oracle scans all positions using long differences and updates
only on strictly smaller distance. It is deliberately simpler than the binary
search. Exhaustive sorted multisets and seeded arrays complement named examples;
safe translation/scaling check relations between answers. None proves an
unstated contract for unsorted inputs, whose behaviour is unspecified here.

Keep the smallest useful regression that explains a failure. A returned index
being in range is too weak: an earlier equal value may still be required.
Performance assertions such as 'must finish within X ns' do not belong in these
functional tests. The [implementation and verification guide](implementation-and-verification-guide.md) gives partitions, trace and acceptance evidence.
Next: [JUnit Jupiter](03-junit-jupiter.md).
