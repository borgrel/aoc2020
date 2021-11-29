package aoc2020.attempt1;

import aoctools.Day;

import java.util.Arrays;

class Day03Test implements Tester {
    final String[] testInput = new String[]{
        "..##.......",
        "#...#...#..",
        ".#....#..#.",
        "..#.#...#.#",
        ".#...##..#.",
        "..#.##.....",
        ".#.#.#....#",
        ".#........#",
        "#.##...#...",
        "#...##....#",
        ".#..#...#.#"  };

    @Override
    public void testExamples() {
        Day day = new Day03();
        day.convertInput(Arrays.stream(testInput));
        day.part1();
        day.part2();
    }
}