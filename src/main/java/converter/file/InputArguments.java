package converter.file;

import lombok.Getter;
import lombok.NonNull;
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
    private final String inputFileName;
    private final String outputFileName;

    public InputArguments(@NonNull String[] args) {
        checkCorrectCount(args);
        inputFileName = args[0];
        outputFileName = args[1];
        checkNotNullArguments();

        checkFilesFormats();
    }

    private void checkCorrectCount(String[] args) {
        if (args.length < 2) {
            log.warn("Неверное количество аргументов");
            System.out.println("Неверное количество аргументов");
            System.exit(-1);
        }
    }

    private void checkNotNullArguments() {
        if (inputFileName == null || outputFileName == null) {
            log.warn("Один или оба аргумента равны null");
            System.out.println("Один или оба аргумента равны null");
            System.exit(1);
        }
    }

    private void checkFilesFormats() {
        if (!isXmlToJson() && !isJsonToXml()) {
            log.warn("Переденные файлы должны иметь разрешения .xml и .json");
            System.out.println("Переденные файлы должны иметь разрешения .xml и .json");
            System.exit(1);
        }
    }

    private Boolean isXmlToJson() {
        return inputFileName.endsWith(".xml") && outputFileName.endsWith(".json");
    }

    private Boolean isJsonToXml() {
        return inputFileName.endsWith(".json") && outputFileName.endsWith(".xml");
    }

    public Reader createReader() {
        return inputFileIsXml() ? new XmlReader() : new JsonReader();
    }

    private Boolean inputFileIsXml() {
        return inputFileName.endsWith(".xml");
    }

    public Writer createWriter() {
        return outputFileIsXml() ? new WriterToXml() : new WriterToJson();
    }

    private Boolean outputFileIsXml() {
        return outputFileName.endsWith(".xml");
    }
}
