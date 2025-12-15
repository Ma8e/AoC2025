package se.magnusrehn;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Homework2Test {

    @Test
    void splitString() {
        assertEquals(List.of("a", " ", "b", "c", " "), Homework2.splitString("a bc "));
    }

    @Test
    void pivot() {
        assertEquals(
                List.of(List.of(1, 4), List.of(2, 5), List.of(3, 6)), Homework2.pivot(List.of(List.of(1, 2, 3), List.of(4, 5, 6)))
        );
    }

    @Test
    void printHomeWork() {
        Homework2 homework2 = new Homework2("testHomework.txt");
        homework2.printHomework();
    }
}