package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

//класс всплывающего окно с сообщением о подтверждении заказа "Хотите оформить заказ?"
public class PopupConfirmationForm {
    protected WebDriver driver;

    public final static String HEADER_PAGE = "Хотите оформить заказ?";
    //кнопка "Да"
    private final By yesButton = By.xpath(
            ".//div[@class='Order_Buttons__1xGrp']//button[text()='Да']");

    //заголовок формы заказа
    private final By orderHeader = By.className("Order_ModalHeader__3FDaJ");

    public PopupConfirmationForm(WebDriver driver) {
        this.driver = driver;
    }

    public void waitDownLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(orderHeader));
    }

    // метод для получения текста заголовка формы заказа
    public String orderPageHeader() {
        return driver.findElement(orderHeader).getText();
    }

    //Клик по кнопке "Да"
    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }
}
