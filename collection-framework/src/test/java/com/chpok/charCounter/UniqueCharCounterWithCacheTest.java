package com.chpok.charCounter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.util.LinkedHashMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.chpok.charCounter.cache.CounterCacheProvider;
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
    @Mock
    private CounterCacheProvider cache;
    @InjectMocks
    private UniqueCharCounterWithCache charCounterWithCache;

    @Test
    void countUniqueCharactersShouldReturnCorrectCharacterCountIfSentenceIsCorrect() {
        final String sentence = "how are you?";
        final LinkedHashMap<Character, Integer> result = new LinkedHashMap<>();
        
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
        
        final String expected = "\"h\" - 1\n\"o\" - 2\n\"w\" - 1\n\" \" - 2\n\"a\" - 1\n\"r\" - 1\n\"e\" - 1\n\"y\" - 1\n\"u\" - 1\n\"?\" - 1\n";
        
        when(cache.isSentenceAlreadyCounted(sentence)).thenReturn(false);
        when(counterProvider.provideCharCount(sentence)).thenReturn(result);
        when(viewProvider.provideView(result)).thenReturn(expected);
        
        final String actual = charCounterWithCache.countUniqueCharacters(sentence);

        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    void countUniqueCharactersShouldReturnCorrectCharacterCountsWhenEqualSentencesGiven() {
        final String sentence = "how are you?";
        final LinkedHashMap<Character, Integer> characterToCount = new LinkedHashMap<>();
        
        characterToCount.put('h', 1);
        characterToCount.put('o', 2);
        characterToCount.put('w', 1);
        characterToCount.put(' ', 2);
        characterToCount.put('a', 1);
        characterToCount.put('r', 1);
        characterToCount.put('e', 1);
        characterToCount.put('y', 1);
        characterToCount.put('u', 1);
        characterToCount.put('?', 1);
        
        final String expected = "\"h\" - 1\n\"o\" - 2\n\"w\" - 1\n\" \" - 2\n\"a\" - 1\n\"r\" - 1\n\"e\" - 1\n\"y\" - 1\n\"u\" - 1\n\"?\" - 1\n";
                
        when(cache.isSentenceAlreadyCounted(sentence)).thenReturn(false);
        when(counterProvider.provideCharCount(sentence)).thenReturn(characterToCount);
        when(viewProvider.provideView(characterToCount)).thenReturn(expected);
        
        charCounterWithCache.countUniqueCharacters(sentence);
        
        when(cache.isSentenceAlreadyCounted(sentence)).thenReturn(true);
        when(cache.getAlreadyCountedSentenceCounter(sentence)).thenReturn(characterToCount);
        when(viewProvider.provideView(characterToCount)).thenReturn(expected);

        final String actual = charCounterWithCache.countUniqueCharacters(sentence);
        
        assertThat(actual).isEqualTo(expected);  
    }
    
    @Test
    void countUniqueCharactersShouldThrowIllegalArgumentExceptionIfSentenceIsNull() {
        final String sentence = null;
        final String expected = "Sentence is null!";

        doThrow(new IllegalArgumentException(expected)).when(validator).validate(sentence);
        
        assertThatThrownBy(() -> validator.validate(sentence)).isInstanceOf(IllegalArgumentException.class).hasMessage(expected);
    }
    
    @Test
    void countUniqueCharactersShouldThrowIllegalArgumentExceptionIfSentenceIsEmpty() {
        final String sentence = "";
        final String expected = "Sentence is empty!";

        doThrow(new IllegalArgumentException(expected)).when(validator).validate(sentence);
        
        assertThatThrownBy(() -> validator.validate(sentence)).isInstanceOf(IllegalArgumentException.class).hasMessage(expected);
    }
    
    @Test
    void countUniqueCharactersShouldThrowIllegalArgumentExceptionIfSentenceContainsOnlySpacesOrTabs() {
        final String sentence = "   ";
        final String expected = "Sentence contains only spaces or tabs!";

        doThrow(new IllegalArgumentException(expected)).when(validator).validate(sentence);
        
        assertThatThrownBy(() -> validator.validate(sentence)).isInstanceOf(IllegalArgumentException.class).hasMessage(expected);
    }

}
