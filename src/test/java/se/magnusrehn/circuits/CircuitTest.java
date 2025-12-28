package se.magnusrehn.circuits;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircuitTest {
    @Test
    void distanceToJunctionBoxTest() {
        Circuit circuit = new Circuit(0, 0, 0);
        JunctionBox junctionBox = new JunctionBox(1, 1, 1);
        assertEquals(Math.sqrt(3), circuit.distanceTo(junctionBox));
    }

    @Test
    void distanceToCircuitTest() {
        Circuit circuit1 = new Circuit(0, 0, 0);
        Circuit circuit2 = new Circuit(1, 1, 1);
        assertEquals(Math.sqrt(3), circuit1.distanceTo(circuit2));
    }

    @Test
    void distanceToCircuitsTest() {
        Circuit circuit1 = new Circuit(0, 0, 0);
        circuit1.addCircuit(new Circuit(10, 10, 10));
        Circuit circuit2 = new Circuit(4, 0, 16);
        circuit2.addCircuit(new Circuit(2, 2, 2));
        assertEquals(Math.sqrt(12), circuit1.distanceTo(circuit2));
    }
}