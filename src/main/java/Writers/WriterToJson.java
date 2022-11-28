package Writers;

import City.District;
import City.Entrance;
import City.House;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WriterToJson implements Writer{
    @Override
    public void writeToFile(String fileName, List<District> districts) {
        JSONArray districtJson = new JSONArray();

        assert districts != null;
        for (District district : districts) {
            districtJson.add(createJsonDistrict(district));
        }

        JSONObject resultJson = new JSONObject();
        resultJson.put("district", districtJson);

        writeJsonObjectToFile(fileName, resultJson);
    }

    private JSONObject createJsonDistrict(District district){
        JSONObject districtJson = new JSONObject();

        districtJson.put("name", district.getName());

        JSONArray houses = new JSONArray();

        for(int i = 0; i < district.getHouses().size(); ++i){
            houses.add(createJsonHouse(district.getHouses().get(i), i));
        }

        districtJson.put("house", houses);

        return districtJson;
    }

    private JSONObject createJsonHouse(House house, int id){
        JSONObject houseJson = new JSONObject();

        houseJson.put("street", house.getStreet());
        houseJson.put("number", house.getNumber());

        JSONObject idJson = new JSONObject();
        idJson.put("id", id);
        houseJson.put("@attributes", idJson);

        JSONArray entrances = new JSONArray();

        for(int i = 0; i < house.getEntrances().size(); ++i){
            entrances.add(createJsonEntrance(house.getEntrances().get(i), i));
        }

        houseJson.put("entrance", entrances);

        return houseJson;
    }

    private JSONObject createJsonEntrance(Entrance entrance, int id){
        JSONObject entranceJson = new JSONObject();

        entranceJson.put("countOfCitizens", entrance.getCountOfCitizens());
        entranceJson.put("countOfFlats", entrance.getCountOfFlats());
        entranceJson.put("debt", entrance.getDebt());

        JSONObject idJson = new JSONObject();
        idJson.put("id", id);
        entranceJson.put("@attributes", idJson);

        return entranceJson;
    }

    private void writeJsonObjectToFile(String resFileName, JSONObject resultJson){
        try {
            Files.write(Paths.get(resFileName), resultJson.toJSONString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}