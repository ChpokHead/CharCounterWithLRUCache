package com.chpok.charCounter.cache;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import org.junit.jupiter.api.Test;

class CounterCacheTest {
    private final CounterCacheProviderImpl cache = new CounterCacheProviderImpl();
    
    @Test
    void addCountedSentenceShouldAddCacheElementInCountedSentences() {
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
        
        cache.addCountedSentence(sentence, characterToCount);
        
        final LRUCache actual = cache.getCountedSentences();
        final LRUCache expected = new LRUCache(cache.getCacheSize());
        
        expected.put(sentence, characterToCount);
        
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    void getAlreadyCountedSentenceCounterShouldReturnCorrectCounterIfSentenceWasCounted() {
        final String sentence = "how are you?";
        LinkedHashMap<Character, Integer> expected = new LinkedHashMap<>();
        
        expected.put('h', 1);
        expected.put('o', 2);
        expected.put('w', 1);
        expected.put(' ', 2);
        expected.put('a', 1);
        expected.put('r', 1);
        expected.put('e', 1);
        expected.put('y', 1);
        expected.put('u', 1);
        expected.put('?', 1);
        
        cache.addCountedSentence(sentence, expected);
        
        final LinkedHashMap<Character, Integer> actual = cache.getAlreadyCountedSentenceCounter(sentence);
        
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    void getAlreadyCountedSentenceCounterShouldReturnNullIfSentenceWasNeverCounted() {
        final String sentence = "how are you?";
        final LinkedHashMap<Character, Integer> expected = null;        
        final LinkedHashMap<Character, Integer> actual = cache.getAlreadyCountedSentenceCounter(sentence);
        
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    void isSentenceAlreadyCountedShouldReturnTrueIfSentenceWasCounted() {
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
        
        cache.addCountedSentence(sentence, characterToCount);
        
        assertThat(cache.isSentenceAlreadyCounted(sentence)).isTrue();
    }
    
    @Test
    void isSentenceAlreadyCountedShouldReturnFalseIfSentenceWasNeverCounted() {
        final String sentence = "how are you?";
        
        assertThat(cache.isSentenceAlreadyCounted(sentence)).isFalse();
    }

}
