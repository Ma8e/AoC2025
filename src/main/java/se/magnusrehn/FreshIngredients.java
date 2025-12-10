package se.magnusrehn;

import java.io.BufferedReader;
import java.util.List;

public class FreshIngredients {

    final List<Range> freshRanges;
    final List<Integer> ingredientIds;

    public FreshIngredients(String resourceName) {

        BufferedReader reader = Reader.reader(resourceName);

        freshRanges = reader.lines()
                .takeWhile(s->s.length() > 0)
                .map(FreshIngredients::freshRange)
                .toList();

        ingredientIds = reader.lines().mapToInt(Integer::parseInt).boxed().toList();

    }

    public static Range freshRange(String s) {
        String[] l = s.split("-");
        return new Range(Long.parseLong(l[0]), Long.parseLong(l[1]));
    }

    public Boolean isFresh(int ingredientId) {
        return false;
    }
}
