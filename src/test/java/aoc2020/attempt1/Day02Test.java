package aoc2020.attempt1;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day02Test {

    /*
    1-3 a: abcde
    1-3 b: cdefg
    2-9 c: ccccccccc

    2 are valid
    */
    @Test
    void convertInput() {
        String[] example = new String[]{"1-3 a: abcde","1-3 b: cdefg","2-9 c: ccccccccc"};

        Day02 daytest = new Day02();
        daytest.convertInput(Arrays.stream(example));
        daytest.part1();
        daytest.part2();
    }
    void testRegexHelper(String value, String regex) {
        System.out.println(value + " matches " + regex + ":- " + value.matches(regex));
    }
    String regexMaker(int min, int max, String ch) {
        return "[^" + ch + "]*(" + ch + "[^" + ch + "]*){" + min + "," + max + "}";
    }
    @Test
    void textRegex() {
        String regex = "(a.*?){1,3}";
        testRegexHelper("abcde",regex);
        testRegexHelper("abcde",regexMaker(1,3,"a"));
        testRegexHelper("abcadde",regex);

        testRegexHelper("rthcgnxmm",regexMaker(2,8,"m"));
        testRegexHelper("kdndzd",regexMaker(1,3,"d"));
        testRegexHelper("lgtr",regexMaker(1,3,"r"));
        testRegexHelper("kdndzd",regexMaker(2,8,"m"));
    }
}