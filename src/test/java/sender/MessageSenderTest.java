package sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.*;

class MessageSenderTest {
    @Test
    void testSendMessageRussia() {
        Map<String, String> map = new HashMap<>();
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.0.32.11"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn( "Добро пожаловать");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");
        String expected = "Добро пожаловать";
        String current = messageSender.send(map);
        Assertions.assertEquals(expected, current);
    }

    @Test
    void testSendMessageUSA() {
        Map<String, String> map = new HashMap<>();
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("96.44.183.149"))
                .thenReturn(new Location("New York", Country.USA, null,  0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        String expected = "Welcome";
        String current = messageSender.send(map);
        Assertions.assertEquals(expected, current);
    }

}