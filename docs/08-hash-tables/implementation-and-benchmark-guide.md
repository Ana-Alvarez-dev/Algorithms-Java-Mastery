# Hash Tables: Implementation and Benchmark Guide

## Selected Scope and Traceability

Package `org.anaalvarezdev.algorithms.hashtables` is mirrored in main, test and
JMH roots. These educational tables expose a selected API, not the entire
`java.util.Map` contract. Scope and contracts precede implementation.

| Module document | Executable responsibility | Verification |
|---|---|---|
| [Fundamentals](01-hashing-fundamentals.md), [hash functions](02-hash-functions.md) | `HashTable`, `Hashing` | Absence, equality/hash policies, negative hashes, capacity arithmetic |
| [Collisions](03-collisions.md), [chaining](04-separate-chaining.md) | `SeparateChainingHashTable` | Full colliding chains, replacement, head/interior/tail deletion, resize |
| [Open addressing](05-open-addressing.md) | `LinearProbingHashTable` | Wrapped probes, tombstones, update beyond deletion and bounded termination |
| [Load and resizing](06-load-factor-and-resizing.md) | Geometric growth, cached hashes and tombstone cleanup | Threshold boundaries, churn, reference identity and preserved mappings |
| [Java HashMap](07-java-hashmap.md) | Standard reference and deliberate API differences | Differential model tests; common non-null JMH workloads |
| [Complexity](08-complexity-analysis.md) | Conditional expected and worst-case bounds below | Analysis remains distinct from timing |
| [Applications](09-common-algorithms.md) | `FrequencyCounter`, `DuplicateDetector`, `TwoSum`, `FirstUniqueElement` | Simple independent oracles, duplicates, boundaries and nonmutation |
| [Mistakes](10-common-mistakes.md) | Stable-key requirement, load/used-slot distinction, absence contract | Seeded sequences and invariant diagnostics |

Quadratic/double probing, universal/perfect hashing, tree bins, concurrent maps,
custom sets, graph indexes and cache eviction are future extensions. Conceptual
discussion of them is not implementation evidence.

## Public Contract

- `put(key,value)` returns the previous value, or null for a new key. Equal keys
  replace the value without increasing size or forcing growth. The original
  stored key reference is retained.
- `get` and `remove` return null for absence; `containsKey` is explicit. Null
  keys and values are rejected with `NullPointerException` before mutation,
  including absent-key queries. Java HashMap permits null, so this is a deliberate
  difference. Duplicated values and distinct-but-equal keys are supported.
- Keys must obey equals/hashCode, with equal keys having equal hashes. Equality
  and hash-relevant state must remain stable while stored. Methods are not
  thread-safe, and no iterator, ordering or fail-fast contract is provided.
- `size`/`isEmpty` use cached counts; `capacity` reports current buckets/slots.
  `clear` empties and resets deletion markers while retaining capacity.
- `toMap` returns an independent mutable shallow HashMap snapshot. No ordering
  is promised. Values/keys themselves are not cloned; internal nodes/arrays
  are never exposed.
- Requested initial capacity must be in `[1, 2^30]`. It rounds up to a power of
  two with minimum four; default is sixteen. Growth doubles until the supported
  maximum, then fails with `IllegalStateException` when more capacity is needed.
  Physical allocation may fail earlier. Updates are still permitted at capacity.

The hash spread is `h ^ (h >>> 16)` and index is `spread & (capacity-1)`.
This accepts `Integer.MIN_VALUE` without unsafe absolute-value conversion.
The deterministic spread is not universal hashing and does not guarantee a
random distribution. User hash/equality costs must be added to the bounds.

## Strategies, Pseudocode and Traces

### Separate chaining

Each bucket owns a private singly linked chain. Entries cache the mixed hash.
Search checks cached hash and key equality. Put first searches for replacement;
an absent key is inserted at the bucket head, growing before the live load
would exceed 0.75. Remove keeps a predecessor and repairs head or next links.
These Map-like operations locate by key. A textbook deletion given a node
pointer has a different input contract; its constant-time bound is not borrowed.

