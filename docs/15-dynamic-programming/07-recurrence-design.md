# Recurrence Design

Define state meaning before writing equations. Then partition all feasible
solutions into cases, prove each transition valid, and prove the cases complete.
A decreasing measure or topological dependency order establishes termination.

## Selected Recurrences

```text
F(0)=0; F(1)=1; F(i)=F(i-1)+F(i-2)

K(0,c)=0
K(i,c)=K(i-1,c)                           if w[i-1]>c
K(i,c)=max(K(i-1,c),v[i-1]+K(i-1,c-w[i-1])) otherwise

L(0,j)=L(i,0)=0
L(i,j)=1+L(i-1,j-1)                      if a[i-1]==b[j-1]
L(i,j)=max(L(i-1,j),L(i,j-1))             otherwise
```

Knapsack's take branch decreases item count too, enforcing 0/1 semantics.
Nonnegative values and the allowed empty subset make zero a valid baseline.
Positive weights are an explicit selected contract. Capacity need not be filled
exactly. LCS permits gaps in both inputs and compares UTF-16 units; a substring
recurrence that resets on a mismatch would solve another problem.

Induction on i proves Fibonacci/knapsack transitions; induction on i+j proves
LCS. Memo lookup order and tabulation loops implement the same dependency graph.
Changing storage can invalidate it: ascending one-row knapsack capacities permit
same-item reuse; losing the old diagonal corrupts one-row LCS matches.

## From Values to Witnesses

Knapsack traces strict improvement over the previous row, skipping ties. LCS
follows a matched diagonal or equal-scoring predecessor, with a documented tie.
Each trace preserves an optimal residual and decreases its index measure.
Proofs must cover reconstruction separately from the optimum value. The [implementation and benchmark guide](implementation-and-benchmark-guide.md)
provides traces, test references and costs. Next: [Fibonacci](08-fibonacci-case-study.md).
