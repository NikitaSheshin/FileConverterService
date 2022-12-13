import converter.file.FileConverter;
import lombok.val;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class FileConverterTests {
    private static final String XML_CORRECT_FILE_NAME = "src\\test\\files_for_tests\\CorrectXml.xml";
    private static final String JSON_CORRECT_FILE_NAME = "src\\test\\files_for_tests\\CorrectJson.json";

    private static final String XML_RESULT_FILE_NAME = "src\\test\\files_for_tests\\ResultXmlForTest.xml";
    private static final String JSON_RESULT_FILE_NAME = "src\\test\\files_for_tests\\ResultJsonForTest.json";

    private FileConverter fileConverter;

    @Before
    public void initConverter() {
        fileConverter = new FileConverter();
    }

    @Test
    public void testConvertXmlToJson() {
        fileConverter.convert(new String[]{XML_CORRECT_FILE_NAME, JSON_RESULT_FILE_NAME} );

        assertTrue(isEqual(JSON_CORRECT_FILE_NAME, JSON_RESULT_FILE_NAME));
    }

    @Test
    public void testConvertJsonToXml() {
        fileConverter.convert(new String[] {JSON_CORRECT_FILE_NAME, XML_RESULT_FILE_NAME} );

        assertTrue(isEqual(XML_CORRECT_FILE_NAME, XML_RESULT_FILE_NAME));
    }

    private static boolean isEqual(String firstFileName, String secondFileName) {
        try {
            val firstFileContent = Files.readAllBytes(Path.of(firstFileName));
            val secondFileContent = Files.readAllBytes(Path.of(secondFileName));

            return Arrays.equals(firstFileContent, secondFileContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
