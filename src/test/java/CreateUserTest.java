import Clients.APIClientUser;
import Clients.HomePageURL;
import Models.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;


public class CreateUserTest extends HomePageURL {
    User user;
    APIClientUser apiClientUser;

    @Before
    public void setUp() {
        this.apiClientUser = new APIClientUser();
    }

    @Test
    @DisplayName("Create new user")
    @Description("Тест на создание нового юзера")
    public void registrationUser() {
        user = new User(User.getRandomEmail(), User.getRandomPassword(), User.getRandomName());
        APIClientUser.createUserAccount(user)
                .then().assertThat().statusCode(HttpStatus.SC_OK)
                .and().assertThat().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Create new user with exist data")
    @Description("Тест на создания юзера дважды")
    public void registrationUserTwice() {
        user = new User(User.getRandomEmail(), User.getRandomPassword(), User.getRandomName());
        boolean isUserRegistered =
                APIClientUser
                        .createUserAccount(user)
                        .then().statusCode(HttpStatus.SC_OK)
                        .and().extract().body().path("success");
        if (!isUserRegistered) {
            Assert.fail("Failed to create user for verification.");
        }
    }

    @Test
    @DisplayName("Create new user with no data")
    @Description("Тест на создание юзера без одного из обязательных параметров")
    public void registrationUserWithNoName() {
        user = new User(User.getRandomEmail(), User.getRandomPassword(), User.getRandomName());
        user.setName(null);

        APIClientUser
                .createUserAccount(user)
                .then().assertThat().statusCode(HttpStatus.SC_FORBIDDEN)
                .and().assertThat().body("success", equalTo(false));
    }

    @After
    public void tearDown() {
        apiClientUser.deleteUserAccount(user);
    }
}
