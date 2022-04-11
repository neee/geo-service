package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class LocalizationServiceImplTest {

    @Test
    void testLocale() {
        LocalizationServiceImpl lsi = new LocalizationServiceImpl();

        String actual1 = lsi.locale(Country.RUSSIA);
        String actual2 = lsi.locale(Country.USA);

        assertThat(actual1, equalTo("Добро пожаловать"));
        assertThat(actual2, equalTo("Welcome"));
        assertThat(actual2, not(equalTo("Добро пожаловать")));
    }
}