package com.epam.cdp.m2.hw2.aggregator.tasks;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class WordsFrequenciesCounter extends RecursiveTask<Map<String, Long>> {
    private static final int SEQUENTIAL_THRESHOLD = 20;
    private final Map<String, Long> mostFrequentWords = Collections.synchronizedMap(new HashMap<>());
    private final int low;
    private final int high;
    private final List<String> words;

    public WordsFrequenciesCounter(List<String> words, int low, int high) {
        this.words = words;
        this.low = low;
        this.high = high;
    }

    protected Map<String, Long> compute() {
        if (high - low <= SEQUENTIAL_THRESHOLD) {
            for (String word : words) {
                long occurrences = 0;
                // get the previous count
                if (mostFrequentWords.get(word) != null) {
                    occurrences = mostFrequentWords.get(word);
                }
                mostFrequentWords.put(word, occurrences + 1);
            }
            return mostFrequentWords;
        } else {
            int mid = low + (high - low) / 2;
            WordsFrequenciesCounter left = new WordsFrequenciesCounter(words, low, mid);
            WordsFrequenciesCounter right = new WordsFrequenciesCounter(words, mid, high);
            left.fork();
            Map<String, Long> rightAns = right.compute();
            Map<String, Long> leftAns = left.join();
            leftAns.putAll(rightAns);
            return leftAns;
        }
    }
}
