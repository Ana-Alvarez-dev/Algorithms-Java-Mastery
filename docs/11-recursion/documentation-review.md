# Recursion Documentation Review

Reviewed on **2026-10-28 (America/Buenos_Aires)** from main
`6e8f87c1dcaae00d0d545d5ebb4ec539c48b33f7`, which merged the
[Graphs review](../10-graphs/documentation-review.md) through PR #108.

## Scope and conclusion

This ordered pass checks selected technical passages in all ten numbered notes:
contracts, helper specifications, base cases/traces, correctness, termination,
work, stack/heap costs, pattern classification and interview guidance. It reads
the module/guide/package entry points, all seven production Java files, eight
test sources and three JMH classes. The original module documentation exceeds
7300 lines; this is a scoped technical review, not an exhaustive editorial or
formal verification of every example and application.

Twelve findings connect the concepts to the existing implementation. Concrete
corrections include labelling factorial traces that stop at one, distinguishing
separate frames from shared arrays, completing a sorting reduction with merge,
replacing graph-depth progress with finite discovery, and removing an unsupported
Java design-motivation claim. Existing sound domain/cost restrictions are
consolidated in the [applied contract/proof map](implementation-contracts.md).

Only **sixteen Markdown files** change: ten notes, module README, guide, new
review/map, package README and project traceability review. Java algorithms,
tests, JMH bodies/templates, POM, CI and historical research files are unchanged.
Actual Maven/JUnit/AssertJ execution supplies Java evidence; no translated
algorithm or Python implementation supplies a Java test result.

## Primary sources inspected

The supplied local PDF is **CLRS, Introduction to Algorithms, Third Edition
(2009)**. Fourth Edition (2022) remains the preferred bibliography; it was not
directly inspected and its pagination is not substituted for this PDF.

| Selected source passages | Printed pages | One-based PDF pages | Use in this review |
|---|---|---|---|
| Section 4.3 substitution, base conditions and strengthened hypotheses | 83-85 | 104-106 | Exact inductive claims, boundary cases and the two-half constant-combination recurrence |
| Chapter 15 introduction and section 15.1 repeated subproblems / memoisation | 359-360, 364-365 | 380-381, 385-386 | Distinct states, saved answers, per-call table initialisation and bottom-up contrast |

The rod-cutting example motivates memoisation; it is not described as a
textbook implementation of this Fibonacci utility. The seven APIs, exact numeric
caps, helper counts, balanced pair recursion, peg plan and Java ownership rules
are repository choices/derivations from the inspected source.

Official Java SE 21 references checked:

