# Recurrence Relations

## Algorithms Java Mastery

## Complete public cost versus helper recurrence

The [source ledger](implementation-contracts.md) writes the merge/crossing
recurrence with floor/ceiling halves and constant bases. InversionCount also
clones and allocates scratch once at the public boundary for n>=2; those
linear costs are included in its complete n log n bound. Repeated length-n
allocation at every node is not part of this implementation. A first equal
band can end QuickSelect without any retained subproblem.

The [implementation guide](implementation-and-benchmark-guide.md) maps these
models to actual Java contracts, traces, correctness arguments, tests and JMH.
A recurrence models source work on a stated input family. It is not a measured
time formula or a consequence of recursion syntax alone.

## Specify the Model First

Choose the size variable, a base domain, local work, executed child sizes and
the case being analysed. Use constant-cost primitive comparisons/additions for
the selected int/long algorithms. Arbitrary-precision multiplication would need
a different bit-cost model.

For positive n, define a constant base cost below a fixed threshold. Empty
arrays have explicit public behaviour, rather than an undefined log(0).
The familiar fixed-fraction form is T(n)=aT(n/b)+f(n), with constant a>=1 and
b>1. Not every divide-and-conquer recurrence has equal child sizes or this form.

| Source pattern | Recurrence / bound | Conclusion with the stated local work |
|---|---|---|
| Single decrement, constant local work | T(n)=T(n-1)+Theta(1) | Theta(n) work; linear logical depth |
| Worst-path binary search | T(n)=T(floor(n/2))+Theta(1) | Theta(log n) for n>=2; best successful query may stop immediately |
| Balanced sum with constant combine | T(n)=T(floor(n/2))+T(ceil(n/2))+Theta(1) | Theta(n) work, logarithmic depth |
| Full merge or crossing scan | T(n)=T(floor(n/2))+T(ceil(n/2))+Theta(n) | Theta(n log n) for n>=2 |
| Hoare Quicksort partition | T(n)=T(k)+T(n-k)+Theta(n), 1<=k<n | Depends on actual partition sizes; not automatically a half-size recurrence |
| Three-way QuickSelect | T(n)=T(m)+Theta(n), 0<=m<n, or return after partition | One retained strict side; quadratic worst-case upper bound, all-equal linear |

An O(n) local term states only an upper bound. From
T(n)<=2T(n/2)+O(n), one obtains an O(n log n) upper bound, not a matching lower
bound automatically. The guide verifies full linear scans before using the
tight Theta(n) merge/crossing model. Two half-sized calls alone can instead
have Theta(n) total work when their local combination is constant.

## Recursion-Tree Reasoning

For powers of two with local work proportional to n, level i has 2^i nodes
of size n/2^i, giving Theta(n) aggregate work at that level. There are log2(n)
internal levels, plus Theta(n) leaves: total Theta(n log n).
For non-powers of two, floor/ceiling halves still have logarithmic height;
the standard rounding argument preserves the bound, not an exact timing ratio.

With constant combination, the same full binary tree has n leaves and n-1
internal nodes. Its total node count is 2n-1, giving linear work, despite its
logarithmic active depth. Work sums all nodes; peak live recursive state follows
the active path and any retained child results. Scratch arrays, returned output
and total allocations are separate storage quantities.

## Balanced Cases Are Not Average-Case Proofs

Replacing k by n/2 in Quicksort describes a balanced case. It does not average
over pivot choices or input permutations. The existing deterministic middle-value
Quicksort documents expected Theta(n log n) only under uniformly random
permutations of distinct keys. Its arbitrary-input worst time remains quadratic.

The new median-of-three QuickSelect is deterministic and three-way; it has no
declared expected-linear guarantee over arbitrary inputs. Neither calling its
pivot a median nor observing favourable timings creates a randomised model.
The median of three sampled positions need not be the median of the full range.

## Choosing a Solution Method

Use substitution with explicit inductive hypotheses, aggregate a recursion
tree, or apply the [Master theorem](04-master-theorem.md) after checking all its
conditions. Variable-size, overlapping, data-dependent or decrement recurrences
need a justified model and may not match that theorem.
No benchmark ratio substitutes for the recurrence proof. The source/test/JMH
mapping remains in the implementation guide. The
[first research report](../17-benchmarking/results/divide-and-conquer/README.md)
now preserves all 48 default means/errors and printed iterations, with source
`507b253` and JDK 21.0.9/JMH 1.37. Its source scan/construction ledgers are
analytical counts, not measured machine events or a fitted asymptotic exponent.
