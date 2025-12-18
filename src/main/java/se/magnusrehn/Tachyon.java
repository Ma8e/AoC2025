package se.magnusrehn;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tachyon {

    public final List<List<Character>> manyfold;
    public Integer splitCount = 0;

    final private HashMap<TimelineCountArgument, Long> timelineCountCache = new HashMap<>();

    public Tachyon(String resourceName) {
        try (BufferedReader reader = Reader.reader(resourceName)) {
            manyfold = reader.lines().map(Tachyon::stringToManifoldRow).toList();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Character> stringToManifoldRow(String s) {
        return s.chars().mapToObj(c -> (char) c).toList();
    }

    public Set<Integer> interact(Set<Integer> tachyons, List<Character> manifoldRow) {
        assert manifoldRow.stream().filter(c -> c == 'S').count() <= 1;
        Set<Integer> newTachyons = new HashSet<>();

        int startingPoint = manifoldRow.indexOf('S');
        if (startingPoint != -1) newTachyons.add(startingPoint);

        tachyons.forEach(t -> {
            if (manifoldRow.get(t).equals('^')) {
                newTachyons.add(t + 1);
                newTachyons.add(t - 1);
                splitCount++;
            } else {
                newTachyons.add(t);
            }
        });

        return newTachyons;
    }

    public int manyfoldSplitCount() {
        splitCount = 0;
        Set<Integer> tachyons = new HashSet<>();
        for (var manifoldRow : manyfold) {
            tachyons = interact(tachyons, manifoldRow);
        }
        return splitCount;
    }

    public record TimelineCountArgument(int pos, int manyfoldRowNumber) {
    }

    public long quantumTachyonTimelineCount() {

        return 1L + quantumTachyonTimelineCountRecursion(
                new TimelineCountArgument(
                        manyfold.getFirst().indexOf('S'), 1));
    }

    public long quantumTachyonTimelineCountRecursion(TimelineCountArgument a) {

        if (timelineCountCache.containsKey(a)) return timelineCountCache.get(a);

        long result;
        assert a.pos >= 0;
        assert a.pos < manyfold.getFirst().size();

        if (a.manyfoldRowNumber > manyfold.size()-1)
            result = 0L;
        else if (manyfold.get(a.manyfoldRowNumber()).get(a.pos) == '.')
            result =  quantumTachyonTimelineCountRecursion(new TimelineCountArgument(a.pos, a.manyfoldRowNumber + 1));
        else if (manyfold.get(a.manyfoldRowNumber()).get(a.pos) == '^')
            result =  1L + quantumTachyonTimelineCountRecursion(new TimelineCountArgument(a.pos + 1, a.manyfoldRowNumber + 1))
                    + quantumTachyonTimelineCountRecursion(new TimelineCountArgument(a.pos - 1, a.manyfoldRowNumber + 1));
        else
            throw new RuntimeException("Invalid character at position " + a.pos);

        timelineCountCache.put(a, result);
        return result;
}

public void printManyfold() {
    manyfold.forEach(l -> {
        l.forEach(System.out::print);
        System.out.println();
    });
}
}
