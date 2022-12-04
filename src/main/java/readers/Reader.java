package readers;

import city.District;
import org.json.simple.parser.ParseException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface Reader {
    List<District> readFromFile(final String fileName) throws JAXBException,
                                                              IOException,
                                                              ParseException;
}

