package se.magnusrehn;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class FreshIngredients {

    final List<Range> freshRanges;
    final List<Long> ingredientIds;

    public FreshIngredients(String resourceName) {

        try(BufferedReader reader = Reader.reader(resourceName)) {

            freshRanges = reader.lines()
                    .takeWhile(s -> !s.isEmpty())
                    .map(FreshIngredients::freshRange)
                    .sorted(Range::compare)
                    .toList();

            ingredientIds = reader.lines().mapToLong(Long::parseLong).boxed().toList();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Range freshRange(String s) {
        String[] l = s.split("-");
        return new Range(Long.parseLong(l[0]), Long.parseLong(l[1]));
    }

    public static List<@NotNull Range> mergeOverlapping(List<Range> ranges) {
        List<Range> result = new ArrayList<>();
        Iterator<Range> iterator = ranges.stream().sorted(Range::compare).iterator();
        Range current = iterator.next();
        while (iterator.hasNext()) {
            Range next = iterator.next();
            if (next.start() <= current.end()) {
                current = current.union(next);
            } else {
                result.add(current);
                current = next;
            }
        }
        result.add(current);
        return result;
    }

    public Boolean isFresh(Long ingredientId) {
        return freshRanges.stream().anyMatch(r -> r.inRange(ingredientId));
    }

    public long countFresh() {
        return ingredientIds.stream().filter(this::isFresh).count();
    }

    public Stream<Long> allFresh() {
        return freshRanges.stream().flatMap(r ->
                LongStream.range(r.start(), r.end() + 1).boxed()).distinct();
    }

    public List<Long> allFreshList() {
        return allFresh().sorted().toList();
    }

    public long countAllFresh() {
        return mergeOverlapping(freshRanges).stream().mapToLong(Range::size).sum();
    }
}
