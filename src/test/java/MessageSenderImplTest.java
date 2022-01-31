import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.mockito.Mockito;
import ru.netology.entity.*;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.netology.sender.MessageSenderImpl.IP_ADDRESS_HEADER;

public class MessageSenderImplTest {
    private static long suiteStartTime;
    private long testStartTime;
    @BeforeAll
    public static void initSuite() {
        System.out.println("Running MessageSenderImplTest");
        suiteStartTime = System.nanoTime();
    }
    @AfterAll
    public static void completeSuite() {
        System.out.println("MessageSenderImplTest complete: " + (System.nanoTime() - suiteStartTime));
    }
    @BeforeEach
    public void initTest() {
        System.out.println("\nStarting new test");
        testStartTime = System.nanoTime();
    }
    @AfterEach
    public void finalizeTest() {
        System.out.println("\nTest complete:" + (System.nanoTime() - testStartTime));
    }

    @ParameterizedTest
    @EnumSource(value = Country.class, names = { "RUSSIA", "USA" })
    void testWithEnumSourceInclude(Country country) {
        assertTrue(EnumSet.of(Country.RUSSIA, Country.USA).contains(country));
    }

    @ParameterizedTest
    @ValueSource(strings = {"172.", "172.0.32.11", "172.66.22.11"})
    void testRussiaIpTrue(String ip) {
        Country testCountry = Country.RUSSIA;
        Map<String,String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, ip);

        LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);
        String expected = localizationService.locale(testCountry);

        Location location = Mockito.mock(Location.class);
        Mockito.when(location.getCountry()).thenReturn(testCountry);

        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(ip)).thenCallRealMethod();

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String result = messageSender.send(headers);

        Assertions.assertTrue(result.contains(expected));
    }
    @ParameterizedTest
    @ValueSource(strings = {"96.", "96.10.132.11", "96.44.183.149"})
    void testUsaIpTrue(String ip) {
        Country testCountry = Country.USA;
        Map<String,String> headers = new HashMap<>();
        headers.put(IP_ADDRESS_HEADER, ip);

        LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);
        String expected = localizationService.locale(testCountry);

        Location location = Mockito.mock(Location.class);
        Mockito.when(location.getCountry()).thenReturn(testCountry);

        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(ip)).thenCallRealMethod();

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String result = messageSender.send(headers);

        Assertions.assertTrue(result.contains(expected));
    }
}
