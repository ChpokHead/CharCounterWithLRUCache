package com.chpok.charCounter;

import java.util.LinkedHashMap;

import com.chpok.charCounter.cache.CounterCacheProvider;
import com.chpok.charCounter.formatter.CharCounterViewProvider;
import com.chpok.charCounter.provider.CharCounterProvider;
import com.chpok.charCounter.validation.ValidationProvider;

public class UniqueCharCounterWithCache {
    private final ValidationProvider validationProvider;
    private final CharCounterProvider charCounterProvider;
    private final CharCounterViewProvider charCounterViewProvider;
    private final CounterCacheProvider counterCacheProvider;
    
    public UniqueCharCounterWithCache(ValidationProvider validationProvider, CharCounterProvider charCounterProvider,
            CharCounterViewProvider charCounterViewProvider, CounterCacheProvider counterCacheProvider) {
        this.validationProvider = validationProvider;
        this.charCounterProvider = charCounterProvider;
        this.charCounterViewProvider = charCounterViewProvider;
        this.counterCacheProvider = counterCacheProvider;
    }

    public String countUniqueCharacters(String sentence) {
        validationProvider.validate(sentence);
        
        final LinkedHashMap<Character, Integer> characterToCount;
        
        if (counterCacheProvider.isSentenceAlreadyCounted(sentence)) {
            characterToCount = counterCacheProvider.getAlreadyCountedSentenceCounter(sentence);
        } else {
            characterToCount = charCounterProvider.provideCharCount(sentence);
            counterCacheProvider.addCountedSentence(sentence, characterToCount);
        }
        
        return charCounterViewProvider.provideView(characterToCount);
    }
    
}
