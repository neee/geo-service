package i18n;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

@DisplayName("Testing the location returned text")
public class LocalizationServiceImplTest {

    private LocalizationServiceImpl localizationService;
    @BeforeEach
    void setUp() {
        localizationService = new LocalizationServiceImpl();
        System.out.println("Starting test...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test completed");
    }

    @DisplayName("Testing when argument is null")
    @Test
    void locale_whenArgumentIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> localizationService.locale(null));
    }

    @DisplayName("Testing with EnumSource")
    @ParameterizedTest
    @EnumSource(Country.class)
    void localeTestWithEnumSource(Country country) {
        Assertions.assertNotNull(country);
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    void locale(Country country, String expected) {
        String actual = localizationService.locale(country);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.BRAZIL, "Welcome"),
                Arguments.of(Country.GERMANY, "Welcome")
        );
    }

}
