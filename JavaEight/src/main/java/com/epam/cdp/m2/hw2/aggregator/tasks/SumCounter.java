package com.epam.cdp.m2.hw2.aggregator.tasks;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SumCounter extends RecursiveTask<Integer> {
    private static final int SEQUENTIAL_THRESHOLD = 5000;

    private final int low;
    private final int high;
    private final List<Integer> numbers;

    public SumCounter(List<Integer> numbers, int low, int high) {
        this.numbers = numbers;
        this.low = low;
        this.high = high;
    }

    protected Integer compute() {
        if (high - low <= SEQUENTIAL_THRESHOLD) {
            int sum = 0;
            for (int i = low; i < high; ++i)
                sum += numbers.get(i);
            return sum;
        } else {
            int mid = low + (high - low) / 2;
            SumCounter left = new SumCounter(numbers, low, mid);
            SumCounter right = new SumCounter(numbers, mid, high);
            left.fork();
            int rightAns = right.compute();
            int leftAns = left.join();
            return leftAns + rightAns;
        }
    }
}
