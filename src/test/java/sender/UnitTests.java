package sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class UnitTests {
    private static final Map<String, String> headers = new HashMap<>();
    private static MessageSender messageSender;
    private static GeoService geoServiceMock;
    private static LocalizationService localizationServiceMock;
    private static String expected;

    @BeforeAll
    public static void init() {
        //Инициализация классов Mock
        geoServiceMock = Mockito.spy(GeoServiceImpl.class);
        localizationServiceMock = Mockito.spy(LocalizationServiceImpl.class);
        //Инициализация классов
        GeoService geoService = new GeoServiceImpl();
        LocalizationService localizationService = new LocalizationServiceImpl();
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    public void ruMessage() {
        Location location = geoServiceMock.byIp("172.0.32.11");
        expected = localizationServiceMock.locale(location.getCountry());

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.0.32.11");
        Assertions.assertEquals(expected, messageSender.send(headers));
    }

    @Test
    public void engMessage() {
        Location location = geoServiceMock.byIp("96.123.12.19");
        expected = localizationServiceMock.locale(location.getCountry());

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.123.12.19");
        Assertions.assertEquals(expected, messageSender.send(headers));
    }

    @Test
    public void localIP() {
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "127.0.0.1");
        Assertions.assertThrows(NullPointerException.class, () -> messageSender.send(headers));
    }

    @Test
    public void badIP() {
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "99.999.99.99");
        Assertions.assertThrows(NullPointerException.class, () -> messageSender.send(headers));
    }
}
