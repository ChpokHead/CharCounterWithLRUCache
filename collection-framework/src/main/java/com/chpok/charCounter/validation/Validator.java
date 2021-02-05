package com.chpok.charCounter.validation;

public class Validator implements ValidationProvider{
    @Override
    public void validate(String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("Sentence is null");
        }
        
        if (sentence.isEmpty()) {
            throw new IllegalArgumentException("Sentence is empty!");
        }
        
        if (sentence.trim().isEmpty()) {
            throw new IllegalArgumentException("Sentence contains only spaces or tabs");
        }
    }
    
}
