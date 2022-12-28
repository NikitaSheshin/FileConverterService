package writers;

import beans.mappers.DistrictsListMapper;
import beans.json.DistrictJson;
import beans.xml.DistrictsStoreXml;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

@Slf4j
public class WriterToXml implements Writer {
    public void writeToFile(final String fileName, final List<?> districts) {
        try {
            val contextMarshaller = JAXBContext.newInstance(DistrictsStoreXml.class).createMarshaller();
            contextMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            contextMarshaller.marshal(new DistrictsStoreXml(
                            DistrictsListMapper.instance.toDistrictXmlList((List<DistrictJson>) districts)),
                    new File(fileName));
        } catch (JAXBException jaxbException) {
            log.warn("Ошибка при попытке записать данные в файл", jaxbException);
            System.out.println("Ошибка при попытке записать данные в файл");
            throw new ClassCastException();
        }

        log.trace("Данные записаны в файл");
    }
}