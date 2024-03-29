package converter.beans.mappers;

import converter.beans.json.HouseJson;
import converter.beans.xml.HouseXml;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = EntranceListMapper.class)
public interface HouseMapper {
    @Mapping(target = "street", source = "street")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "entrances", source = "entrances")
    HouseJson toJsonHouse(HouseXml houseXml);

    @Mapping(target = "street", source = "street")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "entrances", source = "entrances")
    HouseXml toXmlHouse(HouseJson houseJson);
}
