package Readers;

import City.District;
import City.Entrance;
import City.House;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;

public class XmlReader implements Reader {
    @Override
    public List<District> readFromFile(String fileName) {
        Document document = createDocument(fileName);
        NodeList nodeList = document.getElementsByTagName("district");

        List<District> districts = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            districts.add(getDistrict(nodeList.item(i)));
        }

        return districts;
    }

    private Document createDocument(String fileName) {
        Document document = initDocument(fileName);
        document.getDocumentElement().normalize();
        return document;
    }

    private DocumentBuilder createDocumentBuilder() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException parserConfigurationException) {
            throw new RuntimeException(parserConfigurationException);
        }
    }

    private Document initDocument(String fileName){
        File xmlFile = new File(fileName);
        try {
            return createDocumentBuilder().parse(xmlFile);
        } catch (SAXException | IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private District getDistrict(Node node) {
        District district = new District();
        if (isElementNode(node)) {
            NodeList nodeList = node.getChildNodes();

            List<House> housesList = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeName().equals("house"))
                    housesList.add(getHouse(nodeList.item(i)));
            }

            district.setHouses(housesList);

            Element element = (Element) node;
            district.setName(getTagValue("name", element));
        }

        return district;
    }

    private Boolean isElementNode(Node node){
        return node.getNodeType() == Node.ELEMENT_NODE;
    }

    private House getHouse(Node node) {
        House house = new House();
        if (isElementNode(node)) {
            NodeList nodeList = node.getChildNodes();

            List<Entrance> entrancesList = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeName().equals("entrance")) {
                    entrancesList.add(getEntrance(nodeList.item(i)));
                }
            }
            house.setEntrances(entrancesList);

            Element element = (Element) node;
            house.setStreet(getTagValue("street", element));
            house.setNumber(Integer.parseInt(getTagValue("number", element)));
        }

        return house;
    }

    private Entrance getEntrance(Node node) {
        Entrance entrance = new Entrance();
        if (isElementNode(node)) {
            Element element = (Element) node;
            entrance.setCountOfCitizens(Integer.parseInt(getTagValue("countOfCitizens", element)));
            entrance.setCountOfFlats(Integer.parseInt(getTagValue("countOfFlats", element)));
            entrance.setDebt(Integer.parseInt(getTagValue("debt", element)));
        }

        return entrance;
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}