package se.magnusrehn.circuits;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Circuit {
    List<JunctionBox> junctionBoxes = new ArrayList<>();

    public Circuit(@NotNull JunctionBox junctionBox) {
        junctionBoxes.add(junctionBox);
    }

    public Circuit(int x, int y, int z) {
        this(new JunctionBox(x, y, z));
    }

    public void addCircuit(@NotNull Circuit other) {
        junctionBoxes.addAll(other.junctionBoxes);
        other.junctionBoxes.forEach(j -> j.circuit = this);
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
        return junctionBoxes == null ? 0 : junctionBoxes.size();
    }

    public boolean contains(JunctionBox j) {
        return junctionBoxes.contains(j);
    }
}
