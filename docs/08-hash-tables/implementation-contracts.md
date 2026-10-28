# Hash Tables: Java Contracts and Proof Map

Reviewed on **2026-10-28** against main
`60ec9de663589f37bfa81c6d3b1fcd82176bcdaf` (PR #105 merge).
The [review report](documentation-review.md) records the scoped corrections and
actual Java execution; the [implementation guide](implementation-and-benchmark-guide.md)
retains the original pseudocode, traces and historical measurements.

## Public API and key model

[HashTable](../../src/main/java/org/anaalvarezdev/algorithms/hashtables/HashTable.java)
is a selected educational API, not java.util.Map or Iterable. Its two
implementations reject null keys and values, permit repeated values, cache
live size and promise neither order, iterators, fail-fast behaviour nor thread
safety. They expose capacity but keep nodes and backing arrays private.

| Method | Observable contract |
|---|---|
| put(k,v) | New mapping returns null; equal-key replacement returns the old value, retains the original stored key reference and does not increase size or trigger growth/cleanup |
| get(k) / remove(k) | Value or null for absence; remove changes at most one mapping |
| containsKey(k) | get(k)!=null is sufficient because null values are forbidden |
| size / isEmpty / capacity | Cached live count / zero test / current retained bucket or slot count |
| clear | Empty mappings, reset markers, retain capacity |
| toMap | Independent mutable shallow HashMap snapshot; no ordering promise |

Null queries/writes throw NullPointerException. Both put implementations hash
the key, validate the value and only then search/mutate. Rejected null writes
do not add mappings or trigger growth; keys must obey their callback contract.
Snapshot membership is independent, but the key/value objects remain shared.
Mutating equality/hash-relevant state through either alias invalidates the
stable-key precondition. Unrelated key state need not be immutable.

Hash equality is only a filter: equal keys must have equal hashCode values,
but equal hashes do not establish equal keys. The selected spread
`h ^ (h >>> 16)` is deterministic and cannot separate identical complete hashes.
Masking with c-1 selects a valid home for positive power-of-two c, including
negative hashes and Integer.MIN_VALUE. Neither spreading nor masking proves
uniform random placement. Distinct raw hashes can share a home, and distinct
homes can occupy adjacent positions with long probe suffixes.

[Hashing](../../src/main/java/org/anaalvarezdev/algorithms/hashtables/Hashing.java)
requires requested capacity in [1,2^30], rounds to a power of two with minimum
four, and doubles supported capacities. The default is sixteen. grow at 2^30
throws IllegalStateException; invalid requests throw IllegalArgumentException.
A valid capacity is not a guarantee that physical allocation will succeed.
Ordinary replacement returns before capacity checks.

## Separate chaining: representation and preservation

[SeparateChainingHashTable](../../src/main/java/org/anaalvarezdev/algorithms/hashtables/SeparateChainingHashTable.java)
owns private singly linked bucket chains with cached spread hashes. Exactly n
nodes are reachable, every node has a non-null key/value, no equal key repeats,
each chain terminates, and a node belongs to `hash & (c-1)`. Live load is at
most 3/4. These conditions hold initially for the empty bucket array.

Put traverses the selected chain first. A cached-hash/equality match changes
only its value. If absent, growth occurs before n+1 would exceed 3c/4, then a
fresh node is prepended and size increases. Hence distinct-but-equal query
objects do not create duplicates or replace the stored key identity.

Remove tracks the preceding node while searching. On a match it repairs the
bucket head or predecessor.next and decrements size. The remaining chain order,
bucket membership and other mappings survive. Public remove receives a key,
so its cost includes lookup; it is not textbook constant unlink given a doubly
linked node handle. clear nulls every bucket and resets size, retaining c.

During resize, processed nodes have exactly one destination in the expanded
buckets and unprocessed nodes remain reachable through a saved successor.
The loop saves next **before** changing node.next. Moving one node preserves
this partition and decreases the unprocessed count. Exhaustion transfers every
node once, preserving key/value identities and n; bucket/iteration order is not
part of the contract. Cached hashes select new buckets without calling stored
keys' hashCode again.

## Linear probing: paths, markers and termination

[LinearProbingHashTable](../../src/main/java/org/anaalvarezdev/algorithms/hashtables/LinearProbingHashTable.java)
stores parallel keys, values, hashes and states arrays. Let u count OCCUPIED
plus DELETED slots. The invariants are:

- OCCUPIED has non-null key/value and a consistent cached hash. Every mapping
  is reachable from its home before an EMPTY slot, and no equal key repeats.
- EMPTY and DELETED have null key/value and zero cached hash; they differ by
  state, not by the presence of a leftover object reference.
- n counts live entries, u counts nonempty states, with 0<=n<=c/2 and n<=u<=3c/4.

Search checks an increasing prefix of the step-one cyclic probe sequence.
Each checked OCCUPIED slot either matches or is ruled out; DELETED is passed.
An EMPTY proves absence because insertion could not have skipped it along that
key's path. Deletion therefore preserves a DELETED marker: writing EMPTY could
make a surviving later key unreachable. If live size is zero, find returns
absence immediately even though deletion markers may remain.

The mask-based increment visits all c slots once. Both find and reusable have
an explicit c-probe limit, so termination does not depend on eventually seeing
EMPTY. The load invariants additionally ensure insertion has a nonoccupied slot.

Put completes duplicate search **before** selecting the first reusable slot.
At capacity eight with common home zero, insert A,B,C at 0,1,2, then remove B.
Updating C must pass DELETED at 1 and replace C at 2. A new D can reuse 1.
Stopping the replacement search at 1 would create a duplicate C.

| Transition | Live n | Used u | Capacity |
|---|---|---|---|
| Equal-key replacement | Unchanged | Unchanged | Unchanged |
| Insert into EMPTY | +1 | +1 | Unchanged after any rebuild |
| Insert into DELETED | +1 | Unchanged | Unchanged |
| Successful removal | -1 | Unchanged | Unchanged |
| Rebuild before the pending insertion | Unchanged | Reset to n | Double for growth; same for cleanup |
| Explicit clear | 0 | 0 | Retained |

After proving absence, put first grows if n+1>c/2; otherwise it cleans at the
same capacity if u>=3c/4. Removal never shrinks or automatically clears markers.
Rebuild scans old slots, reinserts exactly the OCCUPIED entries with cached
hashes and original references into fresh arrays, and publishes the arrays
after completion. The new probe placement establishes reachability, and all
old tombstones disappear. Duplicate-free input makes reusable sufficient here;
it does not need an equality search for each known-distinct entry.

## Costs, assumptions and sequence arguments

Bounds count reference/probe/pointer work and array initialisation; user hash
and equality costs must be added. They do not bound GC/allocation latency.
Expected costs require a probability model suitable for the strategy, not
just a label saying that the hash function is good. Chaining's simple uniform
hashing model and ideal arbitrary-permutation probing are different assumptions;
step-one linear probing is not arbitrary-permutation uniform hashing.

| Work | Chaining | Selected linear probing |
|---|---|---|
| get/remove/containsKey | Expected O(1+n/c); worst O(n+1) | Expected O(1) with suitable distribution/used occupancy; worst O(c) |
| put without rebuilding | Expected O(1) at bounded load; worst O(n+1) | Conditional expected O(1); worst O(c) |
| Growth/cleanup rebuild | Theta(c+n) | Conditional expected O(c+n); worst O(c+n²) |
| Empty construction / clear | Theta(c) | Theta(c) |
| Cached size/capacity/isEmpty | Theta(1) | Theta(1) |
| Snapshot source traversal | Theta(c+n), plus destination insertions | Theta(c), plus destination insertions |
| Retained storage | Theta(c+n) | Theta(c) |

Under suitable distribution, growth rebuild work is linear in capacity. Doubled
capacities form a geometric sum, giving expected O(c0+m) construction-plus-work
for m basic operations starting empty at initial capacity c0, apart from bulk
clear/snapshot calls. This is expected amortisation over sequences, not constant
worst-case put or a claim about every key distribution.

Probing also needs cleanup accounting. Immediately after cleanup u=n<=c/2.
To reach u=3c/4 again requires at least c/4 insertions into previously EMPTY
slots; removal and reuse of DELETED do not raise u. Charge the expected O(c)
cleanup to those intervening operations. Growth also leaves room, and explicit
clear resets u while paying its own Theta(c) work. Cleaning whenever tombstones
appeared near half live load would not supply this gap.

Under concentrated hashes, rebuild insertions can probe 1,2,...,n occupied-prefix
lengths: the sum is Theta(n²). Add old-slot scanning and new-array initialisation.
Current same-capacity/doubling policies make old/new capacities comparable, so
O(c+n²) covers this actual implementation. Geometric growth cannot convert
that adversarial rebuilding into constant worst-case amortised insertion.

Capacity persists after delete/clear. Small current n therefore does not imply
small construction, clear, source snapshot scan or marker-path costs. Snapshot
toMap additionally inserts into a destination HashMap; its collision/key work
cannot be omitted. Structure diagnostics allocate/search while checking all
entries and may be expensive under collisions; neither JMH nor ordinary API
methods invoke them as constant-time checks.

## Applied algorithms

All four reject null arrays and preserve input. Let N be array length and d
the number of distinct values processed. With suitable hashing, full scans have
expected O(N+1) time and O(d+1) table storage; adversarial distributions can
give O(N²+1) time. The chosen integer keys do not randomise input distributions.

| Java algorithm | Result / invariant | Java evidence |
|---|---|---|
| [FrequencyCounter.count](../../src/main/java/org/anaalvarezdev/algorithms/hashtables/FrequencyCounter.java) | After i elements each stored count equals its prefix frequency. Return an independent mutable frequency Map, empty for empty input; include snapshot work/output storage. | [FrequencyCounterTest](../../src/test/java/org/anaalvarezdev/algorithms/hashtables/FrequencyCounterTest.java) |
| [DuplicateDetector.hasDuplicates](../../src/main/java/org/anaalvarezdev/algorithms/hashtables/DuplicateDetector.java) | Seen table contains exactly the previous distinct values; return true at first repeated value, otherwise false. Early return can inspect only a prefix; empty/singleton false. | [DuplicateDetectorTest](../../src/test/java/org/anaalvarezdev/algorithms/hashtables/DuplicateDetectorTest.java) |
| [TwoSum.findIndices](../../src/main/java/org/anaalvarezdev/algorithms/hashtables/TwoSum.java) | Before right index j, table maps every prior value to its earliest index. Query before inserting ensures left<right; first successful j is earliest completion, and the stored index gives earliest compatible left. Return [left,right] or empty, not values/null. | [TwoSumTest](../../src/test/java/org/anaalvarezdev/algorithms/hashtables/TwoSumTest.java) |
| [FirstUniqueElement.firstIndex](../../src/main/java/org/anaalvarezdev/algorithms/hashtables/FirstUniqueElement.java) | Count first, then scan original order; first count-one value supplies its index. Empty/no unique gives -1. No dependence on table order. | [FirstUniqueElementTest](../../src/test/java/org/anaalvarezdev/algorithms/hashtables/FirstUniqueElementTest.java) |

TwoSum computes `(long)target - values[right]` and only queries a complement
inside the int range. Thus overflowing int addition cannot create a false
mathematical sum. [1,1,2], target 3 returns [0,2]; [3,3], target 6 returns [0,1].
All scans advance an input index or terminate early; all table probe loops
are bounded. Expected table-operation assumptions remain separate from these
correctness and termination arguments.

## Java test and research links

[HashTableContractTest](../../src/test/java/org/anaalvarezdev/algorithms/hashtables/HashTableContractTest.java)
is inherited by both concrete tables. It exercises returns/absence, nulls,
equal-key/value identity, independent snapshots, collisions, integer hash
extremes, growth and 2500 seeded operations per representation/hash mode against
HashMap. [Chaining tests](../../src/test/java/org/anaalvarezdev/algorithms/hashtables/SeparateChainingHashTableTest.java)
add deletion positions and replacement/growth thresholds. [Probing tests](../../src/test/java/org/anaalvarezdev/algorithms/hashtables/LinearProbingHashTableTest.java)
add wrapped paths, replacement past tombstones, slot reuse, fixed-capacity
cleanup, half-live growth and explicit clearing of an empty marked table.
[HashingTest](../../src/test/java/org/anaalvarezdev/algorithms/hashtables/HashingTest.java)
checks capacity limits arithmetically without allocating maximum-size tables.
Application oracles enumerate all 1093 arrays of length 0..6 over {-1,0,1};
TwoSum checks seven targets for 7651 array/target combinations. These are actual
Java tests and finite evidence, not proofs for all keys or resource failures.

[HashTableOperationsBenchmark](../../src/jmh/java/org/anaalvarezdev/algorithms/hashtables/HashTableOperationsBenchmark.java)
has nine methods, three sizes and two modes: 54 default configurations. Hit and
remove/restore cycle equal-but-distinct query objects with cursor work timed;
miss repeats one fixed absent key without that cursor. The pair restores
original stored references, count and mappings, not necessarily physical chain
or JDK bin order. Setup construction/boxing and growth/accumulated cleanup are
excluded. The Comparable fixture permits JDK comparison-based collision handling;
the manual tables are not JDK tree-bin implementations.

In DISTINCT_HASH, small IDs 0..N-1 have distinct but contiguous homes. After
remove(i), put's absence search crosses DELETED at i and the suffix to EMPTY
at N: N-i+1 visits. Over a complete cursor cycle the mean is (N+3)/2 visits,
not a nanosecond formula. reusable then restores i, so used does not accumulate.
The fixed absent key N instead starts at EMPTY. These paths explain why hit,
fixed miss and remove/restore are different workloads even without home collisions.

The [historical subset](../17-benchmarking/results/hash-tables/README.md) preserves
18 DISTINCT_HASH cells at sizes 100/1000 on Oracle JDK 21.0.9 and their original
source identity. The source-counter experiment in that report remains historical;
this review does not claim it was rerun. Fresh Java build/discovery produces no
additional timing, profiler, allocation-rate or heap-footprint conclusion.
