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
    private final static String PATH_TO_INCORRECT_XML_FILE = "src\\test\\resources\\\\WrongXml.xml";

    private final static String PATH_TO_CORRECT_JSON_FILE = "src\\test\\resources\\files\\CorrectJson.json";
    private final static String PATH_TO_INCORRECT_JSON_FILE = "src\\test\\resources\\files\\WrongJson.json";

    private final static String WRONG_PATH = "wrong path";
    private final static int COUNT_OF_DISTRICTS_IN_CORRECT_FILE = 5;

    @Test
    public void jaxbReaderTest() throws JAXBException, IOException {
        reader = new XmlReader();
        var dataFromFile = reader.readFromFile(PATH_TO_CORRECT_XML_FILE);

        assertEquals(dataFromFile.size(), COUNT_OF_DISTRICTS_IN_CORRECT_FILE);
    }

    @Test(expected = IllegalCallerException.class)
    public void jaxbReaderWithWrongPath() throws JAXBException, IOException {
        reader = new XmlReader();
        reader.readFromFile(WRONG_PATH);
    }

    @Test(expected = IllegalCallerException.class)
    public void jaxbReaderWithBadStructure() throws JAXBException, IOException {
        reader = new XmlReader();
        reader.readFromFile(PATH_TO_INCORRECT_XML_FILE);
    }

    @Test
    public void jsonReaderTest() throws JAXBException, IOException {
        reader = new JsonReader();
        var dataFromFile = reader.readFromFile(PATH_TO_CORRECT_JSON_FILE);

        assertEquals(dataFromFile.size(), COUNT_OF_DISTRICTS_IN_CORRECT_FILE);
    }

    @Test(expected = IllegalCallerException.class)
    public void jsonReaderTestWithWrongPath() throws JAXBException, IOException {
        reader = new JsonReader();
        reader.readFromFile(WRONG_PATH);
    }

    @Test(expected = JsonSyntaxException.class)
    public void jsonReaderTestWithWrongStructure() throws JAXBException, IOException {
        reader = new JsonReader();
        reader.readFromFile(PATH_TO_INCORRECT_JSON_FILE);
    }
}
