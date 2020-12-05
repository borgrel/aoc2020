package aoc2020.attempt1;

import aoc2020.Day;
import aoc2020.DayRunner;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day05Test {
    static final String[] test = new String[]{
            "BFFFBBFRRR",
            "FFFBBBFRRR",
            "BBFFBBFRLL"
        };

    /*
        BFFFBBFRRR: row 70, column 7, seat ID 567.
        FFFBBBFRRR: row 14, column 7, seat ID 119.
        BBFFBBFRLL: row 102, column 4, seat ID 820.
    */
    @Test
    void convertInput() {
        Day05 day05 = new Day05();
        day05.convertInput(Arrays.stream(test));
        day05.printBoardingPasses();
        day05.part1();
    }
}