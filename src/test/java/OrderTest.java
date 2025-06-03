import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderTest extends AbstractTest {

    //тестовый набор
    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(1, "Мария", "Ларионова", "Тверская, д.15, кв.30", "Тверская", "+79123456789",
                        "сутки", LocalDateTime.now().plusDays(1).format(DATE_FORMATTER), Set.of("black"), "позвонить"),
                Arguments.of(2, "Марианна", "Точкасова", "Казанская, д.15, кв.30", "Полянка", "+79123456781",
                        "двое суток", LocalDateTime.now().plusDays(10).format(DATE_FORMATTER), Set.of("grey"), "не звонить")

        );
    }

    @ParameterizedTest
    @DisplayName("Оформление заказа")
    @MethodSource("testData")
    public void checkMakeOrder(Integer button,
                                 String name,
                                 String lastName,
                                 String address,
                                 String subway,
                                 String phone,
                                 String expiration,
                                 String deliveryDate,
                                 Set<String> color,
                                 String comment) {
        // объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookiesButton();
        switch (button) {
            case 1:
                objMainPage.clickOrderButton();
                break;
            case 2:
                objMainPage.clickOrderDownButton();
        }

        // объект класса формы заказа "Для кого самокат"
        CustomerForm objCustomerForm = new CustomerForm(driver);
        assertEquals(CustomerForm.HEADER_PAGE, objCustomerForm.orderPageHeader(),
                "Всплывающее окно не с заголовком \"" + CustomerForm.HEADER_PAGE + "\"");
        objCustomerForm.setName(name);
        objCustomerForm.setLastName(lastName);
        objCustomerForm.setAddress(address);
        objCustomerForm.setSubway(subway);
        objCustomerForm.setPhone(phone);
        objCustomerForm.clickNextButton();

        // объект класса формы заказа "Для кого самокат"
        RentForm objRentForm = new RentForm(driver);
        assertEquals(RentForm.HEADER_PAGE, objRentForm.orderPageHeader(),
                "Всплывающее окно не с заголовком \"" + RentForm.HEADER_PAGE + "\"");
        objRentForm.setExpirationField(expiration);
        objRentForm.setDeliveryDateField(deliveryDate);
        objRentForm.setColorField(color);
        objRentForm.setCommentField(comment);
        objRentForm.clickOrderEndButton();

        //Объект класса формы заказа "Хотите оформить заказ?"
        //Такого этапа не должно быть, но в задании тесты должны пройти!!!
        PopupConfirmationForm objPopupConfirmationForm = new PopupConfirmationForm(driver);
        objPopupConfirmationForm.waitDownLoad();
        assertThat(objPopupConfirmationForm.orderPageHeader()).contains(PopupConfirmationForm.HEADER_PAGE);
        objPopupConfirmationForm.clickYesButton();

        // объект класса формы заказа "Заказ оформлен"
        PopupOrderForm objPopupOrderForm = new PopupOrderForm(driver);
        assertThat(objPopupOrderForm.orderPageHeader()).contains(PopupOrderForm.HEADER_PAGE);
        objPopupOrderForm.clickWatchButton();
    }
}
