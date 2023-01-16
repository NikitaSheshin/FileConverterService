package converter.fileconverter;

import converter.readers.JsonReader;
import converter.readers.Reader;
import converter.readers.XmlReader;

import javax.xml.bind.JAXBException;

public class ReaderCreater {
    static public Reader create(String fileName) throws JAXBException {
        return fileName.endsWith("xml") ? new XmlReader() : new JsonReader();
    }
}
