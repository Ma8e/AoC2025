package se.magnusrehn;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ProductIdRanges {

    public final List<Range> ranges;
    public final Long maxEndRange;

    public ProductIdRanges(String resourceName) throws IOException {
        this.ranges = read(resourceName);
        this.maxEndRange = maxEndRange(ranges);
    }

    static public List<Range> read(String resourceName) throws IOException {
        try (var reader = Reader.reader(resourceName)) {
            return Arrays.stream(reader.readLine().split(",")).map(r -> {
                var v = r.split("-");
                return new Range(Long.parseLong(v[0]), Long.parseLong(v[1]));
            }).toList();
        }
    }

    static private long maxEndRange(List<Range> ranges) {
        return ranges.stream().map(Range::end).max(Long::compareTo).orElseThrow();
    }

    public boolean inRange(long l) {
        return ranges.stream().anyMatch(r -> r.inRange(l));
    }

}
