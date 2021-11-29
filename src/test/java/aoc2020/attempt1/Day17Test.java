package aoc2020.attempt1;

import aoctools.Day;

import java.util.Arrays;

public class Day17Test implements Tester {
    final String[] testInput = new String[]{
            ".#.",
            "..#",
            "###"
    };


    @Override
    public void testExamples() {
        Day day = new Day17();
        day.convertInput(Arrays.stream(testInput));
        day.part1();
        day.part2();
    }
}