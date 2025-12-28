package se.magnusrehn.circuits;

import java.util.Objects;

public final class JunctionBox {
    public final int x, y, z;
    Circuit circuit;

    public JunctionBox(int x, int y, int z) {
        assert x >= 0 && y >= 0 && z >= 0;
        this.x = x;
        this.y = y;
        this.z = z;
        this.circuit = new Circuit(this);
    }

    public double distanceTo(JunctionBox other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (JunctionBox) obj;
        return this.x == that.x &&
                this.y == that.y &&
                this.z == that.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "JunctionBox[" +
                "x=" + x + ", " +
                "y=" + y + ", " +
                "z=" + z + ']';
    }

}
