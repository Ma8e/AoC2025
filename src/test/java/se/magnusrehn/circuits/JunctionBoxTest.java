package se.magnusrehn.circuits;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class JunctionBoxTest {

    static final Supplier<Stream<Arguments>> distanceTestData = () -> Stream.of(
            Arguments.arguments(0, 0, 0, 1, 1, 1, Math.sqrt(3)),
            Arguments.arguments(1, 2, 3, 2, 3, 4, Math.sqrt(3)),
            Arguments.arguments(1, 1, 1, 3, 3, 3, Math.sqrt(12))
    );

    @ParameterizedTest
    @FieldSource("distanceTestData")
    void distance(int x1, int y1, int z1, int x2, int y2, int z2, double expectedDistance) {
        assertEquals(
                expectedDistance,
                new JunctionBox(x1, y1, z1).distanceTo(new JunctionBox(x2, y2, z2)));
    }
}