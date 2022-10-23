package Clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class Checks {

    @Step ("Check result as waiting")
    public static void checkWaitingResult (Response response, int answerCode, boolean waitingResult){
        response
                .then()
                .assertThat()
                .statusCode(answerCode)
                .and()
                .body("Success", equalTo(waitingResult));
    }

    @Step ("Check error text as waiting")
    public static void checkWaitingMassage(Response response, int answerCode, boolean waitingResult, String errorMessage) {
        response
                .then()
                .assertThat()
                .statusCode(answerCode)
                .and()
                .body("success", equalTo(waitingResult))
                .and()
                .body("message", equalTo(errorMessage));
    }

    @Step ("Check error text as waiting after user update")
    public static void checkWatingResultAfterUpdate(Response response, int answerCode, boolean waitingResult){
        response
                .then()
                .assertThat()
                .statusCode(answerCode)
                .and()
                .body("success", equalTo(waitingResult));
    }
}
