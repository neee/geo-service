import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {
    private static long suiteStartTime;
    private long testStartTime;
    @BeforeAll
    public static void initSuite() {
        System.out.println("Running LocalizationServiceImplTest");
        suiteStartTime = System.nanoTime();
    }
    @AfterAll
    public static void completeSuite() {
        System.out.println("LocalizationServiceImplTest complete: " + (System.nanoTime() - suiteStartTime));
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

    @Test
    void testCountryRussiaLang() {
        Country country = Country.RUSSIA;
        final String expected = "Добро пожаловать";

        LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);

        Assertions.assertEquals(expected, localizationService.locale(country));
    }
    @Test
    void testCountryUsaLang() {
        Country country = Country.USA;
        final String expected = "Welcome";

        LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);

        Assertions.assertEquals(expected, localizationService.locale(country));
    }
}
