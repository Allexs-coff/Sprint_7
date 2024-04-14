import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import java.io.File;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class NewOrder {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @Description("Проверяется создание заказа с цветом Black")
    public void createOrderWithColorBlack(){
        File newOrder = new File("src/test/resources/newOrderColorBlack.json");
        Response response = given().header("Content-type", "application/json").and().body(newOrder).post("/api/v1/orders");
        response.then().statusCode(201);
        assertTrue(response.asString().contains("track"));
    }
    @Test
    @Description("Проверяется создание заказа с цветом Gray")
    public void createOrderWithColorGray(){
        File newOrder = new File("src/test/resources/newOrderColorGray.json");
        Response response = given().header("Content-type", "application/json").and().body(newOrder).post("/api/v1/orders");
        response.then().statusCode(201);
        assertTrue(response.asString().contains("track"));
    }

    @Test
    @Description("Проверяется создание заказа с цветами Black и Gray")
    public void createOrderWithColorBlackAndGray(){
        File newOrder = new File("src/test/resources/newOrderColorBlackAndGray.json");
        Response response = given().header("Content-type", "application/json").and().body(newOrder).post("/api/v1/orders");
        response.then().statusCode(201);
        assertTrue(response.asString().contains("track"));
    }

    @Test
    @Description("Проверяется создание заказа с цветами Black и Gray")
    public void createOrderWithoutColor(){
        File newOrder = new File("src/test/resources/newOrderWithoutColor.json");
        Response response = given().header("Content-type", "application/json").and().body(newOrder).post("/api/v1/orders");
        response.then().statusCode(201);
        assertTrue(response.asString().contains("track"));
    }



}
