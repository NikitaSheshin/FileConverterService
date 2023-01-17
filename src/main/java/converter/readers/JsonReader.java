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
    private static JsonReader reader = null;
    private static final Gson GSON_READER = new Gson();

    public static Reader getInstance() {
        if (reader == null) {
            reader = new JsonReader();
        }

        return reader;
    }

    private JsonReader() {
        log.debug("Создан объект JsonReader");
    }

    public List<DistrictJson> readFromFile(final String fileName) throws IOException {
        try (val bufferedReader = Files.newBufferedReader(Path.of(fileName))) {
            return (GSON_READER.fromJson(bufferedReader, DistrictsStoreJson.class)).getDistricts();
        }
    }
}
