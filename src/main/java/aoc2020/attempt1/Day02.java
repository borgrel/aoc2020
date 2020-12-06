package aoc2020.attempt1;

import aoc2020.Day;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day02 implements Day {
    public static final int MIN_VALUE = 0;
    public static final int MAX_VALUE = 1;
    public static final int CHAR_VALUE = 2;
    public static final int PWD_VALUE = 3;

    //6-9 d: dddddkzdl
    String splitRegex = "-|(:\\s)|\\s";
    String[][] passwords;

    @Override
    public void convertInput(Stream<String> stream) {
        Pattern p = Pattern.compile(splitRegex);
        passwords = stream.map(p::split)
                .toArray(String[][]::new);
    }
    //Deprecated
    public boolean isValid(String[] input) {
        int low = Integer.parseInt(input[MIN_VALUE]);
        int high = Integer.parseInt(input[MAX_VALUE]);
        int value = input[PWD_VALUE].length() - input[PWD_VALUE].replaceAll(input[CHAR_VALUE],"").length();
        return (value >= low && value <= high);
    }
    String regexMaker(String min, String max, String ch) {
        return "[^" + ch + "]*(" + ch + "[^" + ch + "]*){" + min + "," + max + "}";
    }
    public boolean isValidPart1(String[] input) {
        String regex = regexMaker(input[MIN_VALUE],input[MAX_VALUE],input[CHAR_VALUE]);
        return input[PWD_VALUE].matches(regex);
    }

    //447
    @Override
    public void part1() {
        System.out.println(
                Arrays.stream(passwords)
                    .filter(this::isValidPart1)
                    .count() );
    }
    //REMEMBER to adjust for no 0 index !!!!!
    public boolean isValidPart2(String[] input) {
        int low = Integer.parseInt(input[MIN_VALUE]);
        int high = Integer.parseInt(input[MAX_VALUE]);
        char compare = input[CHAR_VALUE].charAt(0);
        return (input[PWD_VALUE].charAt(low-1) == compare) ^ (input[PWD_VALUE].charAt(high-1) == compare);
    }
    //249
    @Override
    public void part2() {
        System.out.println(
                Arrays.stream(passwords)
                        .filter(this::isValidPart2)
                        .map(s -> s[PWD_VALUE])
                        .count() );
    }
}
