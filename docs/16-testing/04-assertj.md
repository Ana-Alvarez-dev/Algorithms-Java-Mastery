# AssertJ

AssertJ **3.27.3** remains the assertion library. Assertions should express the
contract directly and produce a useful diagnostic when it fails. Jupiter runs
the test; AssertJ compares its observed result with the expected one.

```java
assertThat(ClosestValueSearch.search(values, target))
        .as("nearest index for target=%d", target)
        .isEqualTo(expected);
assertThat(values).containsExactly(original);
```

Index equality checks the tie policy; array equality checks preservation. Merely
asserting a nonnegative index or equal nearest distance misses later duplicate
copies. The linear-oracle test includes input/target descriptions so an
exhaustive or seeded failure can be reduced to a readable case.

For an explicit exception contract, keep the potentially throwing operation
inside `assertThatThrownBy`/`isThrownBy`. Verify type and stable message where
the API specifies one. Do not assert incidental VM-generated exception text or
wrap an entire multi-action scenario in an assertion that cannot identify which
operation threw. Existing `ClosestValueSearchTest` checks its explicit null
message; there is no need to duplicate that test just to demonstrate syntax.

The [implementation and verification guide](implementation-and-verification-guide.md) maps assertions to contracts and the verification report. Next:
[parameterised tests](05-parameterized-tests.md).
