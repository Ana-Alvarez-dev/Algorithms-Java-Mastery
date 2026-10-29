# State Definition

## Shared domain and conceptual mask ordering

The [contract map](implementation-contracts.md) states validation precedence,
shared grid caps and ownership. The knapsack grid cap also applies to rolling
values even though they allocate one row. The smallest numeric subset-mask
rule is the mathematical sum of 2^index over selected positions; it is not
a returned int/long mask or a 32/64-item restriction. The accepted item cap
is 256, and the Selection record only freezes its list, not arbitrary semantics.

A state must contain enough information to determine the residual answer under
fixed input. Minimality helps storage, but sufficiency comes first. Removing a
variable without proving equivalence can make cached answers incorrect.

## Concrete Meanings

| State | Meaning | Terminal condition |
|---|---|---|
| F(i) | Exact ith Fibonacci value | i=0/1 |
| K(i,c) | Maximum value using only the first i items, weight <=c | i=0 |
| L(i,j) | Maximum common subsequence length of first i/j UTF-16 units | i=0 or j=0 |

Knapsack states with the same capacity but different item prefixes are not
equivalent. The input arrays are fixed for one public call, so they need not
be copied into every key; the cache is never shared between inputs. LCS likewise
requires both prefixes and the fixed Strings. Prefix lengths are half-open
boundaries, which makes empty states and original indices unambiguous.

## Encoding and Limits

Knapsack uses integer capacity as a direct dimension. Its numeric size is not
the number of bits encoding it; the O(nC) bound is pseudopolynomial. Validate
the long grid product before allocation, not an overflowing int product. LCS
dimensions are Java UTF-16 lengths, not code-point or grapheme counts. Zero is
a legitimate cached result; -1 is unknown only because answers are nonnegative.

Witness policy is additional: knapsack ties omit the highest remaining index;
LCS unequal-character ties discard from the first prefix. Neither promises the
lexicographically least witness. A value-only compressed state does not retain
all decisions needed for those plans. The [implementation and benchmark guide](implementation-and-benchmark-guide.md) defines the exact domains and
ownership policies. Next: [recurrence design](07-recurrence-design.md).
