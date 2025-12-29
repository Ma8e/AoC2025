package se.magnusrehn.circuits;

import se.magnusrehn.Reader;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Circuits {

    final List<JunctionBox> junctionBoxes;

    public Circuits(String resourceName) {
        junctionBoxes = Reader.reader(resourceName).lines().map(
                l -> Arrays.stream(l.split(","))
                        .mapToInt(Integer::parseInt).boxed().toList()
        ).map(
                a -> new JunctionBox(a.get(0), a.get(1), a.get(2))
        ).toList();
    }

    static Stream<Distance> distances(List<JunctionBox> junctionBoxes) {
        int n = junctionBoxes.size();
        return IntStream.range(0, n).boxed().flatMap(
                i -> IntStream.range(i+1, n).mapToObj(
                        j -> new Distance(i, j, junctionBoxes.get(i).distanceTo(junctionBoxes.get(j)))
                ));
    }

    record Distance(int junctionBox1, int junctionBox2, double distance) {}

    public void pairClosest(int n) {
        distances(junctionBoxes)
                .sorted(Comparator.comparingDouble(Distance::distance))
                .limit(n)
                .forEachOrdered(
                        d -> {
                            JunctionBox j1 = junctionBoxes.get(d.junctionBox1);
                            JunctionBox j2 = junctionBoxes.get(d.junctionBox2);
                            if (!j1.circuit.contains(j2) && !j2.circuit.contains(j1)) {
                                j1.circuit.addCircuit(j2.circuit);
                            }
                        }
                );
    }

    public int pairUntilOneCircuit() {

        Iterator<Distance> distanceInterator = distances(junctionBoxes)
                .sorted(Comparator.comparingDouble(Distance::distance)).iterator();

        JunctionBox j1;
        JunctionBox j2;

        do  {
            Distance d = distanceInterator.next();
            j1 = junctionBoxes.get(d.junctionBox1);
            j2 = junctionBoxes.get(d.junctionBox2);
            if (!j1.circuit.contains(j2) && !j2.circuit.contains(j1)) {
                j1.circuit.addCircuit(j2.circuit);
            }
        } while (circuits().size() > 1);

        return j1.x * j2.x;

    }

    public List<Circuit> circuits() {
        return junctionBoxes.stream().map(j -> j.circuit).distinct().toList();
    }

    public long largestCircuitsSizesMultiplied(int n) {
        pairClosest(n);
        return circuits().stream().map(Circuit::size).sorted(Comparator.reverseOrder()).limit(3)
                .reduce(1, (a, b) -> a * b)   ;
    }
}
