package se.magnusrehn;

import java.util.List;

public class PaperRolls {
    final List<List<Integer>> map;

    public PaperRolls(String resourceName) {
        try (var reader = Reader.reader(resourceName)) {
            map = reader.lines()
                    .map(l -> l.chars().boxed().toList())
                    .toList();
        } catch (Exception e) { throw new RuntimeException(e); }

    }
}
