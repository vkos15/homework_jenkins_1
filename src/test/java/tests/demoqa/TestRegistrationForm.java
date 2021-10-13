package tests.demoqa;

import com.github.javafaker.Faker;
import components.CalendarComponent;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import tests.TestBase;

import static io.qameta.allure.Allure.step;

@Tag("test_registration_form")
public class TestRegistrationForm extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    CalendarComponent calendarComponent = new CalendarComponent();
    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String phone = faker.phoneNumber().subscriberNumber(10);
    String address = faker.address().fullAddress();
    String hobby = "Sports";
    String FILE_PHOTO = "image/photo_test.jpg";
    String userGender = "Female";
    String subject = "Math";


    @Test
    void practiceFormTest() {

        registrationPage.openPage();

        registrationPage.typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .chooseGender(userGender)
                .typePhoneNumber(phone);

        calendarComponent.setDate("15", "May", "1992");
        registrationPage.selectSubject(subject);
        registrationPage.selectHobbyes(hobby);

        registrationPage.uploadYourPhoto(FILE_PHOTO);

        registrationPage.typeAddress(address);
        registrationPage.selectState("NCR")
                .selectCity("Delhi");

        registrationPage.submitForm();


        step("Проверяем, что данные сохранились", () -> {
            registrationPage.checkFieldInForm("Student Name", firstName + " " + lastName)
                    .checkFieldInForm("Student Email", email)
                    .checkFieldInForm("Mobile", phone)
                    .checkFieldInForm("Gender", userGender)
                    .checkFieldInForm("Subjects", subject)
                    .checkFieldInForm("Hobbies", hobby)
                    .checkFieldInForm("Picture", FILE_PHOTO)
                    .checkFieldInForm("Address", address);

        });

    }

}
