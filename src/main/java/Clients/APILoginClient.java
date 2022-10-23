package Clients;

import Models.Login;
import io.qameta.allure.Step;



public class APILoginClient {

    @Step ("Creation object login")
    public static Login creationObjectLogin(String email, String password) {
        return new Login(email,password);
    }
}
