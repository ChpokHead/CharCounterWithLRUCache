package com.chpok.charCounter.cache;

import java.util.LinkedHashMap;

public class CacheElement {
    private final String countedSentence;
    private final LinkedHashMap<Character, Integer> countResult;
    
    public CacheElement(String countedSentence, LinkedHashMap<Character, Integer> countResult) {
        this.countedSentence = countedSentence;
        this.countResult = countResult;
    }

    public String getCountedSentence() {
        return countedSentence;
    }

    public LinkedHashMap<Character, Integer> getCountResult() {
        return countResult;
    }
    
}
