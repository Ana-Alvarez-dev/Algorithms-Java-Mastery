# Test-Data Builders

A test-data builder helps a case state its intent when repeated setup obscures
that intent. It is a test-only convenience, not an automatic production design
pattern or a new public API.

`ClosestSearchCaseBuilder` provides name, values, target and expected index.
The default empty case has expected -1. Nonempty cases specify their expected
answer explicitly. The builder does not sort input, fix a precondition or derive
an answer by calling production code; those actions could hide a faulty test.

Input is copied on builder assignment, case construction and case access. The
isolation test mutates the caller array, a returned copy and the reused builder,
then verifies the first fixture's data/result remain intact. This makes test
independence observable rather than relying on execution order.

Avoid a general builder framework for four scalar/array fields. Keep defaults
valid and visible, named case data reviewable, and assertion/oracle logic outside
construction. The [implementation and verification guide](implementation-and-verification-guide.md) links the helper and the regression class. Next:
[coverage](10-test-coverage.md).

## Ordered Review Clarification

Copying addresses aliasing, not semantic validation. The default expected -1
is suitable for empty input, but the builder does not reject a nonempty case
whose caller forgot to set an expectation. It remains mutable, test-only and
without a thread-safety guarantee. The record's array copies do not supply deep
array equality through generated equals; content assertions remain explicit.
The isolation test checks the three actual copy boundaries.

The [applied evidence map](test-contracts.md) connects this clarification to
the actual source, domains and profile scope. The [documentation review](documentation-review.md)
records TE01-TE10 and the fresh Java 21 outcomes.
