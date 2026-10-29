# Changelog

Meaningful changes to Algorithms Java Mastery are summarised here. This is a
selective retrospective of the engineering/review closure starting on
2026-10-27, checked against main `7dcb403791a8ed9c08333e84fd80b5424a19efd0`.
Earlier algorithm implementations and experiment records remain in
[Git history](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/commits/main/)
and the [traceability review](docs/00-project/11-traceability-review.md).
This file is not an exhaustive commit log or a release announcement.

Dated entries use the main merge's recorded date in **America/Buenos_Aires**.
The POM currently declares **1.0-SNAPSHOT**; that development identifier is not
treated here as a published 1.0 release.

## Unreleased

### Changed

- Expand the AI disclosure in both root READMEs, methodology, references and
  contribution guide: author direction, generated work, CLRS attribution,
  actual validation and continuing learning have distinct responsibilities.
- Record the integrated maintainer guides and successful identified main CI in
  the final scoped follow-up to the [closing review](docs/00-project/12-closing-traceability-review.md).

These entries describe the final documentation contribution; its merge date
and PR are not yet asserted. At integration, record the verified date/reference.

## 2026-10-29

### Added

- Populate [CONTRIBUTING.md](CONTRIBUTING.md) with contribution, validation,
  academic/AI integrity and experiment-evidence requirements; establish this
  changelog and link both guides from the root READMEs
  ([PR #120](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/pull/120)).
  Its merge also records the resolution of the two empty-file follow-ups.

### Changed

- Complete ordered documentation reviews for Recursion, Divide and Conquer,
  Greedy, Backtracking, Dynamic Programming, Testing, Benchmarking, Linux and
  CI/CD ([PRs #109–#117](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/compare/6e8f87c1dcaae00d0d545d5ebb4ec539c48b33f7...8d980122fc9e9ffcc9767789ff785542f723481b)).
  The [closure ledger](docs/00-project/12-closing-traceability-review.md) links
  each review and preserves its scoped findings and validation limits.
- Reconcile the nineteen merged reviews, identified hosted Java 21 evidence and
  source/test/benchmark inventory; reorganise the English root README and add
  its equivalent Spanish entry point
  ([PR #118](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/pull/118)).
- Centre the **English | Español** selector above the project title in both
  root READMEs, with the active language emphasised
  ([PR #119](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/pull/119)).

These documentation changes do not constitute new measured algorithm results;
historical measurements, runtime distinctions and missing metadata remain intact.

## 2026-10-28

### Added

- Integrate the initial scoped engineering closure
  ([PR #98](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/pull/98)).

### Changed

- Review Foundations, Complexity, Arrays, Searching, Sorting, Linked Lists,
  Stacks and Queues, Hash Tables, Trees and Graphs in order
  ([PRs #99–#108](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/compare/d70d8d1c61da6e22394edadaca84d50d4255cc84...6e8f87c1dcaae00d0d545d5ebb4ec539c48b33f7)).
  Clarify contracts, cost models, correctness arguments and empirical limits;
  retain each report's original execution type and date.

## 2026-10-27

### Added

- Record the initial Dynamic Programming research, with its observed results
  and limitations
  ([PR #91](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/pull/91)).
- Add optional JaCoCo coverage and strengthen Java contract regressions
  ([PR #92](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/pull/92)).
- Add an applied JMH protocol and benchmark-profile fixture tests
  ([PR #93](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/pull/93)).
- Transcribe corrected Arrays aggregation results and qualify their source
  attribution using the supplied reflog; retain the missing original jar hash
  ([PR #94](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/pull/94),
  [PR #95](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/pull/95)).
- Implement Linux environment preflight and a separate 20-case shell contract
  suite ([PR #96](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/pull/96)).
- Implement two-job Java 21 CI for coverage/testing, a separate benchmark
  package, provenance, discovery and temporary artifact retention
  ([PR #97](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/pull/97)).
  This adds verification and packaging, without an automated deployment or
  performance threshold.

### Fixed

- Correct closest-value search so equal nearest candidates respect the earliest
  original-index contract, and replace the aggregation benchmark's overflowing
  large ascending fixture with bounded values
  ([PR #92](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/pull/92)).
  Old timing records are preserved with their invalid-domain limitations.

## Maintaining this file

Add meaningful proposed changes under Unreleased, using Added, Changed, Fixed,
Removed or another accurate category as needed. At integration, record the
actual merge date and a verified reference; group related entries without
copying every commit. If a tagged release is introduced later, use its real tag
and release date rather than deriving a release from the Maven snapshot version.

Keep experimental means and uncertainty in their research reports, with source,
runtime and artifact identity. A documentation update, successful build or
discovery listing is not a new performance result. Follow
[CONTRIBUTING.md](CONTRIBUTING.md) for validation and evidence requirements.
