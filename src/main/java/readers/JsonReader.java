package readers;

import beans.json.DistrictJson;
import beans.json.DistrictsStoreJson;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class JsonReader implements Reader {
    @Override
    public List<DistrictJson> readFromFile(final String fileName) {
        return tryToReadFromFile(fileName);
    }

    private static final String ERROR_MESSAGE = "Нет файла по указанному пути";

    private List<DistrictJson> tryToReadFromFile(final String fileName) {
        try (val bufferedReader = Files.newBufferedReader(Paths.get(fileName))) {
            return (new Gson().fromJson(bufferedReader, DistrictsStoreJson.class)).getDistricts();
        } catch (IOException ioException) {
            log.warn(ERROR_MESSAGE, ioException);
            System.out.println(ERROR_MESSAGE);
            throw new IllegalCallerException(ERROR_MESSAGE);
        }
    }
}
