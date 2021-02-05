package com.chpok.charCounter.cache;

import java.util.LinkedHashMap;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(countResult, countedSentence);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CacheElement other = (CacheElement) obj;
        return Objects.equals(countResult, other.countResult) && Objects.equals(countedSentence, other.countedSentence);
    }
    
    
}
