package beans.mappers;

import beans.json.HouseJson;
import beans.xml.HouseXml;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = EntranceListMapper.class)
public interface HouseMapper {
    HouseMapper instance = Mappers.getMapper(HouseMapper.class);

    @Mapping(target = "street", source = "street")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "entrances", source = "entrances")
    HouseJson toJsonHouse(HouseXml houseXml);

    @Mapping(target = "street", source = "street")
    @Mapping(target = "number", source = "number")
    @Mapping(target = "entrances", source = "entrances")
    HouseXml toXmlHouse(HouseJson houseJson);
}
