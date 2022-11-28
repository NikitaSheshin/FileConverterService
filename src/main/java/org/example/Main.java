package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 2){
            throw new Exception("Неверное количество аргументов");
        }

        if (args[0].endsWith(".xml") && args[1].endsWith(".json")){
            XmlToJson.convert(args[0], args[1]);
        } else if (args[0].endsWith(".json") && args[1].endsWith(".xml")){
            JsonToXml.convert(args[0], args[1]);
        } else{
            throw new Exception("Переданы файлы неподходящих форматов");
        }

        System.out.println("Успешно!");
    }
}