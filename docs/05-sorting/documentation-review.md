# Sorting Documentation Review

Reviewed on **2026-10-28 (America/Buenos_Aires)** from main
`8d8a5beec76c97583d721c36ab3903aa1fd267b7`, the PR #102 merge of the
[Searching follow-up](../04-searching/documentation-review.md#ordered-documentation-review-follow-up-2026-10-28).

## Scope and conclusion

This pass checks the technical sections of the fourteen numbered notes and
module navigation, together with the existing implementation/benchmark guide,
five production classes, six test sources, two benchmark classes and their
input helper. It concentrates on current contracts, core loop/recursive
reasoning, selected comparison/property tables, Java-library statements and
the distinction between source, tests and historical measurement evidence.

It is not an exhaustive copyedit or formal audit of every paragraph, generic
example, optional optimisation, backend recommendation or teaching extension
in the long numbered notes. Twelve grouped findings below have scoped
documentation corrections; this does not certify all future implementations.

Concrete errors include Insertion Sort's shifted-region invariant including
the logical hole, a reverse-Lomuto trace saying the pivot is always smallest,
and list/library implementation descriptions stated as universal guarantees.
Other changes strengthen existing explanations and distinguish current
middle-pivot Hoare control from both-side Lomuto examples. The new
[contract/proof map](implementation-contracts.md) supplies a central applied
correspondence, including a quadratic-time/small-stack adversarial family.

Only twenty Markdown files change. Executable Java, tests, JMH bodies/defaults,
build files, CI and historical measurements/provenance are unchanged. No JSON,
log or CSV file is added. There is no new compilation, Java 21/JUnit/Maven suite,
coverage report or timed JMH execution in this pass.

## Consulted sources

The supplied PDF is **CLRS, Introduction to Algorithms, Third Edition (2009)**.
The preferred project bibliography remains **Fourth Edition (2022)**, which
was not directly inspected. Printed pages and one-based PDF positions differ:

| Passage checked | Printed pages | PDF pages | Review use |
|---|---|---|---|
| Section 2.1, invariant discussion | 18-20 | 39-41 | Sorted-prefix contract, initialisation/maintenance/termination; inner-hole formulation is this review's adaptation |
| Exercise 2.2-2 | 29 | 50 | Selection strategy, n-1 positions and requested best/worst analysis |
| Section 2.3.1 | 30-34 | 51-55 | Sorted-run merge, consumed-head invariant, rounded recursive halves |
| Problem 2-2 | 40 | 61 | Bubble adjacent exchanges, order plus permutation and requested invariants |
| Problem 7-1 | 185-186 | 206-207 | Hoare boundary, in-bounds scans and nontrivial split requirements |
| Problem 7-4 | 188 | 209 | Stack-depth question after eliminating one recursive branch |
| Section 8.1, decision tree / Theorem 8.1 | 191-193 | 212-214 | Distinct permutations, reachable leaves and worst-case comparison lower bound |

Exercises/problems are not represented as supplied completed solutions.
CLRS's Hoare example uses the first value and scans in a different order;
the repository uses a middle value and its own control structure. Its exact
proof, the reverse-constructed adversary, movement counts and shared-workspace
accounting below are repository derivations, not identical textbook pseudocode.
The textbook's mathematical infinity sentinel is not an int MAX_VALUE sentinel
in the current Java merge. No fourth-edition pagination is inferred.

Versioned primary references checked:

- [Java SE 21 Arrays API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Arrays.html): sort contracts versus implementation notes.
- [Java SE 21 List API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/List.html): stable sort, modifiability, default array/write-back behaviour and override boundary.
- [Java SE 21 Collections API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Collections.html): sort dispatch and public stability requirement.
- [Java SE 21 Comparator API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Comparator.html): sign, transitivity and equivalence consistency; comparison-zero need not equal equals.
- [JMH 1.37 per-invocation sorting sample](https://github.com/openjdk/jmh/blob/1.37/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_38_PerInvokeSetup.java): repeated sorted-input mistake and copying inside the measured payload versus invocation fixture overhead.

The Java 21 references clarify documentation. They are not evidence of the
library executed by the historical JDK 25 benchmark or a new profiling study.

## Findings and applied corrections

| ID | Evidence / gap | Applied correction |
|---|---|---|
| SO01 | The broad problem definition lacked an applied normal-result/failure/storage boundary for the five existing methods. | Fundamentals, Problem Definition and the contract map give the void/in-place-mutation int[] API, null message, empty cases, order plus multiplicities and resource/concurrency assumptions. Mutation is not constant-space evidence. |
| SO02 | Insertion's inner invariant included A[j+1], the logical hole, even at the initial header; “random” average claims were underspecified. | The inner proof uses unchanged prefix, shifted [j+2:i+1), saved key and excluded hole. The map derives exactly I shifts, n-1 key writes for n>=1 and Theta(1+n+I); the distinct uniform-permutation model supplies E[I]. |
| SO03 | Bubble's inversion/exchange relationship could be read as an inversion-sensitive total-work bound. | The note and map add [2,3,...,n,1]: n-1 inversions/swaps but n(n-1)/2 comparisons. The expected quadratic bound names its distribution and uses inversion lower/worst upper bounds. |
| SO04 | Selection's n-1 counts implicitly omitted n=0, and comparisons, swaps and writes needed separation. | The note records exact comparisons, max(0,n-1) swaps, zero sorted swaps, reverse floor(n/2) swaps and an attained n-1 family. The map states the suffix-minimum and outer multiplicity reasoning. |
| SO05 | Merge discussion mixed two-buffer/sentinel examples with current shared workspace; the recurrence ignored rounding, skip optimisation lacked a best-case distinction and primitive equal tests claimed stability evidence. | The note/map state ceil/floor recurrence, one buffer, exact cumulative copy/write counts versus peak storage, no current skip, and the alternative linear sorted best case with skips. Primitive tests are separated from tagged movement proofs. |
| SO06 | Quick's main final-pivot recurrence/count/invariant were Lomuto examples; reverse input falsely had an always-minimum last pivot. | A prominent variant boundary identifies current Hoare non-empty k/m-k children and proper boundaries. The reverse trace shows the subsequent maximum pivot, and the tail pseudocode is explicitly Lomuto. |
| SO07 | Generic comparison tables could assign linear worst stack to the actual smaller-side implementation; small stack could be mistaken for safe worst time. | The current-code rows state logarithmic worst stack. The applied proof and constructed family establish quadratic comparison work with helper depth two, without randomising the pivot or adding a benchmark. |
| SO08 | Probability/domain assumptions and the lower-bound statement needed precision; an O upper-bound symbol was used as the barrier. | Complexity and comparison notes distinguish current variants, uniform distinct-key expectations, n! decision-tree worst-case bounds and easy individual inputs. The map explicitly limits its expectation-proof scope. |
| SO09 | Deterministic repeatability was conflated with stable/unique-key ordering; comparator equivalence requirements were incomplete. | Properties separates same-input repeatability, stable tie preservation and canonical order across arrivals. Comparator sign/equivalence consistency and zero-versus-equals are explicit. Tagged counterexamples demonstrate movement mechanisms, not a new generic API. |
| SO10 | Framework text promised TimSort for all lists and generalised primitive algorithm/storage descriptions. | The note distinguishes versioned public guarantees, implementation notes, List.sort dispatch/default behaviour and input mutation from memory use. The historical runtime remains JDK 25.0.1. |
| SO11 | Testing examples overclaimed full-range/stability evidence; benchmarking language suggested automatic JVM control and treated allocation as an error despite its intentional inclusion. | Testing states finite-case and primitive-identity limits. Benchmarking qualifies warmup, payload, generator models, elementary size cap and harness limits. Existing 69-test/48-cell historical evidence is preserved, not rerun. |
| SO12 | Extension policies and navigation needed clarification: interval chaining, majority threshold, numeric widening/original positions, stale package and comparison-document names. | Interview Problems states those obligations without adding APIs. README uses anaalvarezdev and the actual comparison filename; both READMEs, guide and project audit link this dated review and map. |

These identifiers belong to this Sorting review. They do not replace Searching
S01-S16, Arrays A01-A11 or earlier Foundations/Complexity review identifiers.
Upper bounds written with O in inherited teaching tables are not reclassified
as false merely because a tight Theta bound is available.

## Independent bounded checks

A temporary Python model checks translations of all five movement strategies
against sorted output and multiplicity oracles. It also checks selected loop
invariants, decreasing intervals and counts. No model script/output is added
to the repository. These are not tests executing the Java implementation.

| Check | Bounded domain | Outcome |
|---|---|---|
| Five sorts, ordering/permutation and strategy invariants/counts | 3280 arrays, lengths 0..7, alphabet {-2,0,3}; 16400 sort cases | Pass; Selection comparisons/swaps, Bubble swaps=I, Insertion shifts=I and key writes, Merge copy/write recurrence, Hoare partition and stack bounds |
| Uniform-distinct inversion expectation | All permutations of lengths 1..7, 5913 permutations | Pass; summed inversions give n(n-1)/4 mean at every length |
| Low-inversion Bubble counterexample | 65 sizes: 2..64, 128 and 256 | Pass; n-1 swaps/shifts, quadratic Bubble comparisons |
| Current Hoare adversarial family | Same 65 sizes | Pass; n(n+1)/2+2n-4 pivot comparisons, n-2 swaps, n-1 partitions and maximum helper depth two |
| Tagged stable movement | 255 two-key arrays, lengths 0..7 | Pass for Bubble/Insertion/Merge models against stable order; explicit Selection/Quick instability examples also pass |
| Extreme/boundary sorting controls | Six int-extreme/empty/singleton arrays across five models, 30 cases | Pass; no subtraction-based comparison introduced |
| Property and scope controls | Multiplicity-versus-set, idempotent unordered identity, deterministic instability and stable non-canonical ties | Counterexamples expose the insufficient property |
| Matrix arithmetic from inspected source | 60 elementary +100 efficient defaults; 8 methods x2 sizes x3 distributions in supplied subset | 160 defaults and 48 historical cells are distinct |

Finite checks complement the stated arguments; they are not universal proofs
inferred from samples or measurements. Tagged models do not become object-
stability JUnit tests. Python arithmetic does not test Java exception/allocation
or concurrent behaviour. Current source uses relational key comparisons; the
mathematical count formulas are not evaluated as overflowing Java int products.

The Java source inventory has thirteen shared @Test methods inherited by five
concrete classes, plus four specialised methods: **69 historical invocations**.
The available host runtime is JDK 17. No Java 21 compilation, Maven/JUnit suite,
coverage or JMH run is claimed here. Earlier 2026-10-09 Java 21 verification in
the implementation guide remains dated evidence rather than a current result.

## Final audit and measurement boundary

The final path audit checks **291 Markdown files / 1072 relative file targets**
with **zero missing destinations**. All **three newly introduced local heading
targets** resolve. Fenced-code balance and `git diff --check` pass. This does
not claim validation of every inherited external URL or heading fragment.

The change boundary is **twenty Markdown files only**. The repository inventory
remains **100 production Java files / 115 JMH methods**. Sorting retains five
production classes, six test source files (17 declared test methods including
the inherited base), and three JMH source files (two benchmark classes, eight
methods and one fixture helper). Test, benchmark-result, build and CI paths
are outside the changed file set. These checks are not Java 21 execution.

The [Sorting research record](../17-benchmarking/results/sorting/README.md)
retains all 48 means/errors, 480 original iteration-average findings, uncertainty
and JDK 25.0.1 metadata. It does not record the exact execution commit/complete
command/CPU; the reviewed snapshot is not silently substituted for execution
identity. Original export hashes remain historical identifiers, not hashes of
a newly built jar. Removed JSON/log files are not recreated by this pass.

Current JMH source has two benchmark classes/eight methods and a separate
input helper. It measures clone/allocation plus sort, including Merge's buffer;
copyOnly is a separate control. Current defaults and one fixed seed per pair
do not establish unknown historical flags, causal JVM/hardware explanations,
asymptotic growth, object stability or stack occupancy. The constructed
adversary and mathematical counts above introduce no new timing observations.

## Next ordered review

Continue with **06 - Linked Lists**, preserving its implementation/test and
research history. This pass does not declare Linked Lists or later modules
re-reviewed, add optional sorting features or reopen the completed numbered
implementation sequence. Optional teaching variants and recommendations outside
the selected technical scope still need their own review if implemented.
