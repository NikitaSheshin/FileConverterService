package beans.mappers;

import beans.json.DistrictJson;
import beans.xml.DistrictXml;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = DistrictMapper.class)
public interface DistrictsListMapper {
    DistrictsListMapper instance = Mappers.getMapper(DistrictsListMapper.class);

    List<DistrictJson> toDistrictJsonList(List<DistrictXml> districtsXml);

    List<DistrictXml> toDistrictXmlList(List<DistrictJson> districtsJson);
}
