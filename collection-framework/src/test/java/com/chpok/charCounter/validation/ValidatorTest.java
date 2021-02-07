package com.chpok.charCounter.validation;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class ValidatorTest {
    private final Validator validator = new Validator();
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfSentenceIsNull() {
        assertThatThrownBy(() -> validator.validate(null)).isInstanceOf(IllegalArgumentException.class).hasMessage("Sentence is null!");
    }
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfSentenceIsEmpty() {        
        assertThatThrownBy(() -> validator.validate("")).isInstanceOf(IllegalArgumentException.class).hasMessage("Sentence is empty!");
    }

    @Test
    void validateShouldThrowIllegalArgumentExceptionIfSentenceContainsOnlyTabsOrSpaces() {
        assertThatThrownBy(() -> validator.validate("  ")).isInstanceOf(IllegalArgumentException.class).hasMessage("Sentence contains only spaces or tabs!");
    }
    
    @Test
    void validateShouldNotThrowExceptionWhenSentenceIsCorrect() {
        final String sentence = "How are you?";
        
        assertDoesNotThrow(() -> validator.validate(sentence));
    }
}