```text
PUT(key,value): validate; hash key; find matching node
  if found: replace value and return old
  if new size exceeds 3/4 capacity: allocate larger buckets; relink cached hashes
  insert new node at bucket head; increment size; return absent
```

With three colliding keys A/B/C, the chain after head insertion is C/B/A.
Updating B changes only its value; removing B reconnects C to A. Removing C
repairs the bucket head, then removing A empties the bucket. Rehash visits every
old node once and chooses its bucket using the cached hash and new mask.

### Linear probing and deletion

Parallel key/value/hash/state arrays use EMPTY, OCCUPIED and DELETED states.
Every probe advances by one modulo capacity, with at most capacity probes.
Search stops at EMPTY, passes DELETED and compares only OCCUPIED slots.

```text
PUT(key,value): validate; find key along its complete search path
  if found: replace value; return old
  if new live size exceeds 1/2 capacity: grow and reinsert occupied entries
  else if used slots reach 3/4 capacity: rebuild at same capacity
  insert at first nonoccupied probe slot; update live/used counts; return absent
REMOVE(key): find key; clear key/value/hash; mark DELETED; decrement live size
```

Searching for replacement **before** reusing a tombstone avoids duplicate keys.
At capacity eight with a common home zero, A/B/C occupy 0/1/2. Remove B and
slot 1 becomes DELETED. Updating C must continue to 2; inserting a new D can
reuse 1. A missing-key search passes 1 and stops at the next EMPTY slot.

Live size stays at most capacity/2, while used counts both live and deleted
slots. Cleanup waits until used reaches 3/4 capacity; cleaning at the live
threshold could rebuild after every delete/insert pair near half occupancy.
The gap allows a linear number of tombstones between same-capacity cleanups.
Delete does not shrink or reset the whole array when the last key disappears.

## Correctness and Termination

The empty state satisfies zero live entries and null unused references. Every
chain node belongs to the bucket selected by its cached hash; each key is unique
and the total node count equals size. Removal changes exactly one link and count.
During relinking, processed nodes occupy correct new buckets and unprocessed
nodes remain reachable through the saved successor. The remaining node count
decreases, establishing termination.

In the probing table, each occupied entry is reachable from its home before
the first EMPTY slot. Deletion preserves that path with DELETED instead of
EMPTY. Every iteration extends the already-checked probe prefix by one slot;
the capacity bound guarantees termination even with markers. Rebuild reinserts
exactly the occupied entries with their original references and cached hashes,
then publishes all new arrays. States, live count and used count agree.

Package-private `hasValidStructure` diagnostics check these conditions and
cleared unused references in tests. They are not called by production operations
or JMH. They may be expensive under collisions; their cost is not included in
the ordinary API's complexity claims.

## Complexity and Assumptions

Let n be live entries, c retained capacity and u occupied-plus-deleted slots.
Hash/equality/reference work is assumed constant for this table. Expected bounds
require a suitable key/hash model and bounded occupancy; they do not follow
automatically from the deterministic spread. Linear probing's clustering must
not be analysed as though it generates arbitrary uniform random permutations.

| Work | Separate chaining | Linear probing |
|---|---|---|
| Get/remove | Expected O(1+n/c); worst O(n) | Expected O(1) under suitable distribution and bounded u/c; worst O(c) |
| Put without rebuild | Expected O(1); worst O(n) | Expected O(1); worst O(c) |
| Rebuild | Θ(c+n), relinking existing nodes | Expected O(c+n); worst O(c+n²) with concentrated collisions |
| Put across a sequence | Expected amortised O(1) under hashing assumptions | Expected amortised O(1), including cleanup, under hashing assumptions |
| Clear | Θ(c), releasing bucket heads | Θ(c), resetting arrays/markers |
| Size/isEmpty/capacity | Θ(1) | Θ(1) |
| Retained storage | Θ(c+n) | Θ(c) |

