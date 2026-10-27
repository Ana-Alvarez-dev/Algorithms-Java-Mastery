package org.anaalvarezdev.algorithms.searching;

/** Small test-only builder: explicit cases, no sorting or expected-value derivation. */
final class ClosestSearchCaseBuilder {
    private final String name;
    private int[] values = {};
    private int target;
    private int expected = -1;

    ClosestSearchCaseBuilder(String name) { this.name = name; }

    ClosestSearchCaseBuilder values(int... values) { this.values = values.clone(); return this; }
    ClosestSearchCaseBuilder target(int target) { this.target = target; return this; }
    ClosestSearchCaseBuilder expected(int expected) { this.expected = expected; return this; }
    SearchCase build() { return new SearchCase(name, values, target, expected); }

    record SearchCase(String name, int[] input, int target, int expected) {
        SearchCase { input = input.clone(); }
        @Override public int[] input() { return input.clone(); }
    }
}
