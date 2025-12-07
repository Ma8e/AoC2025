package se.magnusrehn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static se.magnusrehn.Direction.L;
import static se.magnusrehn.Direction.R;

class DialTest {
    @Test
    void turn() {
        Dial dial = new Dial();
        dial.turn(new Rotation(R, 10));
        assertEquals(new Dial.Position(R, 60), dial.position);

        dial.turn(new Rotation(Direction.L, 30));
        assertEquals(new Dial.Position(Direction.L, 30), dial.position);

        dial.turn(new Rotation(Direction.L, 31));
        assertEquals(new Dial.Position(Direction.L, 99), dial.position);

        dial.turn(new Rotation(R, 1));
        assertEquals(new Dial.Position(R, 0), dial.position);

        dial.turn(new Rotation(R, 101));
        assertEquals(new Dial.Position(R, 1), dial.position);

        dial.turn(new Rotation(R, 101));
        assertEquals(new Dial.Position(R, 2), dial.position);

        dial.turn(new Rotation(Direction.L, 102));
        assertEquals(new Dial.Position(Direction.L, 0), dial.position);

        dial.turn(new Rotation(Direction.L, 101));
        assertEquals(new Dial.Position(Direction.L, 99), dial.position);
    }

    static final Supplier<Stream<Arguments>> zeroPassesData = () -> Stream.of(
            arguments(50, new Rotation(R, 10), 0),
            arguments(50, new Rotation(R, 50), 1),
            arguments(50, new Rotation(R, 70), 1),
            arguments(50, new Rotation(R, 170), 2),
            arguments(0, new Rotation(R, 30), 0),
            arguments(0, new Rotation(R, 100), 1),
            arguments(0, new Rotation(R, 101), 1),
            arguments(0, new Rotation(R, 0), 0),

            arguments(50, new Rotation(L, 0), 0),
            arguments(50, new Rotation(L, 10), 0),
            arguments(50, new Rotation(L, 50), 1),
            arguments(50, new Rotation(L, 51), 1),
            arguments(50, new Rotation(L, 150), 2),

            arguments(0, new Rotation(L, 0), 0),
            arguments(0, new Rotation(L, 1), 0),
            arguments(0, new Rotation(L, 99), 0),
            arguments(0, new Rotation(L, 100), 1),
            arguments(0, new Rotation(L, 101), 1),
            arguments(0, new Rotation(L, 201), 2)

    );

    @ParameterizedTest
    @FieldSource("zeroPassesData")
    void zeroPasses(int startNumber, Rotation rotation, int expectedZeroPasses) {
        Dial dial = new Dial(startNumber);
        dial.turn(rotation);
        assertEquals(expectedZeroPasses, dial.zeroPasses);
    }
}