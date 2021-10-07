package ru.netology.geo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class GeoServiceImplTest {
    private GeoServiceImpl geoService;
    private static final Random r = new Random();

    @BeforeEach
    void init() {
        geoService = new GeoServiceImpl();
    }


    @ParameterizedTest
    @MethodSource("stringIdAndExpectedLocation")
    void shouldReturnLocationByIp(String ip, Location location) {

        Location actualLocation = geoService.byIp(ip);

        assertEquals(location, actualLocation);
    }

    static Stream<Arguments> stringIdAndExpectedLocation() {
        return Stream.of(
                arguments("172.0.32.11"
                        , new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                arguments("96.44.183.149"
                        , new Location("New York", Country.USA, " 10th Avenue", 32)),
                arguments("127.0.0.1"
                        , new Location(null, null, null, 0)),
                arguments(String.format("172.%d.%d.%d", r.nextInt(255), r.nextInt(255), r.nextInt(255))
                        , new Location("Moscow", Country.RUSSIA, null, 0)),
                arguments(String.format("96.%d.%d.%d", r.nextInt(255), r.nextInt(255), r.nextInt(255))
                        , new Location("New York", Country.USA, null, 0))
        );
    }

}