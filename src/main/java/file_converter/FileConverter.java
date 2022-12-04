package file_converter;

import city.District;
import readers.JsonReader;
import readers.Reader;
import readers.XmlReader;
import writers.Writer;
import writers.WriterToJson;
import writers.WriterToXml;
import lombok.val;
import org.json.simple.parser.ParseException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileConverter {
    private final static Logger logger = Logger.getLogger(FileConverter.class.getName());

    Reader reader;
    Writer writer;

    public void convert(final String[] fileNames) {
        if (fileNames.length != 2) {
            throw new RuntimeException("Неверное количество аргументов");
        }

        val inputFileName = fileNames[0];
        val outputFileName = fileNames[1];

        if (inputFileName.endsWith(".xml") && outputFileName.endsWith(".json")) {
            reader = new XmlReader();
            writer = new WriterToJson();
        } else if (inputFileName.endsWith(".json") && outputFileName.endsWith(".xml")) {
            reader = new JsonReader();
            writer = new WriterToXml();
        } else {
            throw new RuntimeException("Переданы файлы неподходящих форматов");
        }

        val dataFromFile = tryToReadDataFromFile(inputFileName);
        tryToWriteDataToFile(outputFileName, dataFromFile);
    }

    private List<District> tryToReadDataFromFile(final String inputFileName) {
        List<District> dataFromFile = new ArrayList<>();
        try {
            dataFromFile = reader.readFromFile(inputFileName);
        } catch (JAXBException | ParseException wrongFormatException) {
            logger.log(Level.WARNING, "Неверный формат данных во входящем файле", wrongFormatException);
            System.out.println("Неверный формат данных во входящем файле");
        } catch (IOException ioException) {
            logger.log(Level.WARNING, "Ошибка при попытке чтения из файла", ioException);
            System.out.println("Ошибка при попытке чтения из файла");
        }

        return dataFromFile;
    }

    private void tryToWriteDataToFile(final String outputFileName, List<District> dataFromFile) {
        try {
            writer.writeToFile(outputFileName, dataFromFile);
        } catch (JAXBException | IOException writeException) {
            logger.log(Level.WARNING, "Ошибка при попытке записать данные в файл", writeException);
            System.out.println("Ошибка при попытке записать данные в файл");
        }
    }
}
