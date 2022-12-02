package Writers;

import City.District;
import City.DistrictsStore;
import lombok.val;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class WriterToXmlWithJaxb implements Writer {
    @Override
    public void writeToFile(final String fileName, final List<District> districts) throws JAXBException {
        DistrictsStore store = new DistrictsStore();
        store.setDistricts(districts);

        val context = JAXBContext.newInstance(DistrictsStore.class);
        val contextMarshaller = context.createMarshaller();
        contextMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        contextMarshaller.marshal(store, new File(fileName));
    }
}
