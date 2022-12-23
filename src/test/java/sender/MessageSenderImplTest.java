package sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MessageSenderImplTest {
    private static final String MOSCOW_IP = "172.0.32.11";
    private static final String NEW_YORK_IP = "96.44.183.149";
    private MessageSenderImpl msi;
    @Mock
    private GeoServiceImpl gsi;
    @Mock
    private LocalizationServiceImpl lsi;

    @BeforeEach
    void setUp(){
        msi = new MessageSenderImpl(gsi, lsi);
    }

    @Test
    void textRus() {
        Mockito.when(gsi.byIp(Mockito.eq(MOSCOW_IP))).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(lsi.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        String actualResult = msi.send(Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, MOSCOW_IP));
        String expectedResult = "Добро пожаловать";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void textEng() {
        Mockito.when(gsi.byIp(Mockito.eq(NEW_YORK_IP))).thenReturn(new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(lsi.locale(Country.USA)).thenReturn("Welcome");
        String actualResult = msi.send(Map.of(MessageSenderImpl.IP_ADDRESS_HEADER, NEW_YORK_IP));
        String expectedResult = "Welcome";
        Assertions.assertEquals(expectedResult, actualResult);
    }

}
