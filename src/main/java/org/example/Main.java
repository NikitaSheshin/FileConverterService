package org.example;

import Readers.JsonReader;
import Readers.Reader;
import Readers.XmlReader;
import Writers.Writer;
import Writers.WriterToJson;
import Writers.WriterToXml;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            throw new Exception("Неверное количество аргументов");
        }

        Reader reader;
        Writer writer;

        if (args[0].endsWith(".xml") && args[1].endsWith(".json")) {
            reader = new XmlReader();
            writer = new WriterToJson();
        } else if (args[0].endsWith(".json") && args[1].endsWith(".xml")) {
            reader = new JsonReader();
            writer = new WriterToXml();
        } else {
            throw new Exception("Переданы файлы неподходящих форматов");
        }

        writer.writeToFile(args[1], reader.readFromFile(args[0]));

        System.out.println("Успешно!");
    }
}