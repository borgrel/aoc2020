package aoc2020.attempt1;

import aoc2020.Day;
import aoc2020.DayRunner;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Day04Test implements Tester {
    static String[] testInput = new String[]{
            "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
            "byr:1937 iyr:2017 cid:147 hgt:183cm",
            "",
            "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884",
            "hcl:#cfa07d byr:1929",
            "",
            "hcl:#ae17e1 iyr:2013",
            "eyr:2024",
            "ecl:brn pid:760753108 byr:1931",
            "hgt:179cm",
            "",
            "hcl:#cfa07d eyr:2025 pid:166559648",
            "iyr:2011 ecl:brn hgt:59in"
    };
    static String[] part2Invalid = new String[]{
            "eyr:1972 cid:100 hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926",
            "",
            "iyr:2019 hcl:#602927 eyr:1967 hgt:170cm ecl:grn pid:012533040 byr:1946",
            "",
            "hcl:dab227 iyr:2012 ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277",
            "",
            "hgt:59cm ecl:zzz eyr:2038 hcl:74454a iyr:2023 pid:3556412378 byr:2007"
    };
    static String[] part2Valid = new String[]{
            "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980 hcl:#623a2f",
            "",
            "eyr:2029 ecl:blu cid:129 byr:1989 iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm",
            "",
            "hcl:#888785 hgt:164cm byr:2001 iyr:2015 cid:88 pid:545766238 ecl:hzl eyr:2022",
            "",
            "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"
    };

    void testValidity(String[] input) {
        Day day = new Day04();
        day.convertInput(Arrays.stream(input));
        day.part1();
        day.part2();
    }
    @Override
    public void testExamples() {
        Arrays.stream(testInput)
                        .collect(DayRunner.toTextBlock())
                        .forEach(System.out::println);
        testValidity(testInput);
        testValidity(part2Invalid);
        testValidity(part2Valid);
    }

    /*
    byr valid:   2002
    byr invalid: 2003

    hgt valid:   60in
    hgt valid:   190cm
    hgt invalid: 190in
    hgt invalid: 190

    hcl valid:   #123abc
    hcl invalid: #123abz
    hcl invalid: 123abc

    ecl valid:   brn
    ecl invalid: wat

    pid valid:   000000001
    pid invalid: 0123456789
    */
    public static void test(Day04.PassportTags tag, String value) {
        System.out.println(tag.name() + " tests " + value + " as " + tag.isValid(value));
    }
    //@Test
    void individualTests() {
        test(Day04.PassportTags.byr, "2002");
        test(Day04.PassportTags.byr, "2003");
        System.out.println();

        test(Day04.PassportTags.hgt, "60in");
        test(Day04.PassportTags.hgt, "190cm");
        test(Day04.PassportTags.hgt, "158cm");
        test(Day04.PassportTags.hgt, "190in");
        test(Day04.PassportTags.hgt, "190");
        System.out.println();

        test(Day04.PassportTags.hcl, "#123abc");
        test(Day04.PassportTags.hcl, "#123abz");
        test(Day04.PassportTags.hcl, "123abc");
        System.out.println();

        test(Day04.PassportTags.ecl, "brn");
        test(Day04.PassportTags.ecl, "wat");
        System.out.println();

        test(Day04.PassportTags.pid, "000000001");
        test(Day04.PassportTags.pid, "0123456789");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        //"pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980 hcl:#623a2f"
        test(Day04.PassportTags.pid, "087499704");
        test(Day04.PassportTags.hgt, "74in");
        test(Day04.PassportTags.ecl, "grn");
        test(Day04.PassportTags.iyr, "2012");
        test(Day04.PassportTags.eyr, "2030");
        test(Day04.PassportTags.byr, "1980");
        test(Day04.PassportTags.hcl, "#623a2f");
    }


}