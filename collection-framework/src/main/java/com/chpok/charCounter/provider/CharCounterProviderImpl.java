package com.chpok.charCounter.provider;

import java.util.LinkedHashMap;

public class CharCounterProviderImpl implements CharCounterProvider{
    @Override
    public LinkedHashMap<Character, Integer> provideCharCount(String sentence) {
        final LinkedHashMap<Character, Integer> characterToCount = new LinkedHashMap<>();
        
        for (int i = 0; i < sentence.length(); i++) {
            char sym = sentence.charAt(i);
            
            addCharacterToCounter(sym, characterToCount);
        }
        
        return characterToCount;
    }
    
    private void addCharacterToCounter(char sym, LinkedHashMap<Character, Integer> characterToCount) {
        characterToCount.merge(sym, 1, (prev, one) -> prev + one);
    }

}
