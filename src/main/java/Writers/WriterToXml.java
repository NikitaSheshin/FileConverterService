package Writers;

import City.District;
import City.Entrance;
import City.House;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class WriterToXml implements Writer{
    @Override
    public void writeToFile(String fileName, List<District> districts) {
        Document doc;
        try {
            doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }

        Element root = doc.createElement("database");

        for (var district : districts){
            root.appendChild(createDistrict(district, doc));
        }

        doc.appendChild(root);

        DOMSource src = new DOMSource(doc);

        StreamResult result;
        try {
            result = new StreamResult(new FileOutputStream(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            TransformerFactory.newInstance().newTransformer().transform(src, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private static Node createDistrict(District district, Document doc){

        Element districtNode = doc.createElement("district");
        Element name = doc.createElement("name");
        name.setTextContent(district.getName());
        districtNode.appendChild(name);

        for(int i = 0; i < district.getHouses().size(); ++i){
            districtNode.appendChild(createHouse(district.getHouses().get(i), i, doc));
        }

        return districtNode;
    }

    private static Node createHouse(House house, int id, Document doc){
        Element houseNode = doc.createElement("house");
        houseNode.setAttribute("id", String.valueOf(id));

        Element street = doc.createElement("street");
        street.setTextContent(house.getStreet());

        Element number = doc.createElement("number");
        number.setTextContent(String.valueOf(house.getNumber()));

        houseNode.appendChild(street);
        houseNode.appendChild(number);

        for(int i = 0; i < house.getEntrances().size(); ++i) {
            houseNode.appendChild(createEntrance(house.getEntrances().get(i), i, doc));
        }

        return houseNode;
    }

    private static Node createEntrance(Entrance entrance, int id, Document doc){
        Element entranceNode = doc.createElement("entrance");
        entranceNode.setAttribute("id", String.valueOf(id));

        Element countOfFlats = doc.createElement("countOfFlats");
        countOfFlats.setTextContent(String.valueOf(entrance.getCountOfFlats()));

        Element countOfCitizens = doc.createElement("countOfCitizens");
        countOfCitizens.setTextContent(String.valueOf(entrance.getCountOfCitizens()));

        Element debt = doc.createElement("debt");
        debt.setTextContent(String.valueOf(entrance.getDebt()));

        entranceNode.appendChild(countOfFlats);
        entranceNode.appendChild(countOfCitizens);
        entranceNode.appendChild(debt);

        return entranceNode;
    }
}