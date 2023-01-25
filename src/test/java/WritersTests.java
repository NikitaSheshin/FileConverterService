import lombok.val;
import org.junit.Test;
import converter.readers.JsonReader;
import converter.readers.Reader;
import converter.readers.XmlReader;
import converter.writers.Writer;
import converter.writers.WriterToJson;
import converter.writers.WriterToXml;

import javax.xml.bind.JAXBException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class WritersTests {
    private final static String PATH_TO_CORRECT_XML_FILE = "src/test/resources/files/CorrectXml.xml";
    private final static String PATH_TO_RESULT_XML_FILE = "src/test/resources/files/ResultXmlForTest.xml";

    private final static String PATH_TO_CORRECT_JSON_FILE = "src/test/resources/files/CorrectJson.json";
    private final static String PATH_TO_RESULT_JSON_FILE = "src/test/resources/files/ResultJsonForTest.json";

    private final static int COUNT_OF_DISTRICTS_IN_CORRECT_FILE = 5;

    private Reader reader;

    @Test
    public void jaxbWriterTest() throws JAXBException, IOException {
        reader = JsonReader.getInstance();
        var dataFromFile = reader.readFromFile(PATH_TO_CORRECT_JSON_FILE);

        Writer writer = WriterToXml.getInstance();
        writer.writeToFile(PATH_TO_RESULT_XML_FILE, dataFromFile);

        reader = XmlReader.getInstance();
        dataFromFile = reader.readFromFile(PATH_TO_RESULT_XML_FILE);
        assertEquals(dataFromFile.size(), COUNT_OF_DISTRICTS_IN_CORRECT_FILE);
    }

    @Test
    public void jsonWriterTest() throws JAXBException, IOException {
        reader = XmlReader.getInstance();

        Writer writer = WriterToJson.getInstance();
        writer.writeToFile(PATH_TO_RESULT_JSON_FILE, reader.readFromFile(PATH_TO_CORRECT_XML_FILE));

        reader = JsonReader.getInstance();
        val dataFromFile = reader.readFromFile(PATH_TO_RESULT_JSON_FILE);
        assertEquals(dataFromFile.size(), COUNT_OF_DISTRICTS_IN_CORRECT_FILE);
    }
}
