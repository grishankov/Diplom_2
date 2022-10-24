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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class CreateUserTest extends HomePageURL {
    User user;
    APIClientUser apiClientUser;
    private String token;

    @BeforeEach
    public void setUp(){
        this.apiClientUser = new APIClientUser();
        this.user = UserRandomaizer.getRandomUser();
    }

    @AfterEach
    public void tearDown(){
    APIClientUser.deleteUserAccount(user);
    }

    @Test
    @DisplayName("Create new user")
    public void registrationUser() {
        ValidatableResponse responseUser = APIClientUser.createUserAccount(user).then();
        token = responseUser.extract().path("accessToken");

        responseUser.assertThat()
                .statusCode(SC_OK);
        assertThat(token, is(not(emptyString())));
    }

    @Test
    @DisplayName("Create new user with exist data")
    public void registrationUserTwice() {
        ValidatableResponse responseUser = APIClientUser.createUserAccount(user).then();
        token = responseUser.extract().path("accessToken");
        responseUser.assertThat()
                .statusCode(SC_OK);
        assertThat(token, is(not(emptyString())));
        ValidatableResponse responseUser2 = APIClientUser.createUserAccount(user).then();
        responseUser2.assertThat()
                .statusCode(SC_FORBIDDEN);
    }
}
