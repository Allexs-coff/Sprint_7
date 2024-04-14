import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;


public class OrderList {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @Description("Проверяется, что в ответе ручки списка заказов пришёл список заказов")
    public void getOrderListTest(){

        Response response = given().header("Content-type", "application/json").and().get("/api/v1/orders");
        System.out.println(response.body().asString());
        response.then().assertThat().body("orders",notNullValue()).and().statusCode(200);
    }

}
