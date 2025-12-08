package se.magnusrehn;

import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class InvalidIds {

    public static LongStream invalidIds(ProductIdRanges ranges) {

        return LongStream
                .range(1, Long.MAX_VALUE)
                .map(l -> {
                    String s = Long.toString(l);
                    return Long.parseLong(s + s);
                })
                .takeWhile(l -> l <= ranges.maxEndRange)
                .filter(ranges::inRange);

    }

    public static LongStream invalidIds2(ProductIdRanges ranges) {

        long maxEndRange = (long) Math.sqrt(ranges.maxEndRange);

        return LongStream
                .range(1, maxEndRange)
                .flatMap(l ->
                     numberMultiples(l).takeWhile(m -> m <= ranges.maxEndRange)
                )
                .filter(ranges::inRange)
                .distinct();

    }

    public static Long invalidsSum(ProductIdRanges ranges) {
        return invalidIds(ranges).sum();
    }

    public static Long invalids2Sum(ProductIdRanges ranges) {
        return invalidIds2(ranges).sum();
    }

    public static LongStream numberMultiples(long number) {
        return IntStream.range(2, Integer.MAX_VALUE).mapToLong(i -> numberMultiplier(number, i));
    }

    public static long numberMultiplier(long number, int multiplier) {

        String s = Long.toString(number);
        String r = Stream.generate(() -> s).limit(multiplier).reduce("", (a, b) -> a + b);

        return Long.parseLong(r);
    }
}
