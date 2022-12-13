package readers;

import beans.json.DistrictJson;
import beans.json.DistrictsStoreJson;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

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

    private List<DistrictJson> tryToReadFromFile (final String fileName) {
        try (var bufferedReader = Files.newBufferedReader(Paths.get(fileName))){
            return (new Gson().fromJson(bufferedReader, DistrictsStoreJson.class)).getDistricts();
        } catch (IOException ioException) {
            log.error("Нет файла по указанному пути", ioException);
            System.out.println("Нет файла по указанному пути");
            throw new IllegalCallerException();
        }
    }
}
