package se.magnusrehn;

import java.io.InputStream;

public class Reader {
    public static InputStream resourceStream(String resourceName) {
        InputStream resourceStream = InstructionReader.class.getClassLoader().getResourceAsStream(resourceName);
        if (resourceStream == null) {
            throw new RuntimeException("Resource not found: " + resourceName);
        }
        return resourceStream;
    }
}
