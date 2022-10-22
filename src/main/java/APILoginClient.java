import Models.Login;
import Models.Token;
import io.qameta.allure.Step;
import org.openqa.selenium.devtools.v104.network.model.Response;


public class APILoginClient {

    @Step ("Creation object login")
    public static Login creationObjectLogin(String email, String password) {
        return new Login(email,password);
    }
}
