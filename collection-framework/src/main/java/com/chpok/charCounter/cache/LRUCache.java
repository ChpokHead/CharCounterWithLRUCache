package com.chpok.charCounter.cache;

import java.util.LinkedHashMap;
import java.util.Map;

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
