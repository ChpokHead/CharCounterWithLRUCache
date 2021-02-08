package com.chpok.charCounter.cache;

import java.util.LinkedHashMap;

public class CounterCacheProviderImpl implements CounterCacheProvider{   
    private final LRUCache cache;
    
    public CounterCacheProviderImpl(LRUCache cache) {
        this.cache = cache;
    }
    
    public LRUCache getCountedSentences() {
        return cache;
    }

    @Override
    public void addCountedSentence(String sentence, LinkedHashMap<Character, Integer> characterToCount) {
        cache.put(sentence, characterToCount);
    }
    
    @Override
    public LinkedHashMap<Character, Integer> getAlreadyCountedSentenceCounter(String sentence) {
        return cache.get(sentence);
    }
    
    public boolean isSentenceAlreadyCounted(String sentence) {
        return cache.containsKey(sentence);
    }
    
}
