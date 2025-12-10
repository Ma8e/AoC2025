package se.magnusrehn;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FreshIngredientsTest {

    FreshIngredients freshIngredients = new FreshIngredients("testIngredients.txt");
    @Test
    public void parsingWorks() {
        assertEquals(
                List.of(new Range(3, 5),
                        new Range(10, 14),
                        new Range(16, 20),
                        new Range(12, 18)
                ), freshIngredients.freshRanges);
        assertEquals(List.of(1L, 5L, 8L, 11L, 17L, 32L), freshIngredients.ingredientIds);
    }

    @Test
    public void freshCount() {
        assertEquals(3, freshIngredients.countFresh());
    }
}