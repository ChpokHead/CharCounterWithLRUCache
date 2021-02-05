package com.chpok.charCounter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedHashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.chpok.charCounter.formatter.CharCounterViewProvider;
import com.chpok.charCounter.provider.CharCounterProvider;
import com.chpok.charCounter.validation.ValidationProvider;

@ExtendWith(MockitoExtension.class)
class UniqueCharCounterWithCacheTest {
    @Mock
    private CharCounterProvider counterProvider;
    @Mock
    private CharCounterViewProvider viewProvider;
    @Mock
    private ValidationProvider validator;
    @InjectMocks
    private UniqueCharCounterWithCache charCounterWithCache;

    @Test
    void countUniqueCharactersShouldReturnCorrectCharacterCountIfSentenceIsCorrect() {
        String sentence = "how are you?";
        LinkedHashMap<Character, Integer> result = new LinkedHashMap<>();
        
        result.put('h', 1);
        result.put('o', 2);
        result.put('w', 1);
        result.put(' ', 2);
        result.put('a', 1);
        result.put('r', 1);
        result.put('e', 1);
        result.put('y', 1);
        result.put('u', 1);
        result.put('?', 1);
        
        String expected = "\"h\" - 1\n\"o\" - 2\n\"w\" - 1\n\" \" - 2\n\"a\" - 1\n\"r\" - 1\n\"e\" - 1\n\"y\" - 1\n\"u\" - 1\n\"?\" - 1\n";
                
        when(counterProvider.provideCharCount(sentence)).thenReturn(result);
        when(viewProvider.provideView(result)).thenReturn(expected);
        
        String actual = charCounterWithCache.countUniqueCharacters(sentence);

        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void countUniqueCharactersShouldReturnCorrectCharacterCountsWhenEqualSentencesGiven() {
        String sentence = "how are you?";
        LinkedHashMap<Character, Integer> firstResult = new LinkedHashMap<>();
        
        firstResult.put('h', 1);
        firstResult.put('o', 2);
        firstResult.put('w', 1);
        firstResult.put(' ', 2);
        firstResult.put('a', 1);
        firstResult.put('r', 1);
        firstResult.put('e', 1);
        firstResult.put('y', 1);
        firstResult.put('u', 1);
        firstResult.put('?', 1);
        
        String expected = "\"h\" - 1\n\"o\" - 2\n\"w\" - 1\n\" \" - 2\n\"a\" - 1\n\"r\" - 1\n\"e\" - 1\n\"y\" - 1\n\"u\" - 1\n\"?\" - 1\n";
                
        when(counterProvider.provideCharCount(sentence)).thenReturn(firstResult);
        when(viewProvider.provideView(firstResult)).thenReturn(expected);
        
        String firstActual = charCounterWithCache.countUniqueCharacters(sentence);

        assertThat(firstActual, is(equalTo(expected)));
        
        LinkedHashMap<Character, Integer> secondResult = firstResult;
        
        when(viewProvider.provideView(secondResult)).thenReturn(expected);

        String secondActual = charCounterWithCache.countUniqueCharacters(sentence);
        
        assertThat(secondActual, is(equalTo(expected)));   
    }
    
    @Test
    void countUniqueCharactersShouldThrowIllegalArgumentExceptionIfSentenceIsNull() {
        final String sentence = null;
        final String expected = "Sentence is null!";

        doThrow(new IllegalArgumentException(expected)).when(validator).validate(sentence);
        
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(sentence));
        final String actual = exception.getMessage();
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void countUniqueCharactersShouldThrowIllegalArgumentExceptionIfSentenceIsEmpty() {
        final String sentence = "";
        final String expected = "Sentence is empty!";

        doThrow(new IllegalArgumentException(expected)).when(validator).validate(sentence);
        
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(sentence));
        final String actual = exception.getMessage();
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void countUniqueCharactersShouldThrowIllegalArgumentExceptionIfSentenceContainsOnlySpacesOrTabs() {
        final String sentence = "   ";
        final String expected = "Sentence contains only spaces or tabs!";

        doThrow(new IllegalArgumentException(expected)).when(validator).validate(sentence);
        
        final Exception exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(sentence));
        final String actual = exception.getMessage();
        
        assertThat(actual, is(equalTo(expected)));
    }

}
