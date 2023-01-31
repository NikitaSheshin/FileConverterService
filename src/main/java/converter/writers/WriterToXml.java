package converter.writers;

import converter.beans.json.DistrictJson;
import converter.beans.mappers.DistrictsListMapper;
import converter.beans.xml.DistrictsStoreXml;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

@Slf4j
public class WriterToXml implements Writer {
    private final Marshaller MARSHALLER = JAXBContext.newInstance(DistrictsStoreXml.class).createMarshaller();

    public WriterToXml() throws JAXBException {
        MARSHALLER.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        log.debug("Создан объект WriterToXml");
    }

    public void writeToFile(final String fileName, final List<?> districts) throws JAXBException {
        MARSHALLER.marshal(new DistrictsStoreXml(
                DistrictsListMapper.instance.toDistrictXmlList((List<DistrictJson>) districts)),
            new File(fileName));

        log.debug("Данные записаны в xml файл");
    }
}