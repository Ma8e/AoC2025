package se.magnusrehn;

import java.util.stream.Stream;

public class PasswordFinder {
    public static long run(String resourceName) {
        Stream<Rotation> instructions = InstructionReader.readInstructionsFromClasspath(resourceName);
        Dial dial = new Dial();
        return instructions.map(dial::turn).filter(p -> p.number() == 0).count();
    }
}
