package se.magnusrehn;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class PaperRolls {
    public record Position(int row, int col) {
    }

    final public List<List<String>> map;

    public PaperRolls(String resourceName) {
        try (var reader = Reader.reader(resourceName)) {
            map = reader.lines()
                    .map(l -> Arrays.stream(l.split("")).toList())
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
                        .filter(col -> getNeighbourCount(row, col) < 4 && getValue(row, col).equals("@")).count()
                ).sum();
    }

    public void printCount() {
        for (int row = 0; row < map.size(); row++) {
            for (int col = 0; col < map.get(row).size(); col++) {
                if (getValue(row, col).equals("@"))
                    System.out.print(getNeighbourCount(row, col) + " ");
                else
                    System.out.print(". ");
            }
            System.out.println();
        }
    }
}
