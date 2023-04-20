package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {
    @Test
    public void test_by_ip_russia() {
        GeoService geoService = new GeoServiceImpl();

        Country actual = geoService.byIp("172.").getCountry();
        Country expected = new Location(null, Country.RUSSIA, null, 0).getCountry();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void test_by_ip_usa() {
        GeoService geoService = new GeoServiceImpl();

        Country actual = geoService.byIp("96.").getCountry();
        Country expected = new Location(null, Country.USA, null, 0).getCountry();

        Assertions.assertEquals(expected, actual);
    }

}