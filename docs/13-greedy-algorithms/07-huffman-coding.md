# Huffman Coding

## Algorithms Java Mastery

## Proof bases, ties and materialisation

The [applied map](implementation-contracts.md) separates internal zero-depth
contracted roots from the public nonempty singleton code. For two weights x,y,
internal contraction gives zero and expansion x+y; calling the public
one-symbol cost would instead return x+y before expansion. The source does
not make that recursive public call. Deterministic subtree ties select an
optimal codebook; they do not implement a canonical Huffman length format.
String construction and exact cost arithmetic are counted separately from
queue comparisons and cost-only benchmarks.

The [implementation guide](implementation-and-benchmark-guide.md) maps this
case study to deterministic HuffmanCoding, its proof, exact-cost APIs, tests
and JMH. The objective is an optimal **binary prefix-code weighted payload
length** for given positive symbol frequencies. It is not a complete compressor
or a guarantee of minimal file size after headers, code tables and other costs.

## Prefix Codes and Domain

No codeword is a prefix of another. Symbol leaves in a binary tree give such a
code by following 0/1 edges from the root; this supports unambiguous parsing.
The API takes opaque signed int symbol ids with positive int frequencies.
Null maps/keys/values fail; zero/negative frequencies are rejected rather than
silently removed. Input is preserved and the returned sorted symbol/code map
is defensively immutable.

Empty input returns no codes and cost zero. A singleton uses **"0"**, costing
one bit per occurrence, an explicit nonempty-word convention. An empty singleton
code in another abstract model is not silently substituted. No text processing,
bitstream encoder/decoder, canonical-code format or length limit is implemented.

## Greedy Construction and Determinism

Create symbol leaves. Repeatedly remove the two minimum subtrees and insert
their merged parent. Queue order is (weight, minimum symbol id); the first
removed subtree gets the 0-edge and the second the 1-edge. Disjoint subtrees
have distinct minimum ids, so ties are deterministic regardless of map insertion
order. Code generation uses an explicit stack, not Java recursion.

For [45,13,12,16,9,5] at symbols 0..5, the merge weights are
14,25,30,55,100, summing to **224**. The resulting codes are
0:0, 1:101, 2:100, 3:111, 4:1101, 5:1100. Frequencies sum to 100 in this
unscaled example. Arbitrary optimal tie choices may give other valid codewords;
the documented rule selects the codebook expected by this repository.

## Why Minimum-Pair Merging Is Safe

With at least two positive-frequency symbols, an optimal tree can be chosen
full: a unary node could be removed to reduce weighted depth. Exchange the two
minimum weights into maximum-depth sibling leaves without increasing cost.
Contracting that pair reduces the alphabet; expanding an optimum for the
contracted tree adds their summed weight. A better contracted solution would
contradict original optimality. Induction justifies the algorithm and the
sum-of-merge-weights identity for n>=2, using zero-depth roots within the
contracted-tree proof. The guide provides the detailed argument
and the separate singleton convention.

## Cost-Only Variants

costWithHeap uses repeated PriorityQueue offers/removals and accumulates merged
weights in BigInteger. costWithTwoQueues sorts a private leaf copy and repeatedly
takes the smaller front of that list or a monotone queue of merged weights.
Each appended merge weight is no smaller than its predecessor, preserving
global minimum access. There are n-1 merges and 2(n-1) front selections for n>=2.
Both preserve input and return the same exact optimum cost, without codewords.

The heap implementation inserts n leaves individually; its construction is
**O(n log n) worst**, not a claim of linear heapify. The two-queue operation
includes sorting, then linear merging. Full codebook generation adds tree,
map and code-string work absent from these two cost-only APIs. Subtree sums
fit long under the positive-int/count domain; weighted length uses BigInteger
because depth adds another factor.

## Space and Evidence

Let L be total returned codeword characters. Queue/tree state is O(n), but
materialised code strings and traversal prefixes make full build **O(n+L)**
space and add O(L) character work to O(n log n) comparison/tree work and exact
arithmetic. Cost-only variants use O(n) queue/array state. An explicit stack
avoids recursive Java calls without making storage constant.

Tests check all 1,093 positive small frequency arrays against an independent
oracle enumerating full-binary-tree root partitions, plus prefix freedom,
recovered weighted lengths, 100 seeded alphabets, uniform/skewed/extreme weights
and immutable deterministic output. The benchmark compares only exact cost
workflows, including heap boxing versus clone/sort/arrays. The
[first report](../17-benchmarking/results/greedy/README.md) now records all
16 cost cells/errors and printed iterations. Two-queue means are lower in the
matching subset; UNIFORM size-100 intervals overlap, and three UNIFORM errors
reach 25%. Source counts n-1 merges/2n-1 heap offers explain work, not timing
shares. No code-generation throughput, compression ratio, allocation rate,
CPU/cache cause or universal speedup follows from cost-only measurements.
