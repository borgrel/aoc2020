package aoc2020.attempt1;

import java.util.Arrays;

public class Day10Test implements Tester {
    final String[] testInput = new String[]{
            "16","10","15","5","1","11","7","19","6","12","4"
    };
    final String[] testInput2 = new String[]{
            "28","33","18","42","31","14","46","20","48","47",
            "24","23","49","45","19","38","39","11","1","32",
            "25","35","8","17","7","9","4","2","34","10","3"
    };

    @Override //35 8 //220 19208
    public void testExamples() {
        Day10 day = new Day10();
        day.convertInput(Arrays.stream(testInput));
        day.part1();
        day.part2();
        day.convertInput(Arrays.stream(testInput2));
        day.part1();
        day.part2();
    }
}
