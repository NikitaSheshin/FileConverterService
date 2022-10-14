package City;

import java.util.List;

public class District {
    String name;
    List<House> houses;

    public District(){

    }

    public District(String name, List<House> houses){
        this.name = name;
        this.houses = houses;
    }

    public List<House> getHouses() {
        return houses;
    }

    public String getName() {
        return name;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public void setName(String name) {
        this.name = name;
    }
}
