package se.magnusrehn;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class InstructionReader {

    // Read instructions from an input stream (e.g. for resources or other input sources)
    public static Stream<Rotation> readInstructionsFromStream(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return reader.lines()
                .map(line -> new Rotation(
                        Direction.valueOf(line.substring(0, 1)),
                        Integer.parseInt(line.substring(1))
                ));
    }

    // Load the file from the application's classpath for better portability
    public static Stream<Rotation> readInstructionsFromClasspath(String resourceName) {
        return readInstructionsFromStream(Reader.resourceStream(resourceName));
    }
}