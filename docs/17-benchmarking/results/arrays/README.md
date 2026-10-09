# Arrays: Empirical Research Report

## Algorithms Java Mastery

**Report prepared:** 2026-10-09  
**Evidence:** recorded means from 11 Arrays JMH templates  
**Status:** descriptive analysis of existing results; uncertainty and run metadata incomplete

## 1. Abstract

This report consolidates 105 recorded mean execution times from the Arrays
module. It examines full-array scans, copying, in-place reversal, positional
insertion and deletion, simultaneous minimum/maximum selection, duplicate
removal and prefix-sum preprocessing. Most recorded trends are compatible with
the operation counts derived from the implementations. The comparison between
pairwise and separate-pass minimum/maximum selection illustrates why fewer
comparisons need not produce a lower observed mean time. Prefix sums illustrate
the trade-off between preprocessing and repeated queries.

The templates retain means but no reported errors, iteration samples or complete
run headers. The analysis is therefore descriptive: it cannot establish
statistical significance, identify hardware causes, or prove asymptotic bounds.
A source audit also identifies an aggregation precondition violation and
state-reuse effects in mutating benchmarks. These limitations remain part of
the evidence and define follow-up work.

## 2. Evidence, Provenance and Scope

The author confirmed that the Arrays benchmarks had been executed and their
results recorded in the templates. This report extracts those recorded values;
it does not perform a new timing experiment.

