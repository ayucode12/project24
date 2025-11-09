package com.devops.service;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class WordCountServiceTest {

    @Test
    void countWords_emptyOrNull() {
        assertEquals(0, WordCountService.countWords(null));
        assertEquals(0, WordCountService.countWords(""));
        assertEquals(0, WordCountService.countWords("   "));
    }

    @Test
    void countWords_basic() {
        String text = "Hello world! This is a test.";
        assertEquals(6, WordCountService.countWords(text)); // Hello world This is a test
    }

    @Test
    void wordFrequencies_top() {
        String text = "apple banana apple orange banana apple";
        LinkedHashMap<String, Integer> top = WordCountService.wordFrequencies(text, 2);
        assertEquals(2, top.size());
        assertTrue(top.containsKey("apple"));
        assertEquals(3, top.get("apple"));
        assertEquals(2, top.get("banana"));
    }

    @Test
    void count_with_punctuation_and_newlines() {
        String text = "Hello,\nHello!  Are you there? Yes.";
        assertEquals(6, WordCountService.countWords(text)); // Hello Hello Are you there Yes
    }
}
