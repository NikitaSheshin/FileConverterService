package beans.json;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class DistrictJson {
    private String name;
    private List<HouseJson> houses;
}
