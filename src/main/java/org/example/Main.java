package org.example;

import City.Entrance;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var districts = XmlToJson.convert("A:\\untitled\\xml.txt");

        System.out.println(districts.get(0).getName());
    }
}