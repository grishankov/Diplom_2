package clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.Order;
import models.OrderParams;
import models.Token;

import static io.restassured.RestAssured.given;

public class APIClientOrder extends HomePageURL {
    @Step("Create an order")
    public Response createOrder(OrderParams orderParams, Token token) {
        return given()
                .spec(getBaseSpeciafications())
                .auth().oauth2(token.getAccessToken())
                .body(orderParams.toJson())
                .when()
                .post(BaseConfigurations.ORDERS);
    }

    @Step("Create an order w/o auth")
    public Response createOrderWithoutAuth(OrderParams orderParams) {
        return given()
                .spec(getBaseSpeciafications())
                .body(orderParams.toJson())
                .when()
                .post(BaseConfigurations.ORDERS);
    }

    @Step("Get user orders")
    public Response getUserOrders(String accessToken) {
        return given()
                .spec(getBaseSpeciafications())
                .and()
                .header("Authorization", accessToken)
                .get(BaseConfigurations.ORDERS);
    }

    @Step("Creation object order")
    public static Order createObjectOrder(String[] ingredients) {
        return new Order(ingredients);
    }

    @Step("Checking answer code with invalid hash")
    public static void checkAnswerCodeInvalidHash(Response response, int answerCode) {
        response
                .then()
                .assertThat()
                .statusCode(answerCode);
    }
}
