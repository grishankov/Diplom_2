import Clients.APIClientIngredients;
import Clients.APIClientOrder;
import Clients.APIClientUser;
import Clients.HomePageURL;
import Models.Order;
import Models.OrderParams;
import Models.Token;
import Models.User;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static Models.OrderParams.getIngredients;
import static Models.OrderParams.getRandomUserAuthInfo;

public class OrderTest {
    User user;
    private final APIClientOrder apiClientOrder = new APIClientOrder();
    private final APIClientIngredients apiClientIngredients= new APIClientIngredients();
    private final APIClientUser apiClientUser = new APIClientUser();

    @Before
            public void setUp() {
        getIngredients();
        getRandomUserAuthInfo();
    }

    @Test
    @DisplayName("Make an order with auth")
    @Description("Создание заказа с авторизацией")
    public void makeOrderTest() {
        List<Order> ingredients = getIngredients();
        if (ingredients.size() == 0) {
            Assert.fail("No ingredients to make on order");
        }
        ArrayList<String> orderIngredients = new ArrayList<>();
        orderIngredients.add(ingredients.get(0).getId());
        Token token = getRandomUserAuthInfo();
        apiClientOrder
                .createOrder(new OrderParams(new ArrayList<>()), token)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("success", Matchers.equalTo(true));
    }

    @Test
    @DisplayName("")
    @Description("Создание заказа без авторизации")
    public void makeOrderWithoutAuthTest() {
        List<Order> ingredients = getIngredients();
        if (ingredients.size() == 0) {
            Assert.fail("No ingredients to make on order");
        }
        ArrayList<String> orderIngredients = new ArrayList<>();
        orderIngredients.add(ingredients.get(0).getId());
        apiClientOrder
                .createOrderWithoutAuth(new OrderParams(new ArrayList<>()))
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("success", Matchers.equalTo(true));;
    }
    @Test
    @DisplayName("Make an order with ingredients")
    @Description("Создание заказа с игнредиентами")
    public void makeOrderTestWithIngredients() {
        List<Order> ingredients = getIngredients();
        if (ingredients.size() == 0) {
            Assert.fail("No ingredients to make on order");
        }
        ArrayList<String> orderIngredients = new ArrayList<>();
        orderIngredients.add(ingredients.get(0).getId());
        Token token = getRandomUserAuthInfo();
        apiClientOrder
                .createOrder(new OrderParams(orderIngredients), token)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body("success", Matchers.equalTo(true));
    }
    @Test
    @DisplayName("")
    @Description("Создание заказа без ингредиентов")
    public void makeOrderWithoutIngredients() {
        Token token = getRandomUserAuthInfo();
        apiClientOrder
                .createOrder(new OrderParams(new ArrayList<>()), token)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .and()
                .assertThat()
                .body("success", Matchers.equalTo(false));
    }

    @Test
    @DisplayName("")
    @Description("Создание заказа с неверными ингредиентами")
    public void makeOrderWithWrongIngredients() {
        Token token = getRandomUserAuthInfo();
        ArrayList<String> orderIngredients = new ArrayList<>();
        orderIngredients.add(RandomStringUtils.randomAlphabetic(10));
        apiClientOrder
                .createOrder(new OrderParams(orderIngredients), token)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @After
    public void tearDown() {
        apiClientUser.deleteUserAccount(user);
    }
}
