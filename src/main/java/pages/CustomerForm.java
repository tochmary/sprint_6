package pages;

import org.openqa.selenium.*;

//Класс формы "Для кого самокат"
public class CustomerForm extends BasePage {
    public final static String HEADER_PAGE = "Для кого самокат";

    //заголовок формы заказа
    private final By orderHeader = By.className("Order_Header__BZXOb");
    //поле ввода * Имя
    private final By nameField = By.xpath(
            ".//div[@class='Order_Form__17u6u']//input[@placeholder='* Имя']");
    //сообщение о некорректном имени
    private final By nameMessage = By.xpath(
            ".//div[@class='Order_Form__17u6u']//div[contains(@class, 'Input_Visible___syz6') and text()='Введите корректное имя']");

    //поле ввода * Фамилия
    private final By lastNameField = By.xpath(
            ".//div[@class='Order_Form__17u6u']//input[@placeholder='* Фамилия']");
    //сообщение о некорректной фамилии
    private final By lastNameMessage = By.xpath(
            ".//div[@class='Order_Form__17u6u']//div[contains(@class, 'Input_Visible___syz6') and text()='Введите корректную фамилию']");

    //поле ввода * Адрес: куда привезти заказ
    private final By addressField = By.xpath(
            ".//div[@class='Order_Form__17u6u']//input[@placeholder='* Адрес: куда привезти заказ']");
    //сообщение о некорректного адреса
    private final By addressMessage = By.xpath(
            ".//div[@class='Order_Form__17u6u']//div[contains(@class, 'Input_Visible___syz6') and text()='Введите корректный адрес']");

    //поле ввода * Станция метро
    private final By subwayField = By.xpath(
            ".//div[@class='Order_Form__17u6u']//input[@placeholder='* Станция метро']");
    private final By subwaySelect = By.xpath(
            ".//div[@class='Order_Form__17u6u']//li[1]/button");
    //сообщение о некорректной станции метро
    private final By subwayMessage = By.xpath(
            ".//div[@class='Order_Form__17u6u']//div[text()='Выберите станцию']");

    //поле ввода * Телефон: на него позвонит курьер
    private final By phoneField = By.xpath(
            ".//div[@class='Order_Form__17u6u']//input[@placeholder='* Телефон: на него позвонит курьер']");
    //сообщение о некорректной фамилии
    private final By phoneMessage = By.xpath(
            ".//div[@class='Order_Form__17u6u']//div[contains(@class, 'Input_Visible___syz6') and text()='Введите корректный номер']");

    //кнопка "Далее"
    private final By nextButton = By.xpath(
            ".//div[@class='Order_NextButton__1_rCA']//button[text()='Далее']");

    public CustomerForm(WebDriver driver) {
        super(driver);
    }

    // метод для получения текста заголовка формы заказа
    public String orderPageHeader() {
        return driver.findElement(orderHeader).getText();
    }

    //метод для заполнения поля "* Имя"
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    //метод для заполнения поля "* Фамилия"
    public void setLastName(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    //метод для заполнения поля "* Адрес: куда привезти заказ"
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    //метод для заполнения поля "* Станция метро"
    public void setSubway(String subway) {
        WebElement input = driver.findElement(subwayField);
        input.sendKeys(subway);
        driver.findElement(subwaySelect).click();
    }

    //метод для заполнения поля "* Телефон: на него позвонит курьер"
    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    //клик вне поля "* Телефон: на него позвонит курьер"
    public void changeFocusPhoneField() {
        driver.findElement(phoneField).sendKeys(Keys.TAB);
    }

    //Клик по кнопке "Далее"
    public void clickNextButton() {
        WebElement element = driver.findElement(nextButton);
        if (element.isEnabled()) element.click();
    }

    //существование сообщения о некорректности поля "* Имя"
    public boolean existNameMessage() {
        return existMessage(nameMessage);
    }

    //существование сообщения о некорректности поля "* Фамилия"
    public boolean existLastNameMessage() {
        return existMessage(lastNameMessage);
    }

    //существование сообщения о некорректности поля "* Адрес: куда привезти заказ"
    public boolean existAddressMessage() {
        return existMessage(addressMessage);
    }

    //существование сообщения о некорректности поля "* Телефон: на него позвонит курьер"
    public boolean existPhoneMessage() {
        return existMessage(phoneMessage);
    }

    //существование сообщения о некорректности поля "* Станция метро"
    public boolean existSubwayMessage() {
        return existMessage(subwayMessage);
    }
}
