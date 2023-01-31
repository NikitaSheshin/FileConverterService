package converter.fileconverter;

import lombok.val;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class FileConverter {
    private static int countOfFiles = 0;
    private static int countOfErrors = 0;

    public static void setStart() {
        countOfFiles = 0;
        countOfErrors = 0;
    }

    static public void convert(final String[] fileNames) throws JAXBException, IOException {
        val arguments = new InputArguments(fileNames);

        if (arguments.isValid()) {
            WriterCreator.create(arguments.getOutputFileName())
                .writeToFile(arguments.getOutputFileName(),
                    ReaderCreator.create(arguments.getInputFileName())
                        .readFromFile(arguments.getInputFileName()));
        } else {
            countOfErrors++;
        }

        countOfFiles++;
    }

    public static void getStatistic() {
        System.out.println("Обработано " + countOfFiles + " файлов\n" +
            "Из них с ошибкой - " + (countOfFiles - countOfErrors));
    }
}