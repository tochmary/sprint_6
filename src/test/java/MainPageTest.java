import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import pages.StatusOrderPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

//Проверка элементов на главной странице
public class MainPageTest extends AbstractTest {
    MainPage objMainPage;

    @BeforeEach
    public void beforeEach() {
        // объект класса главной страницы
        objMainPage = new MainPage(driver);
        objMainPage.clickCookiesButton();
    }

    @Test
    @DisplayName("Нажатие на логотип \"Самоката\"")
    public void checkClickLogoScooter() {
        objMainPage.clickLogoScooter();
        assertEquals(HOST_TEST, driver.getCurrentUrl(), "Открылась не главная страница \"Самоката\"");
    }

    @Test
    @DisplayName("Нажатие на логотип \"Яндекса\"")
    public void checkClickLogoYandex() {
        objMainPage.clickLogoYandex();

        // Переключение на новую вкладку
        String currentHandle = driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        assertEquals("https://ya.ru/", driver.getCurrentUrl(), "Открылась не главная страница \"Яндекса\"");
    }

    @Test
    @DisplayName("Проверка статуса несуществующего заказа со значением 0")
    public void checkStatusNotExistOrder() {
        objMainPage.clickStatusOrderButton();
        String number = "0";
        objMainPage.setOrderNumber(number);
        objMainPage.clickGoButton();

        // объект класса страницы о заказе
        StatusOrderPage objStatusOrderPage = new StatusOrderPage(driver);
        assertEquals(number, objStatusOrderPage.getNumberOrder(), "Номер заказа должен быть " + number);
        assertFalse(objStatusOrderPage.existInfoOrder(), "Информации о заказе есть");
    }
}
