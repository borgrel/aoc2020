package aoc2020.attempt1;

import aoc2020.Day;
import org.javatuples.Pair;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day07 implements Day {
    private static final String NO_BAGS = "no other";
    private static final Pattern splitAt = Pattern.compile(" bags contain | bags?, | bags?.|no other|(?<=\\d) ");
    private final String SOURCE = "shiny gold";

    private String[][] input;
    private Map<String, Set<String>> bagDependencies;
    private Map<String, List<Pair<String, Integer>>> bagContents;

    @Override
    public void convertInput(Stream<String> stream) {
        input = stream.map(splitAt::split)
                .toArray(String[][]::new);
    }
    protected void dependencyMap(String[] arr) {
        for (int i = 2; i< arr.length; i+=2) {
            bagDependencies.putIfAbsent(arr[i], new HashSet<>());
            bagDependencies.get(arr[i]).add(arr[0]);
        }
    }
    protected void contentsMap(String[] arr) {
        for (int i = 1; i< arr.length; i+=2) {
            bagContents.putIfAbsent(arr[0], new ArrayList<>((arr.length-1)/2));
            bagContents.get(arr[0]).add(new Pair<>(arr[i+1], Integer.parseInt(arr[i])));
        }
    }
    protected void buildMap(Consumer<String[]> action) {
        Arrays.stream(input)
                .filter(arr -> arr.length != 1)
                .forEach(action);
    }
    /*bagDependencies = new HashMap<>();
    buildMap(this::dependencyMap);

    Set<String> containers = new HashSet<>();
    Deque<String> mustProcess = new ArrayDeque<>();
    mustProcess.add(SOURCE);
    while (!mustProcess.isEmpty()) {
        String current = mustProcess.poll();
        if (containers.contains(current)) continue;
        containers.add(current);
        Set<String> next = bagDependencies.get(current);
        if (next != null) {
            mustProcess.addAll(bagDependencies.get(current));
        }
    }
    System.out.println(containers.size()-1); //remove the SOURCE from the result*/
    public <T> Stream<T> append(T value, Collection<T> extras) {
        Stream<T> valueStream = Stream.of(value);
        if (extras == null) {
            return valueStream;
        } else {
            return Stream.concat(valueStream,extras.stream());
        }
    }
    @Override //257
    public void part1() {
        bagDependencies = new HashMap<>();
        buildMap(this::dependencyMap);

        Deque<String> mustProcess = new ArrayDeque<>();
        mustProcess.add(SOURCE);
        long value = Stream.generate(mustProcess::poll)
                .distinct()
                .peek(str -> {
                    Set<String> next = bagDependencies.get(str);
                    if (next != null) {
                        mustProcess.addAll(next);
                    }
                })
                .takeWhile(str -> !mustProcess.isEmpty())
                .count();
        System.out.println(value);

        /*bagDependencies.get(SOURCE).stream()
                .flatMap()*/

    }

    public int countBags(String current) {
        List<Pair<String, Integer>> next = bagContents.get(current);
        int value = 1;
        if (next == null) return value;
        for (Pair<String, Integer> using: next) {
            value += using.getValue1()*countBags(using.getValue0());
        }
        return value;
    }
    @Override //1038
    public void part2() {
        bagContents = new HashMap<>();
        buildMap(this::contentsMap);

        System.out.println(countBags(SOURCE)-1); //remove SOURCE from the result
    }
}
