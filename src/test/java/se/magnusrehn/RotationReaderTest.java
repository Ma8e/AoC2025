package se.magnusrehn;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static se.magnusrehn.Direction.L;
import static se.magnusrehn.Direction.R;

class RotationReaderTest {

    @Test
    void readInstructionsFromStream() {}

    @Test
    void readInstructionsFromClasspath() {
        List<Rotation> actual = InstructionReader.readInstructionsFromClasspath("testInstructions.txt").toList();
        List<Rotation> first_expected = List.of(
                new Rotation(L, 68),
                new Rotation(L, 30),
                new Rotation(R, 48));
        List<Rotation> last_expected = List.of(
                new Rotation(L, 99),
                new Rotation(R, 14),
                new Rotation(L, 82));
        assertEquals(first_expected, actual.subList(0, 3));
        assertEquals(last_expected, actual.subList(actual.size() - 3, actual.size()));
        assertEquals(10, actual.size());
    }
}