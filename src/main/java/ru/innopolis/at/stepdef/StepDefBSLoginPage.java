package ru.innopolis.at.stepdef;

import com.codeborne.selenide.Condition;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class StepDefBSLoginPage {

    @Then("Проверяем, что находимся на странице авторизации")
    public void isLoginPage() {
        log.info("Проверяем, что находимся на странице авторизации");
        $(By.className("main-header")).shouldHave(Condition.text("Login"));
    }


//    @And("userName {string}, password {string}, correctCredentials true")
    private void login(String userName, String password, Boolean correctCredentials) {
        log.info("Авторизуемся в систему с userName = {}, password = {}", userName, password);
        $(By.id("userName")).setValue(userName);
        $(By.id("password")).setValue(password);
        $(By.id("login")).click();
        if (correctCredentials) {
            $(By.id("userName-value")).shouldHave(text(userName));
        }
    }

    @And("Авторизуемся в систему с userName {string}, password {string}, credentials = true")
    public void loginCorrect(String userName, String password) {
        login(userName, password, true);
    }

    @And("Авторизуемся в систему с userName {string}, password {string}, credentials = false")
    public void wrongCorrect(String userName, String password) {
        login(userName, password, false);
    }

    @And("Проверяем наличие сообщения об ошибке: Invalid username or password!")
    public void checkErrorMessageAppears() {
        log.info("Проверяем наличие сообщения об ошибке");
        assertEquals($x("//form//div/p").shouldBe(visible).text(), "Invalid username or password!", "Текст сообщения не соответствует ожидаемому");
    }
}
