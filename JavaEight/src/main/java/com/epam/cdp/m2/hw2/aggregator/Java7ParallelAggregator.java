package com.epam.cdp.m2.hw2.aggregator;

import com.epam.cdp.m2.hw2.aggregator.tasks.ListSorter;
import com.epam.cdp.m2.hw2.aggregator.tasks.SumCounter;
import com.epam.cdp.m2.hw2.aggregator.tasks.WordFrequenciesFiller;
import com.epam.cdp.m2.hw2.aggregator.tasks.WordsFrequenciesCounter;

import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class Java7ParallelAggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        return ForkJoinPool.commonPool()
                .invoke(new SumCounter(numbers, 0, numbers.size()));
    }

    @Override
    public List<Map<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        Map<String, Long> mostFrequentWords = ForkJoinPool.commonPool()
                .invoke(new WordsFrequenciesCounter(words, 0, words.size()));
        return ForkJoinPool.commonPool()
                .invoke(new WordFrequenciesFiller(mostFrequentWords,0, mostFrequentWords.size(), limit));
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Set<String> items = new HashSet<>();
        List<String> duplicates = new ArrayList<>();

        for (String element : words) {
            if (duplicates.size() < limit) {
                String word = element.toUpperCase(Locale.ROOT);
                if (!items.add(word)) {
                    duplicates.add(word);
                }
            } else break;
        }
        return ForkJoinPool.commonPool()
                .invoke(new ListSorter(duplicates, 0, duplicates.size()));
    }
}
