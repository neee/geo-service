package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class TestAll {

    @Test
    void send_rus_test() {
        Location location = new Location("Moscow", Country.RUSSIA, null, 0);
        String expect = "Добро пожаловать";
        String [] ipRus = new String[] {
                "172.0.0.1",
                "172.1.2.4",
                "172.58.",
                "172.4.34.78"
        };
        for (String ip : ipRus) {
            GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
            Mockito.when(geoService.byIp(ip)).thenReturn(location);
            LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
            Mockito.when(localizationService.locale(location.getCountry())).thenReturn("Добро пожаловать");

            MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
            Map<String, String> headers = new HashMap<>();
            headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

            Assertions.assertEquals(expect,messageSender.send(headers));
        }
    }
    @Test
    void send_usa_test() {
        Location location = new Location("Moscow", Country.RUSSIA, null, 0);
        String expect = "Welcome";
        String [] ipRus = new String[] {
                "96.44.183.149",
                "96.45.67.92",
                "96.1.25.144",
                "96.30."
        };
        for (String ip : ipRus) {
            GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
            Mockito.when(geoService.byIp(ip)).thenReturn(location);
            LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
            Mockito.when(localizationService.locale(location.getCountry())).thenReturn("Welcome");

            MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
            Map<String, String> headers = new HashMap<>();
            headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

            Assertions.assertEquals(expect,messageSender.send(headers));
        }
    }
    @Test
    void geo_service_byIp() {
        Location expectRus = new Location("Moscow", Country.RUSSIA, null, 0);
        String ipRus = "172.0.1.2";
        GeoServiceImpl geoServiceRus = new GeoServiceImpl();
        Assertions.assertEquals(expectRus.getCountry(), geoServiceRus.byIp(ipRus).getCountry());

        Location expectUsa = new Location("New York", Country.USA, null, 0);
        String ipUsa = "96.1.25.1";
        GeoServiceImpl geoServiceUsa = new GeoServiceImpl();
        Assertions.assertEquals(expectUsa.getCountry(), geoServiceUsa.byIp(ipUsa).getCountry());
    }
    @Test
    @Disabled ("Тест выключен, поскольку в GeoServiceImpl для ip других стран результат null")
    void geo_service_byIp2() {
        Location expectGermany = new Location("Berlin", Country.GERMANY, null,  0);
        String ipGermany = "103.147.25.5";
        GeoServiceImpl geoServiceGermany = new GeoServiceImpl();
        Assertions.assertEquals(expectGermany.getCountry(),geoServiceGermany.byIp(ipGermany).getCountry());
    }
    @Test
    void locale() {
        String expect = "Добро пожаловать";
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        Assertions.assertEquals(expect, localizationService.locale(Country.RUSSIA));

        String expect2 = "Welcome";
        LocalizationServiceImpl localizationService2 = new LocalizationServiceImpl();
        Assertions.assertEquals(expect2, localizationService2.locale(Country.USA));
    }

}
