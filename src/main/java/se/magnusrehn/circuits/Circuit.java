package se.magnusrehn.circuits;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Circuit {
    final List<JunctionBox> junctionBoxes = new ArrayList<>();

    public Circuit(@NotNull JunctionBox junctionBox) {
        junctionBoxes.add(junctionBox);
    }

    public Circuit(int x, int y, int z) {
        new Circuit(new JunctionBox(x, y, z));
    }

    public void addCircuit(@NotNull Circuit other) {
        junctionBoxes.addAll(other.junctionBoxes);
    }

    public double distanceTo(@NotNull JunctionBox junctionBox) {
        return junctionBoxes.stream()
                .mapToDouble(j -> j.distanceTo(junctionBox))
                .min().orElseThrow();
    }

    public double distanceTo(@NotNull Circuit other) {
        return other.junctionBoxes.stream()
                .mapToDouble(this::distanceTo)
                .min().orElseThrow();
    }

    public int size() {
        return junctionBoxes.size();
    }
}
