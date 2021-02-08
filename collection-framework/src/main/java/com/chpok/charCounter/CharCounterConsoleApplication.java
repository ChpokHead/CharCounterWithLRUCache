package com.chpok.charCounter;

import com.chpok.charCounter.cache.CounterCacheProviderImpl;
import com.chpok.charCounter.formatter.CharCounterViewProviderImpl;
import com.chpok.charCounter.provider.CharCounterProviderImpl;
import com.chpok.charCounter.validation.Validator;

public class CharCounterConsoleApplication {
    public static void main(String[] args) {
        Validator validator = new Validator();
        CharCounterProviderImpl charCounter = new CharCounterProviderImpl();
        CharCounterViewProviderImpl viewProvider = new CharCounterViewProviderImpl();
        CounterCacheProviderImpl cache = new CounterCacheProviderImpl();
        UniqueCharCounterWithCache counter = new UniqueCharCounterWithCache(validator, charCounter, viewProvider, cache);
        
        System.out.println(counter.countUniqueCharacters("hello world!"));
    }
}
