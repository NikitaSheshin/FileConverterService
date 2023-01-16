package converter.beans.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "entrance")

@Data
@XmlType(propOrder = {"countOfFlats", "countOfCitizens", "debt"})
@XmlAccessorType(XmlAccessType.FIELD)
public class EntranceXml {
    private int countOfCitizens;
    private int countOfFlats;
    private int debt;
}
