package se.magnusrehn;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordFinderTest {

    @Test
    void run() {
        PasswordFinder passwordFinder = new PasswordFinder("testInstructions.txt");
        assertEquals(3, passwordFinder.password1a());
        assertEquals(6, passwordFinder.password1b());
    }
}