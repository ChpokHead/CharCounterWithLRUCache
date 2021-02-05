package com.chpok.charCounter.validation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ValidatorTest {
    private final Validator validator = new Validator();
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfSentenceIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(null));
        
        String expected = "Sentence is null!";
        String actual = exception.getMessage();
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void validateShouldThrowIllegalArgumentExceptionIfSentenceIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(""));
        
        String expected = "Sentence is empty!";
        String actual = exception.getMessage();
        
        assertThat(actual, is(equalTo(expected)));
    }

    @Test
    void validateShouldThrowIllegalArgumentExceptionIfSentenceContainsOnlyTabsOrSpaces() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> validator.validate("   "));
        
        String expected = "Sentence contains only spaces or tabs!";
        String actual = exception.getMessage();
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void validateShouldNotThrowExceptionWhenSentenceIsCorrect() {
        final String sentence = "How are you?";
        
        assertDoesNotThrow(() -> validator.validate(sentence));
    }
}
