package City;

import org.junit.Assert;
import org.junit.Test;

public class CityTest {
    @Test
    public void CheckEntranceContructor(){
        Entrance entrance1 = new Entrance();
        Entrance entrance2 = new Entrance(1, 1 ,1);

        Assert.assertNotNull(entrance1);
        Assert.assertNotNull(entrance2);
    }

    @Test
    public void CheckEntranceGetters(){
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
}