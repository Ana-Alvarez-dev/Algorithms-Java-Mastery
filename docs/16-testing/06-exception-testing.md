# Exception Testing

Test exceptional behaviour only when the API defines it. `ClosestValueSearch`
requires a non-null array and throws an explicit NullPointerException for null.
Its existing test checks the type and stable message `array must not be null`.
Nondecreasing order is a caller precondition; no O(n) validation or exception
for an unsorted array is promised.

Distinguish a contract rejection from a test error. An unexpected exception is
not a successful negative test, and `catch(Exception)` without a failing path
can silently pass when nothing is thrown. Keep only the operation expected to
throw inside the assertion. Use message checks for deliberate public messages,
not every implementation detail of a runtime or dependency.

Benchmark fixture guards also have domains: aggregation setup accepts sizes
0..1,000,000 and rejects outside values before allocation. A separate functional
fixture probe checks those guards and default sums. This does not add a timing
question or make an exception a measured performance result. JaCoCo branch
counters do not represent exception handling; an exception contract still needs
its own assertion. See the [implementation and verification guide](implementation-and-verification-guide.md). Next: [edge cases](07-edge-case-testing.md).

## Ordered Review Clarification

The existing null test isolates ClosestValueSearch.search(null,10) and checks
its explicit message. It does not promise rejection of unsorted input. The
benchmark-profile fixture tests check three unsupported sizes and their stable
message; source inspection, separately, establishes that the guard precedes
allocation. A thrown exception alone is not an allocation measurement, nor is
a JaCoCo branch percentage evidence that an exception contract was asserted.

The [applied evidence map](test-contracts.md) connects this clarification to
the actual source, domains and profile scope. The [documentation review](documentation-review.md)
records TE01-TE10 and the fresh Java 21 outcomes.
