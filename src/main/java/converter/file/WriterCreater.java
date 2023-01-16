package converter.file;

import writers.Writer;
import writers.WriterToJson;
import writers.WriterToXml;

import javax.xml.bind.JAXBException;

public class WriterCreater {
    static public Writer create(String fileName) throws JAXBException {
        return fileName.endsWith("xml") ? new WriterToXml() : new WriterToJson();
    }
}
