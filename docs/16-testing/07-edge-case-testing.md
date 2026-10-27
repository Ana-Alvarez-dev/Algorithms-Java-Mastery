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
