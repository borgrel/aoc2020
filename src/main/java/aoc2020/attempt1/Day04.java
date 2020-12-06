package aoc2020.attempt1;

import aoc2020.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day04 implements Day {
    List<Passport> passports;

    enum PassportTags {
            // (Birth Year) - four digits; at least 1920 and at most 2002.
        byr ("Birth Year",str -> testValue(str,1920,2002)),
            // (Issue Year) - four digits; at least 2010 and at most 2020.
        iyr ("Issue Year", str -> testValue(str,2010,2020)),
            // (Expiration Year) - four digits; at least 2020 and at most 2030.
        eyr ("Expiration Year", str -> testValue(str,2020,2030)),
            //  (Height) - a number followed by either cm or in:
            //         If cm, the number must be at least 150 and at most 193.
            //         If in, the number must be at least 59 and at most 76.
        hgt ("Height",Pattern.compile("^((1[5-8][0-9]|19[0-3])cm)|((59|6[0-9]|7[0-6])in)$").asMatchPredicate()),
            // (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
        hcl ("Hair Color",Pattern.compile("^#[0-9a-f]{6}$").asMatchPredicate()),
            // (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
        ecl ("Eye Color",Pattern.compile("^(amb|blu|brn|gry|grn|hzl|oth)$").asMatchPredicate()),
            // (Passport ID) - a nine-digit number, including leading zeroes.
        pid ("Passport ID",Pattern.compile("^\\d{9}$").asMatchPredicate()),
            // (Country ID) - ignored, missing or not.
        cid ("Country ID",str -> true);

        String description;
        Predicate<String> testValid;

        PassportTags(String description,Predicate<String> testValid) {
            this.description = description;
            this.testValid = testValid;
        }
        boolean isValid(String value) {
            return testValid.test(value);
        }
        private static boolean testValue(String string, int min, int max) {
            int value = Integer.parseInt(string);
            if (value < min) return false;
            return value <= max;
        }
        static PassportTags parseTag(String input) {
            for (PassportTags check: PassportTags.values()) {
                if (check.name().equals(input))
                    return check;
            }
            return null;
        }

        static EnumMap<PassportTags, String> mapPassportTags(int start, int end, List<String[]> list) {
            EnumMap<PassportTags, String> map = new EnumMap<>(PassportTags.class);
            for (int i = start; i < end; i++) {
                PassportTags tag = PassportTags.valueOf(list.get(i)[0]);
                map.put(tag, list.get(i)[1]);
            }
            return map;
        }
    }
    static class Passport {
        private EnumMap<PassportTags, String> values;

        Passport(EnumMap<PassportTags, String> values) {
            this.values = values;
        }
        boolean isValid() {
            int size = values.size();
            if (size == 8) return true;
            return size == 7 && values.get(PassportTags.cid) == null;
        }
        boolean isValidSyntax() {
            return values.keySet().stream()
                    .filter(tag -> values.get(tag) != null)
                    //.peek(tag -> System.out.println(tag + "=" + values.get(tag) +" gives " + tag.isValid(values.get(tag))))
                    .allMatch(tag -> tag.isValid(values.get(tag)));
        }
        @Override
        public String toString() {
            return "Passport: " + values.toString();
        }
    }
    //todo ....... change input to tokenizer on "\n\n" such that all elements for passport are 1 element in the stream
    @Override // 8, 7, 7, 6
    public void convertInput(Stream<String> stream) {
        Pattern spaceSplit = Pattern.compile(" ");
        Pattern colonSplit = Pattern.compile(":");

        List<String[]> list = stream.map(spaceSplit::split)
                .flatMap(Arrays::stream)
                .map(colonSplit::split)
                .collect(Collectors.toList());
                //.map(Arrays::toString)
                //.forEach(System.out::println);
        passports = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int end = i+1;
            while (end < list.size() && list.get(end).length > 1) {
                end++;
            }
            passports.add(new Passport(
                    PassportTags.mapPassportTags(i, end, list)));
            i = end;
        }
    }

    @Override // 206
    public void part1() {
        System.out.println(passports.stream()
                .filter(Passport::isValid)
                .count()
        );
    }

    @Override // 123
    public void part2() {
        System.out.println(passports.stream()
                .filter(Passport::isValid)
                .filter(Passport::isValidSyntax)
                .count()
        );
    }
}
