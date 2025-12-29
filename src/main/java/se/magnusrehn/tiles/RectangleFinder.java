package se.magnusrehn.tiles;

import java.util.List;
import java.util.stream.IntStream;

public class RectangleFinder {

    public static long areaOfMaxRectangle(List<Tile> tiles) {
        int nofTiles = tiles.size();
        return IntStream.range(0, nofTiles)
                .boxed()
                .flatMapToLong(i ->
                        IntStream.range(i, nofTiles).mapToLong(j ->
                                area(tiles.get(i), tiles.get(j))))
                .max().orElseThrow();
    }

    public static long area(Tile t1, Tile t2) {
        long width = Math.max(t1.col(), t2.col()) - Math.min(t1.col(), t2.col()) + 1L;
        long height = Math.max(t1.row(), t2.row()) - Math.min(t1.row(), t2.row()) + 1L;
        return width * height;
    }
}
