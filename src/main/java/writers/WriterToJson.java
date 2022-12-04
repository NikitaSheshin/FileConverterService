package writers;

import city.District;
import city.DistrictsStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WriterToJson implements Writer {
    private final static Logger logger = Logger.getLogger(WriterToJson.class.getName());

    @Override
    public void writeToFile(final String fileName, final List<District> districts) throws IOException {
        DistrictsStore districtsStore = new DistrictsStore();
        districtsStore.setDistricts(districts);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String jsonStringWithDistricts = gson.toJson(districtsStore);
        logger.log(Level.INFO, "Данные записаны в json-строку");

        FileWriter writer = new FileWriter(fileName);
        writer.write(jsonStringWithDistricts);

        writer.flush();
        logger.log(Level.INFO, "Данные записаны в файл");
    }
}
