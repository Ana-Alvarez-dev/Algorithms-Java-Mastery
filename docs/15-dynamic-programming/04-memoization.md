# Memoisation

## Per-call dense storage and zero-valued answers

The [source map](implementation-contracts.md) records G=(n+1)(C+1) knapsack
cells or G=(m+1)(k+1) LCS cells, including empty boundaries. Each public memo
call fills the dense grid with -1 before any base/lookup return. A cached zero
is known, not missing. The abstract grid/call ledger does not measure physical
zeroing instructions, allocated bytes, GC behaviour or a reused warm cache.

Memoisation evaluates a well-founded recursive recurrence with per-state
storage. A cache hit avoids re-expanding the state, not the lookup itself.
Input context must remain fixed for the cache's lifetime.

## Selected Protocol

```text
solve(state):
    if base: return base answer
    if cache[state] is known: return it
    answer = combine(required smaller states)
    cache[state] = answer
    return answer
```

Knapsack keys (items,capacity); LCS keys (firstPrefix,secondPrefix). Both store
nonnegative answers, so -1 safely means unknown. Zero is a valid optimum for
unavailable items or disjoint Strings and must be cached as such. Base cases
return directly; the once-expanded statement concerns nonbase states.

## Concrete Costs

These implementations allocate/fill dense matrices before recursion. Full
initialisation costs O((n+1)(C+1)) for knapsack and O((m+1)(k+1)) for LCS, even
if only a chain/diagonal is expanded. Live recursion adds O(n) or O(m+k) depth.
Sparse maps would have different storage/lookup contracts and are not implemented.
The reused Fibonacci memo fills a fresh long array for every public invocation.

There is no cross-input/global warmed cache. Reusing one would alter measured
work and require a complete input identity/invalidation policy. Top-down order
is convenient for recurrence reasoning, but it does not guarantee less elapsed
time or safe unbounded recursion. Explicit work/depth caps are part of the APIs.

The [implementation and benchmark guide](implementation-and-benchmark-guide.md) links implementations, boundaries and benchmark controls. Next:
[tabulation](05-tabulation.md).
