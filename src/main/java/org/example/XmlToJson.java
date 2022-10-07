package org.example;

import City.District;
import City.Entrance;
import City.House;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlToJson {
    public static List<District> convert(String fileName){
        File xmlFile = new File(fileName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("district");

            List<District> districts = new ArrayList<District>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                districts.add(getDistrict(nodeList.item(i)));
            }

            return districts;
        }
         catch (Exception exc) {
             exc.printStackTrace();
        }

        return null;
    }

    private static District getDistrict(Node node) {
        District district = new District();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            NodeList nodeList = node.getChildNodes();

            List<House> housesList = new ArrayList<House>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeName() == "house")
                    housesList.add(getHouse(nodeList.item(i)));
            }

            district.setHouses(housesList);

            Element element = (Element) node;
            district.setName(getTagValue("name", element));
        }

        return district;
    }

    private static House getHouse(Node node) {
        House house = new House();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            NodeList nodeList = node.getChildNodes();

            List<Entrance> entrancesList = new ArrayList<Entrance>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeName() == "entrance")
                    entrancesList.add(getEntrance(nodeList.item(i)));
            }
            house.setEntrances(entrancesList);

            Element element = (Element) node;
            house.setStreet(getTagValue("street", element));
            house.setNumber(Integer.parseInt(getTagValue("number", element)));
        }

        return house;
    }

    private static Entrance getEntrance(Node node) {
        Entrance entrance = new Entrance();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            entrance.setCountOfCitizens(Integer.parseInt(getTagValue("countOfCitizens", element)));
            entrance.setCountOfFlats(Integer.parseInt(getTagValue("countOfFlats", element)));
            entrance.setDebt(Integer.parseInt(getTagValue("debt", element)));
        }

        return entrance;
    }

    // получаем значение элемента по указанному тегу
    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}
