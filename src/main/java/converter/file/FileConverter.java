package converter.file;

import lombok.val;

public class FileConverter {
    public void convert(final String[] fileNames) {
        val arguments = new InputArguments(fileNames);

        arguments.getWriter()
                .writeToFile(arguments.getOutputFileName(),
                        arguments.getReader().readFromFile(arguments.getInputFileName()));
    }
}