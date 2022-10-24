import Clients.APIClientUser;
import Clients.HomePageURL;
import Clients.UserRandomaizer;
import Models.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class CreateUserTest extends HomePageURL {
    User user;
    APIClientUser apiClientUser;
    private String token;

    @Before
    public void setUp(){
        this.apiClientUser = new APIClientUser();
        this.user = UserRandomaizer.getRandomUser();
    }

    @After
    public void tearDown(){
    APIClientUser.deleteUserAccount(user);
    }

    @Test
    @DisplayName("Создание пользователя")
    public void registrationUser() {
        ValidatableResponse responseUser = APIClientUser.createUserAccount(user).then();
        token = responseUser.extract().path("accessToken");

        responseUser.assertThat()
                .statusCode(SC_OK);
        assertThat(token, is(not(emptyString())));
    }
}
