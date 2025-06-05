package pages;

import model.Faq;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Класс главной страницы
public class MainPage extends BasePage {

    //кнопка "Заказать" внизу посередине
    private final By orderDownButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    //раздел "Вопросы о важном"
    private final By headerFAQSection = By.xpath(".//div[@class='Home_FourPart__1uthg']/div[@class='Home_SubHeader__zwi_E']");
    //вопрос FAQ
    private String questionPath = ".//div[@class='Home_FAQ__3uVm4']//div[@role='button' and @id='accordion__heading-%d'] ";
    //ответ FAQ
    private String answerPath = ".//div[@class='Home_FAQ__3uVm4']//div[@aria-labelledby='accordion__heading-%d'] ";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    //Клик по кнопке "Заказать" внизу
    public void clickOrderDownButton() {
        WebElement element = driver.findElement(orderDownButton);
        // Прокрутить страницу до этого элемента
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    // метод для получения ответа на вопрос
    public Faq getAnswer(int i) {
        WebElement section = driver.findElement(headerFAQSection);
        // Прокрутить страницу до этого элемента
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", section);
        WebElement question = driver.findElement(By.xpath(String.format(questionPath, i)));
        String questionText = question.getText();
        question.click();
        String answerText = driver.findElement(By.xpath(String.format(answerPath, i))).getText();
        return new Faq(questionText, answerText);
    }
}
