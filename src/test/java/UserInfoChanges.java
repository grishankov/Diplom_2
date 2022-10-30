import clients.APIClientUser;
import jdk.jfr.Description;
import models.Token;
import models.User;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class UserInfoChanges {
    APIClientUser apiClientUser;
    Token token;

    @Before
    public void setUp() {
        APIClientUser apiClientUser = new APIClientUser();
    }

    @Test
    @DisplayName("Change user email")
    @Description("Замена пользовательской почты с авторизацией")
    public void changeUserEmailTest() {
        User user = User.getRandomUserFull();
        APIClientUser.createUserAccount(user)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("success", equalTo(true));
        user.setEmail(User.getRandomEmail());
        token.setUser(user);
        APIClientUser
                .patchUserAccount(token)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Change user name")
    @Description("Замена пользовательского имени с авторизацией")
    public void changeUserNameTest() {
        User user = User.getRandomUserFull();
        APIClientUser.createUserAccount(user)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("success", equalTo(true));
        user.setEmail(User.getRandomName());
        token.setUser(user);
        APIClientUser
                .patchUserAccount(token)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Change user password.")
    @Description("Замена пользовательского пароля с авторизацией")
    public void changeUserPasswordTest() {
        User user = User.getRandomUserFull();
        APIClientUser.createUserAccount(user)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("success", equalTo(true));
        user.setEmail(User.getRandomPassword());
        token.setUser(user);
        APIClientUser
                .patchUserAccount(token)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Change user email w/o auth")
    @Description("Замена пользовательской почты без авторизации")
    public void changeUserEmailWithoutAuthTest() {
        User user = User.getRandomUserFull();
        APIClientUser.createUserAccount(user)
                .as(Token.class);
        user.setEmail(User.getRandomEmail());
        APIClientUser
                .patchUserAccount(token)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .assertThat()
                .body("success", equalTo(false));
    }

    @Test
    @DisplayName("Change user email w/o auth")
    @Description("Замена пользовательского имени без авторизации")
    public void changeUserNameWithoutAuthTest() {
        User user = User.getRandomUserFull();
        APIClientUser.createUserAccount(user)
                .as(Token.class);
        user.setEmail(User.getRandomName());
        APIClientUser
                .patchUserAccount(token)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .assertThat()
                .body("success", equalTo(false));
    }

    @Test
    @DisplayName("Change user email w/o auth")
    @Description("Замена пользовательского пароля без авторизации")
    public void changeUserPasswordWithoutAuthTest() {
        User user = User.getRandomUserFull();
        APIClientUser.createUserAccount(user)
                .as(Token.class);
        user.setEmail(User.getRandomPassword());
        APIClientUser
                .patchUserAccount(token)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .assertThat()
                .body("success", equalTo(false));
    }
}
