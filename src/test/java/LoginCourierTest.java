import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import java.io.File;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class LoginCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @Description("Проверяется авторизация курьера")
    public void authorizationCourierTest(){
        File newCourier = new File("src/test/resources/newCourier.json");
        Response response = given().header("Content-type", "application/json").and().body(newCourier).post("/api/v1/courier");
        File loginCourier = new File("src/test/resources/loginCourier.json");
        response = given().header("Content-type", "application/json").and().body(loginCourier).post("/api/v1/courier/login");
        response.then().statusCode(200);
        assertTrue(response.asString().contains("id"));
    }

    @Test
    @Description("Проверяется авторизация без логина")
    public void authorizationCourierWithoutLoginTest(){
        File loginCourier = new File("src/test/resources/loginCourierWithoutLogin.json");
        Response response = given().header("Content-type", "application/json").and().body(loginCourier).post("/api/v1/courier/login");
        response.then().statusCode(400);
    }

    @Test
    @Description("Проверяется авторизация без пароля")
    public void authorizationCourierWithoutPasswordTest(){
        File loginCourier = new File("src/test/resources/loginCourierWithoutPassword.json");
        Response response = given().header("Content-type", "application/json").and().body(loginCourier).post("/api/v1/courier/login");
        response.then().statusCode(400);
    }

    @Test
    @Description("Проверяется авторизация с не правильным логином")
    public void authorizationCourierWithoutWrongLoginTest(){
        File loginCourier = new File("src/test/resources/loginCourierWithWrongLogin.json");
        Response response = given().header("Content-type", "application/json").and().body(loginCourier).post("/api/v1/courier/login");
        response.then().statusCode(404);
    }



}
