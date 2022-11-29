package i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

public class UnitTest {
    private static LocalizationService localizationServiceMock;
    private static LocalizationService localizationService;
    private static Location locationMock;
    private static GeoService geoService;
    private static GeoService geoServiceMock;

    @BeforeAll
    public static void init() {
        //Инициализация классов Mock
        geoServiceMock = Mockito.mock(GeoService.class);
        locationMock = Mockito.mock(Location.class);
        localizationServiceMock = new LocalizationServiceImpl();
        //Инициализация классов
        geoService = new GeoServiceImpl();
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    public void localeRUSSIA() {
        Mockito.when(geoServiceMock.byIp(""))
                .thenReturn(locationMock = new Location("", Country.RUSSIA, "", 0));

        Location location = geoService.byIp("172.123.12.19");
        Assertions.assertEquals(localizationServiceMock.locale(locationMock.getCountry()),
                localizationService.locale(location.getCountry()));
    }

    @Test
    public void localeUSA() {
        Mockito.when(geoServiceMock.byIp(""))
                .thenReturn(locationMock = new Location("", Country.USA, "", 0));

        Location location = geoService.byIp("96.44.183.149");
        Assertions.assertEquals(localizationServiceMock.locale(locationMock.getCountry()),
                localizationService.locale(location.getCountry()));
    }
}
