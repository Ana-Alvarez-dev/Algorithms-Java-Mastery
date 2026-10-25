# Master Theorem

## Algorithms Java Mastery

The [implementation guide](implementation-and-benchmark-guide.md) connects the
theorem to the reviewed Java sources. This document uses the **basic three-case
version** in CLRS Third Edition, Theorem 4.1, printed p. 94. Comparing vaguely
smaller or larger functions is insufficient: the required gaps and regularity
condition are part of the theorem.

## Domain and Form

Let a>=1 and b>1 be constants, with a nonnegative local-work function f(n),
constant positive base costs for sufficiently small n, and recurrence
T(n)=aT(n/b)+f(n). Standard integer floor/ceiling interpretations preserve
the stated asymptotic conclusions. Let p=log_b(a), so n^p is the comparison
function. The cases below concern sufficiently large n.

| Basic case | Required hypothesis | Conclusion |
|---|---|---|
| 1: polynomially smaller local work | f(n)=O(n^(p-epsilon)) for some constant epsilon>0 | T(n)=Theta(n^p) |
| 2: matching local work | f(n)=Theta(n^p) | T(n)=Theta(n^p log n) |
| 3: polynomially larger local work | f(n)=Omega(n^(p+epsilon)) for some constant epsilon>0, AND a*f(n/b)<=c*f(n) for some constant c<1 | T(n)=Theta(f(n)) |

Case three's inequality is the **regularity condition**. A merely larger f(n),
or a condition that holds only at isolated values, does not establish case
three. Case one also needs a polynomial gap, not just an asymptotic inequality.
The basic cases are not exhaustive; extended theorem versions must state their
own hypotheses rather than silently broadening this version.

## Worked Applications

| Recurrence with tight local work | Parameters / hypothesis | Basic case / solution |
|---|---|---|
| T(n)=T(n/2)+Theta(1) | a=1, b=2, p=0; f=Theta(n^0) | Case 2: Theta(log n), binary-search worst path |
| T(n)=2T(n/2)+Theta(n) | a=2, b=2, p=1; f=Theta(n) | Case 2: Theta(n log n), full merge / crossing scan |
| T(n)=2T(n/2)+Theta(1) | p=1; constant f is polynomially smaller | Case 1: Theta(n), balanced sum |
| T(n)=2T(n/2)+n^2 | p=1; epsilon=1, 2*(n/2)^2=n^2/2 | Case 3 with c=1/2: Theta(n^2) |

These are work bounds under the model, not elapsed-time predictions. Binary
search's best successful query may stop at the first middle value; the
logarithmic recurrence describes its worst path, not every invocation.

For actual odd-length merge/crossing scans, write children floor(n/2) and
ceil(n/2), constant-size bases and a tight linear local cost. The standard
rounding argument justifies the simplified balanced recurrence. No literal
half-element arrays or exact instruction counts are implied.

## A Gap Between the Basic Cases

Consider T(n)=2T(n/2)+n log n. Here p=1. The local term is larger than n, but
its ratio to n is log n, smaller than n^epsilon for every fixed epsilon>0.
It is therefore not polynomially larger; basic case three does not apply.
It also does not match basic case two's Theta(n) hypothesis.

A recursion tree resolves this example: for powers of two, aggregate internal
level work is proportional to n*(log2(n)-i). Summing the logarithmically many
levels gives Theta(n*(log n)^2). This is a separate derivation, not an
application of the stated basic three cases. Extended versions may cover it
after stating their additional log-factor hypotheses.

## Models Outside the Form

- T(n)=T(n-1)+Theta(n) uses a decrement, not a constant-fraction reduction.
- T(n)=T(sqrt(n))+Theta(1) has a different size transformation.
- T(n)=T(k)+T(n-k)+Theta(n) for variable Quicksort partitions does not fix
  equal child sizes; setting k=n/2 analyses only a balanced case.
- T(n)=T(m)+Theta(n) for deterministic selection needs control over the
  retained m; median-of-three does not force a fixed-fraction bound.

Substitution, recursion trees or an appropriate expectation analysis must
justify those cases. A recurrence with only upper-bound local work yields
only a corresponding upper bound unless a matching lower bound is established.
No empirical mean/error or fitted curve proves that the theorem's hypotheses
hold. Source invariants, work ledgers and tests remain distinct evidence.
