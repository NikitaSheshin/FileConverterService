package City;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class House {
    String street;
    int number;
    List<Entrance> entrances;
}
