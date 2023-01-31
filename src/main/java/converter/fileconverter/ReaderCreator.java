package converter.fileconverter;

import converter.readers.JsonReader;
import converter.readers.Reader;
import converter.readers.XmlReader;

import javax.xml.bind.JAXBException;

public class ReaderCreator {
    static XmlReader xmlReader = null;
    static JsonReader jsonReader = null;

    static public Reader create(final String fileName) throws JAXBException {
        return fileName.endsWith("xml") ? getXmlReader() : getJsonReader();
    }

    private static Reader getXmlReader() throws JAXBException {
        if (xmlReader == null) {
            xmlReader = new XmlReader();
        }

        return xmlReader;
    }

    private static Reader getJsonReader() {
        if (jsonReader == null) {
            jsonReader = new JsonReader();
        }

        return jsonReader;
    }
}
