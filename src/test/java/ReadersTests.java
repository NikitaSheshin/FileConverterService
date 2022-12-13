import com.google.gson.JsonSyntaxException;
import org.junit.Test;
import readers.JsonReader;
import readers.Reader;
import readers.XmlReader;

import static org.junit.Assert.assertEquals;

public class ReadersTests {
    private Reader reader;

    private final static String PATH_TO_CORRECT_XML_FILE = "src\\test\\files\\CorrectXml.xml";
    private final static String PATH_TO_INCORRECT_XML_FILE = "src\\test\\files\\WrongXml.xml";

    private final static String PATH_TO_CORRECT_JSON_FILE = "src\\test\\files\\CorrectJson.json";
    private final static String PATH_TO_INCORRECT_JSON_FILE = "src\\test\\files\\WrongJson.json";

    private final static String WRONG_PATH = "wrong path";
    private final static int COUNT_OF_DISTRICTS_IN_CORRECT_FILE = 5;

    @Test
    public void jaxbReaderTest() {
        reader = new XmlReader();
        var dataFromFile = reader.readFromFile(PATH_TO_CORRECT_XML_FILE);

        assertEquals(dataFromFile.size(), COUNT_OF_DISTRICTS_IN_CORRECT_FILE);
    }

    @Test(expected = IllegalCallerException.class)
    public void jaxbReaderWithWrongPath() {
        reader = new XmlReader();
        reader.readFromFile(WRONG_PATH);
    }

    @Test(expected = ClassCastException.class)
    public void jaxbReaderWithBadStructure() {
        reader = new XmlReader();
        reader.readFromFile(PATH_TO_INCORRECT_XML_FILE);
    }

    @Test
    public void jsonReaderTest() {
        reader = new JsonReader();
        var dataFromFile = reader.readFromFile(PATH_TO_CORRECT_JSON_FILE);

        assertEquals(dataFromFile.size(), COUNT_OF_DISTRICTS_IN_CORRECT_FILE);
    }

    @Test(expected = IllegalCallerException.class)
    public void jsonReaderTestWithWrongPath() {
        reader = new JsonReader();
        reader.readFromFile(WRONG_PATH);
    }

    @Test(expected = JsonSyntaxException.class)
    public void jsonReaderTestWithWrongStructure() {
        reader = new JsonReader();
        reader.readFromFile(PATH_TO_INCORRECT_JSON_FILE);
    }
}
