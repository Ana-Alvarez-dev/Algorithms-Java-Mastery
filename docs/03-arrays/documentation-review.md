# Arrays Documentation Review

Reviewed on **2026-10-28 (America/Buenos_Aires)** against main
`ee2383bbe83dd9539171454eeb182f81c150ff83`, the PR #100 merge of the
[Complexity review](../02-complexity/documentation-review.md).

## Scope and conclusion

This technical pass checks the eleven numbered Arrays notes and module README
against all thirteen current production classes, thirteen test files and eleven
JMH classes. It covers contracts, Java semantics, operation counts, selected
invariants, time/space models and the boundary between implementations and
teaching extensions. Eleven grouped findings have scoped corrections below.
This is not an exhaustive copyedit, a formal proof audit of every paragraph or
a new execution of the Java project.

Concrete problems included the prefix convention differing from the actual
length-n+1 API, omitted outer-loop work for empty matrix rows, and an
unconditional non-mutation promise in ArrayTraversal's Javadoc despite callback
aliasing. The new [implementation contract map](implementation-contracts.md)
connects all fourteen static operations to actual results, mutations, failures,
existing tests and cost models.

The change consists of fifteen Markdown files and one Java file whose changes
are comments only. Executable Java, tests, JMH fixtures, POM, scripts, CI and
historical research observations are unchanged. No new JSON, log or CSV file is
added. No Java compilation, Maven suite, coverage run or timed JMH run is claimed.

## Consulted sources

The supplied PDF is **CLRS, Introduction to Algorithms, Third Edition, MIT
Press, 2009**. Printed pages and one-based PDF positions differ:

| Source checked | Printed pages | PDF pages | Review use |
|---|---|---|---|
| Section 9.1 | 214-215 | 235-236 | n-1 comparisons for an extremum; simultaneous min/max by pairing and parity-specific counts |
| Section 10.1 | 232-233 | 253-254 | Array-backed stacks distinguish backing length, active contents and top; inactive cells need not be zero |

The preferred project bibliography remains **Fourth Edition (2022)**, which
was not directly inspected. Notes now distinguish that bibliography from the
edition actually consulted. CLRS's inactive stack slots do not impose this
repository's deletion policy of clearing the old last active slot to zero.

Versioned official primary sources were also read:

