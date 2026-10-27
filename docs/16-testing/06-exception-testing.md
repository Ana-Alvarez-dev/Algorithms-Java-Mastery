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
