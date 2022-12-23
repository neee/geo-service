package sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MessageSenderImplMockitoTest {

    private static final String IP_ADDRESS_HEADER = "x-real-ip";
    private MessageSenderImpl messageSender;

    @Mock
    private GeoServiceImpl geoService;

    @Mock
    private LocalizationServiceImpl localizationService;

    @BeforeEach
    void setUp() {
        messageSender = new MessageSenderImpl(geoService, localizationService);
        System.out.println("Starting test...");
    }

    @ParameterizedTest
    @ValueSource(strings = {"96.123.45.67", "97.456.78.36", "98.123.45.67", "122.98.76.54"})
    void send_whenSendEnglishText(String ip) {

        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, ip);

        Mockito.when(geoService.byIp(Mockito.eq(ip)))
                .thenReturn(new Location("New York", Country.USA, " 5th Avenue", 18));
        Mockito.when(localizationService.locale(geoService.byIp(ip).getCountry())).thenReturn("Welcome");

        String expected = "Welcome";
        String actual = messageSender.send(headers);
        Assertions.assertEquals(expected, actual);
        System.out.println();
    }

    @ParameterizedTest
    @ValueSource(strings = {"172.123.45.67", "172.456.78.36", "172.123.45.67", "172.98.76.54"})
    void send_whenSendRussianText(String ip) {

        Map<String, String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, ip);

        Mockito.when(geoService.byIp(Mockito.eq(ip)))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Leninsky avenue", 7));
        Mockito.when(localizationService.locale(geoService.byIp(ip).getCountry())).thenReturn("Добро пожаловать");

        String expected = "Добро пожаловать";
        String actual = messageSender.send(headers);
        Assertions.assertEquals(expected, actual);
    }


}
