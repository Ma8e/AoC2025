package se.magnusrehn;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class RangeTest {

    @ParameterizedTest
    @CsvSource({
            "1, 2, 2, true",
            "1, 1, 2, false",
            "4, 6, 3, false"
    })
    void inRange(long s, long e, long n, boolean expectedInRange) {
        Range range = new Range(s, e);
        assertEquals(expectedInRange, range.inRange(n));
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3, 4, false",
            "1, 3, 2, 4, true",
            "1, 4, 2, 3, true",
            "1, 4, 1, 4, true",
            "2, 3, 1, 4, true",
            "5, 6, 2, 4, false"
    })
    void overlap(long s1, long e1, long s2, long e2, boolean expectedOverlap) {
        Range range1 = new Range(s1, e1);
        Range range2 = new Range(s2, e2);
        assertEquals(expectedOverlap, range1.overlap(range2));
    }
}