package Readers;

import City.District;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public interface Reader {
    List<District> readFromFile(final String fileName) throws JAXBException, FileNotFoundException;
}