Capacity can remain large after deletion; replacing c by current n would give
incorrect sparse-table bounds. Snapshot traversal scans Θ(c+n) chain work or
Θ(c) slots, **plus** destination HashMap insertion costs; it is not guaranteed
linear under every adversarial equality/hash distribution.

Geometric growth bounds total expected reinsertion work across growing tables.
For probing, same-capacity cleanup needs at least a quarter-capacity gap between
live and used thresholds, allowing expected cleanup work to be charged to
intervening operations. Under concentrated collisions, reinsertion can scan
long clusters repeatedly; the worst-case quadratic rebuild is retained.

## Selected Application Contracts

All four reject null arrays and preserve their input. Expected O(n) time and
O(n) auxiliary storage rely on expected constant table operations; adversarial
hashing can make total time O(n²).

| Algorithm | Output and policy | Invariant / trace |
|---|---|---|
| FrequencyCounter.count | Mutable independent Map from each int to its count; empty input gives empty map | After i elements, counts equal frequencies in the processed prefix; `[2,1,2]` gives `{2:2,1:1}` |
| DuplicateDetector.hasDuplicates | True if any equal values repeat; empty/singleton false | Table contains exactly the previously seen values; `[2,1,2]` detects the second 2 |
| TwoSum.findIndices | First completing right index, then earliest compatible left index; two-element index array or empty for absence | Before j, table stores earliest index of each previous value; query before inserting ensures distinct indices; `[3,3]`, target 6 gives `[0,1]` |
| FirstUniqueElement.firstIndex | First index whose value occurs once, or -1 | First pass counts; second pass scans in original order; `[2,1,2,3]` gives index 1 |

TwoSum computes the complement in long and ignores complements outside int
range. It never treats int overflow as a valid mathematical sum. Loop indices
increase to array length; frequency/uniqueness loops and table probes terminate.

## Automated Verification

On **2026-10-19**, OpenJDK **21+35-2513** verification passed **465 repository
tests**, including **47 Hash Tables tests**:

| Concrete test class | Executed tests |
|---|---:|
| SeparateChainingHashTableTest | 14, including 12 shared contract tests |
| LinearProbingHashTableTest | 18, including the same 12 shared tests |
| HashingTest | 2 |
| FrequencyCounterTest / DuplicateDetectorTest / FirstUniqueElementTest | 3 each |
| TwoSumTest | 4 |

Shared tests run 2,500 seeded operations per table/hash mode against HashMap,
checking invariants before snapshots. They also cover null rejection, equal-key
identity, integer hash extremes, repeated growth and 100 colliding keys.
Specific tests cover wrapped probing, replacement past tombstones, slot reuse,
cleanup at fixed capacity, empty marked tables and load-threshold transitions.
The four algorithms are checked on all 1,093 arrays of length 0–6 over
`{-1,0,1}` with simple scanning/pairwise oracles; TwoSum uses seven targets for
7,651 array/target combinations. Overflow and mutation policies are explicit.

Normal Maven tests and benchmark-profile clean package passed. JMH listed nine
methods and completed all 18 short smoke cases at size 100 across both hash
modes. A separate probe checked all 54 method/size/mode combinations, including
cursor wrap, hits/misses, restored original references, counts, mappings and
invariants. JavaDoc passed doclint with missing-tag checks excluded. These are
functional/harness checks, not experimental performance conclusions.

## JMH Design and Evidence Protocol

`HashTableOperationsBenchmark` compares both manual tables and JDK HashMap:
cycling successful lookup, fixed absent lookup, and a remove/restore pair.
There are nine methods, sizes 100/1,000/10,000 and hash modes DISTINCT_HASH /
CONSTANT_HASH: **54 default configurations**. The initial subset uses two sizes
and DISTINCT_HASH: **18 configurations**.

