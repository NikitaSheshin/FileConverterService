package Readers;

import City.District;
import City.DistrictsStore;
import lombok.val;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class XmlReaderWithJaxb implements Reader {
    public List<District> readFromFile(final String fileName) throws JAXBException, FileNotFoundException {
        val context = JAXBContext.newInstance(DistrictsStore.class);
        val um = context.createUnmarshaller();

        return ((DistrictsStore) um.unmarshal(new InputStreamReader(
                new FileInputStream(fileName), StandardCharsets.UTF_8))).getDistricts();
    }
}
