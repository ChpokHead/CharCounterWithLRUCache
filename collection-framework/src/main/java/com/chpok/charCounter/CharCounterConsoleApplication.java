package com.chpok.charCounter;

import com.chpok.charCounter.cache.CounterCacheProviderImpl;
import com.chpok.charCounter.cache.LRUCache;
import com.chpok.charCounter.formatter.CharCounterViewProviderImpl;
import com.chpok.charCounter.provider.CharCounterProviderImpl;
import com.chpok.charCounter.validation.Validator;

public class CharCounterConsoleApplication {
    private static final int CACHE_SIZE = 100;

    public static void main(String[] args) {
        Validator validator = new Validator();
        CharCounterProviderImpl charCounter = new CharCounterProviderImpl();
        CharCounterViewProviderImpl viewProvider = new CharCounterViewProviderImpl();
        CounterCacheProviderImpl cache = new CounterCacheProviderImpl(new LRUCache(CACHE_SIZE));
        UniqueCharCounterWithCache counter = new UniqueCharCounterWithCache(validator, charCounter, viewProvider, cache);
        
        System.out.println(counter.countUniqueCharacters("hello world!"));
    }
}
