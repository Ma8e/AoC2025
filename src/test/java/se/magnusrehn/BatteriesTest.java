package se.magnusrehn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BatteriesTest {

    Batteries batteries = new Batteries("testBatteries.txt");

    @Test
    void maxJoltages() {
        assertEquals(List.of(98, 89, 78, 92), batteries.maxJoltages());
    }

    static final Supplier<Stream<Arguments>> maxJoltageTestData = () -> Stream.of(
            Arguments.arguments("987654321111111", 98),
            Arguments.arguments("811111111111119", 89),
    Arguments.arguments("987898", 99));


    @ParameterizedTest
    @FieldSource("maxJoltageTestData")
    void MaxJoltage(String batteryString, int expectedMaxJoltage) {
        assertEquals(
                expectedMaxJoltage,
                Batteries.maxJoltage(
                        Arrays.stream(batteryString.split(""))
                                .mapToInt(Integer::parseInt).boxed().toList()
                )
        );
    }

    @Test
    void MaxTotalJoltage() {
        assertEquals(357, batteries.maxTotalJoltage());
    }
}