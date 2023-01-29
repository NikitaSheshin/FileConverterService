package converter.fileconverter;

import lombok.val;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class FileConverter {
    static public void convert(final String[] fileNames) throws JAXBException, IOException {
        val arguments = new InputArguments(fileNames);

        WriterCreator.create(arguments.getOutputFileName())
                .writeToFile(arguments.getOutputFileName(),
                        ReaderCreator.create(arguments.getInputFileName())
                                .readFromFile(arguments.getInputFileName()));
    }
}