package com.epam.cdp.m2.hw2.aggregator.frequency;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runners.Parameterized;

import com.epam.cdp.m2.hw2.aggregator.Aggregator;

public abstract class JavaAggregatorFrequencyTest {

    @Parameterized.Parameter(0)
    public long limit;

    @Parameterized.Parameter(1)
    public List<String> words;

    @Parameterized.Parameter(2)
    public List<Map<String, Long>> expected;

    private Aggregator aggregator;

    public JavaAggregatorFrequencyTest(Aggregator aggregator) {
        this.aggregator = aggregator;
    }

    @Parameterized.Parameters
    public static List<Object[]> data() {
        List<Object[]> data = new ArrayList<>();
        data.add(new Object[]{2, asList("f", "c", "b", "b", "b", "c"), asList(Map.of("b", 3L), Map.of("c", 2L))});
        data.add(new Object[]{2, asList("f", "f"), asList(Map.of("f", 2L))});
        data.add(new Object[]{2, asList("f"), asList(Map.of("f", 1L))});
        data.add(new Object[]{2, asList("a", "b", "b", "a"), asList(Map.of("a", 2L), Map.of("b", 2L))});
        data.add(new Object[]{2, Collections.emptyList(), Collections.emptyList()});
        return data;
    }

    @Test
    public void test() {
        List<Map<String, Long>> actual = aggregator.getMostFrequentWords(words, limit);
        assertEquals(expected, actual);
    }
}