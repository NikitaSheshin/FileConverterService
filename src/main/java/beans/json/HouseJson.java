package beans.json;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class HouseJson {
    private String street;
    private int number;
    @SerializedName("entrances")
    private List<EntranceJson> entrances;
}
