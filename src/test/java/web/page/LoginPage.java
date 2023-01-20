package web.page;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginInput = $("[data-test-id=login] input");
    private SelenideElement passwordInput = $("[data-test-id=password] input");
    private SelenideElement loginButton = $("[data-test-id='action-login']");
    private SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");

    public void login(DataHelper.AuthInfo info) {
        loginInput.setValue(info.getLogin());
        passwordInput.setValue(info.getPassword());
        loginButton.click();
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        login(info);
        return new VerificationPage();
    }

    public LoginPage invalidLogin(DataHelper.AuthInfo info) {
        login(info);
        return this;
    }

    public void errorNotification(String expectedText) {
        errorNotification.shouldHave(Condition.text(expectedText)).shouldBe(Condition.visible);
    }
}