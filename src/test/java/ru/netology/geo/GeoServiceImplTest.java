package ru.netology.geo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @ParameterizedTest
    @MethodSource("source")
    void byIp(String location) {
        GeoServiceImpl gs = new GeoServiceImpl();

        Location actual = gs.byIp(location);
        String expected = "Moscow";

        assertThat(actual.getCity(), equalTo(expected));
        assertThat(Country.RUSSIA, equalTo(actual.getCountry()));
        assertThat(Country.USA, not(equalTo(actual.getCountry())));
    }

    static Stream<String> source() {
        return Stream.of("172.0.32.11", "172.999999");
    }

    @Test
    void byCoordinates() {
        GeoServiceImpl gs = new GeoServiceImpl();

        assertThrows(RuntimeException.class, () -> {
            gs.byCoordinates(123, 123);
        });
    }
}