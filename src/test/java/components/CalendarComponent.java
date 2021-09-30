package components;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    @Step("Выбираем дату")
    public void setDate(String day, String month, String year) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText(month);
        $(".react-datepicker__year-select").selectOptionContainingText(year);
        $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
    }
}
