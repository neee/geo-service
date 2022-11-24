package geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class UnitTests {
    private static Location locationMock;
    private static GeoService geoServiceMock;
    private static GeoService geoService;

    @BeforeAll
    public static void init() {
        //Инициализация классов Mock
        geoServiceMock = Mockito.mock(GeoService.class);
        locationMock = Mockito.mock(Location.class);
        //Инициализация класса
        geoService = new GeoServiceImpl();
    }

    @Test
    public void location_Moscow_RUSSIA_STREET_BUILDING() {
        Mockito.when(geoServiceMock.byIp(""))
                .thenReturn(locationMock = new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        String expected = LocationToString(locationMock);
        String result = LocationToString(geoService.byIp("172.0.32.11"));

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void location_New_York_USA_STREET_BUILDING() {
        Mockito.when(geoServiceMock.byIp(""))
                .thenReturn(locationMock = new Location("New York", Country.USA, " 10th Avenue", 32));
        String expected = LocationToString(locationMock);
        String result = LocationToString(geoService.byIp("96.44.183.149"));

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void location_Moscow_RUSSIA_NULL_ZERO() {
        Mockito.when(geoServiceMock.byIp(""))
                .thenReturn(locationMock = new Location("Moscow", Country.RUSSIA, null, 0));
        String expected = LocationToString(locationMock);
        String result = LocationToString(geoService.byIp("172.0.0.0"));

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void location_New_York_USA_NULL_ZERO() {
        Mockito.when(geoServiceMock.byIp(""))
                .thenReturn(locationMock = new Location("New York", Country.USA, null, 0));
        String expected = LocationToString(locationMock);
        String result = LocationToString(geoService.byIp("96.0.0.0"));

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void bad_location() {
        Mockito.when(geoServiceMock.byIp(""))
                .thenReturn(locationMock = null);
        Assertions.assertEquals(locationMock, geoService.byIp("999.999.999.999"));
    }

    @Test
    public void testByCoordinates() {
        Assertions.assertThrows(RuntimeException.class, () ->
                geoService.byCoordinates(47.018711D, 12.34256D));
    }

    private String LocationToString(Location location) {
        return location.getCity() + "," + location.getCountry() + "," +
                location.getStreet() + "," + location.getBuiling();
    }
}
