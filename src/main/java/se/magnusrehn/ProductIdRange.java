package se.magnusrehn;

public record ProductIdRange(long start, long end) {
    public ProductIdRange {
        assert start <= end;
    }
}
