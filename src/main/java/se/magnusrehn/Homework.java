package se.magnusrehn;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Homework {

    public List<List<Long>> numbers;
    public List<Function<List<Long>, Long>> operators;
    final private List<List<Long>> homeworkRows;
    final String resourceName;
    final int numberOfNumberLines;

    static Function<List<Long>, Long> add = l -> l.stream().reduce(0L, Long::sum);
    static Function<List<Long>, Long> mul = l -> l.stream().reduce(1L, (a, b) -> a * b);

    public Homework(String resourceName, int numberOfNumberLines) {
        this.numberOfNumberLines = numberOfNumberLines;
        this.resourceName = resourceName;
        try (BufferedReader reader = Reader.reader(resourceName)) {
            numbers = reader.lines().limit(numberOfNumberLines).map(Homework::parseNumberLine).toList();
            operators = reader.lines()
                    .findFirst().map(Homework::parseOperators).orElseThrow();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try (BufferedReader reader = Reader.reader(resourceName)) {
            List<List<String>> numberLines = reader.lines().limit(numberOfNumberLines).map(Homework::splitString).toList();
            Integer maxLength = numberLines.stream().map(List::size).max(Integer::compare).orElseThrow();
            List<List<String>> p = numberLines.stream().map(l -> padTo(l, maxLength, " ")).toList();
            homeworkRows = group(p);
            assert homeworkRows.size() == operators.size();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static List<List<Long>> group(List<List<String>> a) {
        var p = pivot(a);
        List<List<Long>> result = new ArrayList<>();
        List<Long> tmp = new ArrayList<>();
        Iterator<String> i = p.stream().map(r -> String.join("", r)).iterator();
        do  {
            String s = i.next();
            if (s.isBlank()) {
                result.add(tmp);
                tmp = new ArrayList<>();
            }
            else {
                tmp.add(Long.parseLong(s.trim()));
            }
        } while (i.hasNext());
        result.add(tmp);
        return result;
    }

    static <T> List<T> padTo(final List<T> list, int size, T pad) {
        int diff = size - list.size();
        if (diff <= 0) return list.subList(0, size);
        return Stream.concat(list.stream(), Collections.nCopies(diff, pad).stream()).toList();
    }

    static <T> List<List<T>> pivot(List<List<T>> p) {
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

    public List<Long> solveAll2() {
        assert homeworkRows.size() == operators.size();
        return IntStream.range(0, homeworkRows.size()).mapToLong(i -> operators.get(i).apply(homeworkRows.get(i))).boxed().toList();
    }

    public Long sumOfAll() {
        return solveAll().stream().reduce(0L, Long::sum);
    }

    public Long sumOfAll2() {
        return solveAll2().stream().reduce(0L, Long::sum);

    }
}
