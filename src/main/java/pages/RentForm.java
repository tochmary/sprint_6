package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Set;

//Класс формы "Про аренду"
public class RentForm extends BasePage {
    public final static String HEADER_PAGE = "Про аренду";

    //заголовок формы заказа
    private final By orderHeader = By.className("Order_Header__BZXOb");

    //поле ввода "* Когда привезти самокат"
    private final By deliveryDateField = By.xpath(
            ".//div[@class='Order_Form__17u6u']//input[@placeholder='* Когда привезти самокат']");
    //сообщение о некорректной даты доставки
    private final By deliveryDateMessage = By.xpath(
            ".//div[@class='Order_Form__17u6u']//div[contains(@class, 'Input_Visible___syz6') and text()='Введите корректную дату доставки']");

    //поле ввода "* Срок аренды"
    private final By expirationField = By.xpath(
            ".//div[contains(@class,'Dropdown-root')]");
    //список "* Срок аренды"
    private final By expirationListField = By.className("Dropdown-option");
    //сообщение о некорректного срока аренды
    private final By expirationMessage = By.xpath(
            ".//div[@class='Order_Form__17u6u']//div[contains(@class, 'Input_Visible___syz6') and text()='Введите корректный срок аренды']");

    //чекбокс "Цвет самоката" - черный
    private final By colorBlackField = By.xpath(
            ".//div[contains(@class,'Order_Checkboxes__3lWSI')]//label[@for='black']");
    //чекбокс "Цвет самоката" - серый
    private final By colorGreyField = By.xpath(
            ".//div[contains(@class,'Order_Checkboxes__3lWSI')]//label[@for='grey']");
    private final By colorMessage = By.xpath(
            ".//div[@class='Order_Form__17u6u']//div[contains(@class, 'Input_Visible___syz6') and text()='Введите корректный цвет самоката']");


    //поле ввода "Комментарий для курьера"
    private final By commentField = By.xpath(
            ".//div[@class='Order_Form__17u6u']//input[@placeholder='Комментарий для курьера']");
    private final By commentMessage = By.xpath(
            ".//div[@class='Order_Form__17u6u']//div[contains(@class, 'Input_Visible___syz6') and text()='Введите корректный комментарий']");

    //кнопка "Заказать"
    private final By orderEndButton = By.xpath(
            ".//div[@class = 'Order_Buttons__1xGrp']/button[text()='Заказать']");

    public RentForm(WebDriver driver) {
        super(driver);
    }

    // метод для получения текста заголовка формы заказа
    public String orderPageHeader() {
        return driver.findElement(orderHeader).getText();
    }

    //метод для заполнения поля "* Когда привезти самокат"
    public void setDeliveryDateField(String data) {
        driver.findElement(deliveryDateField).sendKeys(data);
    }

    //метод для выбора "* Срок аренды"
    public void setExpirationField(String expiration) {
        WebElement element = driver.findElement(expirationField);
        new Actions(driver).click(element).perform();
        List<WebElement> list = driver.findElements(expirationListField);
        for (WebElement el : list) {
            if (expiration.equals(el.getText())) {
                el.click();
                break;
            }
        }
    }

    //метод для заполнения поля "Цвет самоката"
    public void setColorField(Set<String> colors) {
        for (String color : colors) {
            switch (color) {
                case "black":
                    driver.findElement(colorBlackField).click();
                    break;
                case "grey":
                    driver.findElement(colorGreyField).click();
                    break;
            }
        }
    }

    //метод для заполнения поля "Комментарий для курьера"
    public void setCommentField(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    //Клик по кнопке "Заказать"
    public void clickOrderEndButton() {
        WebElement element = driver.findElement(orderEndButton);
        if (element.isEnabled()) element.click();
    }

    //существование сообщения о некорректности поля "* Когда привезти самокат"
    public boolean existDeliveryDateMessage() {
        return existMessage(deliveryDateMessage);
    }

    //существование сообщения о некорректности поля "* Срок аренды"
    public boolean existExpirationMessage() {
        return existMessage(expirationMessage);
    }

    //существование сообщения о некорректности поля "Цвет самоката"
    public boolean existColorMessage() {
        return existMessage(colorMessage);
    }

    //существование сообщения о некорректности поля "Комментарий для курьера"
    public boolean existCommentMessage() {
        return existMessage(commentMessage);
    }
}
