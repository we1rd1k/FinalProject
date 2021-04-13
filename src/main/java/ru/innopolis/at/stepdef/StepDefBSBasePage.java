package ru.innopolis.at.stepdef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import ru.innopolis.at.ui.elements.BSMenu;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class StepDefBSBasePage {

    public static String bookTitle;

    @Then("Проверяем, что находимся на странице магазина книг")
    public void isBookStorePage() {
        log.info("Проверяем, что находимся на странице магазина книг");
        $(By.className("main-header")).shouldHave(text("Book Store"));
    }


    @When("Переходим на страницу авторизации")
    public void goToLoginPage() {
        log.info("Переходим на страницу авторизации");
        $x("//button[.='Login']").click();
        $(By.className("main-header")).shouldHave(text("Login"));
    }

    @And("Проверяем, что количество книг в списке > 0")
    public void checkBooksList() {
        log.info("Проверяем, что количество книг в списке > 0");
        if (!$x("//ul[@class='menu-list']//span[.='Book Store']//parent::li").getAttribute("class").contains("active")) {
            $x("//ul[@class='menu-list']//span[.='Book Store']").click();
        }
        assertTrue($$x("//div[@role='row']//div/span/a").texts().size() > 0, "Книги в списке отсутствуют");
    }

    public void goToPage(BSMenu bsMenu) {
        log.info("Переходим на страницу {}", bsMenu.getPage());
        $x(String.format("//ul[@class='menu-list']//span[.='%s']", bsMenu.getPage())).scrollTo().click();
    }

    @Then("Переходим на страницу Profile")
    public void goToProfilePage() {
        goToPage(BSMenu.PROFILE);
    }

    @Then("Переходим на страницу Book Store")
    public void goToBookStorePage() {
        goToPage(BSMenu.BOOKSTORE);
    }

    @Then("Открываем страницу с детальной информацией о книге")
    public void openBookDetails() {
        log.info("Открываем страницу с детальной информацией о книге");
        String title = $$x("//div[@role='row']//div/span/a").stream().findAny().get().text();
        $$x("//div[@role='row']//div/span/a").findBy(text(title)).click();
        assertEquals(getSelectedBookTitle(), title);
    }

    @And("Получаем заглавие книги")
    public String getSelectedBookTitle() {
        log.info("Получаем заглавие книги");
        return bookTitle = $x("//div[contains(@class, 'profile')]//label[@id='title-label']/../following-sibling::div/label").text();
    }


    @And("Добавляем книгу в коллекцию")
    public void addBookToCollection() {
        log.info("Добавляем книгу в коллекцию");
        $x("//button[@id='addNewRecordButton' and text()='Add To Your Collection']").click();
        if(switchTo().alert().getText().equals("Book added to your collection.")){
            switchTo().alert().accept();
        }
    }
}
