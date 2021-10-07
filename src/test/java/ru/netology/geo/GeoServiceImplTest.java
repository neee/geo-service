package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @ParameterizedTest
    @DisplayName("Проверка метода определения координат по ip")
    @MethodSource ("ipList")
    void byIp(String ip, String country) {
        String expected;
        if (ip.startsWith("172.")) {
            expected = country;
        } else if (ip.startsWith("96.")) {
            expected = country;
        } else {
            expected = country;
        }

        GeoServiceImpl geoService = new GeoServiceImpl();
        try {
            String result = geoService.byIp(ip).getCountry().name();

            Assertions.assertEquals(result, expected);
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
    }

    public static Stream<Arguments> ipList (){
        return Stream.of(
                Arguments.of("192.168.0.1", null),
                Arguments.of("172.0.32.11", "RUSSIA"),
                Arguments.of("96.44.183.149", "USA"),
                Arguments.of("96.58.55.67", "CANADA"));
    }

    @Test
    @DisplayName("Проверка метода по координатам")
    void byCoordinates() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        double latitube = 59.175928;
        double longitube = 48.086285;
        String expected = "RUSSIA";

        assertThrows(RuntimeException.class,
                () -> geoService.byCoordinates(latitube, longitube));
    }
}