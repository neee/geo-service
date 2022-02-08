package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceTest {

    @Test
    void testByIpRU() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location expected = new Location("Moscow", Country.RUSSIA, null, 0);
        Location current = geoService.byIp("172. ");
        assertEquals(expected.getCity(), current.getCity());
        assertEquals(expected.getCountry(), current.getCountry());
    }

    @Test
    void testByIpUS() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location expected = new Location("New York", Country.USA, null, 0);
        Location current = geoService.byIp("96. ");
        assertEquals(expected.getCity(), current.getCity());
        assertEquals(expected.getCountry(), current.getCountry());
    }
}