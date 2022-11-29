package Readers;

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

public class JsonReader implements Reader {
    @Override
    public List<District> readFromFile(String fileName) {

        FileReader reader = createFileReader(fileName);
        JSONObject jsonObject = createJsonObjectFromFile(reader);

        JSONArray districtsJson = (JSONArray) jsonObject.get("district");

        List<District> districts = new ArrayList<>();

        for (var district : districtsJson) {
            districts.add(getDistrict((JSONObject) district));
        }

        return districts;
    }

    private FileReader createFileReader(String fileName) {
        try {
            return new FileReader(fileName);
        } catch (FileNotFoundException fileNotFoundException) {
            throw new RuntimeException(fileNotFoundException);
        }
    }

    private JSONObject createJsonObjectFromFile(FileReader reader) {
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(reader);
        } catch (IOException | ParseException exception) {
            throw new RuntimeException(exception);
        }
    }

    private District getDistrict(JSONObject districtJson) {
        District district = new District();

        district.setName(districtJson.get("name").toString());
        List<House> houses = new ArrayList<>();
        JSONArray housesJson = (JSONArray) districtJson.get("house");

        for(var house : housesJson) {
            houses.add(getHouse((JSONObject)house));
        }

        district.setHouses(houses);

        return district;
    }

    private House getHouse(JSONObject houseJson) {
        House house = new House();

        house.setStreet(houseJson.get("street").toString());
        house.setNumber(Integer.parseInt(houseJson.get("number").toString()));

        List<Entrance> entrances = new ArrayList<>();
        JSONArray entrancesJson = (JSONArray) houseJson.get("entrance");

        for (var entrance : entrancesJson) {
            entrances.add(getEntrance((JSONObject) entrance));
        }

        house.setEntrances(entrances);

        return house;
    }

    private Entrance getEntrance(JSONObject entranceJson) {
        Entrance entrance = new Entrance();

        entrance.setCountOfCitizens(Integer.parseInt(entranceJson.get("countOfCitizens").toString()));
        entrance.setCountOfFlats(Integer.parseInt(entranceJson.get("countOfFlats").toString()));
        entrance.setDebt(Integer.parseInt(entranceJson.get("debt").toString()));

        return entrance;
    }
}