import Clients.APIClientUser;
import Clients.HomePageURL;
import Clients.UserRandomaizer;
import Models.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.devtools.v104.network.model.Response;
import io.restassured.internal.RestAssuredResponseImpl;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


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
        ValidatableResponse responseUser = (ValidatableResponse) APIClientUser.deleteUserAccount(user);
        token = responseUser.extract().path("accessToken");

        responseUser.assertThat()
                .statusCode(SC_OK)
                .extract()
                .path("accessToken");
    }
}
