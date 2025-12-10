package se.magnusrehn;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

public class PaperRolls {
    public record Position(int row, int col) {
    }

    public List<List<String>> map;

    public PaperRolls(String resourceName) {
        try (var reader = Reader.reader(resourceName)) {
            map = reader.lines()
                    .map(l -> Arrays.stream(l.split("")).toList())
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PaperRolls(@NotNull String[][] map) {
        this.map = Arrays.stream(map).map(Arrays::asList).toList();
    }

    public int removeAccessible() {
        List<List<String>> newMap;
        AtomicInteger removed = new AtomicInteger();
        forEachSite((row, col) -> {
            if (isAccessible(row, col)) {

                map.get(row).set(col, ".");
                removed.getAndIncrement();
            }
            else {
                map.get(row).set(col, "@");
            }
        });
        return removed.get();
    }

    public String getValue(int row, int col) {
        return getValue(new Position(row, col));
    }

    public String getValue(Position p) {
        if (p.row < 0 || p.row >= map.size() || p.col < 0 || p.col >= map.get(p.row).size()) {
            return ".";
        }
        return map.get(p.row).get(p.col);
    }

    public long getNeighbourCount(int row, int col) {
        return getNeighbourCount(new Position(row, col));
    }

    public long getNeighbourCount(Position p) {
        return IntStream.range(p.row - 1, p.row + 2)
                .mapToLong(row ->
                        IntStream.range(p.col - 1, p.col + 2).filter(col -> getValue(row, col).equals("@")).count()
                ).sum() - (getValue(p).equals("@") ? 1L : 0L);
    }

    public long numberOfAccessibleRolls() {
        return IntStream.range(0, map.size())
                .mapToLong(row -> IntStream.range(0, map.get(row).size())
                        .filter(col -> isAccessible(row, col)).count()
                ).sum();
    }

    private Boolean isAccessible(int row, int col) {
        return getNeighbourCount(row, col) < 4 && getValue(row, col).equals("@");
    }

    public Boolean[][] isAccessible() {
        Boolean[][] result = new Boolean[map.size()][map.getFirst().size()];
        forEachSite((row, col) ->
                result[row][col] = isAccessible(row, col)
        );
        return result;
    }

    public void printAccessibleSites() {
        forEachSite((row, col) -> {
            if (isAccessible(row, col)) System.out.print("X ");
            else System.out.print(map.get(row).get(col) + " ");
            if (col == map.get(row).size() - 1) System.out.println();
        });
    }

    public void forEachSite(BiConsumer<Integer, Integer> action) {
        for (int row = 0; row < map.size(); row++)
            for (int col = 0; col < map.get(row).size(); col++)
                action.accept(row, col);
    }

    public void printCount() {
        forEachSite((row, col) -> {
            if (getValue(row, col).equals("@"))
                System.out.print(getNeighbourCount(row, col) + " ");
            else
                System.out.print(". ");
            if (col == map.get(row).size() - 1) System.out.println();
        });
    }

    public void printMap() {
        map.forEach(
                l -> {
                    l.forEach(c -> System.out.print(c));
                    System.out.println();
                }
        );
    }
}
