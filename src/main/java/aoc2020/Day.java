package aoc2020;

import java.util.stream.Stream;

public interface Day {
    void convertInput(Stream<String> stream);
    void part1();
    void part2();
}
