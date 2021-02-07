package com.chpok.charCounter.cache;

import java.util.LinkedHashMap;

public interface CounterCacheProvider {
    void addCountedSentence(String sentence, LinkedHashMap<Character, Integer> characterToCount);
    
    LinkedHashMap<Character, Integer> getAlreadyCountedSentenceCounter(String sentence);
    
    boolean isSentenceAlreadyCounted(String sentence);
}
