package ru.innopolis.at.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.innopolis.at.ui.pages.BSBasePage;

public class LoginTest extends BaseTest{

    private BSBasePage bsBasePage;


    @BeforeEach
    void beforeTest() {
        bsBasePage = new BSBasePage();
    }

    @DisplayName("Login with correct credentials")
    @CsvSource({"Test, Q!w2e3r4t5y6"})
    @ParameterizedTest(name="{displayName}, login: {0}, password: {1}")
    void successfulLoginTest(String login, String password) {
        bsBasePage.openAppsPage()
                .goToBookStoreApp()
                .isBookStorePage()
                .goToLoginPage()
                .isLoginPage()
                .login(login, password, true);
    }

    @DisplayName("Login with incorrect credentials")
    @CsvSource({"asd, asf"})
    @ParameterizedTest(name="{displayName}, login: {0}, password: {1}")
    void failedLoginTest(String login, String password) {
        bsBasePage.openAppsPage()
                .goToBookStoreApp()
                .isBookStorePage()
                .goToLoginPage()
                .isLoginPage()
                .login(login, password, false)
                .checkErrorMessageAppears();
    }

}
