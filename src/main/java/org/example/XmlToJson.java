package org.example;

import City.District;

import Readers.Reader;
import Readers.XmlReader;
import Writers.Writer;
import Writers.WriterToJson;
import java.util.*;

public class XmlToJson {
    public static void convert(String fileName, String resFileName){
        Reader xmlReader = new XmlReader();
        Writer jsonWriter = new WriterToJson();

        List<District> districts = xmlReader.readFromFile(fileName);
        jsonWriter.writeToFile(resFileName, districts);
    }
}