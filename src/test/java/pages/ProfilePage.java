package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Setter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    @Setter
    private String isbn;

    private final SelenideElement deleteButtonSelector = $("#delete-record-undefined");
    private final SelenideElement submitButtonSelector = $("#closeSmallModal-ok");
    private final SelenideElement loginNameSelector = $("#userName-value");

    private SelenideElement getBookInListSelector() {
        String bookInList = String.format("a[href='/profile?book=%s']", isbn);
        return $(bookInList);
    }

    public void openComponentFromPage() {
        open("/favicon.ico");
    }

    @Step("Удалить книгу")
    public void deleteCertainBook() {
        deleteButtonSelector.scrollTo().click();
        submitButtonSelector.scrollTo().click();
    }

    @Step("Проверить, что книга удалена, через UI")
    public void checkThatTheBookDeleted() {
        this.getBookInListSelector().shouldNotBe(visible);
    }

    @Step("Открытие страницы авторизованный пользователем")
    public void openPageWithAuthorization() {
        open("/profile");
        loginNameSelector.shouldHave(text(System.getProperty("bookStoreLogin", "MaxKon")));
    }

    @Step("Проверить наличие книги")
    public void checkThatBookIsAdded() {
        this.getBookInListSelector().should(exist).shouldBe(visible);
    }
}