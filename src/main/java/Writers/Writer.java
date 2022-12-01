package Writers;

import City.District;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface Writer {
    void writeToFile(String fileName, List<District> districts) throws JAXBException;
}
