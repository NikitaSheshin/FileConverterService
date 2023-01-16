package converter.readers;

import converter.beans.json.DistrictJson;
import converter.beans.json.DistrictsStoreJson;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class JsonReader implements Reader {
    private static final Gson GSON_READER = new Gson();

    public JsonReader() {
        log.debug("Создан объект JsonReader");
    }

    public List<DistrictJson> readFromFile(final String fileName) throws IOException {
        try (val bufferedReader = Files.newBufferedReader(Path.of(fileName))) {
            return (GSON_READER.fromJson(bufferedReader, DistrictsStoreJson.class)).getDistricts();
        }
    }
}
