# Complexity Documentation Review

Reviewed on **2026-10-28 (America/Buenos_Aires)** against main
`dfb60afc3b8e60231ec421a8211073fee7677385`, the PR #99 merge of the
[Foundations review](../01-foundations/documentation-review.md).

## Scope and conclusion

All ten numbered Complexity notes and the module README were read. This pass
checks the stated models, definitions, case/probability assumptions, time/space
examples, amortized derivations, reference tables and concrete source paths.
Ten grouped findings have scoped documentation corrections applied below.
The existing distinction between case analysis and O/Ω/Θ, between amortized and
average-case analysis, and between theoretical bounds and measurements was
largely sound and is retained.

Concrete errors included an overflowing int-doubling example, a write/copy cost
sequence inconsistent with its dynamic-array model, an unqualified heapsort
best-case table, and non-existent Arrays linear-search paths. Other changes
make previously informal assumptions and proofs explicit. This is a review of
this module's documentation, not a formal proof audit of every Java method or
an exhaustive review of all textbook material.

Only Markdown changes. Production Java, tests, benchmark fixtures, scripts,
POM, CI and historical research numbers/provenance are unchanged. There is no
new Java compilation, Maven suite, coverage run or timed JMH run in this pass.

## Consulted sources

The supplied PDF is **CLRS, Introduction to Algorithms, Third Edition, MIT
Press, 2009**. Printed pages differ from one-based PDF page positions:

| Source checked | Printed pages | PDF pages | Review use |
|---|---|---|---|
| Section 2.2 | 23-28 | 44-49 | RAM instructions/word-size assumptions, encoding length, operation counts and case models |
| Section 3.1 | 44-52 | 65-73 | Eventual bounds, quantified definitions, little o/omega and function comparisons |
| Sections 6.2 and 6.4 | 154-155; 159-162 | 175-176; 180-183 | Heapify's stopping condition, heapsort upper bound, exercises 6.4-4 and 6.4-5 |
| Sections 17.1-17.2 | 451-458 | 472-479 | Aggregate/credit reasoning, zero-initialised counter, non-negative credit |
| Section 17.3 | 459-460 | 480-481 | Potential differences, telescoping and required initial/final-potential relation |
| Sections 17.4-17.4.1 | 463-467 | 484-488 | Initial state, exact insertion/copy costs and geometric expansion |

The preferred project bibliography remains **Fourth Edition (2022)**. That text
was not directly inspected; no equivalence of page numbers is claimed. The
notes now link this source record rather than suggesting that their fourth-
edition citation identifies the supplied PDF.

Versioned official primary sources were also read:

