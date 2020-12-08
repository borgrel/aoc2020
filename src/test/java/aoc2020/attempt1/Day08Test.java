package aoc2020.attempt1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day08Test implements Tester{
    static String[] test = new String[]{
            "nop +0",
            "acc +1",
            "jmp +4",
            "acc +3",
            "jmp -3",
            "acc -99",
            "acc +1",
            "jmp -4",
            "acc +6"
    };

    @Override
    public void testExamples() {
        Day08 day = new Day08();
        day.convertInput(Arrays.stream(test));
        day.part1();
        day.part2();
    }
}