# Hash Table Complexity Analysis

> Contracts, proofs and executable evidence:
> [Implementation guide](implementation-and-benchmark-guide.md).

## Assumptions and Models

Let n denote live entries, c retained buckets/slots and u occupied-plus-deleted
slots. Hashing and equality costs are additional when user keys are expensive.
Expected cost requires a probability model of keys/hashes and suitable occupancy.
A deterministic hash spread does not establish uniform randomness.

Amortised analysis accounts for a sequence of operations, including occasional
rebuilds. It is distinct from average/expected analysis. The selected tables have
expected amortised constant insertion under hashing assumptions, not constant
worst-case insertion or bounded real-world latency.

## Selected Implementations

| Operation | Separate chaining | Linear probing |
|---|---|---|
| Get/remove expected | O(1+n/c) under simple uniform hashing | O(1) with suitable distribution and bounded used occupancy |
| Get/remove worst | O(n) | O(c), including tombstones |
| Put without rebuild worst | O(n) | O(c) |
| Rebuild | Θ(c+n), relinking cached-hash nodes | Expected O(c+n); worst O(c+n²) |
| Put over a sequence | Expected amortised O(1) | Expected amortised O(1) with growth/cleanup policy |
| Clear | Θ(c) | Θ(c) |
| Size/isEmpty/capacity | Θ(1) | Θ(1) |
| Retained storage | Θ(c+n) | Θ(c) |

The chained table grows beyond live load 0.75. The probing table grows beyond
live load 0.5 and cleans at used load 0.75. Removing entries preserves probe
paths with deletion markers, so current n/c alone cannot predict search cost.
Both retain capacity after deletion; c need not be proportional to current n.

A chained rebuild visits every bucket and relinks each entry once. A probing
rebuild visits old slots and reinserts each live entry; concentrated hashes can
make those insertions progressively longer. The sum can be quadratic. Merely
counting old entries does not justify worst-case O(n) rehashing.

Snapshots scan buckets/slots, then insert into a destination HashMap. Add that
map's insertion costs; no universally linear bound is claimed for arbitrary
adversarial keys. Array capacity and allocation overhead also remain relevant.

## Engineering Interpretation

Constant expected operation counts do not imply equal timings across data
structures. The selected JMH experiment compares matched population and hash
fixtures, with growth excluded. Result and Interpretation remain pending an
actual measurement run; smoke results establish harness execution only.

Memory footprint, cache effects, allocation rate and GC behaviour require their
own measurements. Mathematical bounds derive from algorithms and assumptions,
not from a table of nanoseconds.
