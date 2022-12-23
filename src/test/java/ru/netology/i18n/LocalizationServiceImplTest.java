package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @Test
    @DisplayName("проверки возвращаемого текста в зависимости от страны")
    void locale() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String actualResult = localizationService.locale(Country.RUSSIA);
        String expectedResult = "Добро пожаловать";

        Assertions.assertEquals(expectedResult, actualResult);
    }
}