Trial setup creates stable comparable keys 0..n-1 plus separate equal query
objects and an absent key n. All maps request the same power-of-two bucket/slot
capacity, at least 2n. In DISTINCT_HASH, these small integer hashes produce
distinct home positions for this fixture. CONSTANT_HASH forces every key into
one collision class. Neither fixture is a sampled real-world key distribution.
JDK HashMap may use comparison order in collision management; it is a library
reference, not a pure academic chain implementation.

Construction, key generation and initial boxing are excluded. Cycling/index
selection is timed for successful lookup and remove/restore. A pair restores
logical mappings, not necessarily physical chain/tree placement. It returns
the removed value. Node allocation/reinsertion work may be timed subject to JIT
optimisation; growth and accumulated tombstone cleanup are not this workload.
No memory, allocation-rate or isolated removal-latency claim is made.

Defaults: one thread, average ns/op, three 500 ms warmups, five 500 ms
measurements, two forks. The [first reported subset](../17-benchmarking/results/hash-tables/README.md)
now records all 18 DISTINCT_HASH configurations on Oracle JDK 21.0.9, with means,
errors, 180 measurement averages and 108 warmup averages. The execution SHA is
not supplied; reviewed merge `03e6ed5` is kept distinct from run provenance.
A smoke run only checks harness execution.

### Empirical finding and source trace

Probing remove/restore rises from 53.530 +/- 1.553 ns/op at n=100 to
360.300 +/- 4.195 ns/op at n=1,000. Distinct homes occupy consecutive slots
0..n-1. After removing i, `put` searches beyond the tombstone to the first EMPTY
slot n, visiting n-i+1 slots to rule out an existing equal key. `reusable` then
restores slot i. A full cursor cycle averages (n+3)/2 such search visits:
51.5 and 501.5. A temporary instrumented source copy confirmed these counts over
two cycles at both sizes. No production optimisation is introduced.

This deterministic probe-work explanation is separate from profiling exact time.
Distinct homes do not imply independent uniformly distributed positions or short
absence searches after deletion. The fixture preserves correctness and exposes
a workload limitation; conditional expected bounds still require their hashing
assumptions. The report retains all statistics, the full trace and limits.
CONSTANT_HASH and size 10,000 remain unmeasured in this initial subset.

From Windows PowerShell after merging and pulling main:

```powershell
.\mvnw.cmd -version
.\mvnw.cmd -Pbenchmark clean package
& "C:\Program Files\Java\jdk-21\bin\java.exe" -version
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar -l ".*hashtables.*"
```

The Java path is the author's verified installation; adapt it elsewhere.
The first smoke command is:

```powershell
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*hashtables.*" -p size=100 -p hashMode=DISTINCT_HASH,CONSTANT_HASH -wi 1 -w 50ms -i 1 -r 50ms -f 1 -t 1 -foe true
```

Then measure the initial subset:

```powershell
git rev-parse HEAD
& "C:\Program Files\Java\jdk-21\bin\java.exe" -jar target\benchmarks.jar ".*hashtables.*" -p size=100,1000 -p hashMode=DISTINCT_HASH -foe true
```

Record source SHA, actual JDK/JMH, command, header, full result table, errors,
completion marker and available CPU/OS metadata as text or captures. Preserve
them in Markdown without JSON/log artifacts. Later collision-mode runs stay
separate, retaining their own provenance. `release=21` does not select the JVM.
Linux uses `bash mvnw`, a selected JDK 21 and `target/benchmarks.jar`.

## Sources

The supplied CLRS **Third Edition (2009)** was consulted: §11.2, printed
pp. 256–261 (chaining and conditional expected analysis), §11.3 (hash functions),
and §11.4, pp. 269–276 (probing, deletion markers and hashing assumptions).
These are third-edition locations, not fourth-edition pagination. Thresholds,
cached Java hash spreading, snapshot/null policies and application tie rules
are independent repository decisions.

- [Java 21 HashMap](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/HashMap.html): null support, load policy, unspecified order and conditional basic-operation performance.
- [Java 21 Object.hashCode](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/lang/Object.html#hashCode()): equality/hash consistency required of keys.
