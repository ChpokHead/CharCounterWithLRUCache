package com.chpok.charCounter.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class CounterCacheProviderImpl implements CounterCacheProvider {
    public static final int CACHE_SIZE = 1;
    
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
    
    public class LRUCache extends LinkedHashMap<String, LinkedHashMap<Character, Integer>>{
        private final int maxSize;
        
        public LRUCache(int size) {
            super(size, 0.75f, true);
            maxSize = size;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<String, LinkedHashMap<Character, Integer>> eldest) {
            return this.size() > maxSize;
        }
        
    }
    
}
