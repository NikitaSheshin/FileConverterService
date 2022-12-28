package beans.mappers;

import beans.json.EntranceJson;
import beans.xml.EntranceXml;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EntranceMapper {
    EntranceMapper instance = Mappers.getMapper(EntranceMapper.class);

    @Mapping(target = "countOfCitizens", source = "countOfCitizens")
    @Mapping(target = "countOfFlats", source = "countOfFlats")
    @Mapping(target = "debt", source = "debt")
    EntranceJson toJsonEntrance(EntranceXml entranceXml);

    @Mapping(target = "countOfCitizens", source = "countOfCitizens")
    @Mapping(target = "countOfFlats", source = "countOfFlats")
    @Mapping(target = "debt", source = "debt")
    EntranceXml toXmlEntrance(EntranceJson entranceJson);
}
