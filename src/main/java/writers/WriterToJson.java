package writers;

import beans.mappers.DistrictsListMapper;
import beans.json.DistrictsStoreJson;
import beans.xml.DistrictXml;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
public class WriterToJson implements Writer {
    public void writeToFile(final String fileName, final List<?> districts) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        tryToWriteData(fileName, gson.toJson(new DistrictsStoreJson(
                DistrictsListMapper.instance.toDistrictJsonList((List<DistrictXml>) districts)
        )));
    }

    private void tryToWriteData(final String fileName, final String jsonStringWithDistricts) {
        try (final FileWriter writer = new FileWriter(fileName)) {
            writer.write(jsonStringWithDistricts);
            log.trace("Данные записаны в файл");
        } catch (IOException ioException) {
            log.warn("Ошибка при попытке записать данные в файл", ioException);
            System.out.println("Ошибка при попытке записать данные в файл");
        }
    }
}
