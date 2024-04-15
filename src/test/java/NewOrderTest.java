import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class NewOrderTest {

    private final String inputParameter;
    private final String outputParameter;
    private final boolean isTruth;

    public NewOrderTest(String inputParameter, String outputParameter, boolean isTruth) {
        this.isTruth = isTruth;
        this.inputParameter = inputParameter;
        this.outputParameter = outputParameter;
    }

    @Parameterized.Parameters
    public static Object[][] getData(){
        return new Object[][]{
                {"src/test/resources/newOrderColorBlack.json","track", true},
                {"src/test/resources/newOrderColorGray.json","track", true},
                {"src/test/resources/newOrderColorBlackAndGray.json","track", true},
                {"src/test/resources/newOrderWithoutColor.json","track", true}
        };
    }



    @Test
    public void newOrderTest(){
        assertThat(getNewOrder(inputParameter).asString().contains(outputParameter),is(isTruth));
    }

    public Response getNewOrder(String filePath){
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        File newOrder = new File(filePath);
        Response response = given().header("Content-type", "application/json").and().body(newOrder).post("/api/v1/orders");
        response.then().statusCode(201);
        return response;
    }



}
