package converter.file;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import readers.JsonReader;
import readers.Reader;
import readers.XmlReader;
import writers.Writer;
import writers.WriterToJson;
import writers.WriterToXml;

@Getter
@Slf4j
public class InputArguments {
    private String inputFileName;
    private String outputFileName;

    public InputArguments(String[] args) {
        validate(args);
    }

    private void validate(String[] args) {
        checkCorrectCount(args);
        inputFileName = args[0];
        outputFileName = args[1];

        checkFilesFormats();
    }

    private void checkCorrectCount(String[] args) {
        if (args.length != 2) {
            log.warn("Неверное количество аргументов");
            throw new IllegalArgumentException("Неверное количество аргументов");
        }
    }

    private void checkFilesFormats() {
        if (!isXmlToJson() && !isJsonToXml()) {
            log.warn("Переданы файлы неподходящих форматов");
            throw new IllegalArgumentException("Переданы файлы неподходящих форматов");
        }
    }

    private Boolean isXmlToJson() {
        return inputFileName.endsWith(".xml") && outputFileName.endsWith(".json");
    }

    private Boolean isJsonToXml() {
        return inputFileName.endsWith(".json") && outputFileName.endsWith(".xml");
    }

    public Reader getReader() {
        if (inputFileIsXml()) {
            return new XmlReader();
        }
        return new JsonReader();
    }

    private Boolean inputFileIsXml() {
        return inputFileName.endsWith(".xml");
    }

    public Writer getWriter() {
        if (outputFileIsXml()) {
            return new WriterToXml();
        }
        return new WriterToJson();
    }

    private Boolean outputFileIsXml() {
        return outputFileName.endsWith(".xml");
    }
}
