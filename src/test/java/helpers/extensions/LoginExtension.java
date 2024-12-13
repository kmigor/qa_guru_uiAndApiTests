package helpers.extensions;

import api.AccountApi;
import org.openqa.selenium.Cookie;
import models.LoginResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import pages.ProfilePage;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class LoginExtension implements BeforeEachCallback {
    public static LoginResponseModel cookies;
    AccountApi accountApi = new AccountApi();
    ProfilePage profilePage = new ProfilePage();

    @Override
    public void beforeEach(ExtensionContext context) {
        cookies = accountApi.getAuthorizationCookie();

        step("Добавление полученных из ответа cookie к текущему браузеру, " +
                "для проверки успешного входа в учетную запись", () -> {
            profilePage.openComponentFromPage();
            getWebDriver().manage().addCookie(new Cookie("userID", cookies.getUserId()));
            getWebDriver().manage().addCookie(new Cookie("expires", cookies.getExpires()));
            getWebDriver().manage().addCookie(new Cookie("token", cookies.getToken()));
        });
    }
}