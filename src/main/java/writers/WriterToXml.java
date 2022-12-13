package writers;

import beans.json.DistrictJson;
import beans.json.DistrictsStoreJson;
import beans.xml.DistrictsStoreXml;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.dozer.DozerBeanMapper;

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

            DozerBeanMapper mapper = new DozerBeanMapper();
            val data = mapper.map(new DistrictsStoreJson((List<DistrictJson>) districts),
                                                                        DistrictsStoreXml.class);

            contextMarshaller.marshal(data, new File(fileName));
        } catch (JAXBException jaxbException) {
            log.error("Ошибка при попытке записать данные в файл", jaxbException);
            System.out.println("Ошибка при попытке записать данные в файл");
            throw new ClassCastException();
        }

        log.info("Данные записаны в файл");
    }
}
