package se.magnusrehn;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ProductIdRangesTest {

    ProductIdRanges productIdRanges = new ProductIdRanges("testProductIdRanges.txt");

    ProductIdRangesTest() throws IOException {
    }

    @Test
    void read() {
        assertEquals(11, productIdRanges.ranges.size());
        assertEquals(new ProductIdRange(11, 22), productIdRanges.ranges.getFirst());
        assertEquals(new ProductIdRange(2121212118, 2121212124), productIdRanges.ranges.get(10));
    }

    @Test
    void maxEndRange() {
        assertEquals(2121212124, productIdRanges.maxEndRange);
    }
}