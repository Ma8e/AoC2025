package se.magnusrehn;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HomeworkTest {


    @Test
    public void homework() {
        Homework homework = new Homework("testHomework.txt", 3);

        assertEquals(List.of(33210L, 490L, 4243455L, 401L), homework.solveAll());
        assertEquals(4277556, homework.sumOfAll());
    }

    @Test
    public void numberParsingTest() {
        assertEquals(List.of(23L, 328L, 51L, 64L), Homework.parseNumberLine("23 328  51 64"));
    }

    @Test
    void splitString() {
        assertEquals(List.of("a", " ", "b", "c", " "), Homework.splitString("a bc "));
    }

    @Test
    void pivot() {
        assertEquals(
                List.of(List.of(1, 4), List.of(2, 5), List.of(3, 6)), Homework.pivot(List.of(List.of(1, 2, 3), List.of(4, 5, 6)))
        );
    }

    @Test
    void printHomeWork() {
        Homework homework = new Homework("testHomework.txt", 3);
        homework.printHomework();
    }

    @Test
    void padTo() {
        assertEquals(List.of(1, 2, 3, 0, 0), Homework.padTo(List.of(1, 2, 3), 5, 0));
    }

}
