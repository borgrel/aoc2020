package aoc2020.attempt1;

import aoctools.Day;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public class Day06 implements Day {
    private final int GROUP_COUNT = 0;
    private final int OFFSET = 'a' - 1; //-1 because values[0] is holding the group count
    private int[][] values;

    private int[] tempArray;
    public int[] parseInput(String input) {
        if (input.isEmpty()) {
            int[] temp = tempArray;
            tempArray = null;
            return temp;
        }
        if (tempArray == null) tempArray = new int[27];

        tempArray[GROUP_COUNT]++;
        for (char ch: input.toCharArray()) {
            tempArray[ch-OFFSET]++;
        }
        return null;
    }

    @Override
    public void convertInput(Stream<String> stream) {
        Stream<String> appendedStream = Stream.concat(
                stream,
                Stream.of(""));

        values = appendedStream.map(this::parseInput)
                .filter(Objects::nonNull)
                .toArray(int[][]::new);
    }

    @Override //6911
    public void part1() {
        System.out.println(
                Arrays.stream(values)
                        .flatMapToInt( arr -> Arrays.stream(arr)
                                .skip(1))
                        .filter(i -> i !=0)
                        .count()
        );
    }

    @Override //3473
    public void part2() {
        System.out.println(
                Arrays.stream(values)
                        .flatMapToInt(arr -> {
                            int groupNum = arr[0];
                            return Arrays.stream(arr)
                                    .skip(1)
                                    .filter(i -> i == groupNum);
                        })
                        .count()
        );
    }
}
