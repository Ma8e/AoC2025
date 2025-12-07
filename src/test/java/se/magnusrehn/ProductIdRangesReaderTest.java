package se.magnusrehn;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductIdRangesReaderTest {

    @Test
    void read() throws IOException {
        List<ProductIdRange> productIdRanges = ProductIdRangesReader.read("testProductIdRanges.txt");
        assertEquals(11, productIdRanges.size());
        assertEquals(new ProductIdRange(11, 22), productIdRanges.getFirst());
        assertEquals(new ProductIdRange(2121212118, 2121212124), productIdRanges.get(10));
    }
}