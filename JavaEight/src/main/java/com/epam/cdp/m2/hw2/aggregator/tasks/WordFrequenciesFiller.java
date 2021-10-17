package com.epam.cdp.m2.hw2.aggregator.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class WordFrequenciesFiller extends RecursiveTask<List<Map<String, Long>>> {
    static final int SEQUENTIAL_THRESHOLD = 10;
    private final List<Map<String, Long>> wordFrequencies = new ArrayList<>();
    private final int low;
    private final int high;
    private final long limit;
    Map<String, Long> wordFrequenciesMap;

    public WordFrequenciesFiller(Map<String, Long> wordFrequenciesMap, int low, int high, long limit) {
        this.wordFrequenciesMap = wordFrequenciesMap;
        this.low = low;
        this.high = high;
        this.limit = limit;
    }

    protected List<Map<String, Long>> compute() {
        if (high - low <= SEQUENTIAL_THRESHOLD) {
            for (Map.Entry<String, Long> entry : wordFrequenciesMap.entrySet()) {
                if (wordFrequencies.size() < limit){
                    synchronized (this){
                        wordFrequencies.add(Map.of(entry.getKey(), entry.getValue()));
                    }
                } else break;
            }
            return wordFrequencies;
        } else {
            int mid = low + (high - low) / 2;
            WordFrequenciesFiller left = new WordFrequenciesFiller(wordFrequenciesMap, low, mid, limit);
            WordFrequenciesFiller right = new WordFrequenciesFiller(wordFrequenciesMap, mid, high, limit);
            left.fork();
            List<Map<String, Long>> rightAns = right.compute();
            List<Map<String, Long>> leftAns = left.join();
            leftAns.addAll(rightAns);
            return leftAns;
        }
    }
}