package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MessageSenderImplTest {
    private static final String ipRus = "172.0.0.0";
    private static final String ipEn = "96.0.0.0";

    @Mock
    private GeoService geoService;
    @Mock
    private LocalizationService localizationService;

    private MessageSenderImpl messageSenderImpl;

    @BeforeEach
    void setUp() {
        messageSenderImpl = new MessageSenderImpl(geoService, localizationService);

    }

    @Test
    @DisplayName("MessageSenderImpl всегда отправляет только русский текст, если ip относится к российскому сегменту адресов")
    void sendRus() {
        Location location = new Location("Moscow", Country.RUSSIA, null, 0);
        String sms = "Добро пожаловать";
        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", ipRus);

        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn(sms);
        Mockito.when(geoService.byIp(Mockito.eq(ipRus))).thenReturn(location);

        String actualResult = messageSenderImpl.send(headers);
        String expectedResult = "Добро пожаловать";

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("MessageSenderImpl всегда отправляет только английский текст, если ip относится к американскому сегменту адресов")
    void sendEn() {
        LocalizationService local = new LocalizationServiceImpl();

        Location location = new Location("New York", Country.USA, null, 0);
        Map<String, String> headers = new HashMap<>();
        headers.put("x-real-ip", ipEn);

        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        Mockito.when(geoService.byIp(Mockito.eq(ipEn))).thenReturn(location);

        String actualResult = messageSenderImpl.send(headers);
        String expectedResult = "Welcome";

        Assertions.assertEquals(expectedResult, actualResult);
    }
}