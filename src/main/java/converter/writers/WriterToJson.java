package converter.writers;

import converter.beans.mappers.DistrictsListMapper;
import converter.beans.json.DistrictsStoreJson;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import converter.beans.xml.DistrictXml;
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
                            DistrictsListMapper.instance.toDistrictJsonList((List<DistrictXml>) districts)
                    )));

            log.debug("Данные записаны в json файл");
        }
    }
}
