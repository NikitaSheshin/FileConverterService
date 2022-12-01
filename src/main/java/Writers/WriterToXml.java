package Writers;

import City.District;
import City.Entrance;
import City.House;
import Readers.XmlReader;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriterToXml implements Writer {
    private final static Logger logger = Logger.getLogger(XmlReader.class.getName());
    private Document xmlDocument;

    @Override
    public void writeToFile(String fileName, List<District> districts) {
        createDocument();
        Element root = xmlDocument.createElement("districts");

        for (var district : districts) {
            root.appendChild(createDistrict(district));
        }

        xmlDocument.appendChild(root);
        DOMSource src = new DOMSource(xmlDocument);

        StreamResult result = creatStreamResult(fileName);
        saveResult(result, src);
    }

    private void createDocument() {
        try {
            tryToCreateDocument();
        } catch (ParserConfigurationException parserConfigurationException) {
            logger.log(Level.INFO, "Ошибка конфигурации ", parserConfigurationException);
        }
    }

    private void tryToCreateDocument() throws ParserConfigurationException {
        xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    }

    private Node createDistrict(District district) {
        Element districtNode = xmlDocument.createElement("district");
        districtNode.appendChild(createElementWithTextContent("name", district.getName()));

        for(int currentHouseIndex = 0; currentHouseIndex < district.getHouses().size(); ++currentHouseIndex) {
            districtNode.appendChild(createHouse(district.getHouses()
                    .get(currentHouseIndex)));
        }

        return districtNode;
    }

    private Node createHouse(House house) {
        Element houseNode = xmlDocument.createElement("house");

        houseNode.appendChild(createElementWithTextContent("street", house.getStreet()));
        houseNode.appendChild(createElementWithTextContent("number", String.valueOf(house.getNumber())));

        for(int currentEntranceIndex = 0; currentEntranceIndex < house.getEntrances().size(); ++currentEntranceIndex) {
            houseNode.appendChild(createEntrance(house.getEntrances()
                    .get(currentEntranceIndex)));
        }

        return houseNode;
    }

    private Node createEntrance(Entrance entrance) {
        Element entranceNode = xmlDocument.createElement("entrance");

        entranceNode.appendChild(createElementWithTextContent("countOfFlats"
                , String.valueOf(entrance.getCountOfFlats())));
        entranceNode.appendChild(createElementWithTextContent("countOfCitizens"
                , String.valueOf(entrance.getCountOfCitizens())));
        entranceNode.appendChild(createElementWithTextContent("debt"
                , String.valueOf(entrance.getDebt())));

        return entranceNode;
    }

    private Element createElementWithTextContent(String nameOfElement, String contentToElement) {
        Element newElement = xmlDocument.createElement(nameOfElement);
        newElement.setTextContent(contentToElement);
        return newElement;
    }

    private StreamResult creatStreamResult(String fileName) {
        try {
            return new StreamResult(new FileOutputStream(fileName));
        } catch (FileNotFoundException fileNotFoundException) {
            logger.log(Level.WARNING, "Не должно произойти, так как если файла нет должен создасться новый");
            throw new RuntimeException(fileNotFoundException);
        }
    }

    private void saveResult(StreamResult result, DOMSource src) {
        try {
            TransformerFactory.newInstance().newTransformer().transform(src, result);
        } catch (TransformerException transformerException) {
            logger.log(Level.WARNING, "Ошибка при записи в файл", transformerException);
        }
    }
}