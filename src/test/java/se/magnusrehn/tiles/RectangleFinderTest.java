package se.magnusrehn.tiles;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangleFinderTest {

    List<Tile> tiles = TileReader.readTilesFromClasspath("testTiles.txt");

    @Test
    void areaOfMaxRectangle() {
        long actual = RectangleFinder.areaOfMaxRectangle(tiles);
        assertEquals(50L, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 2, 2, 4",
            "2, 2, 1, 1, 4",
            "3, 3, 4, 3, 2",
            "1, 2, 3, 5, 12",
            "7, 21, 7, 21, 1",
            "7, 2, 3, 3, 10"})
    void areaTest(long c1, long r1, long c2, long r2, long expectedArea) {
        assertEquals(expectedArea, RectangleFinder.area(new Tile(c1, r1), new Tile(c2, r2)));
    }
}