package writers;

import city.District;
import city.DistrictsStore;
import lombok.val;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriterToXml implements Writer {
    private final static Logger logger = Logger.getLogger(WriterToXml.class.getName());

    @Override
    public void writeToFile(final String fileName, final List<District> districts) throws JAXBException {
        DistrictsStore store = new DistrictsStore();
        store.setDistricts(districts);

        val context = JAXBContext.newInstance(DistrictsStore.class);
        val contextMarshaller = context.createMarshaller();
        contextMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        contextMarshaller.marshal(store, new File(fileName));
        logger.log(Level.INFO, "Данные записаны в файл");
    }
}
