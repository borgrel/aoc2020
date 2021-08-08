package aoc2020.attempt1;

import aoc2020.Day;

import java.util.Arrays;

public class Day11Test implements Tester {
    final String[] testInput = new String[]{
            "#.##.##.##",
            "#######.##",
            "#.#.#..#..",
            "####.##.##",
            "#.##.##.##",
            "#.#####.##",
            "..#.#.....",
            "##########",
            "#.######.#",
            "#.#####.##"
    };

    @Override
    public void testExamples() {
        Day day = new Day11();
        day.convertInput(Arrays.stream(testInput));
        day.part1();
        day.part2();
    }
}
