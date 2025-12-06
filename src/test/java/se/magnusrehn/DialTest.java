package se.magnusrehn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DialTest {
    @Test
    void turn() {
        Dial dial = new Dial();
        dial.turn(new Rotation(Direction.R, 10));
        assertEquals(new Dial.Position(Direction.R, 60), dial.position);

        dial.turn(new Rotation(Direction.L, 30));
        assertEquals(new Dial.Position(Direction.L, 30), dial.position);

        dial.turn(new Rotation(Direction.L, 31));
        assertEquals(new Dial.Position(Direction.L, 99), dial.position);

        dial.turn(new Rotation(Direction.R, 1));
        assertEquals(new Dial.Position(Direction.R, 0), dial.position);

        dial.turn(new Rotation(Direction.R, 101));
        assertEquals(new Dial.Position(Direction.R, 1), dial.position);

        dial.turn(new Rotation(Direction.R, 101));
        assertEquals(new Dial.Position(Direction.R, 2), dial.position);

        dial.turn(new Rotation(Direction.L, 102));
        assertEquals(new Dial.Position(Direction.L, 0), dial.position);

        dial.turn(new Rotation(Direction.L, 101));
        assertEquals(new Dial.Position(Direction.L, 99), dial.position);
    }
}