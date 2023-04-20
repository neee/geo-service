package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderTest {
    private GeoService geoService;
    private LocalizationService localizationService;
    private MessageSender messageSender;
    private Map<String, String> headers;

    @BeforeEach
    void setUp() {
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
        headers = new HashMap<>();
    }

    @Test
    public void test_send_massage_russia() {
        Mockito.when(geoService.byIp("172."))
                .thenReturn(new Location(null, Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");


        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");
        String actual = messageSender.send(headers);
        String expected = localizationService.locale(Country.RUSSIA);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(geoService, Mockito.times(1)).byIp("172.");
        Mockito.verify(geoService, Mockito.never()).byIp("96.");
    }

    @Test
    public void test_send_massage_usa() {
        Mockito.when(geoService.byIp("96."))
                .thenReturn(new Location(null, Country.USA, null, 0));
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");


        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.");
        String actual = messageSender.send(headers);
        String expected = localizationService.locale(Country.USA);

        Assertions.assertEquals(expected, actual);
        Mockito.verify(geoService, Mockito.times(1)).byIp("96.");
        Mockito.verify(geoService, Mockito.never()).byIp("172.");
    }

}