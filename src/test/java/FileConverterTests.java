import file_converter.FileConverter;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FileConverterTests {
    private static final String XML_CORRECT_FILE_NAME = "A:\\FileConverter\\CorrectXml.xml";
    private static final String JSON_CORRECT_FILE_NAME = "A:\\FileConverter\\CorrectJson.json";

    private static final String XML_RESULT_FILE_NAME = "A:\\FileConverter\\ResultXmlForTest.xml";
    private static final String JSON_RESULT_FILE_NAME = "A:\\FileConverter\\ResultJsonForTest.json";

    private FileConverter fileConverter;

    @Before
    public void initConverter() {
        fileConverter = new FileConverter();
    }

    @Test
    public void testConvertXmlToJson() {
        fileConverter.convert(new String[]{XML_CORRECT_FILE_NAME, JSON_RESULT_FILE_NAME} );

        File correctFile = new File(JSON_CORRECT_FILE_NAME);
        File resultFile = new File(JSON_RESULT_FILE_NAME);

        assertTrue(isEqual(correctFile, resultFile));
    }

    @Test
    public void testConvertJsonToXml() {
        fileConverter.convert(new String[] {JSON_CORRECT_FILE_NAME, XML_RESULT_FILE_NAME} );

        File correctFile = new File(XML_CORRECT_FILE_NAME);
        File resultFile = new File(XML_RESULT_FILE_NAME);

        assertTrue(isEqual(correctFile, resultFile));
    }

    private static boolean isEqual(File firstFile, File secondFile) {
        try {
            byte[] firstFileContent = Files.readAllBytes(firstFile.toPath());
            byte[] secondFileContent = Files.readAllBytes(secondFile.toPath());

            return Arrays.equals(firstFileContent, secondFileContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
