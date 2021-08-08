package aoc2020.attempt1;

import aoc2020.Day;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day10 implements Day {
    int[] adapters;
    @Override
    public void convertInput(Stream<String> stream) {
        Stream<String> catStream = Stream.concat(Stream.of("0"),stream);
        adapters = catStream.mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        adapters = Arrays.copyOf(adapters,adapters.length+1);
        adapters[adapters.length-1] = adapters[adapters.length-2]+3;
    }
    @Override //1690
    public void part1() {
        int[] results = new int[2];
        IntStream.range(1, adapters.length)
                //map a differance of 1 to 0 and a diff of 3 to 1
                .map( i -> (adapters[i] - adapters[i-1]-1)/2)
                .forEach(i -> results[i]++);
        System.out.println(results[0] * results[1]);
    }

    @Override //5 289 227 976 704
    public void part2() {
        long[] solution = new long[adapters.length];
        solution[solution.length-1] = 1;
        for (int i=solution.length-2; i>=0; i--) {
            int temp = i+1;
            int test = adapters[i]+3;
            while (temp < solution.length && adapters[temp] <= test) {
                solution[i] += solution[temp];
                temp++;
            }
        }
        System.out.println(solution[0]);
    }
}
