# Longest Common Subsequence

## Unit semantics and reconstruction orientation

The [applied map](implementation-contracts.md) connects UTF-16 code-unit
semantics to the recurrence and char-array trace. Length is symmetric; the
up-on-tie witness may change when inputs swap. A supplementary character can
contribute two units, and a maximum unit subsequence can contain a lone
surrogate. No normalisation, code-point/grapheme conversion or minimum-word
ordering is implicit. The empty-axis cost includes preparation/outer loops,
not just zero inner transitions.

Find a longest sequence appearing in both inputs in order, with gaps allowed.
This is an optimisation problem with possibly several witnesses. It differs
from longest common substring, which requires contiguity.

## Contract and Recurrence

`LongestCommonSubsequence` accepts non-null Strings of at most 512 UTF-16 units
each. Java char units are the sequence elements; no normalisation or code-point/
grapheme conversion occurs. Empty prefixes have length zero. A supplementary
character has two units, and a returned unit subsequence can contain a lone
surrogate; that explicit policy is checked by tests.

L(i,j) describes the first i/j units. If trailing units match, use 1+L(i-1,j-1).
Otherwise use max(L(i-1,j),L(i,j-1)). An optimum can extend the matching-prefix
optimum; on mismatch at least one trailing unit is excluded. These cases prove
soundness/completeness by induction on i+j. Zero boundary rows represent empty
prefixes and zero is a valid cached result.

## Storage and Reconstruction

The memo variant uses a fresh dense matrix filled with -1. A diagonal-only
search still pays full allocation/fill. Row-major tabulation evaluates the full
grid. One-row length storage uses shorter-input columns and saves the old
diagonal before overwriting; live left/above versions have different meanings.

`sequence` instead retains the full table. On a match it writes a char array
backwards by index; on mismatch it follows an equal-score predecessor. Ties
discard from the first prefix. ABCBDAB/BDCABA gives BCBA; AB/BA gives A, while
swapping the inputs gives B. Length is symmetric but the selected witness need
not be. No lexicographically minimum witness is promised.

For ABC/AC the table rows are [0,0,0], [0,1,1], [0,1,1], [0,1,2]; answer AC
has a gap in ABC. Figure 15.8 in supplied CLRS §15.4 was visually checked.

## Cost, Tests and Measurement

Dense memo/table use O((m+1)(n+1)) space/time including preparation; memo also
has O(m+n) depth. Rolling length keeps O(min(m,n)+1) state. Reconstruction adds
O(m+n) trace and O(L) output to the full table; compression does not retain it
for free. Eight tests compare all 3,969 pairs of binary Strings through length
five with independent subsequence enumeration, plus seeded/Unicode/boundary/
tie/identity cases. They verify witness legality as well as optimum length.

Three length methods define 24 JMH cells: n=32/128, IDENTICAL, DISJOINT,
SHIFTED and seeded RANDOM. Fresh state is timed; witnesses are not. The
[first report](../17-benchmarking/results/dynamic-programming/README.md) retains
all 24 cells/errors and printed iterations from author-reported source `d572651`,
JDK 21.0.9/JMH 1.37. Memo means are lower than rolling on IDENTICAL/SHIFTED;
large DISJOINT/RANDOM favour rolling. Seven errors reach 25%; n=32 RANDOM memo/
rolling and n=128 RANDOM memo/table intervals overlap. Dense setup persists
despite a diagonal search, so lower storage/output length alone do not explain
time. No witness throughput, cache cause or universal winner is inferred.
See the [implementation and benchmark guide](implementation-and-benchmark-guide.md). Next: [complexity](11-complexity-analysis.md).
