# Recursion Implementations

This Java 21 package implements the selected scope of
[module 11](../../../../../../../docs/11-recursion/README.md).
The [implementation guide](../../../../../../../docs/11-recursion/implementation-and-benchmark-guide.md)
connects contracts, pseudocode, traces, correctness, recurrences, tests and JMH.

| Type | Selected contract | Verification |
|---|---|---|
| Factorial | Ordinary/tail/iterative exact long factorial, n=0..20 | Three tests, all accepted inputs against BigInteger products |
| Fibonacci | Naive n=0..30; memoised/iterative n=0..92, fresh memo per call | Four tests, BigInteger oracle and separate work/overflow boundaries |
| Power | Exact BigInteger squaring, nonnegative exponent, 0^0=1 | Four tests, 1,105 small cases and large/bounded-result exponent checks |
| ArraySum | Long sum of an int[] or validated half-open slice, balanced recursion/loop | Five tests, 27,064 small slices, extreme/seeded/large inputs and nonmutation |
| Palindrome | Exact int-array symmetry, balanced recursion over pairs/loop | Four tests, reversed-copy oracle and million-value cases |
| GreatestCommonDivisor | All signed int pairs, nonnegative long result, gcd(0,0)=0 | Three tests, 6,561 small plus 5,000 seeded BigInteger.gcd comparisons |
| Hanoi | Immutable optimal three-peg move plan, n=0..16 | Five tests, legal simulation, output boundary and ownership |

Verification reported 2026-10-24 using OpenJDK 21 passed **600 repository tests**,
including **28 module tests**. Eight JMH methods built; all 24 short smoke cases
completed and a separate probe checked every default combination, three calls
each, using independent oracles. JavaDoc passes doclint excluding missing tags.

There is no mutable shared cache/input or exposed memo state. Numeric range,
work caps and materialised output caps are distinct. BigInteger multiplication
count is not bit-time complexity. Tail position is not a Java constant-stack
promise; balanced sum/pair recursion avoids linear call depth. ArraySum's long
contract is separate from the old Arrays aggregation finding, which stays open.

The three benchmark templates retain
Research Question, Theoretical Expectation, Measured Work, Result and
Interpretation. Result/Interpretation remain pending actual author measurement.
The guide defines the combined 24-cell nanosecond run, records source before
launch, and keeps smoke separate from Markdown research without JSON/log files.
