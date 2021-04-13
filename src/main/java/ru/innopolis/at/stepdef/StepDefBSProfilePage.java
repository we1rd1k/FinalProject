package ru.innopolis.at.stepdef;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.innopolis.at.stepdef.StepDefBSBasePage.bookTitle;

@Slf4j
public class StepDefBSProfilePage {

    @And("Проверяем, что находимся на странице профиля")
    public void isProfilePage() {
        log.info("Проверяем, что находимся на странице профиля");
        $(By.className("main-header")).shouldHave(text("Profile"));
    }

    @And("Проверяем, что книга находится в коллекции")
    public void bookIsInCollection() {
        log.info("Проверяем что книга находится в коллекции");
        assertTrue($$x("//div[@role='rowgroup']//div[@role='gridcell']//span/a").shouldBe(CollectionCondition.sizeGreaterThan(0)).stream().map(SelenideElement::text).anyMatch(bookTitle::equals), "Книга отсутствует в списке");
    }

    @And("Удаляем книгу из коллекции")
    public void deleteBookFromCollection() {
        log.info("Удаляем книгу из коллекции");
        $$x("//div[contains(@class, 'profile')]//div[@class='action-buttons']/span/a").findBy(text(bookTitle)).$x("./ancestor::div[@role='row']//div[@class='action-buttons']/span[@title='Delete']").click();
        $(By.className("modal-body")).shouldHave(text("Do you want to delete this book?"));
        $x("//div[@class='modal-footer']/button[text()='OK']").click();
        if (switchTo().alert().getText().equals("Book deleted.")) {
            switchTo().alert().accept();
        }
    }

    @Then("Проверяем, что книга отсутсвует в списке")
    public void checkBookNotInList() {
        assertTrue($$x("//div[@role='rowgroup']//div[@role='gridcell']//span/a").stream().map(SelenideElement::text).noneMatch(bookTitle::equals), "Книга " + bookTitle + " присутствует в списке");
    }

    @Then("Проверяем, что коллекция пустая")
    public void checkCollectionListEmpty() {
        log.info("Проверяем, что коллекция пустая");
        $x("//*[contains(text(),'No rows found')]").shouldBe(visible);
    }
}
