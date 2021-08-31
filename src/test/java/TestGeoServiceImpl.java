import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class TestGeoServiceImpl {

    @BeforeClass
    public GeoService setGeoService() {
        return new GeoServiceImpl();
    }

    @Test
    public void testByIp() {
        final String ip = "172.8.0.23";
        Location expected = new Location("Moscow", Country.RUSSIA, null, 0);
        Location actual = setGeoService().byIp(ip);
        Assertions.assertEquals(expected, actual);
    }
}
