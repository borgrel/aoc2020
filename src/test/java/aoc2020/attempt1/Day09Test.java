package aoc2020.attempt1;

import aoc2020.Day;

import java.util.Arrays;

public class Day09Test implements Tester {
    final String[] testInput = new String[]{
            "35",
            "20",
            "15",
            "25",
            "47",
            "40",
            "62",
            "55",
            "65",
            "95",
            "102",
            "117",
            "150",
            "182",
            "127",
            "219",
            "299",
            "277",
            "309",
            "576"
    };

    @Override //127 must change preamble in part1 to 5
    public void testExamples() {
        Day09 day = new Day09();
        day.convertInput(Arrays.stream(testInput));
        day.part1();
        day.part2();
    }
}
