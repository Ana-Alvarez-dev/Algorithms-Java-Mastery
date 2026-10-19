package org.anaalvarezdev.algorithms.stacksandqueues;

import java.util.Objects;

/** Evaluates whitespace-separated int operands and checked binary arithmetic. */
public final class PostfixEvaluator {
    private PostfixEvaluator() { }

    /**
     * Evaluates signed decimal int operands and binary +, -, *, / operators.
     * Division truncates towards zero; overflow, including MIN_VALUE/-1, fails.
     * Uses O(L) time and space for expression length L, including tokenisation.
     * @param expression non-null, nonempty postfix expression
     * @return the single final value
     * @throws NullPointerException for null input
     * @throws IllegalArgumentException for bad tokens, arity or final stack size
     * @throws ArithmeticException for overflow or division by zero
     */
    public static int evaluate(String expression) {
        Objects.requireNonNull(expression, "expression must not be null");
        if (expression.isBlank()) { throw new IllegalArgumentException("expression is empty"); }
        Stack<Integer> values = new ArrayStack<>();
        for (String token : expression.strip().split("\\p{javaWhitespace}+")) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                if (values.size() < 2) { throw new IllegalArgumentException("operator needs two operands"); }
                int right = values.pop();
                int left = values.pop();
                int result = switch (token) {
                    case "+" -> Math.addExact(left, right);
                    case "-" -> Math.subtractExact(left, right);
                    case "*" -> Math.multiplyExact(left, right);
                    default -> divide(left, right);
                };
                values.push(result);
            } else {
                values.push(Integer.parseInt(token));
            }
        }
        if (values.size() != 1) { throw new IllegalArgumentException("expression must produce one value"); }
        return values.pop();
    }

    private static int divide(int left, int right) {
        if (left == Integer.MIN_VALUE && right == -1) { throw new ArithmeticException("integer overflow"); }
        return left / right;
    }
}
