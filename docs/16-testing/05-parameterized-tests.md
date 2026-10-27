# Parameterised Tests

Parameterisation applies the same behavioural assertion to explicit partitions.
It reduces duplicated assertion logic without replacing deliberate case design.
The new static MethodSource provides a label, fresh int array, target and
expected index for each named case; `@ParameterizedTest(name="{0}")` exposes
the label in test diagnostics.

The twelve cases include the three previously recorded duplicate failures,
repeated groups, all equal, negative ties, extreme distances, singleton and empty.
Six fail against the old implementation. After the correction all pass, along
with four separate oracle/property/isolation tests. Reported counts distinguish
these 16 invocations from the five annotated source methods.

JUnit gives each parameterised invocation the usual test lifecycle. A static
mutable array in a source could still leak changes; this builder/source copies
arrays explicitly instead. CSV-style sources suit scalar examples, while
MethodSource keeps array cases typed and avoids parsing ambiguities. No new
CSV artifact is needed for these in-code cases.

More rows are not automatically more coverage or better assertions. Include
partitions that expose plausible faults and preserve failure descriptions. The
[implementation and verification guide](implementation-and-verification-guide.md) links the actual source, full suite and pre-fix diagnostics. Next:
[exception testing](06-exception-testing.md).
