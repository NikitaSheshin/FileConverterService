package ReadersAndWritersTests;

import City.District;
import City.Entrance;
import Readers.Reader;
import Readers.XmlReaderWithJaxb;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

public class ReadersTests {
    @Test
    public void jaxbReaderTest() throws JAXBException, FileNotFoundException {
        Reader reader = new XmlReaderWithJaxb();

        var dataFromFile = reader.readFromFile("A:\\untitled\\xml.xml");

        assertEquals(dataFromFile.size(), 5);
    }

    @Test(expected = FileNotFoundException.class)
    public void jaxbReaderWithWrongPath() {
        Reader reader = new XmlReaderWithJaxb();

        var dataFromFile = reader.readFromFile("wrong path");
    }
}
