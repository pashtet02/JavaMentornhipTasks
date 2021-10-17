package com.epam.cdp.m2.hw2.aggregator;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.*;

public class Java8Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public List<Map<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        Map<String, Long> wordCountMap = words.stream()
                .collect(groupingBy(identity(), counting()));

        return wordCountMap.entrySet()
                .stream()
                .map(entry -> Map.of(entry.getKey(), entry.getValue()))
                .limit(limit)
                .collect(toList());
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Set<String> items = new HashSet<>();
        return words.stream()
                .sorted(comparing(String::length).thenComparing(naturalOrder()))
                .map(word -> word.toUpperCase(Locale.ROOT))
                .filter(n -> !items.add(n))
                .limit(limit)
                .collect(Collectors.toList());
    }
}