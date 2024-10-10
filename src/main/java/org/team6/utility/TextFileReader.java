package org.team6.utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextFileReader {
    private static final Logger logger = Logger.getLogger(TextFileReader.class.getName());

    private TextFileReader() {}

    public static String readFileAsString(String resourcePath) {
        try (InputStream inputStream = TextFileReader.class.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                logger.log(Level.SEVERE, () -> "Resource not found: " + resourcePath);
                return "";
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> "An error occurred while reading resource: " + resourcePath);
            return "";
        }
    }
}
