package pages;

import com.codeborne.selenide.SelenideElement;
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

    public ProfilePage openPage() {
        open("/profile");
        return this;
    }

    public void openComponentFromPage() {
        open("/favicon.ico");
    }

    public void deleteCertainBook() {
        deleteButtonSelector.scrollTo().click();
        submitButtonSelector.scrollTo().click();
    }

    public void checkThatTheBookDeletedUI() {
        this.getBookInListSelector().shouldNotBe(visible);
    }

    public void checkThatLoginSucceeded() {
        loginNameSelector.shouldHave(text(System.getProperty("bookStoreLogin", "MaxKon")));
    }

    public void checkThatBookIsAdded() {
        this.getBookInListSelector().should(exist).shouldBe(visible);
    }
}