import clients.APIClientUser;
import clients.APILoginClient;
import io.restassured.response.Response;
import jdk.jfr.Description;
import models.Login;
import models.User;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;

public class UserLoginTest {
    User user;
    Login login;
    APIClientUser apiClientUser;
    APILoginClient apiLoginClient;
    Response response;

    @Before
    public void setUp() {
        apiClientUser = new APIClientUser();
        user = new User();
    }

    @Test
    @DisplayName("Login user normal")
    @Description("Залогин с нормальными данными")
    public void loginUserAccountWithNormalData() {
        User user = User.getRandomUserFull();
        boolean isUserRegistered =
                APIClientUser
                        .createUserAccount(user)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .and()
                        .extract()
                        .body()
                        .path("success");
        if (!isUserRegistered) {
            Assert.fail("Failed to create user for verification.");
            return;
        }
        APIClientUser.loginUserAccount(user)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Login user incorrect")
    @Description("Залогин пользовтеля с неверными данными")
    public void loginUserAccountWithIncorrectData() {
        User user = User.getRandomUserFull();
        boolean isUserRegistered =
                APIClientUser
                        .createUserAccount(user)
                        .then()
                        .statusCode(HttpStatus.SC_OK)
                        .and()
                        .extract().body().path("success");

        if (!isUserRegistered) {
            Assert.fail("Failed to create user for verification.");
            return;
        }
        user.setName(User.getRandomName());
        user.setPassword(User.getRandomPassword());
        APIClientUser.loginUserAccount(user)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .and()
                .assertThat()
                .body("success", equalTo(false));
    }

    @After
    public void deleteUser() {
        apiClientUser.deleteUserAccount(user);
    }
}
