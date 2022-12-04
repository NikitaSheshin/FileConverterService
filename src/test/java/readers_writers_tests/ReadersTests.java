package ReadersAndWritersTests;

import readers.JsonReader;
import readers.Reader;
import readers.XmlReader;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.*;

public class ReadersTests {
    private Reader reader;

    private final static String PATH_TO_CORRECT_XML_FILE = "A:\\FileConverter\\CorrectXml.xml";
    private final static String PATH_TO_INCORRECT_XML_FILE = "A:\\FileConverter\\WrongXml.xml";
    private final static String WRONG_PATH = "wrong path";

    private final static String PATH_TO_CORRECT_JSON_FILE = "A:\\FileConverter\\CorrectJson.json";

    private final static int COUNT_OF_DISTRICTS_IN_CORRECT_FILE = 5;

    @Test
    public void jaxbReaderTest() throws JAXBException,
                                        IOException,
                                        ParseException,
                                        ParserConfigurationException,
                                        SAXException {
        reader = new XmlReader();
        var dataFromFile = reader.readFromFile(PATH_TO_CORRECT_XML_FILE);

        assertEquals(dataFromFile.size(), COUNT_OF_DISTRICTS_IN_CORRECT_FILE);
    }

    @Test(expected = FileNotFoundException.class)
    public void jaxbReaderWithWrongPath() throws JAXBException,
                                                 IOException,
                                                 ParseException,
                                                 ParserConfigurationException,
                                                 SAXException {
        reader = new XmlReader();
        var dataFromFile = reader.readFromFile(WRONG_PATH);
    }

    @Test(expected = JAXBException.class)
    public void jaxbReaderWithBadStructure() throws JAXBException,
                                                    IOException,
                                                    ParseException,
                                                    ParserConfigurationException,
                                                    SAXException {
        reader = new XmlReader();
        var dataFromFile = reader.readFromFile(PATH_TO_INCORRECT_XML_FILE);
    }

    @Test
    public void jsonReaderTest() throws JAXBException,
            IOException,
            ParseException,
            ParserConfigurationException,
            SAXException {
        reader = new JsonReader();
        var dataFromFile = reader.readFromFile(PATH_TO_CORRECT_JSON_FILE);

        assertEquals(dataFromFile.size(), COUNT_OF_DISTRICTS_IN_CORRECT_FILE);
    }
}
