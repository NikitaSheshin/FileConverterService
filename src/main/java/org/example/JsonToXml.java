package org.example;

import City.District;
import City.Entrance;
import City.House;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonToXml {
    public static void convert(String fileName){
        var t = readJsonFromFile(fileName);
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
