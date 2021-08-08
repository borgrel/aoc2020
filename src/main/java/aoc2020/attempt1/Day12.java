package aoc2020.attempt1;

import aoc2020.Day;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day12 implements Day {
    @AllArgsConstructor
    enum Move {
        NORTH ("N"),
        EAST("E"),
        SOUTH("S"),
        WEST("W"),
        T_LEFT("L"),
        T_RIGHT("R"),
        FORWARD("F");

        String code;
        //Function<Consumer<Location>, IntConsumer> instruction;
        //Function

        public IntConsumer makeMove(Location location, int distance) {
            //return i -> instruction.accept(location);
            return null;
        }
        static Move parse(String str) {
            for (Move move: Move.values()) {
                if (move.code.equalsIgnoreCase(str)) return move;
            }
            throw new IllegalArgumentException("No value matching '" + str + "' found in Move enum.");
        }
    }
    @Value
    static class Instruc {
        Move direction;
        int distance;
        Instruc(String[] input) {
            direction = Move.parse(input[0]);
            distance = Integer.parseInt(input[1]);
        }
    }
    @Data
    static class Location {
        Move facing;
        int posX;
        int posY;

        Location() {
            facing = Move.EAST;
            posX = 0;
            posY = 0;
        }
        public void moveEast(int distance) {
            posX+=distance;
        }
        public void moveWest(int distance) {
            moveEast(distance*-1);
        }
        public void moveNorth(int distance) {
            posY+=distance;
        }
        public void moveSouth(int distance) {
            moveNorth(distance*-1);
        }
        public void moveForward(int distance) {
        }
        public void turnLeft(int distance) {
            //moveLeft(distance*-1);
        }
        public void turnRight(int distance) {

        }
        public void processInstruc(Instruc instruction) {
            instruction.direction.makeMove(this, instruction.distance);
        }
    }
    private Instruc[] instructions;
    @Override
    public void convertInput(Stream<String> stream) {
        Pattern regex = Pattern.compile("\b");
        instructions = stream.map(regex::split)
                .map(Instruc::new)
                .toArray(Instruc[]::new);
    }

    @Override
    public void part1() {

    }

    @Override
    public void part2() {

    }
}
