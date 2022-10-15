package org.example;

import City.Entrance;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 2)
            throw new Exception("Неверное количество аргументов");

        if (args[0].substring(args[0].length() - 4).equals(".xml") &&
                args[1].substring(args[1].length() - 5).equals(".json"))
            XmlToJson.convert(args[0], args[1]);
        else if (args[0].substring(args[0].length() - 5).equals(".json")
                && args[1].substring(args[1].length() - 4).equals(".xml"))
            JsonToXml.convert(args[0], args[1]);
        else
            throw new Exception("Переданы файлы неподходящих форматов");

        System.out.println("Успешно!");
    }
}