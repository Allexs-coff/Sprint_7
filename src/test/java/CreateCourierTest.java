
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import java.io.File;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class CreateCourierTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @Description("Проверяется создание курьера")
    public void createNewCourierTest(){
        File newCourier = new File("src/test/resources/newCourier.json");
        Response response = given().header("Content-type", "application/json").and().body(newCourier).post("/api/v1/courier");
        response.then().statusCode(201);
        assertTrue(response.asString().contains("{\"ok\":true}"));
        response = given().header("Content-type", "application/json").and().body(newCourier).post("/api/v1/courier");
        response.then().statusCode(409);
    }

    @Test
    @Description("Создания курьера без обязательного поля login")
    public void createNewCourierWithoutLogin(){
        File newCourier = new File("src/test/resources/newCourier(withoutLogin).json");
        Response response = given().header("Content-type", "application/json").and().body(newCourier).post("/api/v1/courier");
        response.then().statusCode(400);
    }

    @Test
    @Description("Создания курьера с повторяющимся полем login")
    public void creatingCourierWithReLogin(){
        File newCourier1 = new File("src/test/resources/newCourier(re-login)1.json");
        Response response = given().header("Content-type", "application/json").and().body(newCourier1).post("/api/v1/courier");
        response.then().statusCode(201);
        File newCourier = new File("src/test/resources/newCourier(re-login).json");
        response = given().header("Content-type", "application/json").and().body(newCourier).post("/api/v1/courier");
        response.then().statusCode(409);
    }





}
