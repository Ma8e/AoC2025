package se.magnusrehn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;


public class Batteries {

    private final List<List<Integer>> batteries;

    static public List<Integer> batteryRow(String s) {
        return Arrays.stream(s.split("")).map(Integer::parseInt).toList();
    }

    public Batteries(String resourceName) {
        batteries = Reader.reader(resourceName)
                .lines()
                .map(Batteries::batteryRow)
                .toList();
    }

    static public int maxJoltage(List<Integer> batteryRow) {
 /*       int firstMaxIndex = IntStream.range(0, batteryRow.size() - 1).boxed()
                .max(Comparator.comparing(batteryRow::get)).orElse(-1);
        int firstMax = batteryRow.get(firstMaxIndex);
        int secondMax = batteryRow.subList(firstMaxIndex + 1, batteryRow.size()).stream().mapToInt(v -> v).max().orElse(-1);

        return firstMax * 10 + secondMax;*/
        return (int) maxBigJoltage(batteryRow, 2);
    }

    static public long maxBigJoltage(List<Integer> batteryRow, Integer numberOfBatteries) {
        if (numberOfBatteries == 0) {
            return 0;
        }
        if (numberOfBatteries == 1) {
            return batteryRow.stream().mapToLong(v -> v).max().orElse(-1);
        } else {
            int firstMaxIndex = IntStream.range(0, batteryRow.size() - numberOfBatteries + 1).boxed()
                    .max(Comparator.comparing(batteryRow::get)).orElse(-1);
            long firstMax = batteryRow.get(firstMaxIndex);

            return firstMax * (long) Math.pow(10, numberOfBatteries-1) + maxBigJoltage(batteryRow.subList(firstMaxIndex + 1, batteryRow.size()), numberOfBatteries - 1);
        }
    }

    static public long maxBigJoltage(List<Integer> batteryRow) {
        return maxBigJoltage(batteryRow, 12);
    }

    public List<Integer> maxJoltages() {
        return batteries.stream().map(Batteries::maxJoltage).toList();
    }

    public List<Long> maxBigJoltages() {
        return batteries.stream().map(Batteries::maxBigJoltage).toList();
    }

    public int maxTotalJoltage() {
        return maxJoltages().stream().reduce(0, Integer::sum);
    }

    public long maxTotalBigJoltage() {
        return maxBigJoltages().stream().reduce(0L, Long::sum);
    }


}
