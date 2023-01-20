package web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id='action-verify']");
    private SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");

    public VerificationPage() {
        codeField.shouldBe(Condition.visible);
    }

    public void verification(String code) {
        codeField.setValue(code);
        verifyButton.click();
    }

    public DashboardPage validVerify(String code) {
        verification(code);
        return new DashboardPage();
    }

    public VerificationPage invalidVerify(String code) {
        verification(code);
        return this;
    }

    public void errorNotification(String expectedText) {
        errorNotification.shouldHave(Condition.text(expectedText)).shouldBe(Condition.visible);
    }
}