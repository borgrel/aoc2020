package aoc2020.attempt1;

import aoc2020.Day;
import aoc2020.DayRunner;

import java.util.stream.Stream;

public class Day03 implements Day {
    static final char TREE = '#';
    String[] map;
    @Override
    public void convertInput(Stream<String> stream) {
        map = stream.toArray(String[]::new);
    }

    private int followSlope(int deltaX, int deltaY) {
        int posX = 0;
        int collisions = 0;
        for (int posY = 0; posY < map.length; posY+=deltaY, posX+=deltaX) {
            if (map[posY].charAt(posX%map[posY].length()) == TREE) {
                collisions++;
            }
        }
        return collisions;
    }
    @Override
    public void part1() {
        System.out.println(followSlope(3,1));
    }

    @Override // 70, 220, 63, 76, 29 = 2138320800
    public void part2() {
        long result = 1;

        result*= followSlope(1,1);
        result*= followSlope(3,1);
        result*= followSlope(5,1);
        result*= followSlope(7,1);
        result*= followSlope(1,2);
        System.out.println(result);
    }
}
