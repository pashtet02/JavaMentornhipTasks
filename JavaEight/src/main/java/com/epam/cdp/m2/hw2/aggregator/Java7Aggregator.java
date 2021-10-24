package com.epam.cdp.m2.hw2.aggregator;

import java.util.*;

public class Java7Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }

    @Override
    public List<Map<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        Map<String, Long> mostFrequentWords = new HashMap<>();
        for (String word : words) {
            long occurrences = 0;
            // get the previous count
            if (mostFrequentWords.get(word) != null) {
                occurrences = mostFrequentWords.get(word);
            }
            mostFrequentWords.put(word, occurrences + 1);
        }

        List<Map<String, Long>> wordFrequencies = new ArrayList<>();
        for (Map.Entry<String, Long> entry : mostFrequentWords.entrySet()) {
            if (wordFrequencies.size() < limit){
                wordFrequencies.add(Map.of(entry.getKey(), entry.getValue()));

            } else break;
        }
        return wordFrequencies;
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
        duplicates.sort(new Comparator<String>() {
            @Override
            public int compare(String wordOne, String wordTwo) {
                int i = wordOne.length() - wordTwo.length();
                if (i != 0) return i;

                return wordOne.compareTo(wordTwo);
            }
        });
        return duplicates;
    }

    public static void main(String[] args) {
        ArrayList<String> list = null;
        for (String str: list) {
            str.toUpperCase(Locale.ROOT);
        }
    }
}