- [Java SE 21 JLS, sections 4.2.1-4.2.2](https://docs.oracle.com/javase/specs/jls/se21/html/jls-4.html#jls-4.2.2): integral ranges and operation precision.
- [Java SE 21 JLS, section 15.17.1](https://docs.oracle.com/javase/specs/jls/se21/html/jls-15.html#jls-15.17.1): low-order-bit result on integer multiplication overflow.
- [Java SE 21 ArrayList API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/ArrayList.html): amortized add guarantee and unspecified exact growth policy. The note discusses append via add(E), not arbitrary indexed insertion or trimming sequences.

The explicit shifted capacity-one potential, arithmetic trace, particular
counterexamples, output-space naming convention and source correspondences are
this repository's derivations/conventions. They are not copied textbook
pseudocode or an assertion that all these formulations appear verbatim in CLRS.
The heap exercises are cited for their questions and domain qualifications,
not represented as supplied completed solutions.

## Findings and applied corrections

| ID | Classification / evidence before correction | Applied correction |
|---|---|---|
| C01 | Model clarification: representative operations and hardware independence could be read as constant-cost calls, copying or arbitrary-precision arithmetic. Numeric value and encoded length were not concretely distinguished. | [Computational Complexity](01-computational-complexity.md#the-computational-model-is-part-of-the-claim) specifies the word model, encoding example, setup work and limits of comparison counts. [Growth](02-algorithm-growth.md) qualifies fixed logarithm bases and implementation changes. |
| C02 | Ambiguous terminology: Θ comparability was described as asymptotic equivalence; dominant-term shortcuts were unqualified. | [Asymptotic Analysis](03-asymptotic-analysis.md) distinguishes Θ from ratio-one (~), simplifies cancellation before classification and allows direct bounds when ratios do not converge. |
| C03 | Formalisation gap: little o/omega lacked quantified definitions, and the function domain and constant uniformity were not explicit. | [Notations](04-asymptotic-notations.md) gives domains, quantifier order, concrete witnesses and oscillating counterexamples; multi-parameter claims identify the domain/tail. |
| C04 | Incomplete search model: equal target-position language did not exclude duplicates or specify the first matching position. Several average-case references still said only “common assumptions”. | [Case Analysis](05-best-average-worst-case.md) states the unique uniform-target model and weighted success/absence formula. [Time](06-time-complexity.md) and [Examples](09-complexity-examples.md) use matching assumptions; binary-search and adjacent-swap expectations now identify their distributions. |
| C05 | Erroneous Java-shaped example: int doubling at n=1,073,741,825 wraps from 2^30 to -2^31 and then zero. Under a normally completing body, it can keep executing. Inclusive int increment bounds could wrap too. | [Time](06-time-complexity.md#loop-analysis-requires-bounds) uses long counters for a declared non-negative int limit, derives ceil/floor logarithmic body counts and the finite-width failure. The safe range is not generalised to arbitrary long limits. |
| C06 | Cost omissions: branch reasoning did not explicitly include condition evaluation; recursive forms lacked worked base/rounding rules; setup at zero-sized dimensions was implicit. | [Time](06-time-complexity.md) includes condition/helper/I/O work, attainable versus upper branch bounds, two exact power-of-two recurrences, rounded sizes, copied-half costs and Θ(1+n+nm) for direct nested-loop total work. |
| C07 | Space ambiguity: newly returned data was not classified; “up to n” queue occupancy was labelled Θ(n); recursive binary-search stack was not consistently labelled worst case. | [Space](07-space-complexity.md) declares output/workspace conventions, logical peak versus cumulative/JVM occupancy, retained frame buffers and the attainable-bound condition. Recursive search is worst Θ(log n), immediate-match Θ(1). |
| C08 | Amortized derivation errors/gaps: stated append costs mixed capacity with copy-plus-write costs; empty initial state and prefix restrictions were unclear; the potential equation omitted its upper-bound condition. | [Amortized Analysis](08-amortized-analysis.md) supplies exact costs 1,2,3,1,5,1,1,1,9; every-prefix geometric/credit/potential bounds; an arbitrary-initial-state term; and a zero-counter bit-flip sum. |
| C09 | Overgeneral reference claims: heapsort best Θ(n log n) ignored duplicate keys/early stopping; graph Θ(V+E) omitted full-versus-single-source and representation; average sorting tables did not name a distribution. | [Examples](09-complexity-examples.md) states full adjacency-list and reachable-edge costs, always-merge assumptions and explicit probability models. [Cheat Sheet](10-complexity-cheat-sheet.md) corrects heapsort's best-case claim, constant-space assumption and memory-column labels. |
| C10 | Navigation / attribution defects: linear-search source/test/JMH paths pointed into Arrays; README's advertised examples differed from the actual seven examples; footer fragments and empty fences added noise. | [README](README.md) uses real Searching paths and numbered overview links, lists the actual examples and links this report. All ten notes distinguish the consulted third edition from the preferred bibliography; stray fragments are removed. |

These C-identifiers belong to this review. They do not replace Foundations F01-F08
or Searching S01-S08. Applied documentation corrections are not claims of new
production features or re-executed research.

## Key derivations and negative controls

- For `3n^2+7n+4`, the bounds `3n^2 <= f(n) <= 14n^2` hold for n>=1.
  An alternating ratio can still have a Θ bound. Conversely, q(n)=n on even
  n and 1 on odd n is in O(n), not Ω(n), and not o(n); a loose O bound does
  not alone prove little o.
- For first-occurrence search, one unique target uniformly distributed among
  n>=1 positions gives (n+1)/2 comparisons. Adding success probability p gives
  p*(n+1)/2+(1-p)*n. An all-matching array gives one comparison; it is not that
  unique-target distribution.
- Safe doubling executes ceil(log2(n)) bodies for n>=1; non-negative integer
  halving executes floor(log2(n)). Their control cost is still constant at
  zero/one. Inclusive lower-midpoint binary search has at most floor(log2(n))+1
  probes; individual Java comparisons are a different operation count.
- For the empty capacity-one append-only model, final capacity k is the least
  power of two at least m>=1. Copies are k-1 and total writes/copies m+k-1<3m.
  Potential Φ(s,k)=2s-k+1 starts at zero, stays non-negative and gives charge 3
  per append. The +1 is an explicit initial-state normalisation. Starting with
  an arbitrary full N-element table instead requires retaining an O(N) term.
- A difference formula without a potential boundary condition is insufficient:
  actual cost one and Φ(D_i)=-i give zero artificial charges, but actual total m.
- Early-stopping sift-down on all-equal keys examines at most two children per
  call and performs no downward swaps. Bottom-up heap construction plus n-1
  extractions is Θ(n) total work in that model, refuting the unconditional
  best-case table. This does not provide a best-case result for every heapsort
  variant or re-prove the distinct-key exercise.
- A source isolated from a dense unreachable component scans none of that
  component's edges. V-sized initialisation plus reached adjacency entries is
  Θ(V+E_R), whereas a full adjacency-list forest scans all entries. Graph
  construction is excluded from these traversal-only bounds.

## Source correspondence

The pass inspected current source alongside the teaching models:

| Source | Correspondence and boundary |
|---|---|
| [LinearSearch](../../src/main/java/org/anaalvarezdev/algorithms/searching/LinearSearch.java) | Stops at the first equal element; duplicates change the first-match distribution. |
| [BinarySearch](../../src/main/java/org/anaalvarezdev/algorithms/searching/BinarySearch.java) | Inclusive index interval, lower midpoint, no subarray copies or sortedness scan. |
| [RecursiveBinarySearch](../../src/main/java/org/anaalvarezdev/algorithms/searching/RecursiveBinarySearch.java) | Index-range recursion; immediate return at the first midpoint and logarithmic worst active depth. |
| [BubbleSort](../../src/main/java/org/anaalvarezdev/algorithms/sorting/BubbleSort.java) | Adjacent strict swaps and early exit; source already states the distinct-key probability model. |
| [MergeSort](../../src/main/java/org/anaalvarezdev/algorithms/sorting/MergeSort.java) | One reusable linear buffer, rounded halves, no ordered-halves shortcut. |
| [AdjacencyListGraph](../../src/main/java/org/anaalvarezdev/algorithms/graphs/AdjacencyListGraph.java) | Iterator walks existing rows rather than copying adjacency data. Construction normalisation is separate. |
| [BreadthFirstSearch](../../src/main/java/org/anaalvarezdev/algorithms/graphs/BreadthFirstSearch.java) | Single-source traversal, V-sized initialised state and only reached adjacency rows. |
| [ConnectedComponents](../../src/main/java/org/anaalvarezdev/algorithms/graphs/ConnectedComponents.java) | Starts each unvisited root on an undirected graph and walks all components. |

Heap sort and the exact capacity-one doubling array are reference models in
this pass, not newly added production classes. The existing Java test and JMH
paths linked from the README were checked for existence; no new test outcome
is inferred merely from reading them.

## Validation and limits

A temporary independent Python model checked the formulas and controls below.
No script, raw output, JSON, log or CSV is added to the repository.

| Check | Bounded domain | Outcome |
|---|---|---|
| Concrete polynomial/linearithmic witnesses | n=1..4096, linearithmic from 2 | Pass; universal inequalities are also derived in the notes |
| Doubling/halving counts and corrected range | n=0..4096 plus four bounds around 2^30 and int maximum | 4101 limits pass |
| Old int arithmetic negative control | n=2^30+1, 35 simulated updates under JLS multiplication semantics | Reaches -2^31, then repeated zero; non-progress reproduced |
| Triangle and unordered-pair counts | n=0..128 | 129 sizes each pass |
| Linear-search expectations | n=1..128; four success probabilities | 128 successful means and 512 mixtures pass |
| Inclusive binary-search maximum probes | All successful targets and two outside-range absent targets for n=1..256 | Maximum matches bit length and is attained by the last target |
| Append aggregate, credit and shifted potential | Every prefix of 4096 appends | Exact first nine costs; geometric bound and charge 3 pass |
| Zero-initialised counter | Widths 1..12, every prefix before overflow | 8178 prefixes match the flip sum and bound |
| Exact halving/merge recurrences | 13 power-of-two sizes 1..4096 | k+1 and n*(k+1) match |
| Copied-half recurrence | n=2..4096 | 4095 values satisfy n<=T(n)<2n |
| Uniform distinct-key inversion expectations | All permutations of lengths 1..7 | 5913 permutations give n(n-1)/4 mean inversions |
| Equal-key iterative heapsort control | n=3..256 | 254 sizes have 3n-6 key comparisons in this translation; total work is linear |
| Isolated-source/dense-other-component model | V=3..64 | 62 counting examples retain the reachable/full distinction |

The scripts check independent mathematical translations, including a simulated
32-bit operation. They do not execute the Java project, time operations, prove
bounds from samples, or exhaust all int-array inputs. The finite interval checks
are complementary to the symbolic derivations. The heapsort control checks its
stated iterative model; the graph control counts model visits, not Java execution.
The review does not claim that every inherited external URL/anchor or every
optional textbook exercise has been verified.

Final audit: **286 Markdown files / 909 relative file targets / zero missing
destinations**. All **eight newly introduced local heading targets** resolve;
fenced-code balance and `git diff --check` pass. The change boundary is
**thirteen Markdown files only**. The inventory still has 100 production Java
files and 115 JMH methods. These checks do not constitute a new execution of
the existing JUnit/coverage/benchmark profiles.

## Next documentation review

Continue with **03 - Arrays**, examining each documented operation's contract,
proof, time/space model and correspondence to existing Java/test/JMH evidence.
That module has not been comprehensively re-reviewed by this change. Historical
Arrays aggregation overflow/provenance limitations stay documented and are not
replaced by these mathematical examples or a new artifact identity.

Benchmark reruns, additional production tests/features and CI enforcement
configuration remain separate work. This pass advances the ordered documentation
review; it does not reopen the completed numbered implementation sequence.
