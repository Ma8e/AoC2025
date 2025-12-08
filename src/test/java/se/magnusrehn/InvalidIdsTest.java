package se.magnusrehn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class InvalidIdsTest {

    ProductIdRanges productIdRanges = new ProductIdRanges("testProductIdRanges.txt");

    InvalidIdsTest() throws IOException {
    }

    @Test
    void q() {

            }

    @Test
    void invalidIds() {
        assertEquals(
                Set.of(11L, 22L, 99L, 1010L, 1188511885L, 446446L, 222222L, 38593859L),
                InvalidIds.invalidIds(productIdRanges).boxed().collect(Collectors.toUnmodifiableSet())
        );
    }

    @Test
    void InvalidIds2() {
        assertEquals(
                Set.of(11L, 22L, 99L, 111L, 999L, 565656L, 1010L, 1188511885L, 446446L, 222222L, 38593859L,
                        824824824L, 2121212121L),
                InvalidIds.invalidIds2(productIdRanges).boxed().collect(Collectors.toUnmodifiableSet())
        );
    }

    @Test
    void invalidSum() {
        assertEquals(1227775554, InvalidIds.invalidsSum(productIdRanges));
    }

    @Test
    void invalidSum2() {
        assertEquals(4174379265L, InvalidIds.invalids2Sum(productIdRanges));
    }


    static final Supplier<Stream<Arguments>> numberMultiplierTestData = () -> Stream.of(
            arguments(1, 1, 1),
            arguments(1, 2, 11),
            arguments(123, 3, 123123123)
    );

    @ParameterizedTest
    @FieldSource("numberMultiplierTestData")
    void numberMultiplier(long number, int multiplier, long expected) {
        assertEquals(expected, InvalidIds.numberMultiplier(number, multiplier));
    }

    @Test
    void numberMultiples() {
        List<Long> expected = List.of(1212L, 121212L, 12121212L);
        List<Long> actual = InvalidIds.numberMultiples(12L).limit(3).boxed().collect(Collectors.toList());
        assertEquals(expected, actual);
    }
}