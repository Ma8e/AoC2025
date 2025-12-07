package se.magnusrehn;

import java.util.stream.Stream;

public class PasswordFinder {

    final Stream<Rotation> instructions;
    final Dial dial;

    public PasswordFinder(String resourceName) {
        instructions = InstructionReader.readInstructionsFromClasspath(resourceName);
        dial = new Dial();
        instructions.forEach(dial::turn);
    }

    public long password1a() {
        return dial.zeroStops;
    }

    public long password1b() {
        return dial.zeroPasses;
    }
}
