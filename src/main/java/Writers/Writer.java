package Writers;

import City.District;

import java.util.List;

public interface Writer {
    void writeToFile(String fileName, List<District> districts);
}
