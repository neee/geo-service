package ru.netology.i18n;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.EnumSource;
import ru.netology.entity.Country;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    private LocalizationService localizationService;

    @BeforeEach
    void init() {
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    void shouldReturnMessageInRussian() {
        String expectedString = "Добро пожаловать";

        assertEquals(localizationService.locale(Country.RUSSIA), expectedString);
    }

    @ParameterizedTest
    @EnumSource(names = {"GERMANY", "USA", "BRAZIL"})
    void shouldReturnMessageInEnglish(Country country) {
        String expectedString = "Welcome";

        assertEquals(localizationService.locale(country), expectedString);
    }
}