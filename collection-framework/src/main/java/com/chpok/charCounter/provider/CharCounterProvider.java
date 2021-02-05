package com.chpok.charCounter.provider;

import java.util.LinkedHashMap;

public interface CharCounterProvider {
    LinkedHashMap<Character, Integer> countUniqueCharacters(String sentence);
}
