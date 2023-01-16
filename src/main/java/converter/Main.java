package converter;

import converter.fileconverter.FileConverter;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException {
        FileConverter.convert(args);
    }
}