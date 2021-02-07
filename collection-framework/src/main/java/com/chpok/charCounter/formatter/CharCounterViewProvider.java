package com.chpok.charCounter.formatter;

import java.util.Map;

public interface CharCounterViewProvider {
    String provideView(Map<Character, Integer> characterToCount);
}
