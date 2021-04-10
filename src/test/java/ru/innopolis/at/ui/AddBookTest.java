package ru.innopolis.at.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.innopolis.at.ui.pages.BSBasePage;

public class AddBookTest extends BaseTest {

    private BSBasePage bsBasePage;


    @BeforeEach
    void beforeTest() {
        bsBasePage = new BSBasePage();
    }

    @DisplayName("Add book to collection")
    @CsvSource({"Test, Q!w2e3r4t5y6"})
    @ParameterizedTest(name = "{displayName}, login: {0}, password: {1}")
    void addBookTest(String login, String password) {
        bsBasePage.openAppsPage()
                .goToBookStoreApp()
                .isBookStorePage()
                .goToLoginPage()
                .isLoginPage()
                .login(login, password, true)
                .goToBookStorePage()
                .openBookDetails();
        String bookName = bsBasePage.getSelectedBookTitle();
        bsBasePage.addBookToCollection()
                .goToProfilePage()
                .isProfilePage()
                .bookIsInCollection(bookName);
    }

    @DisplayName("Delete book from collection")
    @CsvSource({"Test, Q!w2e3r4t5y6"})
    @ParameterizedTest(name = "{displayName}, login: {0}, password: {1}")
    void deleteBookTest(String login, String password) {
        bsBasePage.openAppsPage()
                .goToBookStoreApp()
                .isBookStorePage()
                .goToLoginPage()
                .isLoginPage()
                .login(login, password, true)
                .goToBookStorePage()
                .openBookDetails();
        String bookName = bsBasePage.getSelectedBookTitle();
        bsBasePage.addBookToCollection()
                .goToProfilePage()
                .isProfilePage()
                .bookIsInCollection(bookName)
                .deleteBookFromCollection(bookName)
                .checkCollectionListEmpty();
    }
}
