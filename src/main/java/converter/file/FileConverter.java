package converter.file;

import lombok.val;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class FileConverter {
    public void convert(final String[] fileNames) throws JAXBException, IOException {
        val arguments = new InputArguments(fileNames);

        arguments.createWriter()
                .writeToFile(arguments.getOutputFileName(),
                        arguments.createReader().readFromFile(arguments.getInputFileName()));
    }
}