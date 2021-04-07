package ru.innopolis.at.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.innopolis.at.ui.pages.BSBasePage;

public class BooksListTest extends BaseTest{

    private BSBasePage bsBasePage;


    @BeforeEach
    void beforeTest() {
        bsBasePage = new BSBasePage();
    }

    @DisplayName("Check books list exist")
    @Test
    void booksListTest() {
        bsBasePage.openAppsPage()
                .goToBookStoreApp()
                .isBookStorePage()
                .checkBooksList();
    }
}
