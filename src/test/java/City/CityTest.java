package City;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CityTest {
    @Test
    public void CheckEntranceContructor() {
        Entrance entrance1 = new Entrance();
        Entrance entrance2 = new Entrance(1, 1 ,1);

        Assert.assertNotNull(entrance1);
        Assert.assertNotNull(entrance2);
    }

    @Test
    public void CheckEntranceGetters() {
        Entrance entrance1 = new Entrance(1, 1 ,1);

        Assert.assertEquals(1, entrance1.getCountOfCitizens());
        Assert.assertEquals(1, entrance1.getCountOfFlats());
        Assert.assertEquals(1, entrance1.getDebt());


        Entrance entrance2 = new Entrance();

        entrance2.setCountOfCitizens(2);
        entrance2.setCountOfFlats(3);
        entrance2.setDebt(4);

        Assert.assertEquals(2, entrance2.getCountOfCitizens());
        Assert.assertEquals(3, entrance2.getCountOfFlats());
        Assert.assertEquals(4, entrance2.getDebt());
    }


    @Test
    public void CheckHouseContructor() {
        House house1 = new House();
        List<Entrance> entrs = new ArrayList<>();
        entrs.add(new Entrance());
        entrs.add(new Entrance(1, 2 ,3));
        House house2 = new House("Советская", 54, entrs);

        Assert.assertNotNull(house1);
        Assert.assertNotNull(house2);
    }

    @Test
    public void CheckHouseGetters() {
        List<Entrance> entrs = new ArrayList<>();
        entrs.add(new Entrance());
        entrs.add(new Entrance(1, 2 ,3));


        House house1 = new House("Советская", 54, entrs);

        Assert.assertEquals("Советская", house1.getStreet());
        Assert.assertEquals(54, house1.getNumber());
        Assert.assertEquals("java.util.ArrayList", house1.getEntrances().getClass().getName());

        House house2 = new House();

        house2.setStreet("Советская");
        house2.setNumber(3);
        house2.setEntrances(entrs);

        Assert.assertEquals("Советская", house2.getStreet());
        Assert.assertEquals(3, house2.getNumber());
        Assert.assertEquals("java.util.ArrayList", house2.getEntrances().getClass().getName());
    }

    @Test
    public void CheckDistrictConstructor() {
        District district1 = new District();

        List<House> houses = new ArrayList<>();
        houses.add(new House());
        houses.add(new House());
        District district2 = new District("Ленинсий", houses);

        Assert.assertNotNull(district1);
        Assert.assertNotNull(district2);
    }

    @Test
    public void CheckDistrictGetters(){
        List<House> houses = new ArrayList<>();
        houses.add(new House());
        houses.add(new House());

        District district1 = new District("Ленинский", houses);
        District district2 = new District();

        district2.setName("Ленинский");
        district2.setHouses(houses);

        Assert.assertEquals("Ленинский", district1.getName());
        Assert.assertEquals("java.util.ArrayList", district1.getHouses().getClass().getName());

        Assert.assertEquals("Ленинский", district2.getName());
        Assert.assertEquals("java.util.ArrayList", district2.getHouses().getClass().getName());
    }
}