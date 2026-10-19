package org.anaalvarezdev.algorithms.stacksandqueues;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DelimiterValidatorTest {
    @Test
    void shouldAcceptEmptyTextAndMixedNestedDelimiters() {
        for (String text : new String[]{"", "abc", "()[]{}", "a{b[c(d)e]f}g"}) {
            assertThat(DelimiterValidator.isBalanced(text)).as(text).isTrue();
        }
    }

    @Test
    void shouldRejectUnclosedUnexpectedAndCrossedDelimiters() {
        for (String text : new String[]{"(", ")", "([)]", "{]", "(()", "())", "}{"}) {
            assertThat(DelimiterValidator.isBalanced(text)).as(text).isFalse();
        }
    }

    @Test
    void shouldTreatQuotedDelimitersAsDelimitersRatherThanParseAProgrammingLanguage() {
        assertThat(DelimiterValidator.isBalanced("\"(\"")).isFalse();
        assertThat(DelimiterValidator.isBalanced("\"()\"")).isTrue();
    }

    @Test
    void shouldHandleDeepInputIteratively() {
        String text = "(".repeat(100000) + ")".repeat(100000);
        assertThat(DelimiterValidator.isBalanced(text)).isTrue();
        assertThat(DelimiterValidator.isBalanced(text + "]")).isFalse();
    }

    @Test
    void shouldRejectNullExplicitly() {
        assertThatThrownBy(() -> DelimiterValidator.isBalanced(null)).isInstanceOf(NullPointerException.class);
    }
}
