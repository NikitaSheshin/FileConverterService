package City;

import java.util.List;

public class House {
    String street;
    int number;
    List<Entrance> entrances;

    public House(){

    }

    public House(String street, int number, List<Entrance> entrances){
        this.entrances = entrances;
        this.number = number;
        this.street = street;
    }

    public void setEntrances(List<Entrance> entrances) {
        this.entrances = entrances;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Entrance> getEntrances() {
        return entrances;
    }

    public int getNumber() {
        return number;
    }
}
