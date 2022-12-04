package writers;

import city.District;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface Writer {
    void writeToFile(final String fileName, final List<District> districts) throws JAXBException,
            IOException;
}
