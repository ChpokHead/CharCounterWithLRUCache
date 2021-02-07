package com.chpok.charCounter.cache;

import java.util.LinkedHashMap;

public class CounterCacheProviderImpl implements CounterCacheProvider{
    private static final int CACHE_SIZE = 100;
   
    private final LRUCache cache = new LRUCache(CACHE_SIZE);
    
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
    
    public int getCacheSize() {
        return CACHE_SIZE;
    }
    
}
