package readers;

import beans.xml.DistrictXml;
import beans.xml.DistrictsStoreXml;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
public class XmlReader implements Reader {
    private static final String FILE_NOT_FOUND_MESSAGE = "Не найден файл по указанному пути";

    private final Unmarshaller UNMARSHALLER = JAXBContext.newInstance(DistrictsStoreXml.class).createUnmarshaller();

    public XmlReader() throws JAXBException {
        log.debug("Создан объект XmlReader");
    }

    public List<DistrictXml> readFromFile(final String fileName) throws JAXBException, IOException {
        try (val fileStream = new FileInputStream(fileName); val inputStream = new InputStreamReader(fileStream)) {
            return ((DistrictsStoreXml) UNMARSHALLER.unmarshal(inputStream)).getDistricts();
        }
    }
}