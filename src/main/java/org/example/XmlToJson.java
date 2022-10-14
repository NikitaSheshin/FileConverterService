package org.example;

import City.District;
import City.Entrance;
import City.House;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class XmlToJson {
    public static List<District> convert(String fileName){
        var districts = readFromXML(fileName);

        JSONArray districtJson = new JSONArray();

        for(int i = 0; i < districts.size(); ++i)
            districtJson.add(createJsonDistrict(districts.get(i)));

        JSONObject resultJson = new JSONObject();
            resultJson.put("district", districtJson);

        try {
            Files.write(Paths.get("json.txt"), resultJson.toJSONString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return districts;
    }

    private static JSONObject createJsonDistrict(District district){
        JSONObject districtJson = new JSONObject();

        districtJson.put("name", district.getName());

        JSONArray houses = new JSONArray();

        for(int i = 0; i < district.getHouses().size(); ++i)
            houses.add(createJsonHouse(district.getHouses().get(i), i));

        districtJson.put("house", houses);

        return districtJson;
    }

    private static JSONObject createJsonHouse(House house, int id){
        JSONObject houseJson = new JSONObject();

        houseJson.put("street", house.getStreet());
        houseJson.put("number", house.getNumber());

        JSONObject idJson = new JSONObject();
        idJson.put("id", id);
        houseJson.put("@attributes", idJson);

        JSONArray entrances = new JSONArray();

        for(int i = 0; i < house.getEntrances().size(); ++i)
            entrances.add(createJsonEntrance(house.getEntrances().get(i), i));

        houseJson.put("entrance", entrances);

        return houseJson;
    }

    private static JSONObject createJsonEntrance(Entrance entrance, int id){
        JSONObject entranceJson = new JSONObject();

        entranceJson.put("countOfCitizens", entrance.getCountOfCitizens());
        entranceJson.put("countOfFlats", entrance.getCountOfFlats());
        entranceJson.put("debt", entrance.getDebt());

        JSONObject idJson = new JSONObject();
        idJson.put("id", id);
        entranceJson.put("@attributes", idJson);

        return entranceJson;
    }

    private static List<District> readFromXML(String fileName){
        File xmlFile = new File(fileName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            document.getDocumentElement().normalize();

            var s = document.toString();

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
