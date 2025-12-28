package se.magnusrehn.circuits;

public record JunctionBox(int x, int y, int z) {

    public JunctionBox {
        assert x >= 0 && y >= 0 && z >= 0;
    }

    public double distanceTo(JunctionBox other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2));
    }
}
