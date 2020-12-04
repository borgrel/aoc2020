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
        byr ("Birth Year","^(19[2-9][0-9])|(200[0-2])$"),     // byr (Birth Year) - four digits; at least 1920 and at most 2002.
        iyr ("Issue Year","^20(1[0-9]|20)$"),               // iyr (Issue Year) - four digits; at least 2010 and at most 2020.
        eyr ("Expiration Year","^20(2[0-9]|30)$"),          // eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
        hgt ("Height","^((1[5-8][0-9]|19[0-3])cm)|((59|6[0-9]|7[0-6])in)$"),    //    hgt (Height) - a number followed by either cm or in:
                                           //"^((1[5-8][0-9]|19[0-3])cm)|((59|6[0-9]|7[0-6])in)$"                                      //         If cm, the number must be at least 150 and at most 193.
                                                                                 //         If in, the number must be at least 59 and at most 76.
        hcl ("Hair Color","^#[0-9a-f]{6}$"),                  // hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
        ecl ("Eye Color","^(amb|blu|brn|gry|grn|hzl|oth)$"),//            ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
        pid ("Passport ID","^\\d{9}$"),//            pid (Passport ID) - a nine-digit number, including leading zeroes.
        cid ("Country ID",null);//    cid (Country ID) - ignored, missing or not.

        String description;
        Predicate<String> regexPredicate;

        PassportTags(String description,String regexPattern) {
            this.description = description;
            if (regexPattern == null) regexPredicate = (s -> true);
            else regexPredicate = Pattern.compile(regexPattern).asMatchPredicate();
        }
        boolean isValid(String value) {
            return regexPredicate.test(value);
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
