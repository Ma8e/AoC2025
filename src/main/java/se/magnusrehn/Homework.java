package se.magnusrehn;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Homework {

    public List<List<Long>> numbers;
    public List<Function<List<Long>, Long>> operators;
    final private List<List<String>> homeworkRows;
    final String resourceName;

    static Function<List<Long>, Long> add = l -> l.stream().reduce(0L, Long::sum);
    static Function<List<Long>, Long> mul = l -> l.stream().reduce(1L, (a, b) -> a * b);

    public Homework(String resourceName, int numberOfNumberLines) {
        this.resourceName = resourceName;
        try (BufferedReader reader = Reader.reader(resourceName)) {
            numbers = reader.lines().limit(numberOfNumberLines).map(Homework::parseNumberLine).toList();
            operators = reader.lines()
                    .findFirst().map(Homework::parseOperators).orElseThrow();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try (BufferedReader reader = Reader.reader(resourceName)) {
            List<List<String>> lines = reader.lines().map(Homework::splitString).toList();
            lines.stream().map(List::size).forEach(System.out::println);
            Integer maxLength = lines.stream().map(List::size).max(Integer::compare).orElseThrow();
            List<List<String>> p = lines.stream().map(l -> padTo(l, maxLength, " ")).toList();
            p.stream().map(List::size).forEach(System.out::println);
            homeworkRows = pivot(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static <T> List<T> padTo(final List<T> list, int size, T pad) {
        int diff = size - list.size();
        if (diff <= 0) return list.subList(0, size);
        return Stream.concat(list.stream(), Collections.nCopies(diff, pad).stream()).toList();
    }

    static <T> List<List<T>> pivot( List<List<T>> p) {
        return IntStream.range(0, p.getFirst().size()).mapToObj(i -> p.stream().map(l -> l.get(i)).toList()).toList();
    }

    public void printHomework() {
        homeworkRows.forEach(System.out::println);
    }

    static List<String> splitString(String s) {
        return List.of(s.split(""));
    }

    public static List<Long> parseNumberLine(String string) {
        return Arrays.stream(string.split(" ")).filter(s -> !s.isEmpty()).map(Long::parseLong).toList();
    }

    public static List<Function<List<Long>, Long>> parseOperators(String string) {
        return Arrays.stream(string.split(" ")).filter(s -> !s.isEmpty())
                .map(s -> switch (s) {
                    case "*" -> mul;
                    case "+" -> add;
                    default -> throw new RuntimeException("Unknown operator: " + s);
                })
                .toList();
    }

    public List<Long> solveAll() {
        List<Long> result = new ArrayList<>();
        for (int i = 0; i < numbers.getFirst().size(); i++) {
            final int col = i;
            List<Long> n = numbers.stream().mapToLong(number -> number.get(col)).boxed().toList();
            result.add(operators.get(i).apply(n));
        }
        return result;
    }

    public Long sumOfAll() {
        return solveAll().stream().reduce(0L, Long::sum);
    }
}
