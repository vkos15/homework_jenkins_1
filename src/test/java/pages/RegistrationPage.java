package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationPage {

    private SelenideElement formTitle = $(".practice-form-wrapper");
    private final String FORM_TITLE = "Student Registration Form";
    private final String FORM_TITLE_AFTER_SUBMIT = "Thanks for submitting the form";
    private SelenideElement firstName = $("#firstName");
    private SelenideElement lastName = $("#lastName");
    private SelenideElement email = $("#userEmail");
    private SelenideElement gender_male = $("label[ for='gender-radio-1']");
    private SelenideElement gender_female = $("label[ for='gender-radio-2']");
    private SelenideElement gender_other = $("label[ for='gender-radio-3']");
    private SelenideElement phone_number = $("#userNumber");

    @Step("Открываем форму")
    public void openPage() {
        open("https://demoqa.com/automation-practice-form");
        formTitle.shouldHave(text(FORM_TITLE));
    }

    @Step("Указываем имя")
    public RegistrationPage typeFirstName(String value) {
        firstName.setValue(value);
        return this; //вернули снова страницу
    }

    @Step("Указываем фамилию")
    public RegistrationPage typeLastName(String value) {
        lastName.setValue(value);
        return this;
    }

    @Step("Указываем email")
    public RegistrationPage typeEmail(String email) {
        this.email.setValue(email);
        return this;
    }

    @Step("Выбираем пол")
    public RegistrationPage chooseGender(String usergender) {
        $("#genterWrapper").$(byText(usergender)).click();
        return this;
    }

    @Step("Вводим номер телефона")
    public void typePhoneNumber(String phoneNumber) {
        phone_number.setValue(phoneNumber);
    }

    @Step("Указываем предметы")
    public void selectSubject(String subject) {
        $("#subjectsInput").setValue(subject).pressEnter();
    }

    @Step("Выбираем хобби")
    public void selectHobbyes(String hobby) {
        $("#hobbiesWrapper").$(byText(hobby)).click();
    }

    @Step("Загружаем фото")
    public void uploadYourPhoto(String fileName) {
        $("#uploadPicture").uploadFromClasspath(fileName);
    }

    @Step("Указываем адрес")
    public void typeAddress(String address) {
        $("#currentAddress").setValue(address);
    }

    @Step("Указываем регион")
    public RegistrationPage selectState(String state) {
        $("#state").scrollIntoView(true).click();
        $("#stateCity-wrapper").$(byText(state)).click();
        return this;
    }

    @Step("Указываем город")
    public void selectCity(String city) {
        $("#city").scrollIntoView(true).click();
        $("#stateCity-wrapper").$(byText(city)).click();
    }

    @Step("Сохраняем данные")
    public void submitForm() {
        $("#submit").click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }


    public RegistrationPage checkFieldInForm(String nameOfField, String valueOfField) {
        $x("//td[text()=" + "'" + nameOfField + "'" + "]/following-sibling::td").shouldHave(text(valueOfField));
        return this;
    }


}

