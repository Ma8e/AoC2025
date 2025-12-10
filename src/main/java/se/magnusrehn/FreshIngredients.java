package se.magnusrehn;

import java.io.BufferedReader;
import java.util.List;

public class FreshIngredients {

    final List<Range> freshRanges;
    final List<Long> ingredientIds;

    public FreshIngredients(String resourceName) {

        BufferedReader reader = Reader.reader(resourceName);

        freshRanges = reader.lines()
                .takeWhile(s->!s.isEmpty())
                .map(FreshIngredients::freshRange)
                .toList();

        ingredientIds = reader.lines().mapToLong(Long::parseLong).boxed().toList();

    }

    public static Range freshRange(String s) {
        String[] l = s.split("-");
        return new Range(Long.parseLong(l[0]), Long.parseLong(l[1]));
    }

    public Boolean isFresh(Long ingredientId) {
        return freshRanges.stream().anyMatch(r -> r.inRange(ingredientId));
    }

    public long countFresh() {
        return ingredientIds.stream().filter(this::isFresh).count();
    }
}
