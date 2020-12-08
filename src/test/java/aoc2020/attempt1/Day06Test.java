package aoc2020.attempt1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day06Test implements Tester {
    String[] test = new String[]{
            "abc", "",
            "a", "b", "c", "",
            "ab", "ac", "",
            "a", "a", "a", "a", "",
            "b"
    };

    @Override
    public void testExamples() {
        Day06 day = new Day06();
        day.convertInput(Arrays.stream(test));
        day.part1();
        day.part2();
    }
}