import converter.file.FileConverter;
import org.junit.Before;
import org.junit.Test;

import org.apache.commons.io.IOUtils;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class FileConverterTests {
    private static final String XML_CORRECT_FILE_NAME = "src\\test\\files\\CorrectXml.xml";
    private static final String JSON_CORRECT_FILE_NAME = "src\\test\\files\\CorrectJson.json";

    private static final String XML_RESULT_FILE_NAME = "src\\test\\files\\ResultXmlForTest.xml";
    private static final String JSON_RESULT_FILE_NAME = "src\\test\\files\\ResultJsonForTest.json";

    private FileConverter fileConverter;

    @Before
    public void initConverter() {
        fileConverter = new FileConverter();
    }

    @Test
    public void testConvertXmlToJson() throws IOException {
        fileConverter.convert(new String[]{XML_CORRECT_FILE_NAME, JSON_RESULT_FILE_NAME} );

        assertTrue(isEqual(JSON_CORRECT_FILE_NAME, JSON_RESULT_FILE_NAME));
    }

    @Test
    public void testConvertJsonToXml() {
        fileConverter.convert(new String[] {JSON_CORRECT_FILE_NAME, XML_RESULT_FILE_NAME} );

        assertTrue(isEqual(XML_CORRECT_FILE_NAME, XML_RESULT_FILE_NAME));
    }

    private static boolean isEqual(String firstFileName, String secondFileName) {
        try (Reader firstFile = new BufferedReader(new FileReader(firstFileName))) {
            try (Reader secondFile = new BufferedReader(new FileReader(secondFileName))) {
                return IOUtils.contentEqualsIgnoreEOL(firstFile, secondFile);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
