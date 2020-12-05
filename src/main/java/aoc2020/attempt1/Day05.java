package aoc2020.attempt1;

import aoc2020.Day;

import java.util.Arrays;
import java.util.stream.Stream;

public class Day05 implements Day {
    public static final char B = '1';
    public static final char F = '0';
    public static final char R = '1';
    public static final char L = '0';

    private int[] seatID;

    //private static final int rowMask = 0b1111111000;
    public int rowNum(int seatID) {
        return seatID >> 3;
    }
    private static final int colMask = 0b111;
    public int colNum(int seatID) {
        return seatID & colMask;
    }
    public void printBoardingPasses() {
        Arrays.stream(seatID)
                .mapToObj(i -> "row " + rowNum(i) + ", col " + colNum(i) + ", seatID " + i)
                .forEach(System.out::println);
    }

    @Override
    public void convertInput(Stream<String> stream) {
        seatID = stream.map(s -> s.replace('F',F))
                .map(s -> s.replace('B',B))
                .map(s -> s.replace('R',R))
                .map(s -> s.replace('L',L))
                .mapToInt(s -> Integer.parseInt(s,2))
                .toArray();
    }

    @Override
    public void part1() {
        Arrays.stream(seatID)
            .max()
            .ifPresent(System.out::println);
    }

    @Override
    public void part2() {
        int value = Arrays.stream(seatID)
                .reduce( (a,b) -> a^b)
                .orElse(-1);
        System.out.println(value + 1);
        
        int[] sorted = Arrays.stream(seatID)
                .sorted()
                .toArray();
        int offset = sorted[0];
        int index = value + 1 - offset;
        int[] test = Arrays.copyOfRange(sorted,index-2,index+2);
        System.out.println(Arrays.toString(test));
    }
}
