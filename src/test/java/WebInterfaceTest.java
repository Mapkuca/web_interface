import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebInterfaceTest {

    @BeforeEach
    void openUrl() {
        open("http://localhost:9999");
    }

    @Test
    void correctForm() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Василькова-Иванова Маргарита");
        form.$("[data-test-id=phone] input").setValue("+71234567890");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void emptyForm() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("");
        form.$(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void wrongNameInForm() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Wасилькова-Иванова Маргарита");
        form.$("[data-test-id=phone] input").setValue("+71234567890");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void emptyNameInForm() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+71234567890");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void wrongPhoneNumberInForm() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Василькова-Иванова Маргарита");
        form.$("[data-test-id=phone] input").setValue("234567890");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void EmptyPhoneNumberInForm() {
        SelenideElement form = $("form");
        form.$("[data-test-id=name] input").setValue("Василькова-Иванова Маргарита");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

}
