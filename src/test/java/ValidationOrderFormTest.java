import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import pages.CustomerForm;
import pages.MainPage;
import pages.PopupConfirmationForm;
import pages.RentForm;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationOrderFormTest extends AbstractTest {
    //Тестовые данные для позитивного теста
    private final String name = "Мария";
    private final String lastName = "Ларионова";
    private final String address = "Тверская, д.15, кв.30";
    private final String subway = "Тверская";
    private final String phone = "+79123456789";
    private final String expiration = "сутки";
    private final String deliveryDate = LocalDateTime.now().plusDays(1).format(DATE_FORMATTER);
    private final Set<String> color = Set.of("black");
    private final String comment = "позвонить";

    @ParameterizedTest
    @CsvSource({"П, false",
            "Ма, true",
            "Мар, true",
            "Мария, true",
            "Марианнамариан, true",
            "Марианнамарианн, true",
            "Марианнамарианна, false",
            "Марианнамарианнам, false",
            "Марианнамарианнамари, false",
            "Анна-Мария, true",
            "Анна Мария, true",
            "Анна—Мария, false",
            "Maria, false",
            "'', false",
            "Мария1, false",
            "Мария@, false"
    })
    @DisplayName("Валидация поля \"* Имя\"")
    public void checkNameField(String nameT, Boolean isPassed) {
        // объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookiesButton();
        objMainPage.clickOrderButton();

        // объект класса формы заказа "Для кого самокат"
        CustomerForm objCustomerForm = new CustomerForm(driver);
        assertEquals(CustomerForm.HEADER_PAGE, objCustomerForm.orderPageHeader(),
                "Всплывающее окно не с заголовком \"" + CustomerForm.HEADER_PAGE + "\"");
        objCustomerForm.setName(nameT);
        objCustomerForm.setLastName(lastName);
        objCustomerForm.setAddress(address);
        objCustomerForm.setSubway(subway);
        objCustomerForm.setPhone(phone);
        //проверка несуществования/существования сообщения об ошибке
        assertEquals(!isPassed, objCustomerForm.existNameMessage());
        objCustomerForm.clickNextButton();
        // объект класса формы заказа "Для кого самокат"
        RentForm objRentForm = new RentForm(driver);
        //проверка перехода/не перехода на другую форму
        assertEquals(isPassed, RentForm.HEADER_PAGE.equals(objRentForm.orderPageHeader()));
    }

    @ParameterizedTest
    @CsvSource({
            "Л, false",
            "Ла, true",
            "Лар, true",
            "Ларионова, true",
            "Ларионларионов, true",
            "Ларионларионова, true",
            "Ларионовларионов, false",
            "Ларионовларионова, false",
            "Ларионовларионоваларионоваларионова, false",
            "Ма-Ларионова, true",
            "Ма Ларионова, true",
            "Ма—Ларионова, false",
            "Larionova, false",
            "'', false",
            "Ларионова1, false",
            "Ларионова@, false"
    })
    @DisplayName("Валидация поля \"* Фамилия\"")
    public void checkLastNameField(String lastNameT, Boolean isPassed) {
        // объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookiesButton();
        objMainPage.clickOrderButton();

        // объект класса формы заказа "Для кого самокат"
        CustomerForm objCustomerForm = new CustomerForm(driver);
        assertEquals(CustomerForm.HEADER_PAGE, objCustomerForm.orderPageHeader(),
                "Всплывающее окно не с заголовком \"" + CustomerForm.HEADER_PAGE + "\"");
        objCustomerForm.setName(name);
        objCustomerForm.setLastName(lastNameT);
        objCustomerForm.setAddress(address);
        objCustomerForm.setSubway(subway);
        objCustomerForm.setPhone(phone);
        //проверка несуществования/существования сообщения об ошибке
        assertEquals(!isPassed, objCustomerForm.existLastNameMessage());
        objCustomerForm.clickNextButton();
        // объект класса формы заказа "Для кого самокат"
        RentForm objRentForm = new RentForm(driver);
        //проверка перехода/не перехода на другую форму
        assertEquals(isPassed, RentForm.HEADER_PAGE.equals(objRentForm.orderPageHeader()));
    }

    @ParameterizedTest
    @CsvSource({"T, false",
            "Тверь, true",
            "Тверьс, true",
            "Тверская, true",
            "'Центральный проезд Хорошёвского Серебряного Бора,', true",
            "'Центральный проезд Хорошёвского Серебряного Борас,', true",
            "'Центральный проезд Хорошёвского Серебряного Бора, 1', false",
            "'Центральный проезд Хорошёвского Серебряного Бора, 12', false",
            "'Центральный проезд Хорошёвского Серебряного Бора, дом 74, корпус 5 (66 символов)', false",
            "Larionova, false",
            "Тверская-Ямская, true",
            "Тверская улица, true",
            "'', false",
            "Тверская1, true",
            "Тверская@, false",
            "'Тверская улица, 15 — 30', true",
            "'Тверская улица, 15/30', true",
            "Тверская., true",
            "'Тверская,', true",
            "'Тверская ', true",
            "' Тверская', true"
    })
    @DisplayName("Валидация поля \"* Адрес: куда привезти заказ\"")
    public void checkAddressField(String addressT, Boolean isPassed) {
        // объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookiesButton();
        objMainPage.clickOrderButton();

        // объект класса формы заказа "Для кого самокат"
        CustomerForm objCustomerForm = new CustomerForm(driver);
        assertEquals(CustomerForm.HEADER_PAGE, objCustomerForm.orderPageHeader(),
                "Всплывающее окно не с заголовком \"" + CustomerForm.HEADER_PAGE + "\"");
        objCustomerForm.setName(name);
        objCustomerForm.setLastName(lastName);
        objCustomerForm.setAddress(addressT);
        objCustomerForm.setSubway(subway);
        objCustomerForm.setPhone(phone);
        //проверка несуществования/существования сообщения об ошибке
        assertEquals(!isPassed, objCustomerForm.existAddressMessage());
        objCustomerForm.clickNextButton();
        // объект класса формы заказа "Для кого самокат"
        RentForm objRentForm = new RentForm(driver);
        //проверка перехода/не перехода на другую форму
        assertEquals(isPassed, RentForm.HEADER_PAGE.equals(objRentForm.orderPageHeader()));
    }

    @Test
    @DisplayName("Валидация поля \"* Станция метро\"-существующее значение")
    public void checkExistSubwayField() {
        // объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookiesButton();
        objMainPage.clickOrderButton();

        // объект класса формы заказа "Для кого самокат"
        CustomerForm objCustomerForm = new CustomerForm(driver);
        assertEquals(CustomerForm.HEADER_PAGE, objCustomerForm.orderPageHeader(),
                "Всплывающее окно не с заголовком \"" + CustomerForm.HEADER_PAGE + "\"");
        objCustomerForm.setName(name);
        objCustomerForm.setLastName(lastName);
        objCustomerForm.setAddress(address);
        objCustomerForm.setSubway(subway);
        objCustomerForm.setPhone(phone);
        //проверка несуществования сообщения об ошибке
        assertFalse(objCustomerForm.existSubwayMessage());
        objCustomerForm.clickNextButton();
        // объект класса формы заказа "Для кого самокат"
        RentForm objRentForm = new RentForm(driver);
        //проверка перехода на другую форму
        assertEquals(RentForm.HEADER_PAGE, objRentForm.orderPageHeader());
    }

    @Test
    @DisplayName("Валидация поля \"* Станция метро\" - пустое поле")
    public void checkEmptySubwayField() {
        // объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookiesButton();
        objMainPage.clickOrderButton();

        // объект класса формы заказа "Для кого самокат"
        CustomerForm objCustomerForm = new CustomerForm(driver);
        assertEquals(CustomerForm.HEADER_PAGE, objCustomerForm.orderPageHeader(),
                "Всплывающее окно не с заголовком \"" + CustomerForm.HEADER_PAGE + "\"");
        objCustomerForm.setName(name);
        objCustomerForm.setLastName(lastName);
        objCustomerForm.setAddress(address);
        objCustomerForm.setPhone(phone);
        objCustomerForm.clickNextButton();

        //проверка существования сообщения об ошибке
        assertTrue(objCustomerForm.existSubwayMessage());
        // объект класса формы заказа "Для кого самокат"
        RentForm objRentForm = new RentForm(driver);
        //проверка перехода/не перехода на другую форму
        assertNotEquals(RentForm.HEADER_PAGE, objRentForm.orderPageHeader());
    }

    @ParameterizedTest
    @CsvSource({"8, false",
            "89123, false",
            "891234567, false",
            "8912345678, false",
            "89123456789, true",
            "891234567890, false",
            "8912345678901, false",
            "891234567890123456789, false",
            "+, false",
            "+7, false",
            "+7912, false",
            "+791234567, false",
            "+7912345678, false",
            "+79123456789, true",
            "+791234567890, false",
            "+7912345678901, false",
            "qwertyuiopa, false",
            "79123456789, false",
            "+89123456789, false",
            "'', false"
    })
    @DisplayName("Валидация поля \"* Телефон: на него позвонит курьер\"")
    public void checkPhoneField(String phoneT, Boolean isPassed) {
        // объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookiesButton();
        objMainPage.clickOrderButton();

        // объект класса формы заказа "Для кого самокат"
        CustomerForm objCustomerForm = new CustomerForm(driver);
        assertEquals(CustomerForm.HEADER_PAGE, objCustomerForm.orderPageHeader(),
                "Всплывающее окно не с заголовком \"" + CustomerForm.HEADER_PAGE + "\"");
        objCustomerForm.setName(name);
        objCustomerForm.setLastName(lastName);
        objCustomerForm.setAddress(address);
        objCustomerForm.setSubway(subway);
        objCustomerForm.setPhone(phoneT);
        objCustomerForm.changeFocusPhoneField();
        //проверка несуществования/существования сообщения об ошибке
        assertEquals(!isPassed, objCustomerForm.existPhoneMessage());
        objCustomerForm.clickNextButton();
        // объект класса формы заказа "Для кого самокат"
        RentForm objRentForm = new RentForm(driver);
        //проверка перехода/не перехода на другую форму
        assertEquals(isPassed, RentForm.HEADER_PAGE.equals(objRentForm.orderPageHeader()));
    }

    @ParameterizedTest
    @CsvSource({"сутки, true",
            "двое суток, true",
            "трое суток, true",
            "четверо суток, true",
            "пятеро суток, true",
            "шестеро суток, true",
            "семеро суток, true",
            "'', false"
    })
    @DisplayName("Валидация поля \"* Срок аренды\"")
    public void checkExpirationField(String expirationT, Boolean isPassed) {
        // объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookiesButton();
        objMainPage.clickOrderButton();

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
        objRentForm.setExpirationField(expirationT);
        objRentForm.setDeliveryDateField(deliveryDate);
        objRentForm.setColorField(color);
        objRentForm.setCommentField(comment);
        //проверка несуществования/существования сообщения об ошибке
        assertEquals(!isPassed, objRentForm.existExpirationMessage());
        objRentForm.clickOrderEndButton();

        //проверка перехода/не перехода на другую форму
        PopupConfirmationForm objPopupConfirmationForm = new PopupConfirmationForm(driver);
        boolean actual = (objPopupConfirmationForm.orderPageHeader()).contains(PopupConfirmationForm.HEADER_PAGE);
        assertEquals(isPassed, actual);
    }

    //тестовый набор для даты доставки
    private static Stream<Arguments> testDeliveryDateData() {
        return Stream.of(
                Arguments.of(LocalDateTime.now().plusDays(-1).format(DATE_FORMATTER), "false"),
                Arguments.of(LocalDateTime.now().format(DATE_FORMATTER), "false"),
                Arguments.of(LocalDateTime.now().plusDays(1).format(DATE_FORMATTER), "true"),
                Arguments.of(LocalDateTime.now().plusDays(2).format(DATE_FORMATTER), "true"),
                Arguments.of("", "false")
        );
    }

    @ParameterizedTest
    @DisplayName("Валидация поля \"* Когда привезти самокат\"")
    @MethodSource("testDeliveryDateData")
    public void checkDeliveryDateField(String deliveryDateT, Boolean isPassed) {
        // объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookiesButton();
        objMainPage.clickOrderButton();

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
        objRentForm.setDeliveryDateField(deliveryDateT);
        objRentForm.setColorField(color);
        objRentForm.setCommentField(comment);
        //проверка несуществования/существования сообщения об ошибке
        assertEquals(!isPassed, objRentForm.existDeliveryDateMessage());
        objRentForm.clickOrderEndButton();

        //проверка перехода/не перехода на другую форму
        PopupConfirmationForm objPopupConfirmationForm = new PopupConfirmationForm(driver);
        boolean actual = (objPopupConfirmationForm.orderPageHeader()).contains(PopupConfirmationForm.HEADER_PAGE);
        assertEquals(isPassed, actual);
    }

    //тестовый набор для даты доставки
    private static Stream<Arguments> testColorData() {
        return Stream.of(
                Arguments.of(Set.of("black"), "true"),
                Arguments.of(Set.of("grey"), "true"),
                Arguments.of(Set.of("black", "grey"), "true"),
                Arguments.of(Set.of(), "true")
        );
    }

    @ParameterizedTest
    @MethodSource("testColorData")
    @DisplayName("Валидация поля \"Цвет самоката\"")
    public void checkColorField(Set<String> colorT, Boolean isPassed) {
        // объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookiesButton();
        objMainPage.clickOrderButton();

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
        objRentForm.setColorField(colorT);
        objRentForm.setCommentField(comment);
        //проверка несуществования/существования сообщения об ошибке
        assertEquals(!isPassed, objRentForm.existColorMessage());
        objRentForm.clickOrderEndButton();

        //проверка перехода/не перехода на другую форму
        PopupConfirmationForm objPopupConfirmationForm = new PopupConfirmationForm(driver);
        boolean actual = (objPopupConfirmationForm.orderPageHeader()).contains(PopupConfirmationForm.HEADER_PAGE);
        assertEquals(isPassed, actual);
    }

    @ParameterizedTest
    @CsvSource({"'', true",
            "п, true",
            "позвонить, true",
            "позвонить по телефону д, true",
            "позвонить по телефону до, true",
            "позвонить по телефону д, true",
            "позвонить по телефону пер, false",
            "позвонить по телефону пер, false",
            "позвонить по телефону перед тем как звонить по домофону, false",
            "позвонить1, true",
            "позвонить по, true",
            "позвонить—, true",
            "позвонить., true",
            "'позвонить,', true",
            "call me, false",
            "позвонить-, false",
            "позвонить@, false"
    })
    // @CsvSource({"'', false"})
    @DisplayName("Валидация поля \"Комментарий для курьера\"")
    public void checkCommentField(String commentT, Boolean isPassed) {
        // объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);
        objMainPage.clickCookiesButton();
        objMainPage.clickOrderButton();

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
        objRentForm.setCommentField(commentT);
        //проверка несуществования/существования сообщения об ошибке
        assertEquals(!isPassed, objRentForm.existCommentMessage());
        objRentForm.clickOrderEndButton();

        //проверка перехода/не перехода на другую форму
        PopupConfirmationForm objPopupConfirmationForm = new PopupConfirmationForm(driver);
        boolean actual = (objPopupConfirmationForm.orderPageHeader()).contains(PopupConfirmationForm.HEADER_PAGE);
        assertEquals(isPassed, actual);
    }
}
