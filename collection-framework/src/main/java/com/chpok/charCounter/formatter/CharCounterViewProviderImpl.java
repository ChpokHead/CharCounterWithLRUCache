package com.chpok.charCounter.formatter;

import java.util.Map;

public class CharCounterViewProviderImpl implements CharCounterViewProvider{
    @Override
    public String provideView(Map<Character, Integer> characterToCount) {
        final StringBuilder builder = new StringBuilder();

        characterToCount.forEach((sym, count) -> builder.append(String.format("\"%c\" - %d%n", sym, count)));
        
        return builder.toString();
    }

}
