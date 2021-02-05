package com.chpok.charCounter.formatter;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.LinkedHashMap;
import org.junit.jupiter.api.Test;

class CharCounterViewTest {
    private final CharCounterViewProviderImpl viewProvider = new CharCounterViewProviderImpl();
    
    @Test
    void provideViewShouldReturnCorrectViewIfCountResultIsCorrect() {
        LinkedHashMap<Character, Integer> countResult = new LinkedHashMap<>();
        
        countResult.put('h', 2);
        countResult.put('i', 1);
        countResult.put(' ', 1);
        countResult.put('t', 1);
        countResult.put('a', 1);
        countResult.put('n', 1);
        countResult.put('k', 1);
        countResult.put('s', 1);
        countResult.put('.', 3);
        
        String expected = "\"h\" - 2\n\"i\" - 1\n\" \" - 1\n\"t\" - 1\n\"a\" - 1\n\"n\" - 1\n\"k\" - 1\n\"s\" - 1\n\".\" - 3\n";
        String actual = viewProvider.provideView(countResult);
        
        assertThat(actual, is(equalTo(expected)));
    }

}
