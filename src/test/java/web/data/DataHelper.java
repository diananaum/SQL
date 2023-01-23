package web.data;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private DataHelper(){}

    private static Faker faker = new Faker(new Locale("en"));

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthCode {
        private String id;
        private String user_id;
        private String code;
        private String created;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo randomAuthInfo() {
        var randomLogin = faker.name().username();
        var randomPass = faker.internet().password();
        return new AuthInfo(randomLogin, randomPass);
    }

    public static VerificationCode randomVerificationCode() {
        var randomCode = faker.numerify("#####");
        return new VerificationCode(randomCode);
    }
}