package se.magnusrehn;

public record Range(long start, long end) {
    public Range {
        assert start <= end;
    }

    public boolean inRange(long l) {
        return l >= start && l <= end;
    }
}
