package org.anaalvarezdev.algorithms.stacksandqueues;

import java.util.Objects;

/** Validates (), [] and {} nesting; ignores all other characters, including quotes. */
public final class DelimiterValidator {
    private DelimiterValidator() { }

    /**
     * Returns whether all delimiters match; empty input is balanced.
     * This is not a source-language parser. Uses O(n) time/auxiliary space.
     * @param text non-null input
     * @return true for correct nesting
     * @throws NullPointerException if text is null
     */
    public static boolean isBalanced(String text) {
        Objects.requireNonNull(text, "text must not be null");
        Stack<Character> openings = new ArrayStack<>();
        for (int index = 0; index < text.length(); index++) {
            char symbol = text.charAt(index);
            if (symbol == '(' || symbol == '[' || symbol == '{') {
                openings.push(symbol);
            } else if (symbol == ')' || symbol == ']' || symbol == '}') {
                if (openings.isEmpty()) { return false; }
                char opening = openings.pop();
                if ((symbol == ')' && opening != '(') || (symbol == ']' && opening != '[')
                        || (symbol == '}' && opening != '{')) { return false; }
            }
        }
        return openings.isEmpty();
    }
}
