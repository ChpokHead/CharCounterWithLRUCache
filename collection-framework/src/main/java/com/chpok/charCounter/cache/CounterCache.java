package com.chpok.charCounter.cache;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CounterCache {
    private final ArrayList<CacheElement> countedSentences = new ArrayList<>();
    
    public void addCountedSentence(String sentence, LinkedHashMap<Character, Integer> counter) {
        countedSentences.add(new CacheElement(sentence, counter));
    }
    
    public LinkedHashMap<Character, Integer> getAlreadyCountedSentenceCounter(String sentence) {
        for (CacheElement el : countedSentences) {
            if (el.getCountedSentence().equals(sentence)) {
                return el.getCountResult();
            }
        }
        
        return null;
    }
    
    public boolean isSentenceAlreadyCounted(String sentence) {
        for (CacheElement el : countedSentences) {
            if (el.getCountedSentence().equals(sentence)) {
                return true;
            }
        }
        
        return false;
    }
    
}
