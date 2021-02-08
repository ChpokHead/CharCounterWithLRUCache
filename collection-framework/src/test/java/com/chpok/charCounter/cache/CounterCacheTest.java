package com.chpok.charCounter.cache;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.LinkedHashMap;
import org.junit.jupiter.api.Test;
import com.chpok.charCounter.cache.CounterCacheProviderImpl.LRUCache;

class CounterCacheTest {
    private static final int CACHE_SIZE = 1;

    private CounterCacheProviderImpl counterCache = new CounterCacheProviderImpl();
    
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
        
        counterCache.addCountedSentence(sentence, characterToCount);
        
        final LRUCache actual = counterCache.getCountedSentences();
        
        final LRUCache expected = counterCache.new LRUCache(CACHE_SIZE);
        
        expected.put(sentence, characterToCount);
        
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    void addCountedSentenceShouldRemoveEarliestItemFromCacheIfMaxCacheSizeReached() {
        final String firstSentence = "how are you?";
        final String secondSentence = "i am fine";
        
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
        
        counterCache.addCountedSentence(firstSentence, characterToCount);
        
        characterToCount.clear();
        
        characterToCount.put('i', 2);
        characterToCount.put(' ', 2);
        characterToCount.put('a', 1);
        characterToCount.put('m', 1);
        characterToCount.put('f', 1);
        characterToCount.put('n', 1);
        characterToCount.put('e', 1);
        
        counterCache.addCountedSentence(secondSentence, characterToCount);
        
        final LRUCache actual = counterCache.getCountedSentences();
        final LRUCache expected = counterCache.new LRUCache(CACHE_SIZE);
        
        expected.put(secondSentence, characterToCount);
        
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
        
        counterCache.addCountedSentence(sentence, expected);
        
        final LinkedHashMap<Character, Integer> actual = counterCache.getAlreadyCountedSentenceCounter(sentence);
        
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    void getAlreadyCountedSentenceCounterShouldReturnNullIfSentenceWasNeverCounted() {
        final String sentence = "how are you?";
        final LinkedHashMap<Character, Integer> expected = null;        
        final LinkedHashMap<Character, Integer> actual = counterCache.getAlreadyCountedSentenceCounter(sentence);
        
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
        
        counterCache.addCountedSentence(sentence, characterToCount);
        
        assertThat(counterCache.isSentenceAlreadyCounted(sentence)).isTrue();
    }
    
    @Test
    void isSentenceAlreadyCountedShouldReturnFalseIfSentenceWasNeverCounted() {
        final String sentence = "how are you?";
        
        assertThat(counterCache.isSentenceAlreadyCounted(sentence)).isFalse();
    }

}
