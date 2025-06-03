package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//класс всплывающего окно с сообщением об успешном создании заказа "Заказ оформлен"
public class PopupOrderForm {
    protected WebDriver driver;

    public final static String HEADER_PAGE = "Заказ оформлен";
    //кнопка "Да"
    private final By watchButton = By.xpath(
            ".//div[@class='Order_NextButton__1_rCA']//button[text()='Посмотреть статус']");

    //заголовок формы заказа
    private final By orderHeader = By.className("Order_ModalHeader__3FDaJ");

    public PopupOrderForm(WebDriver driver) {
        this.driver = driver;
    }

    // метод для получения текста заголовка формы заказа
    public String orderPageHeader() {
        return driver.findElement(orderHeader).getText();
    }

    //Клик по кнопке "Посмотреть статус"
    public void clickWatchButton() {
        WebElement element = driver.findElement(watchButton);
        if (element.isEnabled()) element.click();
    }
}
