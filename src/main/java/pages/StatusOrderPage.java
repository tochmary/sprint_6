package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

// Класс страницы с информацией о заказе
public class StatusOrderPage extends BasePage {

    //поле ввода "Введите номер заказа"
    private final By orderNumberField = By.xpath(
            ".//div[@class='Track_Form__N4FE3']//input[@type='text']");

    //блок с информацией о заказе
    private final By infoOrder = By.className("Track_OrderInfo__2fpDL");

    public StatusOrderPage(WebDriver driver) {
        super(driver);
    }

    //существование информации о заказе
    public boolean existInfoOrder() {
        try {
            driver.findElement(infoOrder);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    //метод получения номера заказа из поля
    public String getNumberOrder() {
        return driver.findElement(orderNumberField).getDomAttribute("value");
    }
}
