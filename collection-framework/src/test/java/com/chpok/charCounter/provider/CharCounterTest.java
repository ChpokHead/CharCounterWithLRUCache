package com.chpok.charCounter.provider;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

class CharCounterTest {
    private final CharCounterProviderImpl charCounter = new CharCounterProviderImpl();
    
    @Test
    void countUniqueShouldReturnCorrectCountResultIfSentenceIsCorrect() {
        String sentence = "yes sir!";
        LinkedHashMap<Character, Integer> expected = new LinkedHashMap<>();
        
        expected.put('y', 1);
        expected.put('e', 1);
        expected.put('s', 2);
        expected.put(' ', 1);
        expected.put('i', 1);
        expected.put('r', 1);
        expected.put('!', 1);
        
        LinkedHashMap<Character, Integer> actual = charCounter.provideCharCount(sentence);
        
        assertThat(actual, is(equalTo(expected)));
    }

}
