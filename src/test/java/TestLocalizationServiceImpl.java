import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

public class TestLocalizationServiceImpl {

    @BeforeClass
    public LocalizationService setLocalizationService() {
        return new LocalizationServiceImpl();
    }

    @Test
    public void testLocale() {
        final Country country = Country.USA;
        String expected = "Welcome";
        String actual = setLocalizationService().locale(country);
        Assertions.assertEquals(expected, actual);
    }
}
