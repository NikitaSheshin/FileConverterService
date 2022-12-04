package city;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "district")

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class District {
    private String name;
    @SerializedName("houses")
    @XmlElement(name = "house")
    private List<House> houses;
}