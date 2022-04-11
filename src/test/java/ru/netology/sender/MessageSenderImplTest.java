package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

import java.util.HashMap;
import java.util.Map;


class MessageSenderImplTest {

    @Test
    void send() {
        GeoService gsi = Mockito.mock(GeoService.class);
        Mockito.when(gsi.byIp("172."))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationService lsi = Mockito.mock(LocalizationService.class);
        Mockito.when(lsi.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSender msi = new MessageSenderImpl(gsi, lsi);
        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, "172.");
        String actual = msi.send(headers);

        String expected = "Добро пожаловать";
        Assertions.assertEquals(expected, actual);
        assertThat(actual, equalTo(expected));
    }
}