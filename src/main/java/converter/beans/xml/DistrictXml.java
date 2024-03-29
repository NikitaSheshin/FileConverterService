package converter.beans.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "district")

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class DistrictXml {
    private String name;
    @XmlElement(name = "house")
    private List<HouseXml> houses;
}
