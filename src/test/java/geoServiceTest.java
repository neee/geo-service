import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;


public class geoServiceTest {

    @Test
    @DisplayName("Correct localhost IP test")
    public void testCorrectIpLocalHost() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location expected = new Location(null, null, null, 0);
        final String LOCALHOST = "127.0.0.1";

        final Location result = geoService.byIp(LOCALHOST);

        Assertions.assertEquals(result, expected);
    }

    @Test
    @DisplayName("Correct Moscow IP test")
    public void testCorrectIpLocationMoscow() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location expected = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        final String MOSCOW_IP = "172.0.32.11";

        final Location result = geoService.byIp(MOSCOW_IP);

        Assertions.assertEquals(result, expected);
    }

    @Test
    @DisplayName("Correct New-York IP test")
    public void testCorrectIpLocationNewYork() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location expected = new Location("New York", Country.USA, " 10th Avenue", 32);
        final String NEW_YORK_IP = "96.44.183.149";

        final Location result = geoService.byIp(NEW_YORK_IP);

        Assertions.assertEquals(result, expected);
    }

    @Test
    @DisplayName("Correct Russian IP test")
    public void testCorrectIpLocationRussia() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location expected = new Location("Moscow", Country.RUSSIA, null, 0);
        final String RUSSIAN_IP = "172.0.0.0";

        final Location result = geoService.byIp(RUSSIAN_IP);

        Assertions.assertEquals(result, expected);
    }

    @Test
    @DisplayName("Correct American IP test")
    public void testCorrectIpLocationUSA() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location expected = new Location("New York", Country.USA, null,  0);
        final String USA_IP = "96.0.0.0";

        final Location result = geoService.byIp(USA_IP);

        Assertions.assertEquals(result, expected);
    }

}
