package aoc2020.attempt1;

import aoc2020.Day;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.stream.Stream;

public class Day05 implements Day {
    public static final char B = '1';
    public static final char F = '0';
    public static final char R = '1';
    public static final char L = '0';

    private int[] seatID;
    IntSummaryStatistics stats;

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
        seatID = stream.map(s -> s.replaceAll("[BR]","1"))
                .map(s -> s.replaceAll("[FL]","0"))
                .mapToInt(s -> Integer.parseInt(s,2))
                .toArray();
    }

    @Override //951
    public void part1() {
        stats = Arrays.stream(seatID)
            .summaryStatistics();

        System.out.println(stats.getMax());
    }

    @Override //653
    public void part2() {
        long sum = stats.getSum()*2; //multiply sum by 2 instead of dividing to avoid floating pnt arithmetic
        long count = stats.getCount()+1; //assume sequence is 1 longer because of missing term
        //S = n(min + max)/2
        long sequence = (count)*(stats.getMin() + stats.getMax());
        long result = sequence - sum;
        if (result%2 == 1)
            throw new ArithmeticException("This calculation done fucked up!");

        System.out.println(result/2);
    }
}
