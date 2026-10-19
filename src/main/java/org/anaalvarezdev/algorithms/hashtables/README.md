# Hash Table Implementations

This Java 21 package implements the selected scope of
[module 08](../../../../../../../docs/08-hash-tables/README.md).
The [implementation guide](../../../../../../../docs/08-hash-tables/implementation-and-benchmark-guide.md)
connects contracts, pseudocode, traces, correctness, conditional costs, tests
and the experiment protocol.

| Type | Responsibility | Verification |
|---|---|---|
| `HashTable<K,V>` | Selected non-null associative API; no ordering or thread safety | Shared `HashTableContractTest` inherited by both implementations |
| `Hashing` | Capacity rounding/growth and deterministic hash spreading | `HashingTest`, negative/extreme-hash contract cases |
| `SeparateChainingHashTable<K,V>` | Private chains, replacement, keyed deletion, cached-hash relinking | 14 tests, including collisions, removals and growth thresholds |
| `LinearProbingHashTable<K,V>` | Bounded probes, deletion markers, separate live/used thresholds | 18 tests, including wrap, cleanup and update past tombstones |
| `FrequencyCounter` | Integer counts via manual chaining | Scanning oracle and result independence |
| `DuplicateDetector` | Repetition detection via manual probing | Pairwise oracle |
| `TwoSum` | Earliest-completing distinct index pair, overflow-safe complement | Ordered pairwise oracle and integer boundaries |
| `FirstUniqueElement` | First original index whose value occurs once | Frequency/scanning oracle |

Equal keys replace values without growing the table or replacing the stored key
reference. Null keys/values are rejected, making null results unambiguous absence.
Keys must keep equals/hashCode-relevant state stable. Snapshots are independent,
mutable, shallow HashMaps; no internal nodes or arrays are exposed.

Verification on 2026-10-19 with OpenJDK 21 passed **465 repository tests**,
including **47 module tests**. Nine JMH methods built and passed smoke checks in
both hash modes; a separate probe validated all 54 default method/size/mode
combinations. Result and Interpretation remain pending a measured author run.

Worst-case collision handling and probing rebuild costs remain explicit in the
guide. Smoke results do not establish expected or amortised bounds.
