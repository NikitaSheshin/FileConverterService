package org.example;

import City.District;
import City.Entrance;
import City.House;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.imageio.metadata.IIOMetadataNode;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonToXml {
    public static void convert(String fileName, String resFileName){
        var districts = readJsonFromFile(fileName);

        DocumentBuilderFactory dbf;
        DocumentBuilder db ;
        Document doc;

        dbf = DocumentBuilderFactory.newInstance();
        try {
            db  = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        doc = db.newDocument();

        Element root = doc.createElement("database");

        for (var district : districts)
            root.appendChild(createDistrict(district, doc));

        doc.appendChild(root);

        Transformer trf = null;
        DOMSource src = null;
        FileOutputStream fos = null;

        try {
            trf = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        src = new DOMSource(doc);
        try {
            fos = new FileOutputStream(resFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        StreamResult result = new StreamResult(fos);
        try {
            trf.transform(src, result);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private static Node createDistrict(District district, Document doc){

        Element districtNode = doc.createElement("district");
        Element name = doc.createElement("name");
        name.setTextContent(district.getName());
        districtNode.appendChild(name);

        for(int i = 0; i < district.getHouses().size(); ++i)
            districtNode.appendChild(createHouse(district.getHouses().get(i), i, doc));

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

        for(int i = 0; i < house.getEntrances().size(); ++i)
            houseNode.appendChild(createEntrance(house.getEntrances().get(i), i, doc));

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

    private static List<District> readJsonFromFile(String fileName){
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader(fileName);) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray districtsJson = (JSONArray)jsonObject.get("district");
            List<District> districts = new ArrayList<>();

            for(var district : districtsJson)
                districts.add(getDistrict((JSONObject) district));

            return districts;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static District getDistrict(JSONObject districtJson){
        District district = new District();

        district.setName(districtJson.get("name").toString());
        List<House> houses = new ArrayList<>();
        JSONArray housesJson = (JSONArray) districtJson.get("house");

        for(var house : housesJson)
            houses.add(getHouse((JSONObject)house));

        district.setHouses(houses);

        return district;
    }

    private static House getHouse(JSONObject houseJson){
        House house = new House();

        house.setStreet(houseJson.get("street").toString());
        house.setNumber(Integer.parseInt(houseJson.get("number").toString()));

        List<Entrance> entrances = new ArrayList<>();
        JSONArray entrancesJson = (JSONArray) houseJson.get("entrance");

        for (var entrance : entrancesJson)
            entrances.add(getEntrance((JSONObject) entrance));

        house.setEntrances(entrances);

        return house;
    }

    private static Entrance getEntrance(JSONObject entranceJson){
        Entrance entrance = new Entrance();

        entrance.setCountOfCitizens(Integer.parseInt(entranceJson.get("countOfCitizens").toString()));
        entrance.setCountOfFlats(Integer.parseInt(entranceJson.get("countOfFlats").toString()));
        entrance.setDebt(Integer.parseInt(entranceJson.get("debt").toString()));

        return entrance;
    }
}
