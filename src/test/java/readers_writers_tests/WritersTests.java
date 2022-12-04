package ReadersAndWritersTests;

import readers.JsonReader;
import readers.Reader;
import readers.XmlReader;
import writers.Writer;
import writers.WriterToJson;
import writers.WriterToXml;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

import static org.junit.Assert.*;

public class WritersTests {
    private final static String PATH_TO_CORRECT_XML_FILE = "A:\\FileConverter\\CorrectXml.xml";
    private final static String PATH_TO_RESULT_XML_FILE = "A:\\FileConverter\\ResultXmlForTest.xml";

    private final static String PATH_TO_CORRECT_JSON_FILE = "A:\\FileConverter\\CorrectJson.json";
    private final static String PATH_TO_RESULT_JSON_FILE = "A:\\FileConverter\\ResultJsonForTest.json";

    private final static int COUNT_OF_DISTRICTS_IN_CORRECT_FILE = 5;

    private Reader reader;

    @Test
    public void jaxbWriterTest() throws JAXBException,
                                        IOException,
                                        ParseException,
                                        ParserConfigurationException,
                                        SAXException,
                                        TransformerException {
        reader = new XmlReader();
        var dataFromFile = reader.readFromFile(PATH_TO_CORRECT_XML_FILE);

        Writer writer = new WriterToXml();
        writer.writeToFile(PATH_TO_RESULT_XML_FILE, dataFromFile);

        dataFromFile = reader.readFromFile(PATH_TO_RESULT_XML_FILE);
        assertEquals(dataFromFile.size(), COUNT_OF_DISTRICTS_IN_CORRECT_FILE);
    }

    @Test
    public void jsonWriterTest() throws JAXBException,
            IOException,
            ParseException,
            ParserConfigurationException,
            SAXException,
            TransformerException {
        reader = new JsonReader();
        var dataFromFile = reader.readFromFile(PATH_TO_CORRECT_JSON_FILE);

        Writer writer = new WriterToJson();
        writer.writeToFile(PATH_TO_RESULT_JSON_FILE, dataFromFile);

        dataFromFile = reader.readFromFile(PATH_TO_RESULT_JSON_FILE);
        assertEquals(dataFromFile.size(), COUNT_OF_DISTRICTS_IN_CORRECT_FILE);
    }
}
