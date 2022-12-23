package i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {

    @Test
    void locale(){
        LocalizationServiceImpl lsi = new LocalizationServiceImpl();
        Assertions.assertEquals("Добро пожаловать", lsi.locale(Country.RUSSIA));
    }
}
