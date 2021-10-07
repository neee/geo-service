package ru.netology.sender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

class MessageSenderImplTest {
    private MessageSenderImpl messageSender;
    private LocalizationService localizationService;
    private GeoService geoService;


    @BeforeEach
    void setUp() {
        localizationService =mock(LocalizationService.class);
        geoService =mock(GeoService.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    void shouldReturnMessageInRussian() {
        final String moscowIp = "172.255.255.255";
        final Location locationMoscow = new Location("Moscow", Country.RUSSIA, null, 0);
        final String message = "Привет";

        final String expectedMessage = "Привет";

        given(geoService.byIp(moscowIp)).willReturn(locationMoscow);
        given(localizationService.locale(Country.RUSSIA)).willReturn(message);

        assertEquals(expectedMessage, messageSender.send(Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, moscowIp)));
    }

    @Test
    void shouldReturnMessageInEnglish() {
        final String moscowIp = "96.255.255.255";
        final Location locationNewYork = new Location("New York", Country.USA, null, 0);
        final String message = "Hello";

        final String expectedMessage = "Hello";

        given(geoService.byIp(moscowIp)).willReturn(locationNewYork);
        given(localizationService.locale(Country.USA)).willReturn(message);

        assertEquals(expectedMessage, messageSender.send(Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, moscowIp)));
    }
}