- [Java SE 21 JLS, chapter 10](https://docs.oracle.com/javase/specs/jls/se21/html/jls-10.html): fixed-length array objects, runtime component types, indexing and shallow cloning.
- [Java SE 21 JVMS, section 2.7](https://docs.oracle.com/javase/specs/jvms/se21/html/jvms-2.html#jvms-2.7): no prescribed internal object representation.
- [Java SE 21 Arrays API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Arrays.html): binarySearch's missing-key/duplicate conventions, copyOf truncation/padding and nested content comparison.
- [Java SE 21 JLS, section 4.12.5](https://docs.oracle.com/javase/specs/jls/se21/html/jls-4.html#jls-4.12.5): default field/component values versus local definite assignment.
- [Java SE 21 JLS, section 14.14.2](https://docs.oracle.com/javase/specs/jls/se21/html/jls-14.html#jls-14.14.2): enhanced-for variable assignment and iteration semantics.
- [Java SE 21 JLS, chapter 15](https://docs.oracle.com/javase/specs/jls/se21/html/jls-15.html): array creation failures (15.10.2), argument values assigned to fresh parameters (15.12.4.5), and low-order-bit integer addition on overflow (15.18.2).

The applied insertion/deletion invariants, callback examples, modular-sum
argument, prefix representation and workload formula are repository derivations
or descriptions of existing code, not claims of identical CLRS pseudocode.

## Findings and applied corrections

| ID | Classification / evidence before correction | Applied correction |
|---|---|---|
| A01 | Model ambiguity: classical contiguous storage could be read as a portable Java layout; shifting “up to n” elements was unconditionally labelled Theta(n). | [Fundamentals](01-array-fundamentals.md), [Memory Layout](02-memory-layout.md) and [Complexity](08-complexity-analysis.md) separate the word model from JVM representation and give Theta(1+k) for k moves, with linear worst cases when attained. |
| A02 | Java semantic omissions: reference copying, enhanced-for reassignment, runtime array type, local initialisation and library return conventions were easy to conflate. | [Java Arrays](03-java-arrays.md) gives concrete aliasing/covariance examples, pass-by-value behaviour, valid index types, copy depth, copyOf length behaviour, equals/deepEquals scope and the missing binary-search insertion-point convention. |
| A03 | Shape/cost error: matrix traversal omitted row-control work when rows were empty; zero/null/shared rows and output storage were insufficiently distinguished. | [Multidimensional Arrays](04-multidimensional-arrays.md) and [Traversal](06-traversal-patterns.md) state shape assumptions and Theta(1+R+sum L_i), or Theta(1+R+RC) for rectangular row traversal. Shared rows affect retained storage; materialised output is distinguished from workspace. |
| A04 | Contract gap: physical capacity and active logical length lacked a precise correspondence to current insertion/deletion methods. | [Operations](05-array-operations.md) and the contract map state validation order, original-state postconditions, decreasing variants, exact shifts, clearing policy and the caller's responsibility to retain the returned size. Zero is valid data, not an occupancy marker. |
| A05 | False unconditional source promise: traversal's postcondition said the input remained unchanged; callbacks can write through an alias, fail or retain output. | [Traversal](06-traversal-patterns.md) and ArrayTraversal Javadoc now qualify normal completion, then-current reads, callback costs/effects and lack of snapshot/rollback. The loop body is unchanged; eager null-action validation is not invented. |
| A06 | Numeric invariant error: an int accumulator need not equal the mathematical sum at every prefix, even when the final sum fits. Teaching mean also needed a division/domain distinction. | [Common Algorithms](07-common-algorithms.md) explains the modulo-2^32 invariant and final-range contract, with cancellation counterexamples; mean examples require non-empty input and floating division when intended. |
| A07 | Implementation mismatch: the teaching prefix convention used n entries and a special left-zero query, while PrefixSum returns n+1 entries with leading zero. General direct-query work was overstated as qn. | [Traversal](06-traversal-patterns.md) uses the actual long representation, build/query invariants, inclusive bounds, snapshot semantics, allocation limits and Theta(q+sum rangeLength) versus Theta(n+q). |
| A08 | Applied proof gap: pairwise min/max and sorted compaction lacked a central current-API explanation; physical tail contents could be mistaken for another valid sorted input. | The contract map proves membership/bounds, records parity-specific value-comparison counts, and explains the compaction read/write invariant, k-1 writes and unspecified tail. These counts do not establish a measured speedup. |
| A09 | Count/case ambiguity: reversal was described as involving half the positions rather than half as many swaps; search averages did not consistently identify target probabilities. | [Common Algorithms](07-common-algorithms.md) and [Complexity](08-complexity-analysis.md) give floor(n/2) swaps and the odd central slot, a stronger reversal invariant, unique-uniform successful search assumptions and worst-case recursive search space. |
| A10 | Teaching scope/case gaps: standalone examples could be mistaken for shipped APIs; early-exit duplicate checks, monotone pointers, negative sliding-window inputs and sentinel values needed qualifications. | [Guide](09-problem-solving-guide.md), [Mistakes](10-common-mistakes.md) and [Interview Notes](11-interview-notes.md) distinguish existing APIs from extensions and correct expected/worst/best labels. Traversal includes a negative-number counterexample; second-largest and rotation state absence/empty policies. |
| A11 | Traceability/attribution gap: current source defaults, preferred textbook edition and historical execution evidence needed explicit separation. | [README](README.md), note references and the project audit link this report, the contract map and both historical research records. No current fixture or rebuild is substituted for missing historical settings or artifact identity. |

These A-identifiers belong to this review; they do not replace Foundations F,
Complexity C or Searching S identifiers. The linked contract map records the
observed failure behaviour of existing methods, including implicit exceptions;
it does not claim explicit validation or test coverage for every invalid input.

## Independent checks and negative controls

A temporary Python model checked bounded mathematical translations. It did not
load Java classes, execute JUnit, profile a JVM or measure time. No script or raw
output is added to the repository.

| Check | Bounded domain / cases | Outcome |
|---|---|---|
| Copy, reversal, modular aggregation and prefix construction | 781 arrays, lengths 0..4, entries from {-2,-1,0,1,2} | Pass; copy independence, reversal invariant/swap count and prefix values agree |
| Minimum, maximum and pairwise comparison counts | 780 non-empty arrays in that domain | Pass; membership, bounds and odd/even counts agree |
| Indexed access/update | 2930 valid indexed cases | Pass; selected value/update and preservation of other slots agree |
| Insertion | 11133 cases across all valid positions and three inserted values, with non-zero spare slots | Pass; shift invariant, exact count, returned size, active result and untouched tail agree |
| Deletion | 2930 valid indexed cases | Pass; shift invariant, exact count, clearing and untouched suffix agree |
| Inclusive prefix queries | 7080 ranges | Pass; prefix difference equals direct mathematical sum |
| Sorted compaction | 1287 sorted arrays, lengths 0..8, same five-value alphabet | Pass; read/write invariant, unique prefix, comparison and write counts agree |
| Jagged traversal model | 341 shapes, 0..4 rows with lengths 0..3 | Pass; row visits persist when the cell-count sum is zero |

Additional controls include:

- [MAX_VALUE,1,-1] and [MIN_VALUE,-1,1]: intermediate int wrap but correct final mathematical result; two maxima/minima have out-of-range final sums and wrap.
- The mathematical n*2^31 bound is less than 2^62 for representable int-array lengths. This is a range proof, not an allocation experiment.
- A callback reading [1,2] changes the next slot to 99 and the next observed value is 99. A shallow outer copy also shares row mutation in the independent aliasing model; Java semantics are supported by the JLS source above.
- Sorted compaction of [1,1,1,2,2,3] returns 3 and leaves [1,2,3,2,2,3]. Reusing that full physical array as sorted input is invalid.
- For minimum-length sum-at-least-5, the usual non-negative sliding-window rule on [1,-1,5] reports length 3 while exhaustive ranges find length 1.
- Integer division of the sum of [1,2] yields 1 rather than the real average 1.5. MIN_VALUE can be an actual second-largest distinct value in [MIN_VALUE,0], so it cannot alone encode absence.

These finite checks complement the stated invariants and assumptions. They are
not universal proofs inferred from samples or newly passing Java tests. Existing
test sources contain **103 @Test methods in thirteen Arrays files**; reading
those assertions does not supply new execution or coverage evidence. Callback
mutation/exception and overflow-cancellation controls above are model checks,
not claims that corresponding new JUnit cases were added.

## Benchmark and historical-evidence boundary

Current Arrays JMH source has **eleven classes / fifteen benchmark methods**.
The bodies were inspected for measured work: copy/build allocate in the payload;
traversal includes callback consumption; reverse/insert/delete reuse trial state;
compaction restores its input per invocation; prefix queries use a fixed full
range. Fixed logical sizes in insertion/deletion do not model a dynamically
growing/shrinking collection. Source annotations do not establish historical
effective flags, environment safety or generated-code behaviour.

The [105 historical template means](../17-benchmarking/results/arrays/README.md)
retain their missing errors/settings/source-identity limitations, including the
two ascending aggregation inputs whose final sums violate the int contract.
The [five corrected aggregation observations](../17-benchmarking/results/arrays/aggregation-corrected-run.md)
remain a separate JDK 21.0.9/JMH 1.37 run. Their source identity is inferred from
the supplied history, and the original jar hash remains unavailable. This pass
does not recover that artifact, replace its identity with a rebuilt jar or make
a controlled old/new performance comparison.

## Final audit

The final path audit checks **288 Markdown files / 988 relative file targets**
with **zero missing destinations**. All **four newly introduced local heading
targets** resolve. Fenced-code balance and `git diff --check` pass. This does
not claim validation of every inherited external URL or heading fragment.

The change boundary is **fifteen Markdown files and one Java comment-only
change**. Removing Java comments/whitespace yields the same executable tokens
as the reviewed base. The inventory remains **100 production Java files and
115 JMH methods** across the repository. Test, benchmark, historical-result,
build and CI paths are outside the changed file set. These checks and the
independent models do not constitute Java 21, JUnit or JMH execution.

## Next documentation review

Continue with **04 - Searching**, respecting its existing S01-S08 corrections
and historical measurements. That follow-up should examine remaining technical
claims and current API/test/JMH correspondence. This pass does not declare
Searching or later modules comprehensively re-reviewed, add production features
or reopen the completed numbered implementation sequence.
