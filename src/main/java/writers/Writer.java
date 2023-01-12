package writers;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface Writer {
    void writeToFile(final String fileName, final List<?> districts) throws JAXBException, IOException;
}
