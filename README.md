<p align="center">
  <strong>English</strong> | <a href="README.es.md">Español</a>
</p>

# Algorithms Java Mastery

An academic **Java 21** project connecting algorithms and data structures with
formal specifications, correctness reasoning, complexity analysis, automated
tests and selected performance experiments. It develops foundations relevant
to backend engineering through executable, documented examples.

The main academic reference is *Introduction to Algorithms* (CLRS). Explanations,
contracts and implementations are developed for this repository; inspected
sources and edition-specific references are recorded in each review.

## Current scope and evidence

The selected implementation sequence and the ordered documentation reviews
through **19 — CI/CD** are complete within their recorded scope. The
[closing traceability review](docs/00-project/12-closing-traceability-review.md)
reconciles the nineteen merged reviews, current source and hosted evidence.

Verified snapshot: **2026-10-29**, at main `8d98012`.

| Evidence | Snapshot |
|---|---|
| Production Java sources | 100 files across 13 algorithm packages |
| Normal / coverage suite | 710 JUnit invocations |
| Benchmark-profile suite | 723 invocations, including 13 fixture checks |
| Linux shell contracts | 20 separate cases |
| JMH registry | 115 methods in 40 classes |
| Default parameter combinations | 803 cells; registration inventory, not 803 measured results |
| Hosted verification | Both jobs passed in [run 40880667469](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/actions/runs/40880667469) |

Repeated builds do not increase the distinct test inventory. Passing tests,
coverage, mathematical arguments and timings provide different evidence;
completion of the scoped review is not a universal correctness certification.

## Study and engineering method

1. Define the problem, input/output, preconditions and postconditions.
2. Identify valid boundaries, rejection policies and mutation/ownership rules.
3. Design the strategy and write language-independent pseudocode.
4. Trace representative cases manually.
5. Argue correctness and termination using invariants or induction.
6. Analyse time, auxiliary space, output costs and model assumptions.
7. Implement in Java and test the observable contract with JUnit and AssertJ.
8. Add JMH only for a justified empirical question; retain settings and uncertainty.
9. Link source, tests, experiments and technical conclusions.

See the [study methodology](docs/00-project/03-study-methodology.md) and
[project documentation](docs/00-project/README.md).

## Modules

Technical module documentation is in English.

| Module | Focus |
|---|---|
| [00 — Project](docs/00-project/README.md) | Methodology, architecture, standards and traceability |
| [01 — Foundations](docs/01-foundations/README.md) | Specifications, correctness, invariants and termination |
| [02 — Complexity](docs/02-complexity/README.md) | O, Ω, Θ; time/space and cost models |
| [03 — Arrays](docs/03-arrays/README.md) | Access, traversal, aggregation, updates and prefix sums |
| [04 — Searching](docs/04-searching/README.md) | Linear/binary search, boundaries, ranges and nearest values |
| [05 — Sorting](docs/05-sorting/README.md) | Elementary sorts, merge sort and quicksort |
| [06 — Linked Lists](docs/06-linked-lists/README.md) | Singly, doubly and circular linked structures |
| [07 — Stacks and Queues](docs/07-stacks-and-queues/README.md) | LIFO/FIFO/deque structures and applications |
| [08 — Hash Tables](docs/08-hash-tables/README.md) | Chaining, probing, collisions and applications |
| [09 — Trees](docs/09-trees/README.md) | BST, AVL, min-heaps, traversal and metrics |
| [10 — Graphs](docs/10-graphs/README.md) | Representations, BFS/DFS, ordering, Dijkstra and Kruskal |
| [11 — Recursion](docs/11-recursion/README.md) | Recursive contracts, exact arithmetic and iterative references |
| [12 — Divide and Conquer](docs/12-divide-and-conquer/README.md) | Maximum subarray, inversions and selection |
| [13 — Greedy](docs/13-greedy-algorithms/README.md) | Activities, interval partitioning, Huffman and coin-change counterexamples |
| [14 — Backtracking](docs/14-backtracking/README.md) | Permutations, combinations, N-Queens and subset decisions |
| [15 — Dynamic Programming](docs/15-dynamic-programming/README.md) | Fibonacci, 0/1 knapsack and UTF-16 LCS |
| [16 — Testing](docs/16-testing/README.md) | Regression cases, independent oracles and coverage |
| [17 — Benchmarking](docs/17-benchmarking/README.md) | JMH protocols, recorded results and interpretation |
| [18 — Linux](docs/18-linux-environment/README.md) | Environment, Bash, permissions, processes, Java, Maven and Git |
| [19 — CI/CD](docs/19-ci-cd/README.md) | Hosted verification, package preparation and artifact identity |

## Source layout and tools

| Path | Responsibility |
|---|---|
| `src/main/java` | Algorithms, data structures and supporting types |
| `src/test/java` | Functional JUnit/AssertJ tests |
| `src/jmh/java` | JMH benchmarks and trial fixtures |
| `src/jmh-test/java` | Fixture tests active only with the benchmark profile |
| `docs` | Specifications, proofs, analysis, reviews and research records |
| `scripts/linux` | Environment preflight and shell contract checks |
| `.github/workflows/maven-ci.yml` | Two-job Java 21 CI workflow |

