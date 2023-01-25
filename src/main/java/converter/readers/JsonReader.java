package converter.readers;

import com.google.gson.Gson;
import converter.beans.json.DistrictJson;
import converter.beans.json.DistrictsStoreJson;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class JsonReader implements Reader {
    private static volatile JsonReader reader = null;
    private static final Gson GSON_READER = new Gson();

    public static Reader getInstance() {
        JsonReader localInstance = reader;
        if (localInstance == null) {
            synchronized (JsonReader.class) {
                localInstance = reader;
                if (localInstance == null) {
                    reader = localInstance = new JsonReader();
                }
            }
        }

        return localInstance;
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
