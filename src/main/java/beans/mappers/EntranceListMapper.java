package beans.mappers;

import beans.json.EntranceJson;
import beans.xml.EntranceXml;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = EntranceMapper.class)
public interface EntranceListMapper {
    List<EntranceJson> toEntranceJsonList(List<EntranceXml> entrancesXml);

    List<EntranceXml> toEntranceXmlList(List<EntranceJson> entrancesJson);
}
