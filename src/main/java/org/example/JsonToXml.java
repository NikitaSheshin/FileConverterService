package org.example;

import City.District;

import Readers.JsonReader;
import Readers.Reader;

import Writers.Writer;
import Writers.WriterToXml;

import java.util.List;

public class JsonToXml {
    public static void convert(String fileName, String resFileName){
        Reader jsonReader = new JsonReader();
        Writer xmlWriter = new WriterToXml();

        List<District> districts = jsonReader.readFromFile(fileName);
        xmlWriter.writeToFile(resFileName, districts);
    }
}
