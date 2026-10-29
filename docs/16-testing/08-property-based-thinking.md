# Property-Based Thinking

A property describes a relation that should hold over a specified domain.
Examples and broad generators serve different roles: a small case explains a
regression, an oracle checks complete outputs, and a metamorphic relation checks
consistent transformation behaviour.

For closest-value search, the linear oracle minimises (long distance,index).
The test independently scans all elements; it does not call a second binary
search or compare only minimum distances. All small sorted multisets are tested,
and seed **20261027** makes the 300 generated arrays reproducible.

Within safe int arithmetic, adding the same constant to all values and target,
or multiplying them by a positive factor, preserves the nearest index and ties.
The selected test uses fixed bounded data, +1000 and x3. Multiplying by a
negative factor reverses ordering and changes index semantics; overflow would
break the relation. Preconditions belong to the property.

These are deterministic loops, not a claim that jqwik/QuickTheories or automatic
shrinking is installed. A failure should be kept with its seed/input and reduced
to a named case. Agreement between similar implementations can share a bug;
independent reference logic and proof arguments remain necessary. The [implementation and verification guide](implementation-and-verification-guide.md)
connects the properties to the actual tests. Next:
[test-data builders](09-test-data-builders.md).

## Ordered Review Clarification

The selected metamorphic test has 45 targets and 135 production calls: base,
translated and positively scaled for each. On these bounded values, translation
preserves distance and multiplication by 3 scales every distance equally,
preserving both minimisers and their indices. Seeded arrays sample an eight-
entry pool including extremes; they are not uniform samples of all sorted int
arrays. Preserve an actual failing input as well as its seed when debugging.

The [applied evidence map](test-contracts.md) connects this clarification to
the actual source, domains and profile scope. The [documentation review](documentation-review.md)
records TE01-TE10 and the fresh Java 21 outcomes.
