package web.pages;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import com.github.javafaker.Faker;

import java.io.File;
import java.util.Locale;

public class WebPagesRegistrationForm {
    Faker faker = new Faker(new Locale("en"));
    private final String titleText = "Practice Form";
    private final String firstName = faker.name().firstName();
    private final String lastName = faker.name().lastName();
    private final String email = faker.internet().emailAddress();
    private final String number = String.format("%010d", faker.number().numberBetween(0, 10000000000L)); // генерируем 10 значный телефон
    private final String subject = faker.address().country();
    File file = new File("src/test/resources/Face.jpg");

    private final String address = faker.address().streetAddress();

    public void openPage(){
        open("https://demoqa.com/automation-practice-form");
        $(".text-center").shouldHave(text(titleText));
    }
    public void setFirstName() {
        $("#firstName").setValue(firstName);
    }
    public void setLastName() {
        $("#lastName").setValue(lastName);
    }
    public void setEmail() {
        $("#userEmail").setValue(email);
    }
    public void shouldTable() {
        $(".table-responsive").shouldHave(text(firstName), text(lastName), text(email));
    }
    public void clickGenderRadio() {
        $("#gender-radio-1").parent().click();
    }
    public void setNumber() {
        $("#userNumber").setValue(number);
    }
    public void setBirthDay() {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").click();
        $(".react-datepicker__year-select").find(byText("2000")).click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").find(byText("October")).click();
        $(".react-datepicker__month").find(byText("17")).click();
    }
    public void setSubject() {
        $("#subjectsInput").setValue(subject);
    }
    public void setHobbi() {
        $("#hobbies-checkbox-2").parent().click();
    }
    public void uploadPicture() {
        $("#uploadPicture").uploadFile(file);
    }
    public void setAddress() {
        $("#currentAddress").setValue(address);
    }
    public void setStateCity() {
        $("#stateCity-wrapper").find(byText("Select State")).click();
        $("#stateCity-wrapper").find(byText("NCR")).click();
        $("#stateCity-wrapper").find(byText("Select City")).click();
        $("#stateCity-wrapper").find(byText("Noida")).click();
    }
    public void clickSubmit() {
        $("#submit").click();
    }
}
