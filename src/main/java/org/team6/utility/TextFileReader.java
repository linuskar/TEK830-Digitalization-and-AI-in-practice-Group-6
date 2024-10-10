package org.team6.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextFileReader {
    private static final Logger logger = Logger.getLogger(TextFileReader.class.getName());

    private TextFileReader() {}

    public static String readFileAsString(String filePathName) {
        Path filePath = Paths.get(filePathName);
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e, () -> "An error occurred while reading file at: " + filePath);
            return "";
        }
    }
}
