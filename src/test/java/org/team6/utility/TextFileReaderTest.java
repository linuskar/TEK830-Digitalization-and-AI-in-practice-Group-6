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
    void testTextFileReader_readTxt_notnull(TextPath textPath) {
        String testText = TextFileReader.readFileAsString(textPath.getPath());
        assertNotNull(testText);
    }

    @ParameterizedTest
    @EnumSource(TextPath.class)
    void testTextFileReader_readTxt_isNotEmpty(TextPath textPath) {
        String testText = TextFileReader.readFileAsString(textPath.getPath());
        assertFalse(testText.isEmpty());
    }

    @Test
    void testTextFileReader_readTxt_worksOneRow() {
        String test = "ok";
        String testText = TextFileReader.readFileAsString("/org/team6/texts/oneRowTestText.txt");
        assertEquals(test,testText);
    }

    @Test
    void testTextFileReader_readTxt_worksManyRows() {
        String test = "hi guy local idiot here \r\n" + //
                        "guy: ok\r\n" + //
                        "local iebaot: hehea i the robbing you\r\n" + //
                        "guy: ok\r\n" + //
                        "polceiace: i am poltergieste to scary four you away\r\n" + //
                        "locale ibeote: ahh and then the locale idiot ran away\r\n" + //
                        "locale iebaot: why died h running away\r\n" + //
                        "table: he ws sacred or the poltergieste\r\n" + //
                        "poltesgeist: haha\r\n" + //
                        "vacume clener: vroom\r\n" + //
                        "locale ibeote: and the poltergiest was dead by the vacuem clener\r\n" + //
                        "guy: ok\r\n" + //
                        "\r\n" + //
                        "the edn : hi guys i ame the end, over";
        String testText = TextFileReader.readFileAsString("/org/team6/texts/manyRowTestText.txt");
        assertEquals(test,testText);
    }


    @Test
    void testTextFileReader_readMd_worksOneRow() {
        String test = "ok";
        String testText = TextFileReader.readFileAsString("/org/team6/texts/oneRowTestText.md");
        assertEquals(test,testText);
    }

    @Test
    void testTextFileReader_readMd_worksManyRows() {
        String test = "hi guy local idiot here \r\n" + //
                        "guy: ok\r\n" + //
                        "local iebaot: hehea i the robbing you\r\n" + //
                        "guy: ok\r\n" + //
                        "polceiace: i am poltergieste to scary four you away\r\n" + //
                        "locale ibeote: ahh and then the locale idiot ran away\r\n" + //
                        "locale iebaot: why died h running away\r\n" + //
                        "table: he ws sacred or the poltergieste\r\n" + //
                        "poltesgeist: haha\r\n" + //
                        "vacume clener: vroom\r\n" + //
                        "locale ibeote: and the poltergiest was dead by the vacuem clener\r\n" + //
                        "guy: ok\r\n" + //
                        "\r\n" + //
                        "the edn : hi guys i ame the end, over";
        String testText = TextFileReader.readFileAsString("/org/team6/texts/manyRowTestText.md");
        assertEquals(test,testText);
    }
}   