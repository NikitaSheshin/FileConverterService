package writers;

import beans.json.DistrictsStoreJson;
import beans.xml.DistrictXml;
import beans.xml.DistrictsStoreXml;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.dozer.DozerBeanMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
public class WriterToJson implements Writer {
    public void writeToFile(final String fileName, final List<?> districts) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        DozerBeanMapper mapper = new DozerBeanMapper();
        val data = mapper.map(new DistrictsStoreXml((List<DistrictXml>) districts),
                DistrictsStoreJson.class);
        tryToWriteData(fileName, gson.toJson(data));
    }

    private void tryToWriteData(final String fileName, final String jsonStringWithDistricts) {
        try (final FileWriter writer = new FileWriter(fileName)) {
            writer.write(jsonStringWithDistricts);
            writer.flush();
            log.info("Данные записаны в файл");
        } catch (IOException ioException) {
            log.error("Ошибка при попытке записать данные в файл", ioException);
            System.out.println("Ошибка при попытке записать данные в файл");
        }
    }
}
