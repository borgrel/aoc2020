package aoc2020.attempt1;

import aoc2020.Day;

import java.util.stream.Stream;

public class Day01 implements Day {
    final int GOAL = 2020;
    int[] values;

    @Override
    public void convertInput(Stream<String> stream) {
        values = stream.mapToInt(Integer::parseInt)
                    .sorted()
                    .toArray();
    }

    private int findSum(int start, int end, int goal) {
        int index1 = start;
        int index2 = end;
        while (index1 < index2) {
            int sum = values[index1] + values[index2];

            if (sum < goal)
                index1++;
            else if (sum > goal)
                index2--;
            else return values[index1];
        }
        return -1;
    }

    //388075
    @Override
    public void part1() {
        int value1 = findSum(0, values.length-1, GOAL);

        if (value1 != -1) {
            System.out.println(value1*(GOAL- value1));
        }
    }

    //293450526
    @Override
    public void part2() {
        for (int index1=0; index1 < values.length; index1++) {
            int value2 = findSum(index1+1, values.length-1, GOAL - values[index1]);
            if (value2 != -1) {
                int value3 = GOAL - values[index1] - value2;
                System.out.println(values[index1]*value2*value3);
            }
        }
    }
}
