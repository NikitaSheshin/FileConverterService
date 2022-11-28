package Readers;

import City.District;

import java.util.List;

public interface Reader {
    List<District> readFromFile(String fileName);
}
