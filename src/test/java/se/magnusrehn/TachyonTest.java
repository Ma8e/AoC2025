package se.magnusrehn;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static se.magnusrehn.Tachyon.stringToManifoldRow;

class TachyonTest {

    Tachyon tachyon = new Tachyon("testTachyonManifold.txt");

    @Test
    void manifold() {
        tachyon.printManyfold();
    }

    @Test
    void interactAddsNewTachyonAtS() {
        List<Character> manifoldRow = stringToManifoldRow(".......S.......");
        assertEquals(Set.of(7), tachyon.interact(Set.of(), manifoldRow));
    }

    @Test
    void interactSplitsTachyonAtSplitter() {
        List<Character> manifoldRow = stringToManifoldRow("....^........");
        assertEquals(Set.of(3, 5, 7), tachyon.interact(Set.of(4, 7), manifoldRow));
        assertEquals(1, tachyon.splitCount);
    }

    @Test
    void testSplitCount() {
        assertEquals(21, tachyon.manyfoldSplitCount());
    }

    @Test
    void quantumTachyonTimelineCount() {
        assertEquals(40, tachyon.quantumTachyonTimelineCount());
    }

}