package se.magnusrehn;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FreshIngredientsTest {

    List<Range> ranges =
            List.of(new Range(3, 5),
                    new Range(10, 14),
                    new Range(12, 18),
                    new Range(16, 20)
            );

    FreshIngredients freshIngredients = new FreshIngredients("testIngredients.txt");

    @Test
    public void parsingWorks() {
        assertEquals(
                List.of(new Range(3, 5),
                        new Range(10, 14),
                        new Range(12, 18),
                        new Range(16, 20)
                ), freshIngredients.freshRanges);
        assertEquals(List.of(1L, 5L, 8L, 11L, 17L, 32L), freshIngredients.ingredientIds);
    }

    @Test
    public void mergeOverlappingRangesTest() {
        assertEquals(List.of(new Range(3, 5), new Range(10, 20)),
                FreshIngredients.mergeOverlapping(ranges));
    }

    @Test
    public void freshCount() {
        assertEquals(3, freshIngredients.countFresh());
    }

    @Test
    public void allFresh() {
        assertEquals(List.of(3L, 4L, 5L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L),
                freshIngredients.allFreshList());
    }

    @Test
    public void countAllFresh() {
        assertEquals(14, freshIngredients.countAllFresh());
    }
}