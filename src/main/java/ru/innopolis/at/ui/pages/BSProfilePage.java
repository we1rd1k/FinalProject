package ru.innopolis.at.ui.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class BSProfilePage extends BSBasePage {

    public BSProfilePage isProfilePage() {
        log.info("Проверяем, что находимся на странице профиля");
        $(By.className("main-header")).shouldHave(text("Profile"));
        return this;
    }

    public BSProfilePage bookIsInCollection(String bookName) {
        assertEquals(bookName, $x("//div[contains(@class, 'profile')]//div[@class='action-buttons']/span/a").text(), "Книга отсутствует в списке");
        return this;
    }

    public BSProfilePage deleteAllBooks() {
        log.info("Удаляем все книги из коллекции");
        $x("//div[contains(@class, 'text')]//button[text()='Delete All Books']").click();
        $x("//div[@class = 'modal-footer']/button[text()='OK']").click();
        if (switchTo().alert().getText().equals("All Books deleted.")) {
            switchTo().alert().accept();
        }
        return this;
    }

    public BSProfilePage deleteBookFromCollection(String bookName) {
        log.info("Удаляем книгу из коллекции");
        $$x("//div[contains(@class, 'profile')]//div[@class='action-buttons']/span/a").findBy(text(bookName)).$x("./ancestor::div[@role='row']//div[@class='action-buttons']/span[@title='Delete']").click();
        $(By.className("modal-body")).shouldHave(text("Do you want to delete this book?"));
        $x("//div[@class='modal-footer']/button[text()='OK']").click();
        if (switchTo().alert().getText().equals("Book deleted.")) {
            switchTo().alert().accept();
        }
        return this;
    }

    public BSProfilePage checkCollectionListEmpty() {
        log.info("Проверяем, что коллекция пустая");
        $x("//*[contains(text(),'No rows found')]").shouldBe(visible);
        return this;
    }
}
