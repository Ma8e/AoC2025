package se.magnusrehn.circuits;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CircuitsTest {

    Circuits circuits = new Circuits("testJunctionBoxes.txt");

    @Test
    void circuitsExists() {
        assertNotNull(circuits);
    }

    @Test
    void allCircuitsAreRead() {
        assertEquals(20, circuits.circuits().size());
    }

    @ParameterizedTest
    @CsvSource({
            "2, 906,360,560",
            "13, 805,96,715",
            "19,425,690,689"
    })
    void someSamplesAreCorrect(int junctionBoxNo, int x, int y, int z) {
        assertEquals(new JunctionBox(x, y, z),
                circuits.circuits().get(junctionBoxNo).junctionBoxes.getFirst());
    }

    @Test
    void largestCircuitsSizesMultipliedTest() {
        assertEquals(40, circuits.largestCircuitsSizesMultiplied(10));
    }

    @Test
    void distancesTest() {
        Circuits.distances(circuits.junctionBoxes).forEach(System.out::println);
    }

    @Test
    void pairClosestTest() {
        circuits.pairClosest(10);
        circuits.circuits().forEach(c -> System.out.println(c == null ? 0 : c.size()));
    }
}
