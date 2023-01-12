import lombok.val;
import org.junit.Test;
import readers.JsonReader;
import readers.Reader;
import readers.XmlReader;
import writers.Writer;
import writers.WriterToJson;
import writers.WriterToXml;

import javax.xml.bind.JAXBException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class WritersTests {
    private final static String PATH_TO_CORRECT_XML_FILE = "src\\test\\resources\\files\\CorrectXml.xml";
    private final static String PATH_TO_RESULT_XML_FILE = "src\\test\\resources\\files\\ResultXmlForTest.xml";

    private final static String PATH_TO_CORRECT_JSON_FILE = "src\\test\\resources\\files\\CorrectJson.json";
    private final static String PATH_TO_RESULT_JSON_FILE = "src\\test\\resources\\files\\ResultJsonForTest.json";

    private final static int COUNT_OF_DISTRICTS_IN_CORRECT_FILE = 5;

    private Reader reader;

    @Test
    public void jaxbWriterTest() throws JAXBException, IOException {
        reader = new JsonReader();
        var dataFromFile = reader.readFromFile(PATH_TO_CORRECT_JSON_FILE);

        Writer writer = new WriterToXml();
        writer.writeToFile(PATH_TO_RESULT_XML_FILE, dataFromFile);

        reader = new XmlReader();
        dataFromFile = reader.readFromFile(PATH_TO_RESULT_XML_FILE);
        assertEquals(dataFromFile.size(), COUNT_OF_DISTRICTS_IN_CORRECT_FILE);
    }

    @Test
    public void jsonWriterTest() throws JAXBException, IOException {
        reader = new XmlReader();

        Writer writer = new WriterToJson();
        writer.writeToFile(PATH_TO_RESULT_JSON_FILE, reader.readFromFile(PATH_TO_CORRECT_XML_FILE));

        reader = new JsonReader();
        val dataFromFile = reader.readFromFile(PATH_TO_RESULT_JSON_FILE);
        assertEquals(dataFromFile.size(), COUNT_OF_DISTRICTS_IN_CORRECT_FILE);
    }
}
