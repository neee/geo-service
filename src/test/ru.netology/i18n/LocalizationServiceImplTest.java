package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

public class LocalizationServiceImplTest {
    private LocalizationService localizationService;

    @Test
    @DisplayName("Проверка русского ответа")
    void russianResponseTest() {
        localizationService = new LocalizationServiceImpl();
        Assertions.assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }

    @Test
    @DisplayName("Проверка английского ответа (как интернациональный)")
    void englishResponseTest() {
        localizationService = new LocalizationServiceImpl();
        Assertions.assertEquals("Welcome", localizationService.locale(Country.BRAZIL));
    }
}
