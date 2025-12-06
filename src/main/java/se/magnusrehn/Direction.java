package se.magnusrehn;

public enum Direction {
    L(-1),
    R(1);

    Direction(int multiplier) {this.multiplier = multiplier;}
    public final int multiplier;
}
