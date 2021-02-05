package com.chpok.charCounter.formatter;

import java.util.LinkedHashMap;

public interface CharCounterViewProvider {
    String provideView(LinkedHashMap<Character, Integer> countResult);
}
