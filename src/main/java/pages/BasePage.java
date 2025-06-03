package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

// Класс базовой страницы
abstract public class BasePage {
    protected WebDriver driver;

    //логотип "Яндекс"
    private final By logoYandex = By.xpath(".//img[@alt='Yandex']");
    //логотип "Самокат"
    private final By logoScooter = By.xpath(".//img[@alt='Scooter']");
    //кнопка "Заказать" наверху справа
    private final By orderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[@class='Button_Button__ra12g']");
    //кнопка "Статус заказа"
    private final By statusOrderButton = By.className("Header_Link__1TAG7");
    //поле ввода "Введите номер заказа"
    private final By orderNumberField = By.xpath(
            ".//div[@class='Input_InputContainer__3NykH']/input[@placeholder='Введите номер заказа']");
    //кнопка "Go" для статуса заказа
    private final By goButton = By.xpath(
            ".//div[@class='Header_SearchInput__3YRIQ']/button[text()='Go!']");

    //кнопка использования кук
    private final By cookieButton = By.xpath(".//button[@class='App_CookieButton__3cvqF']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    //Клик по кнопке "Заказать" сверху
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    //Клик по кнопке использования кук
    public void clickCookiesButton() {
        driver.findElement(cookieButton).click();
    }

    //Клик по логотипу "Яндекс"
    public void clickLogoYandex() {
        driver.findElement(logoYandex).click();
    }

    //Клик по логотипу "Самокат"
    public void clickLogoScooter() {
        driver.findElement(logoScooter).click();
    }

    //Клик по кнопке "Статус заказа"
    public void clickStatusOrderButton() {
        driver.findElement(statusOrderButton).click();
    }

    //метод для заполнения поля "Введите номер заказа"
    public void setOrderNumber(String order) {
        driver.findElement(orderNumberField).sendKeys(order);
    }

    //Клик по кнопке "Go"
    public void clickGoButton() {
        driver.findElement(goButton).click();
    }

    //существование сообщения о некорректности поля
    public boolean existMessage(By field) {
        try {
            driver.findElement(field);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}