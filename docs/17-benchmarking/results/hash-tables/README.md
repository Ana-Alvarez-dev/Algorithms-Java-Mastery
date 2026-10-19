# Hash Tables: First Reported Measurement Subset

The author supplied this complete console execution on **2026-10-19**. It
contains **18 configurations**, **180 measurement-iteration averages** and
**108 warmup iteration averages**, completed in **2 minutes 39 seconds**.
The benchmark template's Result and Interpretation now use this evidence.
All supplied numerical observations are retained below in Markdown; no JSON,
log or copied console-text artifact is versioned.

The main finding is specific to this fixture: distinct home hashes do not make
linear-probing reinsertion constant. After removal, the duplicate search passes
a tombstone and the remaining contiguous occupied suffix. The source trace
below explains the increasing probe work; it does not attribute nanoseconds
to CPU, allocation or cache effects.

## Provenance and Runtime

| Property | Supplied evidence or explicit limitation |
|---|---|
| Source | Author-pasted console output supplied as `Texto pegado(2).txt` |
| Date | Reporting date 2026-10-19; no execution timestamp in output |
| Execution source SHA | Not supplied; pending author confirmation |
| Reviewed source | `03e6ed5e5649b4018b47f054319e0495676b7fcf`, merge of PR #73; implementation commit `6808f47160e7c2f0d1b55f688036843f6c683407` |
| JMH | 1.37, present in all 18 headers |
| Runtime | JDK 21.0.9, Java HotSpot(TM) 64-Bit Server VM, 21.0.9+7-LTS-338 |
| Java invoker | `C:\Program Files\Java\jdk-21\bin\java.exe` |
| VM options | `<none>` |
| Blackhole mode | Compiler, auto-detected |
| Threads | 1, synchronised iterations |
| Warmup | 3 iterations per fork, 500 ms each |
| Measurement | 5 iterations per fork, 500 ms each |
| Forks | 2 per configuration |
| Mode and units | Average time (`avgt`), ns/op |
| Timeout | 10 minutes per iteration |
| Coverage | 9 methods, sizes 100 and 1,000, DISTINCT_HASH only |
| Measurement count | `Cnt = 10` iteration averages per cell, not ten individual invocations |
| Confidence output | 99.9% intervals, printed normal-distribution assumption |
| CPU and profiler metrics | Not supplied |
| Operating system | Windows executable path; Windows 11 amd64 reported separately during setup |
| Full command line | Initial pasted line is truncated; exact invocation is not captured |

The author reported pulling the preceding changes. Remote main confirms PR #73
is merged, but neither fact identifies the exact revision used to build the
measured jar. The reviewed SHA above is **not** substituted for the missing
execution SHA. A later `git rev-parse HEAD` identifies the run only if the
checkout and built source have remained unchanged since measurement.

