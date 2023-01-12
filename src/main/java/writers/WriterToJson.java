package writers;

import beans.mappers.DistrictsListMapper;
import beans.json.DistrictsStoreJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
public class WriterToJson implements Writer {
    private final static Gson GSON_WRITER = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public WriterToJson() {
        log.debug("Создан объект WriterToJson");
    }

    public void writeToFile(final String fileName, final List<?> districts) throws IOException {
        try (final FileWriter writer = new FileWriter(fileName)) {
            writer.write(GSON_WRITER.toJson(
                    new DistrictsStoreJson(
                            DistrictsListMapper.instance.toDistrictJsonList((List<beans.xml.DistrictXml>) districts)
                    )));

            log.debug("Данные записаны в json файл");
        }
    }
}
