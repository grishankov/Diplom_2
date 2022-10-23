package Clients;

import Models.Order;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIClientOrder extends HomePageURL{
    @Step ("Create an order")
    public Response createOrder (String json) {
        return given()
                .spec(getBaseSpeciafications())
                .and()
                .body(json)
                .post(BaseConfigurations.ORDERS);
    }

    @Step ("Get user orders")
    public Response getUserOrders (String accessToken) {
        return given()
                .spec(getBaseSpeciafications())
                .and()
                .header("Authorization", accessToken)
                .get(BaseConfigurations.ORDERS);
    }

    @Step ("Creation object order")
    public static Order createObjectOrder(String[] ingredients){
        return new Order(ingredients);
    }

    @Step ("Checking answer code with invalid hash")
    public static void checkAnswerCodeInvalidHash(Response response, int answerCode){
        response
                .then()
                .assertThat()
                .statusCode(answerCode);
    }
}