The [POM](pom.xml) selects Java release 21, JUnit Jupiter 5.12.2, AssertJ 3.27.3,
JMH 1.37 and optional JaCoCo 0.8.13. The wrapper selects **Maven 3.9.11**;
a global Maven installation is unnecessary. First use may download Maven and
dependencies. Runtime selection and bytecode target are separate checks.

## Build and test

Use an installed **JDK 21**, with both `java` and `javac`, and align JAVA_HOME
and PATH. Start at the repository root. Run commands as checkpoints and stop
on failure. Windows remains supported; local WSL is not required for hosted CI.

### Windows PowerShell

```powershell
.\mvnw.cmd -version
.\mvnw.cmd -DfailIfNoTests=true clean verify
```

### Linux Bash

```bash
bash scripts/linux/check-environment.sh
bash scripts/linux/test-check-environment.sh
./mvnw -version
./mvnw -DfailIfNoTests=true clean verify
```

Maven must report Java 21. The normal suite has 710 invocations in the identified
snapshot. Linux setup details are in the [environment guide](docs/18-linux-environment/implementation-and-verification-guide.md).

## Coverage

The optional profile generates HTML/XML under ignored `target/site/jacoco`.

```powershell
.\mvnw.cmd -DfailIfNoTests=true -Pcoverage clean verify
```

```bash
./mvnw -DfailIfNoTests=true -Pcoverage clean verify
```

Open `target/site/jacoco/index.html` after that build. Coverage reports execution;
it does not prove assertion adequacy or all-path correctness. No percentage gate
is configured. See the [Testing evidence](docs/16-testing/verification-report.md).

## Benchmark preparation and discovery

The benchmark profile runs 723 tests and creates `target/benchmarks.jar`.
The commands below discover one method and its five sizes; they do not time it.
JAVA_HOME must select the intended JDK 21.

```powershell
.\mvnw.cmd -DfailIfNoTests=true -Pbenchmark clean package
& "$env:JAVA_HOME\bin\java.exe" -jar .\target\benchmarks.jar '^org\.anaalvarezdev\.algorithms\.arrays\.ArrayAggregationBenchmark\.sum$' -lp
```

```bash
./mvnw -DfailIfNoTests=true -Pbenchmark clean package
"$JAVA_HOME/bin/java" -jar target/benchmarks.jar '^org\.anaalvarezdev\.algorithms\.arrays\.ArrayAggregationBenchmark\.sum$' -lp
```

Follow the [benchmark guide](docs/17-benchmarking/implementation-and-benchmark-guide.md)
for a selected controlled run, source/state and jar-hash capture, effective
settings and separate profiler diagnostics. A later `clean` removes earlier
generated output, including the jar. Keep needed evidence before cleanup and
run coverage separately from performance measurement.

## CI and evidence limits

[GitHub Actions](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/actions/workflows/maven-ci.yml)
runs tests/coverage first, then a separate uninstrumented benchmark package with
provenance and discovery. It retains reports and package artifacts temporarily.
The [workflow map](docs/19-ci-cd/workflow-contracts.md) distinguishes implemented
gates from observed counts, uploads and repository enforcement settings.

Research records retain their original units, runtime, source attribution and
uncertainty. Some matrices cover subsets; old Arrays measurements have missing
metadata and two invalid int-sum inputs. Sorting's JDK 25 record stays separate
from Java 21. The corrected aggregation record retains its missing original
jar hash. See the [results index](docs/17-benchmarking/README.md) and
[closing limitations](docs/00-project/12-closing-traceability-review.md).

## AI assistance, references and changes

AI supports explanation, review, alternatives and technical writing. Academic
sources and official documentation remain primary; accepted reasoning and code
require human understanding and validation. Tests and measurements must be
executed, and AI output is not evidence by itself. Course-specific policies
apply to graded work.

Use the [central bibliography](docs/00-project/10-references.md),
[engineering standards](docs/00-project/08-engineering-standards.md) and
[development workflow](docs/00-project/09-development-workflow.md). Changes use
focused branches and PRs, with contract/test evidence and preserved research
history.

For contribution steps and verification requirements, see
[CONTRIBUTING.md](CONTRIBUTING.md). [CHANGELOG.md](CHANGELOG.md) summarises
meaningful changes with links to their history and evidence.

## Language policy and author

English is primary for code and technical documentation. This README and
[README.es.md](README.es.md) provide equivalent project entry points; update
both when shared scope, counters, commands or navigation change. Technical
module files and Java identifiers keep their existing English names.

**Ana María Alvarez** — Systems Engineering and Software Development student,
focused on backend engineering with Java.

GitHub: [@Ana-Alvarez-dev](https://github.com/Ana-Alvarez-dev)
