package aoc2020.attempt1;

import aoc2020.Day;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day03Test {
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
    @Test
    void convertInput() {
        Day day = new Day03();
        day.convertInput(Arrays.stream(testInput));
        day.part1();
        day.part2();
    }
}