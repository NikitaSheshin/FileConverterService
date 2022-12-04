package city;

import lombok.*;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "entrance")

@Data
@XmlType(propOrder = { "countOfFlats", "countOfCitizens", "debt"} )
@XmlAccessorType(XmlAccessType.FIELD)
public class Entrance {
    private int countOfCitizens;
    private int countOfFlats;
    private int debt;
}