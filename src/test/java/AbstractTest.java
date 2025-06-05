import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.format.DateTimeFormatter;

abstract public class AbstractTest {
    protected WebDriver driver;
    protected final static String HOST_TEST = "https://qa-scooter.praktikum-services.ru/";
    public static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @BeforeEach
    public void setUp() {
        //драйвер для браузера Chrome
        driver = new ChromeDriver();//FirefoxDriver();
        // переход на страницу тестового приложения
        driver.get(HOST_TEST);
    }

    @AfterEach
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}
