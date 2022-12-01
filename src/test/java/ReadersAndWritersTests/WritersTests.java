package ReadersAndWritersTests;

import Readers.Reader;
import Readers.XmlReaderWithJaxb;
import Writers.Writer;
import Writers.WriterToXmlWithJaxb;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class WritersTests {
    @Test
    public void jaxbWriterTest() throws JAXBException, FileNotFoundException {
        Reader reader = new XmlReaderWithJaxb();
        var dataFromFile = reader.readFromFile("A:\\FileConverter\\xml.xml");

        Writer writer = new WriterToXmlWithJaxb();
        writer.writeToFile("A:\\FileConverter\\ResultXmlForTest.xml", dataFromFile);

        dataFromFile = reader.readFromFile("A:\\FileConverter\\ResultXmlForTest.xml");
        assertEquals(dataFromFile.size(), 5);
    }
}
