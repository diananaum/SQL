package web.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import web.data.DataHelper;
import web.data.SqlHelper;
import web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999", LoginPage.class);
    }

    @AfterAll
    static void teardown() {
        SqlHelper.cleanDataBase();
    }

    @Test
    void shouldSuccessfullyLogin() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = new LoginPage()
                .validLogin(authInfo);
        var verificationCode = SqlHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    void shouldThrowExceptionIfWrongLogin() {
        var randomAuthInfo = DataHelper.randomAuthInfo();
        new LoginPage()
                .invalidLogin(randomAuthInfo)
                .errorNotification("Ошибка! Неверно указан логин или пароль");
    }

    @Test
    void shouldThrowExceptionIfWrongVerificationCode() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = new LoginPage()
                .validLogin(authInfo);
        var wrongVerificationCode = DataHelper.randomVerificationCode().getCode();
        verificationPage
                .invalidVerify(wrongVerificationCode)
                .errorNotification("Ошибка! Неверно указан код! Попробуйте ещё раз.");
    }
}