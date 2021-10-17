package com.epam.cdp.m2.hw2.aggregator.tasks;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class ListSorter extends RecursiveTask<List<String>> {
    private static final int SEQUENTIAL_THRESHOLD = 20;

    private final int low;
    private final int high;
    private final List<String> words;

    public ListSorter(List<String> words, int low, int high) {
        this.words = words;
        this.low = low;
        this.high = high;
    }

    protected List<String> compute() {
        if (high - low <= SEQUENTIAL_THRESHOLD) {
            words.sort(new Comparator<>() {
                @Override
                public int compare(String wordOne, String wordTwo) {
                    int i = wordOne.length() - wordTwo.length();
                    if (i != 0) return i;

                    return wordOne.compareTo(wordTwo);
                }
            });
            return words;
        } else {
            int mid = low + (high - low) / 2;
            ListSorter left = new ListSorter(words, low, mid);
            ListSorter right = new ListSorter(words, mid, high);
            left.fork();
            List<String> rightAns = right.compute();
            List<String> leftAns = left.join();
            leftAns.addAll(rightAns);
            return leftAns;
        }
    }
}
