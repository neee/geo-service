package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Location;

class GeoServiceImplTest {
    private static final String NEW_YORK_IP = "96.44.183.149";

    @Test
    @DisplayName("проверки определения локации по ip")
    void byIp() {
        GeoService geoService = new GeoServiceImpl();
        Location location = geoService.byIp(NEW_YORK_IP);

        String actualResult = location.getCity() + location.getCountry() + location.getStreet() + location.getBuiling();
        String expectedResult = "New YorkUSA 10th Avenue32";

        Assertions.assertEquals(expectedResult, actualResult);
    }
}