Headers and coverage match the instructed protocol:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*hashtables.*" -p size=100,1000 -p hashMode=DISTINCT_HASH -foe true
```

This is the instructed command, not a recovered complete command line.
Decimal commas become dots; all printed observations retain three decimals.
These are rounded console iteration averages, not full-precision raw samples
or individual-operation latency distributions. Warmups remain separate.

## Timed Work and Analytical Model

The [implementation guide](../../../08-hash-tables/implementation-and-benchmark-guide.md)
connects contracts, traces, invariants, conditional complexity and tests.
The [benchmark source](../../../../src/jmh/java/org/anaalvarezdev/algorithms/hashtables/HashTableOperationsBenchmark.java)
defines nine methods across chaining, probing and JDK HashMap.

| Group | One timed invocation | Important restriction |
|---|---|---|
| Hit | Select the next equal-but-distinct query key and return its mapped value | Cursor/index selection is timed |
| Miss | Look up the same absent key n and return null | Fixed query has an empty home in DISTINCT_HASH |
| Remove/restore | Select key, remove, reinsert original stored key/value, return removed value | Pair cost, not isolated removal latency |

Trial setup creates keys 0..n-1 with their integer IDs as hashes and an absent
key n. The spread leaves these small hashes unchanged. Capacities are 256 for
n=100 and 2,048 for n=1,000; occupancy is approximately 39.1% and 48.8%.
Each resident key starts in its distinct home slot, but occupied homes form
one **contiguous block**. This is an ideal successful-lookup fixture, not a
randomly distributed workload. Distinct home positions do not imply independent
uniform hashing or short unsuccessful searches after deletion.

Construction, query generation and initial boxing are excluded. The pair
restores mappings, original references and count, with no retained tombstone
in probing. Node reinsertion/allocation sites are timed, subject to JIT
optimisation; no allocation rate is measured. Growth and accumulated-tombstone
cleanup are excluded. Fixed misses and cycling hits have different timed work,
so their difference is not an isolated hit-versus-miss penalty.

## Complete Reported Means and Errors

Each cell is **mean +/- JMH-reported error in ns/op**, `avgt`, `Cnt = 10`.
All cells use DISTINCT_HASH. Keep errors with their means.

| Method | Size 100 | Size 1,000 |
|---|---:|---:|
| `chainingHit` | 3.457 +/- 0.288 | 4.000 +/- 0.315 |
| `chainingMiss` | 1.332 +/- 0.107 | 1.380 +/- 0.206 |
| `chainingRemoveRestore` | 12.859 +/- 0.397 | 13.408 +/- 0.585 |
| `javaMapHit` | 3.217 +/- 0.041 | 3.791 +/- 0.052 |
| `javaMapMiss` | 1.468 +/- 0.076 | 1.489 +/- 0.095 |
| `javaMapRemoveRestore` | 15.593 +/- 1.025 | 15.783 +/- 0.637 |
| `probingHit` | 4.337 +/- 0.106 | 4.468 +/- 0.161 |
| `probingMiss` | 1.445 +/- 0.010 | 1.440 +/- 0.010 |
| `probingRemoveRestore` | 53.530 +/- 1.553 | 360.300 +/- 4.195 |

Successful-lookup means range from 3.217 to 4.468 ns/op, and fixed absent-query
means from 1.332 to 1.489 ns/op. Chaining and JDK remove/restore means are about
12.9–15.8 ns/op. Probing remove/restore grows from **53.530 +/- 1.553** to
**360.300 +/- 4.195 ns/op**, approximately **6.73 times** the mean for ten
times the population. This is a ratio of observed means, not an asymptotic fit
or a universal implementation ranking. No formal significance test is claimed.

## Why Probing Reinsertion Traverses the Occupied Suffix

This explanation comes from the reviewed source, distinct from a profiler claim.
`remove(i)` finds slot i and marks it DELETED. `put(i,value)` first calls `find`
to rule out an existing equal key beyond the tombstone. It cannot stop at a
DELETED slot: another displaced equal key could occur later under the general
API contract. Here `find` visits slot i, all occupied slots i+1..n-1, and the
EMPTY slot n. Its absence search therefore visits **n-i+1 slots**. Only then
`reusable` selects the tombstone at i in one visit and restores the entry.
The next invocation starts from the same logical and physical arrangement.

For a complete cursor cycle over i=0..n-1, this `put` search visits
**n(n+3)/2 slots**, averaging **(n+3)/2** per pair. The remove search and
`reusable` each add one slot visit. There is no rebuild, retained tombstone or
home collision required for this repeated long absence search.

| Population n | Capacity | First `put` search | Last `put` search | Total per cursor cycle | Average `put` search |
|---|---:|---:|---:|---:|---:|
| 100 | 256 | 101 | 2 | 5,150 | 51.5 slots |
| 1,000 | 2,048 | 1,001 | 2 | 501,500 | 501.5 slots |

A temporary diagnostic copy of `LinearProbingHashTable`, instrumented only with
a counter in `find`, checked every expected count for two full cycles at both
sizes using the same integer home hashes. Capacity, restored mappings, zero
retained tombstones and final structural invariants were checked. It reproduced
the totals above with OpenJDK 21 on the verification host. This was a source
trace check, not a new timed benchmark; instrumentation is not versioned.

For a small trace, n=4 at capacity eight occupies slots 0..3. Removing key 1
leaves OCCUPIED/DELETED/OCCUPIED/OCCUPIED/EMPTY at slots 0..4. The restoring
`find(1)` visits 1,2,3,4, then insertion reuses slot 1. Across all four keys the
search visits 5,4,3,2 slots, averaging 3.5, matching (4+3)/2.

This establishes linear average **probe work for this deterministic pair
fixture**, while ordinary hits still find their home in one probe. It does not
invalidate expected constant bounds under suitable random-distribution
assumptions: this fixture does not satisfy that model. It also does not explain
the exact measured ratio. JIT code, per-slot constants, hardware and runtime
variation were not isolated. Removing tombstone safety or stopping at DELETED
would violate the general correctness contract and is not justified by this run.

## Supplied Statistics and Derived Fork Means

All supplied min/max, standard deviations and 99.9% confidence bounds are in
**ns/op**. Min/max describe iteration averages, not individual operation latency.
The printed bounds are retained even where rounding differs by 0.001 from
arithmetic on the printed mean/error. Fork means are calculated from the five
rounded measurement averages and rounded to three decimals.

| Method | Size | Min | Max | Standard deviation | CI low | CI high | Fork 1 mean | Fork 2 mean |
|---|---:|---:|---:|---:|---:|---:|---:|---:|
| `chainingHit` | 100 | 3.253 | 3.881 | 0.191 | 3.169 | 3.745 | 3.307 | 3.607 |
| `chainingHit` | 1000 | 3.831 | 4.482 | 0.208 | 3.685 | 4.314 | 4.093 | 3.907 |
| `chainingMiss` | 100 | 1.302 | 1.533 | 0.071 | 1.225 | 1.439 | 1.311 | 1.352 |
| `chainingMiss` | 1000 | 1.300 | 1.743 | 0.136 | 1.173 | 1.586 | 1.449 | 1.310 |
| `chainingRemoveRestore` | 100 | 12.668 | 13.562 | 0.263 | 12.462 | 13.256 | 12.897 | 12.821 |
| `chainingRemoveRestore` | 1000 | 13.163 | 14.475 | 0.387 | 12.823 | 13.992 | 13.348 | 13.468 |
| `javaMapHit` | 100 | 3.182 | 3.278 | 0.027 | 3.176 | 3.258 | 3.217 | 3.217 |
| `javaMapHit` | 1000 | 3.756 | 3.865 | 0.035 | 3.739 | 3.843 | 3.802 | 3.780 |
| `javaMapMiss` | 100 | 1.431 | 1.598 | 0.050 | 1.392 | 1.545 | 1.492 | 1.445 |
| `javaMapMiss` | 1000 | 1.432 | 1.613 | 0.063 | 1.394 | 1.584 | 1.541 | 1.437 |
| `javaMapRemoveRestore` | 100 | 15.150 | 17.255 | 0.678 | 14.567 | 16.618 | 15.289 | 15.896 |
| `javaMapRemoveRestore` | 1000 | 15.417 | 16.852 | 0.421 | 15.147 | 16.420 | 15.649 | 15.917 |
| `probingHit` | 100 | 4.270 | 4.481 | 0.070 | 4.231 | 4.443 | 4.296 | 4.378 |
| `probingHit` | 1000 | 4.346 | 4.722 | 0.107 | 4.306 | 4.629 | 4.531 | 4.404 |
| `probingMiss` | 100 | 1.434 | 1.456 | 0.007 | 1.434 | 1.455 | 1.443 | 1.446 |
| `probingMiss` | 1000 | 1.428 | 1.450 | 0.007 | 1.430 | 1.451 | 1.439 | 1.442 |
| `probingRemoveRestore` | 100 | 52.693 | 56.174 | 1.027 | 51.977 | 55.082 | 54.010 | 53.049 |
| `probingRemoveRestore` | 1000 | 356.733 | 364.968 | 2.775 | 356.105 | 364.495 | 358.871 | 361.729 |

All 18 pooled means match their ten rounded iteration averages within 0.001
ns/op. All min/max values, result blocks, final table and configuration headers
agree. No observation was removed or replaced as an outlier.

## Complete Measurement-Iteration Evidence

The following **180 values** are measurement-iteration averages in **ns/op**.
Each timed iteration contains many invocations. They are not request-latency
percentiles or individual operation samples.

| Method | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 | Iteration 4 | Iteration 5 |
|---|---:|---:|---:|---:|---:|---:|---:|
| `chainingHit` | 100 | 1 | 3.253 | 3.348 | 3.365 | 3.282 | 3.289 |
| `chainingHit` | 100 | 2 | 3.881 | 3.555 | 3.527 | 3.532 | 3.540 |
| `chainingHit` | 1000 | 1 | 3.913 | 3.864 | 4.030 | 4.174 | 4.482 |
| `chainingHit` | 1000 | 2 | 4.120 | 3.831 | 3.846 | 3.883 | 3.857 |
| `chainingMiss` | 100 | 1 | 1.306 | 1.310 | 1.309 | 1.311 | 1.320 |
| `chainingMiss` | 100 | 2 | 1.533 | 1.308 | 1.302 | 1.302 | 1.317 |
| `chainingMiss` | 1000 | 1 | 1.743 | 1.460 | 1.344 | 1.320 | 1.378 |
| `chainingMiss` | 1000 | 2 | 1.314 | 1.307 | 1.314 | 1.300 | 1.315 |
| `chainingRemoveRestore` | 100 | 1 | 12.668 | 12.733 | 12.795 | 13.562 | 12.726 |
| `chainingRemoveRestore` | 100 | 2 | 12.762 | 12.795 | 12.817 | 12.727 | 13.004 |
| `chainingRemoveRestore` | 1000 | 1 | 13.350 | 13.414 | 13.308 | 13.233 | 13.433 |
| `chainingRemoveRestore` | 1000 | 2 | 14.475 | 13.163 | 13.312 | 13.166 | 13.224 |
| `javaMapHit` | 100 | 1 | 3.222 | 3.195 | 3.200 | 3.234 | 3.236 |
| `javaMapHit` | 100 | 2 | 3.182 | 3.205 | 3.205 | 3.278 | 3.215 |
| `javaMapHit` | 1000 | 1 | 3.795 | 3.756 | 3.865 | 3.784 | 3.808 |
| `javaMapHit` | 1000 | 2 | 3.762 | 3.827 | 3.785 | 3.758 | 3.770 |
| `javaMapMiss` | 100 | 1 | 1.435 | 1.598 | 1.468 | 1.452 | 1.505 |
| `javaMapMiss` | 100 | 2 | 1.444 | 1.443 | 1.431 | 1.469 | 1.439 |
| `javaMapMiss` | 1000 | 1 | 1.519 | 1.490 | 1.541 | 1.613 | 1.543 |
| `javaMapMiss` | 1000 | 2 | 1.432 | 1.438 | 1.437 | 1.442 | 1.434 |
| `javaMapRemoveRestore` | 100 | 1 | 15.215 | 15.389 | 15.455 | 15.234 | 15.150 |
| `javaMapRemoveRestore` | 100 | 2 | 15.263 | 15.319 | 16.350 | 17.255 | 15.295 |
| `javaMapRemoveRestore` | 1000 | 1 | 15.580 | 15.513 | 15.723 | 15.690 | 15.741 |
| `javaMapRemoveRestore` | 1000 | 2 | 15.740 | 15.481 | 16.094 | 16.852 | 15.417 |
| `probingHit` | 100 | 1 | 4.270 | 4.312 | 4.271 | 4.337 | 4.292 |
| `probingHit` | 100 | 2 | 4.294 | 4.343 | 4.435 | 4.481 | 4.337 |
| `probingHit` | 1000 | 1 | 4.722 | 4.493 | 4.436 | 4.487 | 4.519 |
| `probingHit` | 1000 | 2 | 4.377 | 4.391 | 4.497 | 4.408 | 4.346 |
| `probingMiss` | 100 | 1 | 1.443 | 1.448 | 1.445 | 1.434 | 1.447 |
| `probingMiss` | 100 | 2 | 1.456 | 1.438 | 1.436 | 1.450 | 1.449 |
| `probingMiss` | 1000 | 1 | 1.434 | 1.442 | 1.445 | 1.440 | 1.433 |
| `probingMiss` | 1000 | 2 | 1.446 | 1.440 | 1.444 | 1.450 | 1.428 |
| `probingRemoveRestore` | 100 | 1 | 56.174 | 53.948 | 53.119 | 52.941 | 53.868 |
| `probingRemoveRestore` | 100 | 2 | 53.592 | 53.014 | 53.209 | 52.739 | 52.693 |
| `probingRemoveRestore` | 1000 | 1 | 360.656 | 357.595 | 362.408 | 356.961 | 356.733 |
| `probingRemoveRestore` | 1000 | 2 | 361.164 | 359.489 | 359.589 | 364.968 | 363.436 |

## Complete Warmup Evidence

These **108 values**, in **ns/op**, are excluded from all measurement means.

| Method | Size | Fork | Iteration 1 | Iteration 2 | Iteration 3 |
|---|---:|---:|---:|---:|---:|
| `chainingHit` | 100 | 1 | 3.663 | 3.329 | 3.238 |
| `chainingHit` | 100 | 2 | 6.320 | 4.579 | 4.600 |
| `chainingHit` | 1000 | 1 | 4.182 | 3.982 | 3.870 |
| `chainingHit` | 1000 | 2 | 5.274 | 4.091 | 4.617 |
| `chainingMiss` | 100 | 1 | 1.321 | 1.347 | 1.316 |
| `chainingMiss` | 100 | 2 | 1.334 | 1.627 | 1.687 |
| `chainingMiss` | 1000 | 1 | 1.320 | 1.334 | 1.304 |
| `chainingMiss` | 1000 | 2 | 1.430 | 1.328 | 1.310 |
| `chainingRemoveRestore` | 100 | 1 | 14.184 | 13.345 | 12.825 |
| `chainingRemoveRestore` | 100 | 2 | 18.385 | 17.710 | 13.079 |
| `chainingRemoveRestore` | 1000 | 1 | 14.082 | 13.146 | 20.543 |
| `chainingRemoveRestore` | 1000 | 2 | 13.696 | 12.869 | 13.401 |
| `javaMapHit` | 100 | 1 | 3.850 | 3.806 | 3.196 |
| `javaMapHit` | 100 | 2 | 3.829 | 3.767 | 3.225 |
| `javaMapHit` | 1000 | 1 | 4.155 | 4.142 | 3.798 |
| `javaMapHit` | 1000 | 2 | 4.379 | 4.208 | 3.798 |
| `javaMapMiss` | 100 | 1 | 1.562 | 1.490 | 1.440 |
| `javaMapMiss` | 100 | 2 | 1.470 | 1.464 | 1.462 |
| `javaMapMiss` | 1000 | 1 | 1.490 | 1.452 | 1.486 |
| `javaMapMiss` | 1000 | 2 | 1.594 | 1.478 | 1.492 |
| `javaMapRemoveRestore` | 100 | 1 | 18.955 | 15.256 | 15.187 |
| `javaMapRemoveRestore` | 100 | 2 | 18.842 | 15.216 | 15.218 |
| `javaMapRemoveRestore` | 1000 | 1 | 17.922 | 15.690 | 17.350 |
| `javaMapRemoveRestore` | 1000 | 2 | 17.603 | 15.449 | 15.566 |
| `probingHit` | 100 | 1 | 4.641 | 4.608 | 4.301 |
| `probingHit` | 100 | 2 | 4.528 | 4.628 | 4.274 |
| `probingHit` | 1000 | 1 | 5.199 | 4.822 | 4.606 |
| `probingHit` | 1000 | 2 | 5.005 | 4.736 | 4.380 |
| `probingMiss` | 100 | 1 | 1.456 | 1.493 | 1.453 |
| `probingMiss` | 100 | 2 | 1.517 | 1.467 | 1.442 |
| `probingMiss` | 1000 | 1 | 1.456 | 1.465 | 1.454 |
| `probingMiss` | 1000 | 2 | 1.450 | 1.459 | 1.449 |
| `probingRemoveRestore` | 100 | 1 | 55.903 | 53.408 | 53.312 |
| `probingRemoveRestore` | 100 | 2 | 59.398 | 56.511 | 53.187 |
| `probingRemoveRestore` | 1000 | 1 | 395.925 | 365.637 | 360.746 |
| `probingRemoveRestore` | 1000 | 2 | 381.973 | 517.645 | 422.840 |

## Research Limits and Next Experiments

- Execution source SHA is missing. The reviewed PR #73 revision is known, but
  exact source-to-run identity remains unconfirmed. CPU model, power settings
  and an intact captured command line were not supplied.
- This subset measures **18 of 54 default configurations**. CONSTANT_HASH and
  size 10,000 remain unmeasured. The largest reported relative error here is
  about **14.9%**; uncertainty and systematic fixture effects remain
  distinct. Overlapping intervals are not a formal test, and narrower intervals
  do not validate a general workload model.
- DISTINCT_HASH means distinct home slots in this fixture, not uniform random
  placement. Live occupancy also differs between sizes (39.1% versus 48.8%).
  The probe-count derivation is specific to the contiguous occupied block.
- Fixed absent queries, repeated warm state, returned results, compiler
  blackholes and one thread restrict applicability. No thread safety, memory
  footprint, allocation rate, cold-start or individual latency claim follows.
- Pair costs cannot be labelled isolated remove times. No growth, accumulated
  cleanup or rehash latency is measured. Expected/amortised bounds still need
  their analytical assumptions; two sizes do not establish a scaling exponent.
- Correctness evidence remains the separate **465-test verification**, including
  **47 module tests**. This documentation update changes no executable Java.
  The source trace counter checks work counts, not performance causes.

Useful follow-ups are separate experiments: compare contiguous versus spaced
or seeded dispersed home slots at matched capacity/load; retain tombstones in
one controlled workload versus immediate restoration in another; add the
CONSTANT_HASH subset separately; and use justified profiler runs to investigate
allocation or generated code. Keep source identity, seeds, capacity and all
runtime settings with each run. These experiments have not been executed here.

The template can now use this subset as preliminary empirical evidence. Confirm
the execution SHA before treating its source-to-measurement link as complete.
No source optimisation or new module is implied by the timing table alone.

## Source Integrity

SHA-256 of the original supplied attachment bytes, including line endings:

`d8921f06c7b6a1d0b1db73041b167a4c7c21a59241aa03dcb50cea42a8529673`

This identifies the received evidence. Decimal normalisation and Markdown
layout retain every printed numerical observation. The text attachment itself
is not copied into the repository.
