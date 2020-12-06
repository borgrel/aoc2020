package aoc2020.attempt1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day06Test {
    String[] test = new String[]{
            "abc", "",
            "a", "b", "c", "",
            "ab", "ac", "",
            "a", "a", "a", "a", "",
            "b"
    };

    @Test
    void convertInput() {
        Day06 day = new Day06();
        day.convertInput(Arrays.stream(test));
        day.part1();
        day.part2();
    }
}