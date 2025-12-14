package se.magnusrehn;

public record Range(long start, long end) {
    public Range {
        assert start <= end;
    }

    public boolean inRange(long l) {
        return l >= start && l <= end;
    }

    public Boolean overlap(Range other) {
        return other.start <= end && other.end >= start;
    }

    public Range union(Range other) {
        return new Range(Math.min(start, other.start), Math.max(end, other.end));
    }

    public long size() {
        return end - start + 1;
    }

    public int compare(Range range) {
        return Long.compare(start, range.start);
    }
}
