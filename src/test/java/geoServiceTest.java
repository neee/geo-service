import org.junit.jupiter.api.Assertions;
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
    public void testCorrectRussianText() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.2.32.11"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.2.32.11");

        final String result = messageSender.send(headers);
        final String expected = "Добро пожаловать";

        Assertions.assertEquals(result, expected);
    }

    @Test
    public void testCorrectEnglishText() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("96.45.183.149"))
                .thenReturn(new Location("New York", Country.USA, null,  0));

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.45.183.149");

        final String result = messageSender.send(headers);
        final String expected = "Welcome";

        Assertions.assertEquals(result, expected);
    }

    @Test
    public void testCorrectIpLocationMoscow() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location expected = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        final String MOSCOW_IP = "172.0.32.11";

        final Location result = geoService.byIp(MOSCOW_IP);

        Assertions.assertEquals(result, expected);
    }

    @Test
    public void testLocalizationRussia() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        final String expected = "Добро пожаловать";
        final Country country = Country.RUSSIA;

        final String result = localizationService.locale(country);

        Assertions.assertEquals(result, expected);
    }

    @Test
    public void testLocalizationWorldwide() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        final String expected = "Welcome";
        final Country country = Country.USA;

        final String result = localizationService.locale(country);

        Assertions.assertEquals(result, expected);
    }

}
