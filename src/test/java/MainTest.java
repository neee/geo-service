import org.junit.jupiter.api.Assertions;
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

class MainTest {

    @Test
    void testGetLocationByIP(){
        //given:
        GeoService geoService = new GeoServiceImpl();
        Location expectedLocation = new Location("Moscow", Country.RUSSIA, "Lenina", 15);

        //when:
        Location location = geoService.byIp("172.");

        //then:
        Assertions.assertEquals(expectedLocation.getCountry(), location.getCountry() );
    }
    @Test
    void testGetTextByCountry(){
        //given:
        String expectedLocale = "Добро пожаловать";

        //when:
        LocalizationService localizationService = new LocalizationServiceImpl();
        String locale = localizationService.locale(Country.RUSSIA);

        //then:
        Assertions.assertEquals(expectedLocale, locale);
    }

    @Test
    void testGetGreetingByIpRu(){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");

        String expectedMessage = "Отправлено сообщение: Добро пожаловать";

        GeoService geoServiceRu = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceRu.byIp("172.123.12.19"))
                .thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        LocalizationService localizationServiceRu = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationServiceRu.locale(Country.RUSSIA))
                .thenReturn("Отправлено сообщение: Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoServiceRu, localizationServiceRu);
        String message = messageSender.send(headers);
        Assertions.assertEquals(expectedMessage, message);
    }

    @Test
    void testGetGreetingByIpEng(){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");

        String expectedMessage = "Отправлено сообщение: Welcome";

        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("96.44.183.149"))
                .thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Отправлено сообщение: Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String message = messageSender.send(headers);
        Assertions.assertEquals(expectedMessage, message);
    }

}