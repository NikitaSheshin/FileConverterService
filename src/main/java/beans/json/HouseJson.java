package beans.json;

import lombok.Data;

import java.util.List;

@Data
public class HouseJson {
    private String street;
    private int number;
    private List<EntranceJson> entrances;
}
