import Clients.APIClientUser;
import Clients.HomePageURL;
import Clients.UserRandomaizer;
import Models.User;
import io.qameta.allure.Description;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonTypeInfo;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;


public class CreateUserTest extends HomePageURL {
    User user;
    APIClientUser apiClientUser;

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
    @DisplayName("Check courier creation with valid input data")
    @Description("Basic test for /api/auth/user")
    public void createUserTest(){
        ValidatableResponse createUser = (ValidatableResponse) APIClientUser.createUserAccount(user);
        int statusCode = createUser.extract().statusCode();
        Boolean messageSuccess= Boolean.parseBoolean(createUser.extract().path("success").toString());
        Assert.assertEquals("Статус запроса", statusCode, equalTo(200));
    }
}
