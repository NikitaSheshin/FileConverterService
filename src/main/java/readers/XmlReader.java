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
import java.util.logging.Level;
import java.util.logging.Logger;

public class XmlReaderWithJaxb implements Reader {
    private final static Logger logger = Logger.getLogger(XmlReader.class.getName());

    public List<District> readFromFile(final String fileName) throws JAXBException, FileNotFoundException {
        val context = JAXBContext.newInstance(DistrictsStore.class);
        val um = context.createUnmarshaller();

        val dataFromFile = ((DistrictsStore) um.unmarshal(new InputStreamReader(
                new FileInputStream(fileName), StandardCharsets.UTF_8))).getDistricts();
        logger.log(Level.INFO, "Данные из файла считаны в List");

        return dataFromFile;
    }
}
