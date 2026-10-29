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

## Ordered Review Clarification

For the linear reference, the prefix invariant is minimum distance with the
first index attaining it. Replacing only on strictly smaller distance preserves
the tie rule; widening before subtraction makes the full int domain safe.
Distance-only agreement would accept the six historical wrong duplicate indices.
Test construction, expected-answer logic and production binary-search control
flow remain separate responsibilities.

The [applied evidence map](test-contracts.md) connects this clarification to
the actual source, domains and profile scope. The [documentation review](documentation-review.md)
records TE01-TE10 and the fresh Java 21 outcomes.
