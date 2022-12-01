package Writers;

import City.District;
import City.DistrictsStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class WriterToXmlWithJaxb implements Writer {
    @Override
    public void writeToFile(String fileName, List<District> districts) throws JAXBException {
        DistrictsStore store = new DistrictsStore();
        store.setDistricts(districts);

        JAXBContext context = JAXBContext.newInstance(DistrictsStore.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(store, new File(fileName));
    }
}