- [JVM specification, abstract machine and frames](https://docs.oracle.com/javase/specs/jvms/se21/html/jvms-2.html): logical invocation model and implementation freedom, separate from physical frame/byte claims.
- [BigInteger](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/math/BigInteger.html): immutable integer operations with supported-range constraints; not unlimited feasible output or constant-cost multiplication.

No source consulted here documents a historical language-design motivation for
omitting mandatory tail-call optimisation. The revised note states the portable
contract boundary without inventing that explanation.

## Findings and applied corrections

| ID | Gap / source observation | Applied correction |
|---|---|---|
| RE01 | “Exactly the same problem” wording can exclude accumulator/interval/peg helper specifications. | Fundamentals, Correctness, Mistakes and map distinguish public contracts from strengthened helper hypotheses; accumulator a*n! is explicit. |
| RE02 | Several factorial diagrams stop at 1; depth example says five for input 5, while product/accumulate stop at 0. | Cases/Stack/map label the alternative convention; exact source trace reaches 0, with n+1 helper invocations/depth and n products. |
| RE03 | Independent frame language could imply independently owned objects. | Stack/Mistakes/map distinguish local slots from shared int[] and the per-public-call shared memo; no automatic clone or concurrent-input guarantee. |
| RE04 | Sorting reduction showed two sorted halves without the required merge; correctness prose needed resource qualification. | Correctness adds merge preserving multiplicities, precise helper postconditions and normal-result/termination/resource boundaries. |
| RE05 | “Graph depth” appeared as a well-founded decreasing measure. | Correctness uses finite undiscovered vertices plus visited-before-recursion and finite scans; cycles/path depth alone do not prove termination. |
| RE06 | Call count was equated with time and depth with total memory; tail position implied constant local work. | Complexity/map sum local costs, isolate source frame depth, include memo/numeric/output storage and qualify balancing/variable work. |
| RE07 | Tail-recursion note inferred a Java priority/design motivation from complete stack traces. | Tail note states specification/optimisation limits, removes unsupported causal history, and ties accumulator invariants to actual operations. |
| RE08 | Power's already-qualified BigInteger cost needed one complete source-to-proof/count map. | Map derives parity/loop invariant, one reused half, differing exact multiplication counts, output-bit growth and bounded-result MAX-exponent tests. |
| RE09 | Binary recursion said two calls for every invocation; pair semantics/short-circuit costs were scattered. | Patterns/Complexity/map use up to two non-base calls; mirrored-pair contract, first versus last mismatch and work/depth are explicit. |
| RE10 | Numeric/work/output caps and ownership needed a unified applied boundary. | Map connects exact long sums, widening-before-abs, F(92) stopping, independent memo calls, Hanoi record/factory distinction and exponential retained output; interview prompts reinforce them. |
| RE11 | General “slightly better” iterative performance language exceeded the selected experiment. | Iteration/map tie observations to actual timed work, different product/call/allocation counts, 24 selected cells and preserved uncertainty; no isolated call-cost claim. |
| RE12 | Current Windows instructions expected 600 tests, the original implementation total. | Guide/README label original evidence and link fresh 710/28 execution; package README/project audit extend the ordered chain without relabelling research. |

Identifiers are local to this review. Existing domain validation, balanced
recursion and benchmark design are documented, not newly implemented or optimised.

## Fresh verification in Java 21

JAVA_HOME selects temporary **Amazon Corretto 21.0.13.0.11**, OpenJDK
**21.0.13+11-LTS**, vendor Amazon.com Inc.; Maven Wrapper uses **Maven 3.9.11**,
with compiler release 21. This functional environment is separate from the
author's Oracle JDK 21.0.9 experiment and Windows installation.

An initial offline build could not resolve an uncached Maven plugin and ran no
tests. The subsequent normal online build used temporary session proxy settings
and the environment Java trust store; those operational files stay outside the
repo. The successful normal suite finished at **2026-10-28 23:50:27 -03:00**
(32.771 s); profile packaging at **23:51:19 -03:00** (31.824 s).

Core commands, with JAVA_HOME and temporary network/trust settings separately:

```bash
bash mvnw --batch-mode --no-transfer-progress -DfailIfNoTests=true test
bash mvnw --batch-mode --no-transfer-progress -Pbenchmark -DskipTests package
java -jar target/benchmarks.jar -l '^org\.anaalvarezdev\.algorithms\.recursion\..*'
java -jar target/benchmarks.jar -lp '^org\.anaalvarezdev\.algorithms\.recursion\..*'
```

Both discovery commands use the explicit Java 21 executable.

| Check | Actual result |
|---|---|
| Normal Maven/JUnit/AssertJ suite | **710 tests**, zero failures/errors/skips; BUILD SUCCESS |
| FactorialTest / FibonacciTest / PowerTest | **3 / 4 / 4** passed |
| ArraySumTest / PalindromeTest | **5 / 4** passed |
| GreatestCommonDivisorTest / HanoiTest | **3 / 5** passed |
| Recursion total | **28** actual JUnit invocations |
| Benchmark profile package | BUILD SUCCESS; executable benchmarks.jar generated |
| JMH method discovery | All **8** methods listed |
| Parameter discovery | Each method's three values listed, yielding **24** default cells: 9 factorial, 9 Fibonacci, 6 sum |

Surefire XML confirms the totals. Inner enumerated/seeded cases remain checks
within 28 tests: 21 accepted factorial inputs; every exact long Fibonacci input;
1105 small power cases; 1093 arrays/27064 valid sum slices; 128 seeded slices;
6561 small GCD pairs plus 5000 seeded pairs; and legal Hanoi simulations through
12 disks and at 16. Large sum/palindrome tests run on the actual Java classes.
The [map](implementation-contracts.md) links each test class and oracle.

Packaging skips further execution after the successful normal suite. It compiles
profile-only fixtures without claiming they were newly executed. JMH discovery
verifies registration/parameters, not timing or a new smoke/probe run. Historical
doclint/smoke/probe claims retain their original dates; none is rerun or used as
fresh evidence here. No fresh coverage percentage, profiler or memory figure
is produced. The shade manifest-overlap warning did not prevent BUILD SUCCESS.

## Final audit and preserved research

The closing audit checks **303 Markdown files / 1328 relative file targets**,
with no missing destinations, balanced code fences and passing
`git diff --check`. No new local heading-fragment links are introduced.
External URLs and inherited heading fragments are not exhaustively validated.
All sixteen changed paths are Markdown within the declared scope.

Inventory remains **100 production Java files / 115 JMH methods** repository-wide.
Recursion has **7 production files, 8 test sources, 28 declared/executed tests,
3 JMH classes and 8 methods**. No new JSON, CSV, log or binary file is included.
Generated Maven output remains ignored; temporary operational output is outside
the worktree.

The [first research report](../17-benchmarking/results/recursion/README.md)
retains its 24 means/errors and iteration evidence, author-reported source
`fe370a1953c089a55f8851399acc42a59e3d37b7`, JDK 21.0.9/JMH 1.37 and explicit
provenance limits. Its five high-error cells remain unchanged. Source counts
are not profiled events; mean orderings do not establish machine causes,
tail-call elimination, significance, universal rankings or asymptotic fits.

The original module build's 600 tests, fresh repository's 710 tests and author's
24-cell timing run are three distinct evidence statements. This pass closes
only the scoped **11 — Recursion documentation review**. Next in the ordered
sequence is **12 — Divide and Conquer**, after this review is integrated.
