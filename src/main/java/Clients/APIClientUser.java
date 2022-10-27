package Clients;

import Models.Token;
import Models.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class APIClientUser extends HomePageURL {

    @Step("Creation object of User")
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
    public static Response loginUserAccount(User user){
        return given()
                .spec(getBaseSpeciafications())
                .and()
                .body(user)
                .post(BaseConfigurations.USER_LOGIN);
    }

    @Step ("Delete a user")
    public void deleteUserAccount(User user) {
        Response response = loginUserAccount(user);
        if (response.statusCode() == HttpStatus.SC_OK) {
            Token authorizationInfo = response.as(Token.class);

            given()
                    .spec(getBaseSpeciafications())
                    .auth().oauth2(authorizationInfo.getAccessToken())
                    .when()
                    .delete("auth/user");
        }
    }


    @Step ("Patch user information")
    public static Response patchUserAccount(Token token){
        return given()
                .spec(getBaseSpeciafications())
                .auth().oauth2(token.getAccessToken())
                .body(token.getUser().toJson())
                .patch(BaseConfigurations.USER);
    }
}
