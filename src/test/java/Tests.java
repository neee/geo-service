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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Tests {

    public final String LOCALHOST = "127.0.0.1";
    public final String MOSCOW_IP = "172.0.32.11";
    public final String NEW_YORK_IP = "96.44.183.149";
    public final String ANY_RU_IP = "172.0.32.12";
    public final String ANY_USA_IP = "96.44.183.150";
    public final String RANDOM_API = "100.100.100.100";
    public final String IP_ADDRESS_HEADER = "x-real-ip";

    @Test
    public void locationByIPTest() {
        GeoService geoService = new GeoServiceImpl();
        //assertEquals(new Location(null, null, null, 0), geoService.byIp(LOCALHOST));
        assertEquals(new Location("Moscow", Country.RUSSIA, "Lenina", 15), geoService.byIp(MOSCOW_IP));
        assertEquals(new Location("New York", Country.USA, " 10th Avenue", 32), geoService.byIp(NEW_YORK_IP));
        //assertEquals(new Location("Moscow", Country.RUSSIA, null, 0), geoService.byIp(ANY_RU_IP));
        //assertEquals(new Location("New York", Country.USA, null,  0), geoService.byIp(ANY_USA_IP));
        assertNull(geoService.byIp(RANDOM_API));
    }

    @Test
    public void sendRussianTest() {
        GeoService geoServiceMock = Mockito.mock(GeoService.class);
        Mockito.when(geoServiceMock.byIp(MOSCOW_IP)).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationService localizationServiceMock = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationServiceMock.locale(Country.RUSSIA)).thenReturn("Добро пожаловать в Москву");

        MessageSender messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(IP_ADDRESS_HEADER, MOSCOW_IP);
        String response = messageSender.send(headers);
        assertEquals("Добро пожаловать в Москву", response);
    }

    @Test
    public void sendEnglishTest() {
        GeoService geoServiceMock = Mockito.mock(GeoService.class);
        Mockito.when(geoServiceMock.byIp(NEW_YORK_IP)).thenReturn(new Location("New York", Country.USA, "Lenina", 15));

        LocalizationService localizationServiceMock = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationServiceMock.locale(Country.USA)).thenReturn("Any english message");

        MessageSender messageSender = new MessageSenderImpl(geoServiceMock, localizationServiceMock);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(IP_ADDRESS_HEADER, NEW_YORK_IP);
        String response = messageSender.send(headers);
        assertEquals("Any english message", response);
    }
}
