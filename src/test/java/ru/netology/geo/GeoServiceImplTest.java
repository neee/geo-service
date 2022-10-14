package ru.netology.geo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.stream.Stream;

public class GeoServiceImplTest {
    private GeoServiceImpl geoService;

    @BeforeEach
    void setUp() {
        geoService = new GeoServiceImpl();
        System.out.println("Starting test...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test completed");
    }

    @DisplayName("Testing location by IP address")
    @ParameterizedTest
    @MethodSource("getArguments")
    void byIp(String ip, Location expected) {
        Location actual = geoService.byIp(ip);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of("127.0.0.1", new Location(null, null, null, 0)),
                Arguments.of("172.0.32.11", new Location("Moscow", Country.RUSSIA, "Lenina", 15)),
                Arguments.of("96.44.183.149", new Location("New York", Country.USA, " 10th Avenue", 32)),
                Arguments.of("172.12.345.678", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.12.345.678", new Location("New York", Country.USA, null, 0)),
                Arguments.of("98.76.543.21", null),
                Arguments.of("", null)
        );
    }
}
