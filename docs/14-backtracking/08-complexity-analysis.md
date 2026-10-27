# Complexity Analysis

Analyse visited states, candidate checks, preprocessing and output separately.
Exponential/factorial labels do not specify the actual work of a bounded
implementation, and pruning is not guaranteed to eliminate most of a tree.

## Search Work and Output

Let P be unique value permutations, I nonterminal permutation prefixes,
u distinct values, V visited states, C=C(n,k) and Q ordinary queen placements.

| Operation | Search/preparation | Retained result |
|---|---|---|
| Position permutation count | Clone/sort plus O(nI+P+1) | One long |
| Frequency permutation count | Clone/sort/compress plus O(uI+P+1) | One long |
| Permutation enumeration | Position search plus Omega((n+1)P) copies | O((n+1)P) |
| Combination count | O(V), exact call formulas below | One long |
| Combination enumeration | Search plus Omega((k+1)C) copies | O((k+1)C) |
| Queen flags / masks | O(nV) / O(V) word work, within validated mask domain | One long |
| Queen enumeration | Flag search plus Omega((n+1)Q) copies | O((n+1)Q) |
| Signed subset decision | O(n) suffix preparation for bounded plus O(V), worst O(2^n) search | One boolean |

For distinct permutations P=n!; duplicates give n!/product(mi!). All-equal
input has one output but the position version still scans n entries at every
depth. Loose worst search bounds are O(n*n!) for position permutations/queen
flags and O(n!) for queens masks. These labels omit materialised rows unless
the output term is added.

## Exact Combination Calls

Without capacity pruning, calls equal sum(C(n,d), d=0..k). With pruning they
equal C(n+1,k). At n=18, k=2 these are 172/171; k=9 gives 155,382/92,378;
k=18 gives 262,144/19. Result counts are respectively 153, 48,620 and one.
Both counters have O(k+1) logical active depth, distinct from total calls.

## General Trees and Space

A uniform tree with depth d and branching bound b has at most sum(b^i,i=0..d)
nodes. It is O(b^d) for fixed b>1, O(d+1) for b=1, and one root for b=0.
Multiply by local work; add preparation and result-copying costs.

Depth-first search retains its active path, not all visited nodes. These
permutation/queen/subset methods have O(n) working state/depth; combinations
use O(k+1). Immutable enumeration output is additional. Masks have constant-size
state per frame and O(n) frames. None of this measures JVM stack bytes or
establishes a universal safe recursion depth.

## Pruning and Timing

Sound pruning can sharply change particular input families; it need not help
every case. Subset range rejection has one recursive call but O(n) bounds
preparation. An early witness may favour the version without that preparation.
Candidate reordering affects first-hit work, while an unchanged complete
counter still visits the same valid prefixes.

Eight methods in four JMH templates define 46 matching default cells. Their
future means/errors describe those fixtures and full timed contracts. Short
smoke, two sizes, or lower means cannot establish an exponent, universal ranking,
CPU/cache cause, allocation rate or formal statistical significance.

The [implementation and benchmark guide](implementation-and-benchmark-guide.md) has the full state/call ledgers and evidence protocol.
Next: [common mistakes](09-common-mistakes.md).
