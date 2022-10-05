package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceImplTest {
    private GeoService geoService;

    @Test
    @DisplayName("Тест ip: Moscow, Lenina st., 15")
    void geoServiceTest() {
        geoService = new GeoServiceImpl();
        Location expectedLocation = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Assertions.assertEquals(expectedLocation.getCity(), geoService.byIp(GeoServiceImpl.MOSCOW_IP).getCity());
        Assertions.assertEquals(expectedLocation.getCountry(), geoService.byIp(GeoServiceImpl.MOSCOW_IP).getCountry());
        Assertions.assertEquals(expectedLocation.getStreet(), geoService.byIp(GeoServiceImpl.MOSCOW_IP).getStreet());
        Assertions.assertEquals(expectedLocation.getBuilding(), geoService.byIp(GeoServiceImpl.MOSCOW_IP).getBuilding());
    }

    @Test
    @DisplayName("Тест ip: New York, 10th avenue, 32")
    void geoService2Test() {
        geoService = new GeoServiceImpl();
        Location expectedLocation = new Location("New York", Country.USA, " 10th Avenue", 32);
        Assertions.assertEquals(expectedLocation.getCity(), geoService.byIp(GeoServiceImpl.NEW_YORK_IP).getCity());
        Assertions.assertEquals(expectedLocation.getCountry(), geoService.byIp(GeoServiceImpl.NEW_YORK_IP).getCountry());
        Assertions.assertEquals(expectedLocation.getStreet(), geoService.byIp(GeoServiceImpl.NEW_YORK_IP).getStreet());
        Assertions.assertEquals(expectedLocation.getBuilding(), geoService.byIp(GeoServiceImpl.NEW_YORK_IP).getBuilding());
    }

}
