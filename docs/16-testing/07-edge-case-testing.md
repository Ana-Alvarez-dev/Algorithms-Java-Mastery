# Edge-Case Testing

An edge case is relative to the contract. Empty can be valid, null can be a
rejection, and an out-of-domain input can have unspecified behaviour. Testing
must not quietly invent new guarantees. Sorted duplicates are valid here and
therefore belong in nearest-value tests.

| Boundary | Risk exposed |
|---|---|
| Empty/singleton | Wrong sentinel or boundary access |
| Exact repeated target | Returning a later exact copy |
| Repeated maximum with target above it | Returning the last rather than first copy |
| Tied repeated neighbours | Correct distance but wrong index |
| MIN_INT/MAX_INT | Overflow before widening subtraction |
| Caller array after search | Unexpected mutation |

The new class exhausts 792 sorted multisets through length seven and 11 targets
each (8,712 queries), then exercises 300 seeded full-int/duplicate arrays.
Finite enumeration is exhaustive only over that declared domain. Translation
and scaling remain in a safe int range; overflowing test transformations would
invalidate the intended relation.

Large benchmark fixtures need domain checks too. The old aggregation series
used invalid int-sum cases at its two largest sizes. New modulo-100 data has
valid prefixes/results for all defaults; its historical means are unchanged
and cannot be reused as measurements of the new fixture. The [implementation and verification guide](implementation-and-verification-guide.md) defines
both evidence boundaries. Next: [property thinking](08-property-based-thinking.md).

## Ordered Review Clarification

The finite sorted domain has C(n+4,4) arrays at length n. Summing n=0..7 gives
C(12,5)=792, then 792*11=8,712 oracle queries within one JUnit invocation. The
benchmark-only fixture tests additionally check valid sizes 0,1,99,103 to cover
empty input and incomplete modulo-100 cycles, as well as all five @Param
sizes. Boundary evidence is tied to each API's domain; it does not enlarge
that domain or certify every untested fixture.

The [applied evidence map](test-contracts.md) connects this clarification to
the actual source, domains and profile scope. The [documentation review](documentation-review.md)
records TE01-TE10 and the fresh Java 21 outcomes.
