package converter.beans.mappers;

import converter.beans.json.HouseJson;
import converter.beans.xml.HouseXml;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = HouseMapper.class)
public interface HousesListMapper {
    List<HouseJson> toHouseJsonList(List<HouseXml> housesXml);

    List<HouseXml> toHouseXmlList(List<HouseJson> housesJson);
}
