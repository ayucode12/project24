package com.devops.service;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordCountService {

    // Count words (returns total count)
    public static int countWords(String text) {
        if (text == null) return 0;
        String trimmed = text.trim();
        if (trimmed.isEmpty()) return 0;
        // Split on any non-letter/digit/apostrophe (keeps contractions as a single word)
        String[] tokens = TOKEN_SPLIT_PATTERN.split(trimmed);
        int count = 0;
        for (String t : tokens) {
            if (!t.isBlank()) count++;
        }
        return count;
    }

    // Return map of word -> frequency (lowercased), sorted by frequency desc
    public static LinkedHashMap<String, Integer> wordFrequencies(String text, int topN) {
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        if (text == null) return result;
        String trimmed = text.trim();
        if (trimmed.isEmpty()) return result;

        String[] tokens = TOKEN_SPLIT_PATTERN.split(trimmed.toLowerCase(Locale.ROOT));
        Map<String, Integer> freq = new HashMap<>();
        for (String t : tokens) {
            if (t == null || t.isBlank()) continue;
            freq.put(t, freq.getOrDefault(t, 0) + 1);
        }

        // sort by frequency desc, then alphabetically
        List<Map.Entry<String, Integer>> sorted = freq.entrySet().stream()
            .sorted((a, b) -> {
                int cmp = b.getValue().compareTo(a.getValue());
                return (cmp != 0) ? cmp : a.getKey().compareTo(b.getKey());
            })
            .collect(Collectors.toList());

        int limit = Math.min(topN, sorted.size());
        for (int i = 0; i < limit; i++) {
            Map.Entry<String, Integer> e = sorted.get(i);
            result.put(e.getKey(), e.getValue());
        }
        return result;
    }

    // Split pattern: split on anything that is not letter, digit, or apostrophe
    private static final Pattern TOKEN_SPLIT_PATTERN = Pattern.compile("[^\\p{L}\\p{Nd}']+");
}
