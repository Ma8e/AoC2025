package se.magnusrehn;

public record ProductIdRange(long start, long end) {
    public ProductIdRange {
        assert start <= end;
    }

    public boolean inRange(long l) {
        return l >= start && l <= end;
    }
}
