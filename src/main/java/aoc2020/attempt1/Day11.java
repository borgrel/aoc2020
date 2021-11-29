package aoc2020.attempt1;

import aoctools.Day;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day11 implements Day {
    enum Seat {
        FLOOR('.'),
        SEAT('L'),
        OCCUPIED('#');

        char value;
        Seat(char value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return String.valueOf(value);
        }
        public static String toString(Seat[] seats) {
            return Arrays.stream(seats)
                    .map(Seat::toString)
                    .collect(Collectors.joining());
        }
        public static Seat parseValue(char value) {
            for (Seat seat: Seat.values()) {
                if (seat.value == value) return seat;
            }
            throw new IllegalArgumentException("enum Seat does not include an element mapped to '" + value + "'");
        }
        public static Seat[] parseValues(String values) {
            return values.chars()
                    .mapToObj(i -> parseValue((char)i))
                    .toArray(Seat[]::new);
        }
    }
    Seat[][] seating;
    @Override
    public void convertInput(Stream<String> stream) {
        seating = stream.map(Seat::parseValues)
                .toArray(Seat[][]::new);
    }

    public int checkAdjacent(Seat[][] layout, int indexX, int indexY) {
        int count = 0;
        for (int x=indexX-1; x<=indexX+1; x++) {
            if (x < 0 || x >= layout.length) continue;
            for (int y=indexY-1; y <=indexY+1; y++) {
                if (y < 0 || y >= layout.length) continue;
                if (x == indexX && y == indexY) continue;
                if (layout[x][y] == Seat.OCCUPIED) count++;
            }
        }
        return count;
    }
    public Seat applyRules(Seat[][] layout, int indexX, int indexY) {
        switch (layout[indexX][indexY]) {
            case FLOOR: return Seat.FLOOR;
            case SEAT: return (checkAdjacent(layout, indexX, indexY) ==0 )?
                    Seat.OCCUPIED:Seat.SEAT;
            case OCCUPIED: return (checkAdjacent(layout, indexX, indexY) >=4 )?
                    Seat.SEAT:Seat.OCCUPIED;
        }
        return null;
    }
    @Override //2519 too high
    public void part1() {
        Seat[][] increment = Arrays.stream(seating)
                .map(i -> Arrays.copyOf(i,i.length))
                .toArray(Seat[][]::new);

        boolean changing = true;
        //int test = 0;
        while (changing) {
            boolean hasChanged = false;
            Seat[][] temp = new Seat[increment.length][increment[0].length];
            for(int i=0; i<seating.length; i++) {
                for (int j=0; j<seating[i].length; j++) {
                    temp[i][j] = applyRules(increment,i,j);
                    if (temp[i][j] != increment[i][j]) hasChanged = true;
                }
            }
            //DayRunner.printArray(temp);
            //System.out.println();
            if (!hasChanged) changing = false;
            increment = temp;
            //test++;
        }
        long count = Arrays.stream(increment)
                .flatMap(Arrays::stream)
                .filter(seat -> seat == Seat.OCCUPIED)
                .count();

        Arrays.stream(increment)
                .map(Seat::toString)
                .forEach(System.out::println);
        System.out.println(count);
    }

    @Override
    public void part2() {

    }
}
