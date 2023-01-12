import com.google.gson.JsonSyntaxException;
import org.junit.Test;
import readers.JsonReader;
import readers.Reader;
import readers.XmlReader;

import javax.xml.bind.JAXBException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ReadersTests {
    private Reader reader;

    private final static String PATH_TO_CORRECT_XML_FILE = "src\\test\\resources\\files\\CorrectXml.xml";

    private final static String PATH_TO_CORRECT_JSON_FILE = "src\\test\\resources\\files\\CorrectJson.json";

    private final static int COUNT_OF_DISTRICTS_IN_CORRECT_FILE = 5;

    @Test
    public void jaxbReaderTest() throws JAXBException, IOException {
        reader = new XmlReader();
        var dataFromFile = reader.readFromFile(PATH_TO_CORRECT_XML_FILE);

        assertEquals(dataFromFile.size(), COUNT_OF_DISTRICTS_IN_CORRECT_FILE);
    }

    @Test
    public void jsonReaderTest() throws JAXBException, IOException {
        reader = new JsonReader();
        var dataFromFile = reader.readFromFile(PATH_TO_CORRECT_JSON_FILE);

        assertEquals(dataFromFile.size(), COUNT_OF_DISTRICTS_IN_CORRECT_FILE);
    }
}
