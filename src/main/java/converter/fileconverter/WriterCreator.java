package converter.fileconverter;

import converter.writers.Writer;
import converter.writers.WriterToJson;
import converter.writers.WriterToXml;

import javax.xml.bind.JAXBException;

public class WriterCreator {
    static public Writer create(String fileName) throws JAXBException {
        return fileName.endsWith("xml") ? WriterToXml.getInstance() : WriterToJson.getInstance();
    }
}
