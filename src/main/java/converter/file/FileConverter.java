package converter.file;

import lombok.val;

public class FileConverter {
    public void convert(final String[] fileNames) {
        InputArguments arguments     = new InputArguments(fileNames);

        val reader = arguments.getReader();
        val writer = arguments.getWriter();

        writer.writeToFile(arguments.getOutputFileName(), reader.readFromFile(arguments.getInputFileName()));
    }
}