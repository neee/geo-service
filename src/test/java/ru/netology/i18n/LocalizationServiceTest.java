package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceTest {

    @Test
    void testLocaleRU() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected = "Добро пожаловать";
        String current = localizationService.locale(Country.RUSSIA);
        assertEquals(expected,current);
    }

    @Test
    void testLocaleUS() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String expected = "Welcome";
        String current = localizationService.locale(Country.USA);
        assertEquals(expected,current);

    }
}