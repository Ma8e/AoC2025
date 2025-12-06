package se.magnusrehn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordFinderTest {

    @Test
    void run() {
        assertEquals(3, PasswordFinder.run("testInstructions.txt"));
    }
}