The inspected repository snapshot is
[`e3339243913402d0b84559be121b2184643a7fca`](https://github.com/Ana-Alvarez-dev/Algorithms-Java-Mastery/commit/e3339243913402d0b84559be121b2184643a7fca).
This identifies the source inspected for extraction, not the unknown commit
used by the original benchmark executions. Their execution dates are also
unknown; the report date must not be treated as a measurement date.

The [complete extracted dataset](arrays-template-results.csv) contains:

- 11 benchmark classes and 15 benchmark methods;
- 105 unique method/scenario/size combinations;
- five sizes: 100, 1,000, 10,000, 100,000 and 1,000,000;
- means in `ns/op`, with `avgt` matching the source annotations;
- the source path and inspected commit for each value.

The error and measurement-count fields are deliberately empty because those
values were not preserved in the result tables. Empty fields do not mean zero
error or zero measurements. This CSV is an extraction from source comments,
not an original JMH export. The extraction preserves the displayed decimal
precision, removing thousands separators for machine readability.

## 3. Research Questions and Theoretical Expectations

| Question | Model derived from the implementations |
|---|---|
| How do complete scans and reversal scale? | Full scans perform linear work; reversal performs floor(n/2) swaps |
| Does copy strategy affect recorded time? | Both measured paths allocate a new array and copy n values |
| How does position affect insertion/deletion? | Insertion shifts n-i values; deletion shifts n-i-1 values |
| Does fewer comparisons mean lower time? | Pairwise min/max reduces comparison count while both strategies remain linear |
| Do duplicate distributions affect recorded time? | Every distribution is scanned; the number of writes differs |
| When can prefix preprocessing pay off? | Build is linear; each valid query uses constant work |

For insertion with capacity already available, use
`T_insert(n, i) = Theta(n - i + 1)`. For deletion, including the final clearing
operation, use `T_delete(n, i) = Theta(n - i)`. These models include constant
work when the operation targets the logical end. They do not include capacity
growth or allocation of a replacement array.

CLRS separates the operation-count model from particular hardware execution
costs [R1, Section 2.2]. Its pairwise min/max analysis motivates a more specific
comparison-count hypothesis [R1, Section 9.1]. The timing study complements
those models; it does not supply their proofs.

## 4. Experimental Design Reconstructed from Source

All templates use `Mode.AverageTime`, nanosecond output and thread-scoped state.
The source contains two annotation configurations:

| Template group | Warmup | Measurement | Forks |
|---|---|---|---:|
| Traversal, aggregation, minimum | 3 x 500 ms | 5 x 500 ms | 1 |
| Copy, reversal, maximum, min/max, insertion, deletion, prefix sums, duplicate removal | 5 x 1 s | 10 x 1 s | 2 |

These are source defaults, not verified effective settings of the original
runs. Runtime options can override annotations [R2]. A comparison between
unrelated classes should not assume identical execution conditions.

### Workload and Measurement Boundaries

- **Traversal:** the method passes `blackhole::consume` as a callback. Its
  measured payload includes per-element callback/result consumption, not only
  array access.
- **Aggregation:** ascending values `0..n-1` are summed using an `int`.
- **Minimum/maximum:** individual benchmarks use descending/ascending inputs,
  respectively. The comparative min/max benchmark uses `Random(42)` to generate
  the same reproducible input construction for its two methods.
- **Copy:** manual and library paths both allocate a destination inside the
  timed method. The comparison includes allocation as well as copying;
  `System.arraycopy` is not measured in isolation [R3].
- **Reversal:** the same array is reversed repeatedly, alternating its order.
- **Insertion/deletion:** each trial prepares one array. Invocations reuse it
  while continuing to pass the original logical size; they do not model a
  growing or shrinking collection across calls. Repeated shifts change values.
- **Duplicate removal:** an invocation-level fixture copies a sorted source
  into a working array before each removal. This restores each distribution,
  but copying changes the memory state and invocation-level timing has its own
  measurement caveats [R4].
- **Prefix sums:** the prefix is prepared in trial setup for query benchmarks;
  construction is measured separately. Every query covers the full fixed range
  `[0, n-1]`, rather than randomly varying range lengths.

Results are consumed with JMH Blackhole. This is part of the design, not a
guarantee that every possible JVM optimisation or measurement bias is absent.

## 5. Recorded Results: Scans and Reversal

The following selected endpoints are means only. Between the two sizes, input
length increases by 1,000 times. Complete values are preserved in the CSV.

| Operation | n = 1,000, ns/op | n = 1,000,000, ns/op | Ratio of means |
|---|---:|---:|---:|
| Traversal | 136.340 | 173,251.665 | 1,270.73 |
| Reversal | 311.847 | 337,446.045 | 1,082.09 |
| Minimum | 167.196 | 169,009.064 | 1,010.84 |
| Maximum | 263.930 | 267,564.949 | 1,013.77 |

These increases are broadly compatible with linear work. Departures from exact
proportionality cannot be attributed to a particular cache, JIT or scheduling
effect from the preserved means alone. The rows represent different payloads
and inputs and should not be read as a universal ranking of scan algorithms.

### Aggregation Contract Audit

The aggregation template records 277,151.221 ns/op at one million elements.
However, its production contract requires the mathematical sum to fit in an
`int`. For the generated input, that sum is `n(n-1)/2`:

| n | Mathematical sum | Fits a Java int? |
|---:|---:|---|
| 10,000 | 49,995,000 | Yes |
| 100,000 | 4,999,950,000 | No |
| 1,000,000 | 499,999,500,000 | No |

Java integer arithmetic does not signal overflow automatically [R5]. The two
largest cases therefore time overflowing arithmetic outside the documented
contract. Their means remain preserved as historical observations, but cannot
be presented as measurements of correct mathematical summation for those
inputs. A future experiment should use contract-valid data or an explicitly
defined wider-sum operation and report new results separately.

## 6. Copy Strategy

At one million elements, the recorded means are:

| Method | Mean, ns/op |
|---|---:|
| Manual copy | 1,994,178.554 |
| New array plus System.arraycopy | 1,210,007.886 |

The manual mean is approximately 1.648 times the library-path mean. The library
path has the lower recorded mean at all five sizes. Without errors or samples,
this remains a descriptive comparison. Both paths still perform linear copying
and allocate a linear-sized result. The result does not demonstrate which JVM
optimisation or allocation behaviour produced the difference.

## 7. Positional Insertion and Deletion

At one million elements:

| Position | Insertion, ns/op | Deletion, ns/op |
|---|---:|---:|
| BEGINNING | 321,372.055 | 694,656.039 |
| MIDDLE | 134,187.915 | 189,389.996 |
| END | 1.838 | 1.391 |

Beginning and middle operations shift many elements. End operations require no
shifting. The small end means and larger shifting means align with the
position-sensitive cost model.

One recorded insertion value deserves special attention: `END`, n = 100, is
258.638 ns/op, whereas larger-size end means range from 1.583 to 2.684 ns/op.
This is an atypical recorded point. Without raw samples and errors, it cannot
be identified conclusively as noise, a transcription error or a runtime effect.
It is retained, not removed from the dataset.

Trial-only state preparation limits generalisation: repeated insertion fills
the shifted region with -1, while repeated deletion fills it with zero.
The loop bounds still dictate the same shift count for a given case, but these
measurements do not describe independent operations on fresh original arrays.
They also do not compare a maintained sequence of insertions with a maintained
sequence of deletions.

## 8. Minimum and Maximum: Comparisons Versus Time

For even n, the inspected pairwise implementation performs `3n/2 - 2` value
comparisons. Separate scans perform `2n - 2`. All measured sizes are even. The
pairwise strategy thus uses approximately 25% fewer comparisons at large n
[R1, Section 9.1], while both strategies remain linear.

| n | Pairwise, ns/op | Separate passes, ns/op |
|---:|---:|---:|
| 100 | 35.124 | 45.098 |
| 1,000 | 407.223 | 389.591 |
| 10,000 | 4,236.359 | 3,554.124 |
| 100,000 | 181,296.459 | 34,087.864 |
| 1,000,000 | 1,915,624.640 | 493,124.143 |

Pairwise has a lower mean at n = 100. Separate passes have lower means at every
larger size, with a pairwise/separate mean ratio of approximately 3.885 at one
million. The conspicuous increase for pairwise between 10,000 and 100,000
elements merits replication. Branch prediction and JIT behaviour are possible
hypotheses, not established causes. No statistical significance follows from
the means alone.

This comparison illustrates that an optimisation in a comparison-count model
does not guarantee lower elapsed time in a particular Java implementation.

## 9. Duplicate Distributions

At one million elements, means are 541,469.762 ns/op for `UNIQUE`,
924,959.431 for `PAIRS`, and 637,098.637 for `ALL_EQUAL`. `PAIRS` has the highest
recorded mean at every tested size. Each distribution still requires a scan of
the input, even though the number of writes differs.

The data support studying distribution-dependent observed costs. They do not
establish that branch prediction caused the differences. The invocation-level
copy restores the logical workload but affects memory state and measurement
mechanics; those effects require a controlled follow-up rather than inference
from a ranking [R4].

## 10. Prefix Sums and Preprocessing Cost

Let B be the recorded build mean, D the direct-query mean, P the prefix-query
mean and q the number of repeated queries on an unchanged input. A simplified
point-estimate model compares `qD` with `B + qP`. When `D > P`, preprocessing
has lower modelled total time if `q > B/(D-P)`.

| n | Build B, ns/op | Direct D, ns/op | Prefix P, ns/op | First integer q satisfying the model |
|---:|---:|---:|---:|---:|
| 100 | 227.298 | 1.385 | 1.890 | None: D <= P |
| 1,000 | 2,253.308 | 68.508 | 1.627 | 34 |
| 10,000 | 19,826.184 | 648.226 | 1.816 | 31 |
| 100,000 | 193,249.154 | 11,748.563 | 1.615 | 17 |
| 1,000,000 | 3,075,827.204 | 150,525.519 | 1.295 | 21 |

These thresholds are calculations from separate preserved means, not measured
end-to-end break-even points. They assume the same full-range workload,
unchanged data and additive costs, and have no uncertainty estimates. They
should guide a follow-up experiment rather than a general recommendation.
Preprocessing allocates `n+1` long values and must be updated or rebuilt when
the underlying data changes. A fast isolated prefix query does not eliminate
that construction, storage or update cost.

## 11. Threats to Validity and Follow-Up

| Limitation | Consequence | Follow-up |
|---|---|---|
| No errors or iteration samples | Precision and significance cannot be assessed | Preserve original JSON and full logs |
| Unknown run dates, source commits and hardware | Exact reproduction is not established | Record effective JMH/JVM configuration and machine metadata |
| Mixed source annotation settings | Cross-class comparisons may mix protocols | Define and record a common protocol where comparisons require it |
| Aggregation exceeds int-sum contract | Largest two cases measure an invalid mathematical-sum workload | Correct the experiment and retain a separate new dataset |
| Trial-only mutating state | Input evolves between operations | Specify repeated-state versus fresh-input workloads before redesigning |
| Invocation-level duplicate fixture | Setup changes memory state and measurement mechanics | Evaluate fixture effects with a controlled design |
| Fixed ranges and distributions | Results do not cover general workloads | Add justified varied-target/range/distribution experiments |
| Atypical means | Causes and stability are unknown | Replicate while retaining existing observations |

These are evidence limits, not reasons to discard the report or overwrite
historical values. Production implementations and tests are not changed by this
documentation contribution. No new benchmark execution is claimed here.

## 12. Conclusions

The existing Arrays evidence is sufficient for a documented exploratory study.
It illustrates linear scans, position-sensitive shifting, implementation-level
differences within the same complexity class and preprocessing/query trade-offs.
It also makes the boundary between theoretical operation counts and execution
time visible.

The report does not establish universal speed rankings or exact break-even
thresholds. The next empirical stage should repair contract-invalid inputs,
define state behaviour explicitly and preserve statistical and environment
metadata. Research documentation can proceed now using the findings and their
limits together.

## 13. References and Source Traceability

- **[R1]** Cormen, T. H.; Leiserson, C. E.; Rivest, R. L.; Stein, C. (2009).
  *Introduction to Algorithms*, third edition. MIT Press. Section 2.2,
  "Analyzing algorithms" (printed p. 23 onward), and Section 9.1,
  "Minimum and maximum" (printed pp. 214-215). Verified against the author's
  supplied PDF. The book supports the theoretical reasoning; it is not the
  source of any timing result.
- **[R2]** OpenJDK, JMH: [Warmup annotation](https://github.com/openjdk/jmh/blob/master/jmh-core/src/main/java/org/openjdk/jmh/annotations/Warmup.java),
  and the project's JMH 1.37 command-line help (`java -jar target/benchmarks.jar -h`).
- **[R3]** Oracle, Java SE 21:
  [System.arraycopy API](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/System.html#arraycopy(java.lang.Object,int,java.lang.Object,int,int)).
- **[R4]** OpenJDK, JMH:
  [JMHSample_07_FixtureLevelInvocation](https://github.com/openjdk/jmh/blob/master/jmh-samples/src/main/java/org/openjdk/jmh/samples/JMHSample_07_FixtureLevelInvocation.java).
- **[R5]** Oracle, Java Language Specification, Java SE 21:
  [Section 4.2.2, Integer Operations](https://docs.oracle.com/javase/specs/jls/se21/html/jls-4.html#jls-4.2.2).

The CSV identifies every source template and the audited snapshot. The
[benchmark source directory](../../../../src/jmh/java/org/anaalvarezdev/algorithms/arrays/)
and [production implementations](../../../../src/main/java/org/anaalvarezdev/algorithms/arrays/)
provide the experiment and algorithm definitions. Related concepts are covered
in [Arrays documentation](../../../03-arrays/README.md),
[benchmark design](../../07-benchmark-design.md) and
[result interpretation](../../09-result-interpretation.md).
