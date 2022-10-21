import Models.Login;
import Models.Token;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class APILoginClient {

    @Step ("Creation object login")
    public static Login creationObjectLogin(String email, String password) {
        return new Login(email,password);
    }

    @Step ("Deserialization User Login")
    public static Token deserializationUserLogin(Response responseLoginUser) {
        return responseLoginUser.as(Token.class);
    }
}
