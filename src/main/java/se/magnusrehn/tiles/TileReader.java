package se.magnusrehn.tiles;

import se.magnusrehn.Reader;

import java.util.Arrays;
import java.util.List;

public class TileReader {
    static public List<Tile> readTilesFromClasspath(String resourceName) {
        return Reader.reader(resourceName).lines()
                .map(l -> Arrays.asList(l.split(",")))
                .map(l -> new Tile(Integer.parseInt(l.get(0)), Integer.parseInt(l.get(1))))
                .toList();
    }
}
