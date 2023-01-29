package converter.fileconverter;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class InputArguments {
    private final String inputFileName;
    private final String outputFileName;

    private final static String WRONG_COUNT_OF_ARGUMENTS_MESSAGE = "Неверное количество аргументов";
    private final static String NULL_ARGUMENTS_MESSAGE = "Один или оба аргумента равны null";
    private final static String WRONG_FORMAT_MESSAGE = "Переденные файлы должны иметь разрешения .xml и .json";

    public InputArguments(@NonNull String[] args) {
        checkCorrectCount(args);
        inputFileName = args[0];
        outputFileName = args[1];
        checkNotNullArguments();

        checkFilesFormats();
    }

    private void checkCorrectCount(final String[] args) {
        if (args.length < 2) {
            log.warn(WRONG_COUNT_OF_ARGUMENTS_MESSAGE);
            System.out.println(WRONG_COUNT_OF_ARGUMENTS_MESSAGE);
            throw new IllegalArgumentException(WRONG_COUNT_OF_ARGUMENTS_MESSAGE);
        }
    }

    private void checkNotNullArguments() {
        if (inputFileName == null || outputFileName == null) {
            log.warn(NULL_ARGUMENTS_MESSAGE);
            System.out.println(NULL_ARGUMENTS_MESSAGE);
            throw new IllegalArgumentException(NULL_ARGUMENTS_MESSAGE);
        }
    }

    private void checkFilesFormats() {
        if (!isXmlToJson() && !isJsonToXml()) {
            log.warn(WRONG_FORMAT_MESSAGE);
            System.out.println(WRONG_FORMAT_MESSAGE);
            throw new IllegalArgumentException(WRONG_FORMAT_MESSAGE);
        }
    }

    private Boolean isXmlToJson() {
        return inputFileName.endsWith(".xml") && outputFileName.endsWith(".json");
    }

    private Boolean isJsonToXml() {
        return inputFileName.endsWith(".json") && outputFileName.endsWith(".xml");
    }
}
