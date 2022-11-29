package City;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class House {
    String street;
    int number;
    List<Entrance> entrances;
}
