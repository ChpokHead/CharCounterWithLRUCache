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

    public UniqueCharCounterWithCache(Builder builder) {
        validationProvider = builder.validationProvider;
        charCounterProvider = builder.charCounterProvider;
        charCounterViewProvider = builder.charCounterViewProvider;
        counterCacheProvider = builder.counterCacheProvider;
    }
    
    public static Builder builder() {
        return new Builder();
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
    
    public static class Builder {
        private ValidationProvider validationProvider;
        private CharCounterProvider charCounterProvider;
        private CharCounterViewProvider charCounterViewProvider;
        private CounterCacheProvider counterCacheProvider;
        
        private Builder() {}
        
        public Builder withValidation(ValidationProvider validationProvider) {
            this.validationProvider = validationProvider;
            return this;
        }
        
        public Builder withCharCounter(CharCounterProvider charCounterProvider) {
            this.charCounterProvider = charCounterProvider;
            return this;
        }
        
        public Builder withCharCounterView(CharCounterViewProvider charCounterViewProvider) {
            this.charCounterViewProvider = charCounterViewProvider;
            return this;
        }
        
        public Builder withCounterCache(CounterCacheProvider counterCacheProvider) {
            this.counterCacheProvider = counterCacheProvider;
            return this;
        }
        
        public UniqueCharCounterWithCache build() {
            return new UniqueCharCounterWithCache(this);
        }
    }
}
