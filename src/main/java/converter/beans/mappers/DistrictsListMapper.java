package converter.beans.mappers;

import converter.beans.json.DistrictJson;
import converter.beans.xml.DistrictXml;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = DistrictMapper.class)
public interface DistrictsListMapper {
    DistrictsListMapper instance = Mappers.getMapper(DistrictsListMapper.class);

    List<DistrictJson> toDistrictJsonList(List<DistrictXml> districtsXml);

    List<DistrictXml> toDistrictXmlList(List<DistrictJson> districtsJson);
}
