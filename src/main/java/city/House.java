package City;

import lombok.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "house")

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class House {
    private String street;
    private int number;
    @XmlElement(name = "entrance")
    private List<Entrance> entrances;
}