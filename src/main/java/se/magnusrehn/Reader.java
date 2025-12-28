package se.magnusrehn;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Reader {

    public static InputStream resourceStream(String resourceName) {
        InputStream resourceStream = Reader.class.getClassLoader().getResourceAsStream(resourceName);
        if (resourceStream == null) {
            throw new RuntimeException("Resource not found: " + resourceName);
        }
        return resourceStream;
    }

    public static BufferedReader reader(String resourceName) {
        return new BufferedReader(new InputStreamReader(resourceStream(resourceName)));
    }
}
