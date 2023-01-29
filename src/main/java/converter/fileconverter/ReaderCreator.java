package converter.fileconverter;

import converter.readers.JsonReader;
import converter.readers.Reader;
import converter.readers.XmlReader;

import javax.xml.bind.JAXBException;

public class ReaderCreator {
    static public Reader create(final String fileName) throws JAXBException {
        return fileName.endsWith("xml") ? XmlReader.getInstance() : JsonReader.getInstance();
    }
}
