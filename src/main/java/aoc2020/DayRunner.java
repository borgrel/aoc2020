package aoc2020;

import aoc2020.attempt1.Days;

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
    public static Stream<String> readFile(String fileName) {
        Path path;
        try {
            URL url = DayRunner.class.getResource(fileName);
            path = (url != null)?
                        Path.of(url.toURI()):
                        Path.of(fileName);
            return Files.lines(path);
        } catch (URISyntaxException e) {
            System.err.println("File path " + fileName + " generated a syntax exception");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("ERROR reading file: " + fileName);
            e.printStackTrace();
            throw new IllegalArgumentException("File not found");
        }
        return null;
    }
    public static Stream<String> readInput(int day) {
        return readInput(Days.dayFromInt(day));
    }
    public static Stream<String> readInput(Days day) {
        return readFile(day.getFileName() + ".txt");
    }

    // ----------++- DYNAMIC START UTILITIES -++---------------------------------------
    public static void run(int value, Day solution) {
        final Days day = Days.dayFromInt(value);
        Stream<String> input = readInput(day);
        solution.convertInput(input);
        solution.part1();
        solution.part2();
    }
    public static Day invokeDay(int value) {
        String className = Days.dayFromInt(value).getClassName();
        try {
            return invoke(className);
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            System.err.println("Error invoking and instantiating " + className + " via reflection");
            e.printStackTrace();
            throw new IllegalArgumentException("Unable to locate .class for '" + className + "'");
        }
    }

    public static <T> T invoke(String className) throws ClassNotFoundException, IllegalAccessException,
                                            InvocationTargetException, InstantiationException {
        @SuppressWarnings("unchecked")
        T t = (T)DayRunner.class.getClassLoader().loadClass(className).getConstructors()[0].newInstance();
        return t;
    }

    public static void main(String[] args) {
        int dayNum = 10;
        if (args.length > 1) {
            dayNum = Integer.parseInt(args[1]);
        }
        System.out.printf("Running starter for Day%02d%s", dayNum, System.lineSeparator());

        Day day = DayRunner.invokeDay(dayNum);
        DayRunner.run(dayNum, day);
    }
}
