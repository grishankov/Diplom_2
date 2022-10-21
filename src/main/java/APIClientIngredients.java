import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIClientIngredients  extends HomePageURL{

    @Step
    public Response getIngredients(){
        return (Response) given()
                .spec(getBaseSpeciafications())
                .when()
                .get("ingredients");
    }
}
