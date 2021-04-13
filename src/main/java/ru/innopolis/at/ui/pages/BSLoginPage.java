package ru.innopolis.at.ui.pages;

import com.codeborne.selenide.Condition;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Slf4j
public class BSLoginPage extends BSBasePage {

    public BSLoginPage isLoginPage() {
        log.info("Проверяем, что находимся на странице авторизации");
        $(By.className("main-header")).shouldHave(Condition.text("Login"));
        return this;
    }


    public BSLoginPage login(String userName, String password, Boolean correctCredentials) {
        log.info("Авторизуемся в системе с userName = {}, password = {}", userName, password);
        $(By.id("userName")).setValue(userName);
        $(By.id("password")).setValue(password);
        $(By.id("login")).click();
        if (correctCredentials) {
            $(By.id("userName-value")).shouldHave(text(userName));
        }
        return this;
    }


    public BSLoginPage checkErrorMessageAppears() {
        log.info("Проверяем наличие сообщения об ошибке");
        assertEquals($x("//form//div/p").shouldBe(visible).text(), "Invalid username or password!", "Текст сообщения не соответствует ожидаемому");
        return this;
    }

}
