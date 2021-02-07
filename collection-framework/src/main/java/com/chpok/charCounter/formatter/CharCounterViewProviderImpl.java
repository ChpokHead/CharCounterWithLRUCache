package com.chpok.charCounter.formatter;

import java.util.Map;

public class CharCounterViewProviderImpl implements CharCounterViewProvider{
    private static final String QUOTE = "\"";
    private static final String DASH = "-";
    private static final String SPACE = " ";
    private static final String NEWLINE = "\n";
    
    @Override
    public String provideView(Map<Character, Integer> characterToCount) {
        final StringBuilder builder = new StringBuilder();
        
        characterToCount.forEach((sym, count) -> builder.append(QUOTE + sym + QUOTE + SPACE + DASH + SPACE + count + NEWLINE));
        
        return builder.toString();
    }

}
