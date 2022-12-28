package converter.file;

import lombok.val;

public class FileConverter {
    public void convert(final String[] fileNames) {
        val arguments = new InputArguments(fileNames);

        arguments.createWriter()
                .writeToFile(arguments.getOutputFileName(),
                        arguments.createReader().readFromFile(arguments.getInputFileName()));
    }
}