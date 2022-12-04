package readers;

import city.District;
import city.DistrictsStore;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonReader implements Reader {
    private final static Logger logger = Logger.getLogger(JsonReader.class.getName());

    @Override
    public List<District> readFromFile(final String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader reader = new BufferedReader(fileReader);

        StringBuilder jsonStringFromFile = new StringBuilder();
        String currentString = reader.readLine();
        while (currentString != null) {
            jsonStringFromFile.append(currentString);
            currentString = reader.readLine();
        }

        DistrictsStore districts = new Gson().fromJson(jsonStringFromFile.toString(), DistrictsStore.class);
        logger.log(Level.INFO, "Данные считаны из файла");

        return districts.getDistricts();
    }
}
