package readers;

import beans.xml.DistrictXml;
import beans.xml.DistrictsStoreXml;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
public class XmlReader implements Reader {
    private static final String FILE_NOT_FOUND_MESSAGE = "Не найден файл по указанному пути";
    private static final String READ_ERROR_MESSAGE = "Ошибка считывания данных из файла";

    public List<DistrictXml> readFromFile(final String fileName) {
        try {
            return tryToReadFromFile(fileName);
        } catch (JAXBException jaxbException) {
            log.warn(READ_ERROR_MESSAGE, jaxbException);
            System.out.println(READ_ERROR_MESSAGE);
            throw new ClassCastException(READ_ERROR_MESSAGE);
        }
    }

    private List<DistrictXml> tryToReadFromFile(final String fileName) throws JAXBException {
        try (val fileStream = new FileInputStream(fileName); val inputStream = new InputStreamReader(fileStream)) {
            val um = JAXBContext.newInstance(DistrictsStoreXml.class).createUnmarshaller();
            return ((DistrictsStoreXml) um.unmarshal(inputStream)).getDistricts();
        } catch (IOException fileNotFoundException) {
            log.warn(FILE_NOT_FOUND_MESSAGE, fileNotFoundException);
            System.out.println(FILE_NOT_FOUND_MESSAGE);
            throw new IllegalCallerException(FILE_NOT_FOUND_MESSAGE);
        }
    }
}