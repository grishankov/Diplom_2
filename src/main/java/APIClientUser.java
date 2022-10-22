import Models.Login;
import Models.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIClientUser extends HomePageURL {

    @Step("Creation of User")
    public static User creationObjectUser(String email, String password, String name) {
        return new User(email, password, name);
    }

    @Step ("Create a user")
    public static Response createUserAccount(User user){
        return given()
                .spec(getBaseSpeciafications())
                .and()
                .body(user)
                .post(BaseConfigurations.USER_CREATE);
    }

    @Step ("Login a user")
    public static Response loginUserAccount(Login login){
        return given()
                .spec(getBaseSpeciafications())
                .and()
                .body(login)
                .post(BaseConfigurations.USER_LOGIN);
    }

    @Step ("Login a user")
    public static Response deleteUserAccount(String accessToken){
        return given()
                .spec(getBaseSpeciafications())
                .and()
                .header("Authorization", accessToken)
                .delete("/api/auth/user");
    }

    @Step ("Patch user information")
    public static Response patchUserAccount (User user, String accessToken){
        return given()
                .spec(getBaseSpeciafications())
                .and()
                .header("Authorization", accessToken)
                .body(user)
                .patch(BaseConfigurations.USER);
    }
}
