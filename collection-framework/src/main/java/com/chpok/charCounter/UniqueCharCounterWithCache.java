package com.chpok.charCounter;

import java.util.LinkedHashMap;
import com.chpok.charCounter.cache.CounterCache;
import com.chpok.charCounter.formatter.CharCounterViewProvider;
import com.chpok.charCounter.provider.CharCounterProvider;
import com.chpok.charCounter.validation.ValidationProvider;

public class UniqueCharCounterWithCache {
    private final ValidationProvider validationProvider;
    private final CharCounterProvider charCounterProvider;
    private final CharCounterViewProvider charCounterViewProvider;
    private final CounterCache cache;

    public UniqueCharCounterWithCache(ValidationProvider validationProvider, CharCounterProvider charCounterProvider, CharCounterViewProvider charCounterViewProvider) {
        this.validationProvider = validationProvider;
        this.charCounterProvider = charCounterProvider;
        this.charCounterViewProvider = charCounterViewProvider;
        cache = new CounterCache();
    }
    
    public String countUniqueCharacters(String sentence) {
        validationProvider.validate(sentence);
        
        LinkedHashMap<Character, Integer> result;
        
        if (cache.isSentenceAlreadyCounted(sentence)) {
            result = cache.getAlreadyCountedSentenceCounter(sentence);
        } else {
            result = charCounterProvider.provideCharCount(sentence);
            cache.addCountedSentence(sentence, result);
        }
        
        return charCounterViewProvider.provideView(result);
    }
}
