package ru.netology.i18n;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalizationServiceImplTest {

    @ParameterizedTest
    @MethodSource ("source")
    void locale(Country country, String message) {
        String exception = message;

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String resCountryName = localizationService.locale(country);

        assertEquals(resCountryName, exception);

    }

    public static Stream<Arguments> source(){
        return Stream.of(
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.BRAZIL, "Салам")
        );
    }
}