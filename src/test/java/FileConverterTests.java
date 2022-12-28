import converter.file.FileConverter;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class FileConverterTests {
    private static final String XML_CORRECT_FILE_NAME = "src\\test\\resources\\files\\CorrectXml.xml";
    private static final String JSON_CORRECT_FILE_NAME = "src\\test\\resources\\files\\CorrectJson.json";

    private static final String XML_RESULT_FILE_NAME = "src\\test\\resources\\files\\ResultXmlForTest.xml";
    private static final String JSON_RESULT_FILE_NAME = "src\\test\\resources\\files\\ResultJsonForTest.json";

    private FileConverter fileConverter;

    @Before
    public void initConverter() {
        fileConverter = new FileConverter();
    }

    @Test
    public void testConvertXmlToJson() {
        fileConverter.convert(new String[]{XML_CORRECT_FILE_NAME, JSON_RESULT_FILE_NAME});

        assertTrue(isEqual("/files/CorrectJson.json", "/files/ResultJsonForTest.json"));
    }

    @Test
    public void testConvertJsonToXml() {
        fileConverter.convert(new String[]{JSON_CORRECT_FILE_NAME, XML_RESULT_FILE_NAME});

        assertTrue(isEqual("/files/CorrectXml.xml", "/files/ResultXmlForTest.xml"));
    }

    private boolean isEqual(String firstFileName, String secondFileName) {
        var firstFile = getClass().getResourceAsStream(firstFileName);
        var secondFile = getClass().getResourceAsStream(secondFileName);

        try {
            return Arrays.equals(firstFile.readAllBytes(), secondFile.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void xmlToJsonSpeedCheck() {
        for (int numberOfTry = 0; numberOfTry < 10000; ++numberOfTry) {
            fileConverter.convert(new String[]{XML_CORRECT_FILE_NAME, JSON_RESULT_FILE_NAME});
            assertTrue(isEqual("/files/CorrectJson.json", "/files/ResultJsonForTest.json"));
        }
    }

    @Test
    public void jsonToXmlSpeedCheck() {
        for (int numberOfTry = 0; numberOfTry < 10000; ++numberOfTry) {
            fileConverter.convert(new String[]{JSON_CORRECT_FILE_NAME, XML_RESULT_FILE_NAME});
            assertTrue(isEqual("/files/CorrectXml.xml", "/files/ResultXmlForTest.xml"));
        }
    }
}
