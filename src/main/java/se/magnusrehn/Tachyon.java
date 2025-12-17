package se.magnusrehn;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tachyon {

    public final List<List<Character>> manyfold;
    public Integer splitCount = 0;

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
            }
            else {
                newTachyons.add(t);
            }
        });

        return newTachyons;
    }

    public int manyfoldSplitCount() {
        splitCount = 0;
        Set<Integer> tachyons = new HashSet<>();
        for (var manifoldRow: manyfold) {
            tachyons = interact(tachyons, manifoldRow);
        }
        return splitCount;
    }

    public void printManyfold() {
        manyfold.forEach(l -> {
            l.forEach(System.out::print);
            System.out.println();
        });
    }
}
