import model.Faq;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Проверка текста вопросов и ответов в разделе "Вопросы о важном" на главной странице
public class TextTest extends AbstractTest {

    @ParameterizedTest
    @DisplayName("Проверка текста вопросов и ответов в разделе \"Вопросы о важном\"")
    @CsvFileSource(resources = "/faq.csv", delimiter = ';')
    public void checkTextFAQ(int i, String question, String answer) {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookiesButton();
        Faq faq = objMainPage.getAnswer(i);
        assertEquals(question, faq.getQuestion(), "Неверный вопрос под номером " + i + 1);
        assertEquals(answer, faq.getAnswer(), "Неверный ответ под номером " + i + 1);
    }
}
