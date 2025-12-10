package se.magnusrehn;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PaperRollsTest {
    PaperRolls paperRolls = new PaperRolls("testPaperRolls.txt");

    @Test
    void paperRollsHaveTheRightSize() {
        assertEquals(10, paperRolls.map.size());
        assertEquals(10, paperRolls.map.getFirst().size());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 2",
            "0, 1, 4",
            "0, 9, 3",
            "9, 0, 1",
            "9, 9, 2",
            "4, 0, 3",
            "4, 9, 3",
            "3, 2, 6"})
    void paperRollNeigbourCount(int row, int col, int expectedCount) {
        assertEquals(expectedCount, paperRolls.getNeighbourCount(new PaperRolls.Position(row, col)));
    }

    @Test
    void getValue() {
        assertEquals(".", paperRolls.getValue(new PaperRolls.Position(0, 0)));
        assertEquals("@", paperRolls.getValue(new PaperRolls.Position(1, 2)));
        assertEquals( ".", paperRolls.getValue(new PaperRolls.Position(1, 3)));
    }


    @Test
    void numberOfAccessibleRolls() {
        paperRolls.printCount();
        assertEquals(13, paperRolls.numberOfAccessibleRolls());
    }

    @Test
    void printMap() {
        paperRolls.printMap();
    }

    @Test
    void printCount() {
        paperRolls.printCount();
    }

    @Test
    void printAccessible() {
        paperRolls.printAccessibleSites();
    }

    @Test
    void removeAccessible() {
        PaperRolls paperRolls = new PaperRolls("testPaperRolls.txt");
        assertEquals(13, paperRolls.removeAccessible());
        paperRolls.printAccessibleSites();
    }
}