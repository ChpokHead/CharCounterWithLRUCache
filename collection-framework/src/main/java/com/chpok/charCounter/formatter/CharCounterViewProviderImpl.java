package com.chpok.charCounter.formatter;

import java.util.LinkedHashMap;
import java.util.Set;

public class CharCounterViewProviderImpl implements CharCounterViewProvider{
    private static final String QUOTE = "\"";
    private static final String DASH = "-";
    private static final String SPACE = " ";
    private static final String NEWLINE = "\n";
    
    @Override
    public String provideView(LinkedHashMap<Character, Integer> countResult) {
        StringBuilder builder = new StringBuilder();
        Set<Character> keys = countResult.keySet();
        
        for (Character sym : keys) {
            builder.append(QUOTE + sym + QUOTE + SPACE + DASH + SPACE + countResult.get(sym) + NEWLINE);
        }
        
        return builder.toString();
    }

}
