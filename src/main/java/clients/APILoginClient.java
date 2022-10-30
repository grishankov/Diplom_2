package clients;

import io.qameta.allure.Step;
import models.Login;


public class APILoginClient {

    @Step("Creation object login")
    public static Login creationObjectLogin(String email, String password) {
        return new Login(email, password);
    }
}
