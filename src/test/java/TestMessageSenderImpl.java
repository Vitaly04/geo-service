import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class TestMessageSenderImpl {

    @BeforeClass
    public GeoService setGeoService() {
        return new GeoServiceImpl();
    }

    @BeforeClass
    public LocalizationService setLocalizationService() {
        return new LocalizationServiceImpl();
    }

    @Test
    public void testMessageSenderImpl_should_send_rusText() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.123.12.20")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.20");
        String actual = messageSender.send(headers);

        MessageSender messageSender1 = new MessageSenderImpl(setGeoService(), setLocalizationService());
        Map<String, String> headers1 = new HashMap<String, String>();
        headers1.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.21");
        String expected = messageSender1.send(headers1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testMessageSenderImpl_should_send_usaText() {
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("96.44.183.149")).thenReturn(new Location("New York", Country.USA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        String actual = messageSender.send(headers);

        MessageSender messageSender1 = new MessageSenderImpl(setGeoService(), setLocalizationService());
        Map<String, String> headers1 = new HashMap<String, String>();
        headers1.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        String expected = messageSender1.send(headers1);
        Assertions.assertEquals(expected, actual);
    }
}
