package readers;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface Reader {
    List<?> readFromFile(final String fileName) throws JAXBException, IOException;
}
