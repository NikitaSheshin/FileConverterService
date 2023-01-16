package converter.file;

import readers.JsonReader;
import readers.Reader;
import readers.XmlReader;

import javax.xml.bind.JAXBException;

public class ReaderCreater {
    static public Reader create(String fileName) throws JAXBException {
        return fileName.endsWith("xml") ? new XmlReader() : new JsonReader();
    }
}
