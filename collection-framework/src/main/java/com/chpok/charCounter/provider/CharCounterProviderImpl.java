package com.chpok.charCounter.provider;

import java.util.LinkedHashMap;

public class CharCounterProviderImpl implements CharCounterProvider{
    @Override
    public LinkedHashMap<Character, Integer> countUniqueCharacters(String sentence) {
        LinkedHashMap<Character, Integer> result = new LinkedHashMap<>();
        
        for (int i = 0; i < sentence.length(); i++) {
            char sym = sentence.charAt(i);
            
            addCharacterToCounter(sym, result);
        }
        
        return result;
    }
    
    private void addCharacterToCounter(char sym, LinkedHashMap<Character, Integer> counter) {
        if (counter.containsKey(sym)) {
            counter.put(sym, counter.get(sym) + 1);
        } else {
            counter.put(sym, 1);
        }
    }

}
