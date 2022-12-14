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
    public List<DistrictXml> readFromFile(final String fileName) {
        try {
            return tryToReadFromFile(fileName);
        } catch (JAXBException jaxbException) {
            log.warn("Ошибка при попытке считать данные из файла", jaxbException);
            System.out.println("Ошибка при попытке считать данные из файла");
            throw new ClassCastException("Ошибка считывания данных из файла");
        }
    }

    private List<DistrictXml> tryToReadFromFile(final String fileName) throws JAXBException {
        try(var fileStream = new FileInputStream(fileName)) {
            try (var inputStream = new InputStreamReader(fileStream)){
                val um = JAXBContext.newInstance(DistrictsStoreXml.class).createUnmarshaller();
                return ((DistrictsStoreXml) um.unmarshal(inputStream)).getDistricts();
            }
        } catch (IOException fileNotFoundException) {
            log.warn("Не найден файл по указанному пути", fileNotFoundException);
            System.out.println("Не найден файл по указанному пути");
            throw new IllegalCallerException(fileNotFoundException);
        }
    }
}