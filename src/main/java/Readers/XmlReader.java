package Readers;

import City.District;
import City.Entrance;
import City.House;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XmlReader implements Reader {
    private final static Logger logger = Logger.getLogger(XmlReader.class.getName());

    @Override
    public List<District> readFromFile(final String fileName) {
        Document document = createDocument(fileName);
        NodeList nodeList = tryToGetNodeListFromDocument(document);
        logger.log(Level.INFO, "Файл открыт");

        final List<District> districts = new ArrayList<>();
        for (int currentNodeIndex = 0; currentNodeIndex < nodeList.getLength(); currentNodeIndex++) {
            districts.add(getDistrict(nodeList.item(currentNodeIndex)));
        }
        logger.log(Level.INFO, "Данные записаны в List");

        return districts;
    }

    private Document createDocument(String fileName) {
        try {
            return initDocument(fileName);
        } catch (IOException ioException) {
            logger.log(Level.WARNING, "Ошибка при открытии файла ", ioException);
        } catch (SAXException saxException) {
            logger.log(Level.WARNING, "Ошибка при интерпритации xml ", saxException);
        } catch (ParserConfigurationException parserConfigurationException) {
            logger.log(Level.WARNING, "Ошибка конфигурации", parserConfigurationException);
        }
        return null;
    }

    private Document initDocument(String fileName) throws SAXException, ParserConfigurationException, IOException {
        File xmlFile = new File(fileName);
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile);
    }

    private NodeList tryToGetNodeListFromDocument(Document document) {
        try {
            return document.getElementsByTagName("district");
        }
        catch (NullPointerException nullPointerException) {
            logger.log(Level.INFO, "Не получилось открыть документ", nullPointerException);
        }

        return new NodeList() {
            @Override
            public Node item(int index) {
                return null;
            }

            @Override
            public int getLength() {
                return 0;
            }
        };
    }

    private District getDistrict(Node node) {
        District district = new District();
        if (isElementNode(node)) {
            NodeList nodeList = node.getChildNodes();

            List<House> housesList = new ArrayList<>();
            for (int currentNodeIndex = 0; currentNodeIndex < nodeList.getLength(); currentNodeIndex++) {
                if (isHouseNode(nodeList.item(currentNodeIndex))) {
                    housesList.add(getHouse(nodeList.item(currentNodeIndex)));
                }
            }

            district.setHouses(housesList);

            district.setName(getTagValue("name", (Element) node));
        }

        return district;
    }

    private Boolean isElementNode(Node node) {
        return node.getNodeType() == Node.ELEMENT_NODE;
    }

    private Boolean isHouseNode(Node node) {
        return node.getNodeName().equals("house");
    }

    private House getHouse(Node node) {
        House house = new House();
        if (isElementNode(node)) {
            NodeList nodeList = node.getChildNodes();

            List<Entrance> entrancesList = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (isEntranceNode(nodeList.item(i))) {
                    entrancesList.add(getEntrance(nodeList.item(i)));
                }
            }
            house.setEntrances(entrancesList);

            house.setStreet(getTagValue("street", (Element) node));
            house.setNumber(Integer.parseInt(getTagValue("number", (Element) node)));
        }

        return house;
    }

    private Boolean isEntranceNode(Node node) {
        return node.getNodeName().equals("entrance");
    }

    private Entrance getEntrance(Node node) {
        Entrance entrance = new Entrance();
        if (isElementNode(node)) {
            entrance.setCountOfCitizens(Integer.parseInt(getTagValue("countOfCitizens", (Element) node)));
            entrance.setCountOfFlats(Integer.parseInt(getTagValue("countOfFlats", (Element) node)));
            entrance.setDebt(Integer.parseInt(getTagValue("debt", (Element) node)));
        }

        return entrance;
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}