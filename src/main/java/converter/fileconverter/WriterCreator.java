package converter.fileconverter;

import converter.writers.Writer;
import converter.writers.WriterToJson;
import converter.writers.WriterToXml;

import javax.xml.bind.JAXBException;

public class WriterCreator {
    static WriterToXml xmlWriter = null;
    static WriterToJson jsonWriter = null;

    static public Writer create(final String fileName) throws JAXBException {
        return fileName.endsWith("xml") ? getXmlWriter() : getJsonWriter();
    }

    static private Writer getXmlWriter() throws JAXBException {
        if (xmlWriter == null) {
            xmlWriter = new WriterToXml();
        }

        return xmlWriter;
    }

    static private Writer getJsonWriter() {
        if (jsonWriter == null) {
            jsonWriter = new WriterToJson();
        }

        return jsonWriter;
    }
}
