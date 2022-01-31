import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTest {
    private static long suiteStartTime;
    private long testStartTime;
    @BeforeAll
    public static void initSuite() {
        System.out.println("Running GeoServiceImplTest");
        suiteStartTime = System.nanoTime();
    }
    @AfterAll
    public static void completeSuite() {
        System.out.println("GeoServiceImplTest complete: " + (System.nanoTime() - suiteStartTime));
    }
    @BeforeEach
    public void initTest() {
        System.out.println("\nStarting new test");
        testStartTime = System.nanoTime();
    }
    @AfterEach
    public void finalizeTest() {
        System.out.println("Test complete:" + (System.nanoTime() - testStartTime));
    }

    @ParameterizedTest
    @ValueSource(strings = {"172.", "172.0.32.11", "172.66.22.11"})
    void testRussiaIpCountry(String ip) {
        Country expected = Country.RUSSIA;
        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);

        Assertions.assertEquals(expected, geoService.byIp(ip).getCountry());
    }
    @ParameterizedTest
    @ValueSource(strings = {"96.", "96.10.132.11", "96.44.183.149"})
    void testUsaIpCountry(String ip) {
        Country expected = Country.USA;
        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);

        Assertions.assertEquals(expected, geoService.byIp(ip).getCountry());
    }
    @Test
    void testLocalhostIpNull() {
        String ip = "127.0.0.1";
        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);

        Assertions.assertNull(geoService.byIp(ip).getCountry());
    }
}
