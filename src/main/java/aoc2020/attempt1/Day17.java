package aoc2020.attempt1;

import aoctools.Cubes;
import aoctools.Day;

import java.util.stream.Stream;

public class Day17 implements Day {
    Cubes initialState;
    Cubes part1State;

    @Override
    public void convertInput(Stream<String> stream) {
        initialState = new Cubes(stream);
    }

    @Override //319
    public void part1() {
        part1State = initialState;
        for (int i=1; i<=6; i++) {
            part1State = new Cubes(part1State);
            //System.out.println(part1State);
        }
        System.out.println(part1State.getActiveCubes().count());
    }

    @Override
    public void part2() {

    }
}
