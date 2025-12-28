package se.magnusrehn.circuits;

import se.magnusrehn.Reader;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Circuits {

    List<Circuit> circuits;

    public Circuits(String resourceName) {
        circuits = Reader.reader(resourceName).lines().map(
                l -> Arrays.stream(l.split(","))
                        .mapToInt(Integer::parseInt).boxed().toList()
        ).map(
                a -> new Circuit(a.get(0), a.get(1), a.get(2))
        ).toList();
    }

    public void pairClosest() {

    }

    public long largestCircuitsSizesMultiplied(int n) {
        return circuits.stream().map(Circuit::size).sorted(Comparator.reverseOrder()).limit(n)
                .reduce(1, (a, b) -> a * b)   ;
    }
}
