package City;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "districts")

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class DistrictsStore {
    @XmlElement(name = "district")
    private List<District> districts;
}