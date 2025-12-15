package se.magnusrehn;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class Homework2 {

    final private List<List<String>> homeworkRows;

    public Homework2(String resourceName) {
        try (BufferedReader reader = Reader.reader(resourceName)) {
            List<List<String>> lines = reader.lines().map(Homework2::splitString).toList();
            homeworkRows = pivot(lines);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static List<String> splitString(String s) {
        return List.of(s.split(""));
    }


    static <T> List<List<T>> pivot(List<List<T>> p) {
        return IntStream.range(0, p.getFirst().size()).mapToObj(i -> p.stream().map(l -> l.get(i)).toList()).toList();
    }

    public void printHomework() {
        homeworkRows.forEach(System.out::println);
    }
}
