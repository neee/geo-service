package ru.netology.sender;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;


@ExtendWith(MockitoExtension.class)
public class MessageSenderImplWithMockitoTest {
    @Mock
    GeoService geoService;
    @Mock
    LocalizationService localizationService;

    private MessageSender messageSender;
    private final Map<String, String> headers = new HashMap<>();

    @BeforeEach
    void setUp() {
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    @DisplayName("Проверка отправки русского текста")
    void russianTextTest() {
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.0.0");
        String expectedRussian = "Добро пожаловать";
        Location locationMoscow = new Location("Moscow", Country.RUSSIA, null, 0);
        Mockito.when(geoService.byIp(Mockito.startsWith("172."))).thenReturn(locationMoscow);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn(expectedRussian);
        Assertions.assertEquals(expectedRussian, messageSender.send(headers));
        System.out.println();
    }

    @Test
    @DisplayName("Проверка отправки английского текста")
    void englishTextTest() {
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.0.0.0");
        String expectedEnglish = "Welcome";
        Location locationNewYork = new Location("New York", Country.USA, null, 0);
        Mockito.when(geoService.byIp(Mockito.startsWith("96."))).thenReturn(locationNewYork);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn(expectedEnglish);
        Assertions.assertEquals(expectedEnglish, messageSender.send(headers));
        System.out.println();
    }

    @Test
    @DisplayName("Проверка ip Moscow, Russia")
    void ipMoscowTest() {
        String ipMoscow = "172.1.1.1";
        Location locationMoscow = new Location("Moscow", Country.RUSSIA, null, 0);
        Mockito.when(geoService.byIp(Mockito.startsWith("172."))).thenReturn(locationMoscow);
        Assertions.assertEquals(locationMoscow, geoService.byIp(ipMoscow));
    }

    @Test
    @DisplayName("Проверка конкретного ip: Moscow, Russia, Lenina, 15")
    void ipSpecMoscowTest() {
        String ipSpecMoscow = "172.123.12.19";
        Location specLocationMoscow = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Mockito.when(geoService.byIp(ipSpecMoscow)).thenReturn(specLocationMoscow);
        Assertions.assertEquals(specLocationMoscow, geoService.byIp(ipSpecMoscow));
    }

    @Test
    @DisplayName("Проверка ip New York, USA")
    void ipNewYorkTest() {
        String ipNewYork = "96.1.1.1";
        Location locationNewYork = new Location("New York", Country.USA, null, 0);
        Mockito.when(geoService.byIp(Mockito.startsWith("96"))).thenReturn(locationNewYork);
        Assertions.assertEquals(locationNewYork, geoService.byIp(ipNewYork));
    }

    @Test
    @DisplayName("Проверка конкретного ip: New York, USA, 10th Avenue, 32")
    void ipSpecNewYorkTest() {
        String ipSpecNewYork = "96.44.183.149";
        Location specLocationNewYork = new Location("New York", Country.USA, " 10th avenue", 32);
        Mockito.when(geoService.byIp(ipSpecNewYork)).thenReturn(specLocationNewYork);
        Assertions.assertEquals(specLocationNewYork, geoService.byIp(ipSpecNewYork));
    }
}
