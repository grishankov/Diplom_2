package models;

import clients.APIClientIngredients;
import clients.APIClientUser;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class OrderParams {
    private final ArrayList<String> ingredients;
    static APIClientUser apiClientUser;
    static APIClientIngredients apiClientIngredients;
    Response response;
    static Object registerToken;


    public OrderParams(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String toJson() {
        return new GsonBuilder().serializeNulls().create().toJson(this);
    }

    public static List<Order> getIngredients() {
        return apiClientIngredients
                .getIngredients().then().extract().body().jsonPath()
                .getList("data", Order.class);
    }

    public static Token getRandomUserAuthInfo() {
        User user = User.getRandomUserFull();
        return APIClientUser
                .createUserAccount(user)
                .body()
                .path("accessToken");
    }
}
