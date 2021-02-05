package com.chpok.charCounter.cache;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.junit.jupiter.api.Test;

class CounterCacheTest {
    private final CounterCache cache = new CounterCache();
    
    @Test
    void addCountedSentenceShouldAddCacheElementInCountedSentences() {
        String sentence = "how are you?";
        LinkedHashMap<Character, Integer> counter = new LinkedHashMap<>();
        
        counter.put('h', 1);
        counter.put('o', 2);
        counter.put('w', 1);
        counter.put(' ', 2);
        counter.put('a', 1);
        counter.put('r', 1);
        counter.put('e', 1);
        counter.put('y', 1);
        counter.put('u', 1);
        counter.put('?', 1);
        
        cache.addCountedSentence(sentence, counter);
        
        ArrayList<CacheElement> actual = cache.getCountedSentences();
        ArrayList<CacheElement> expected = new ArrayList<>();
        
        expected.add(new CacheElement(sentence, counter));
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void getAlreadyCountedSentenceCounterShouldReturnCorrectCounterIfSentenceWasCounted() {
        String sentence = "how are you?";
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
        
        LinkedHashMap<Character, Integer> actual = cache.getAlreadyCountedSentenceCounter(sentence);
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void getAlreadyCountedSentenceCounterShouldReturnNullIfSentenceWasNeverCounted() {
        String sentence = "how are you?";
        LinkedHashMap<Character, Integer> expected = null;        
        LinkedHashMap<Character, Integer> actual = cache.getAlreadyCountedSentenceCounter(sentence);
        
        assertThat(actual, is(equalTo(expected)));
    }
    
    @Test
    void isSentenceAlreadyCountedShouldReturnTrueIfSentenceWasCounted() {
        String sentence = "how are you?";
        LinkedHashMap<Character, Integer> counter = new LinkedHashMap<>();
        
        counter.put('h', 1);
        counter.put('o', 2);
        counter.put('w', 1);
        counter.put(' ', 2);
        counter.put('a', 1);
        counter.put('r', 1);
        counter.put('e', 1);
        counter.put('y', 1);
        counter.put('u', 1);
        counter.put('?', 1);
        
        cache.addCountedSentence(sentence, counter);
        
        assertTrue(cache.isSentenceAlreadyCounted(sentence));
    }
    
    @Test
    void isSentenceAlreadyCountedShouldReturnFalseIfSentenceWasNeverCounted() {
        String sentence = "how are you?";
        
        assertFalse(cache.isSentenceAlreadyCounted(sentence));
    }

}
