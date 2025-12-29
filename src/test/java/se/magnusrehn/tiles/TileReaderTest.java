package se.magnusrehn.tiles;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TileReaderTest {

    @Test
    void readTilesFromClasspath() {
        List<Tile> tiles = TileReader.readTilesFromClasspath("testTiles.txt");
        assertEquals(8, tiles.size());
        assertEquals(new Tile(9, 7), tiles.get(3));
    }
}