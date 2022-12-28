package beans.mappers;

import beans.json.DistrictJson;
import beans.xml.DistrictXml;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = HousesListMapper.class)
public interface DistrictMapper {
    DistrictMapper instance = Mappers.getMapper(DistrictMapper.class);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "houses", source = "houses")
    DistrictJson toJsonDistrict(DistrictXml districtXml);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "houses", source = "houses")
    DistrictXml toXmlDistrict(DistrictJson districtJson);

    DistrictXml toXmlDistrict(Object districtJson);

    DistrictJson toJsonDistrict(Object districtXml);
}
