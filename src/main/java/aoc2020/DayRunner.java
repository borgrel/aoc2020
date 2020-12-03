package aoc2020;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class DayRunner {
    public static final String FOLDER = "src/main/resources/aoc2020/";
    public static Days dayFromInt(int value) {
        return Days.values()[value-1];
    }
    public static void run(int value, Day solution) {
        final Days day = dayFromInt(value);
        Stream<String> input = readInput(day);
        solution.convertInput(input);
        solution.part1();
        solution.part2();
    }

    public static void run(int value) {
        Days days = dayFromInt(value);
        try {
            Day solution = days.getDay();
            run(value, solution);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            System.err.println("Error invoking and instantiating " + days.name() + " via reflection");
            e.printStackTrace();
        }
    }

    // ----------++- CONSOLE UTILITIES -++---------------------------------------
    public static <T> void printArray(T[][] input) {
        Arrays.stream(input)
                .map(Arrays::toString)
                .forEach(System.out::println);
    }
    public static <T> void printArray(T[] input) {
        Arrays.stream(input)
                .forEach(System.out::println);
    }
    public static <T> void printArray(List<T[]> input) {
        input.stream()
                .map(Arrays::toString)
                .forEach(System.out::println);
    }

    // ----------++- FILE UTILITIES -++---------------------------------------
    private static Stream<String> readFile(String filePath) {
        Path path;
        try {
            URL url = DayRunner.class.getResource(filePath);
            if (url == null) {
                path = Path.of(filePath);
            } else {
                path = Path.of(url.toURI());
            }
            return Files.lines(path);
        } catch (URISyntaxException e) {
            System.err.println("File path " + filePath + " generated a syntax exception");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("ERROR reading file: " + filePath);
            e.printStackTrace();
            throw new IllegalArgumentException("File not found");
        }
        return null;
    }
    public static Stream<String> readInput(int day) {
        return readInput(dayFromInt(day));
    }
    public static Stream<String> readInput(Days day) {
        return readFile(day.getFileName() + ".txt");
    }
    public static void main(String[] args) {
        int value = 3;
        if (args.length > 1) {
            value = Integer.parseInt(args[1]);
        }
        System.out.printf("Running starter for Day%02d\n", value);

        DayRunner.run(value);
    }
}
