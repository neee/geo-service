import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class localizationServiceTest {

    @Test
    @DisplayName("Correct Russian localization test")
    public void testLocalizationRussia() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        final String expected = "Добро пожаловать";
        final Country country = Country.RUSSIA;

        final String result = localizationService.locale(country);

        Assertions.assertEquals(result, expected);
    }

    @Test
    @DisplayName("Correct worldwide localization test")
    public void testLocalizationWorldwide() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        final String expected = "Welcome";
        final Country country = Country.USA;

        final String result = localizationService.locale(country);

        Assertions.assertEquals(result, expected);
    }
}
