package org.team6.utility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class TextFileReaderTest {
    
    @ParameterizedTest
    @EnumSource(TextPath.class)
    void testTextFileReader_GivenTextFile_ShouldNotReturnNull(TextPath textPath) {
        String testText = TextFileReader.readFileAsString(textPath.getPath());
        assertNotNull(testText, "Should not return null.");
    }

    @ParameterizedTest
    @EnumSource(TextPath.class)
    void testTextFileReader_GivenTextFile_ShouldNotReturnEmptyText(TextPath textPath) {
        String testText = TextFileReader.readFileAsString(textPath.getPath());
        assertFalse(testText.isEmpty(), "Should not return empty text.");
    }

    @Test
    void testTextFileReader_GivenTxtFileWithOneRow_ReturnsCorrectText() {
        String test = "ok";
        String testText = TextFileReader.readFileAsString("/org/team6/texts/oneRowTestText.txt");
        assertEquals(test, testText, "Should return correct text.");
    }

    @Test
    void testTextFileReader_GivenTxtFileWithManyRows_ReturnsCorrectText() {
        String test = """
                hi guy local idiot here \r
                guy: ok\r
                local iebaot: hehea i the robbing you\r
                guy: ok\r
                polceiace: i am poltergieste to scary four you away\r
                locale ibeote: ahh and then the locale idiot ran away\r
                locale iebaot: why died h running away\r
                table: he ws sacred or the poltergieste\r
                poltesgeist: haha\r
                vacume clener: vroom\r
                locale ibeote: and the poltergiest was dead by the vacuem clener\r
                guy: ok\r
                \r
                the edn : hi guys i ame the end, over""";
        String testText = TextFileReader.readFileAsString("/org/team6/texts/manyRowTestText.txt");
        assertEquals(test, testText, "Should return correct text.");
    }


    @Test
    void testTextFileReader_GivenMdFileWithOneRow_ReturnsCorrectText() {
        String test = "ok";
        String testText = TextFileReader.readFileAsString("/org/team6/texts/oneRowTestText.md");
        assertEquals(test, testText, "Should return correct text.");
    }

    @Test
    void testTextFileReader_GivenMdFileWithManyRows_ReturnsCorrectText() {
        String test = """
                hi guy local idiot here \r
                guy: ok\r
                local iebaot: hehea i the robbing you\r
                guy: ok\r
                polceiace: i am poltergieste to scary four you away\r
                locale ibeote: ahh and then the locale idiot ran away\r
                locale iebaot: why died h running away\r
                table: he ws sacred or the poltergieste\r
                poltesgeist: haha\r
                vacume clener: vroom\r
                locale ibeote: and the poltergiest was dead by the vacuem clener\r
                guy: ok\r
                \r
                the edn : hi guys i ame the end, over""";
        String testText = TextFileReader.readFileAsString("/org/team6/texts/manyRowTestText.md");
        assertEquals(test, testText, "Should return correct text.");
    }

    @Test
    void testTextFileReader_GivenNonexistentFile_ShouldReturnEmptyString() {
        String testText = TextFileReader.readFileAsString("/org/team6/texts/fakeText.txt");
        assertEquals("", testText, "Should return empty string when file does not exist.");
    }
}   
