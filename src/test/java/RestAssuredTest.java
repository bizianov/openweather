import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by slava23 on 1/25/2017.
 */
public class RestAssuredTest {

    private static final String URL =
            "http://api.openweathermap.org/data/2.5/weather?q=London&appid=9dc8ad5a19c357a02b70a7581d18d43c";
    private static final Integer LONDON_ID = 2643743;

    @Test
    public void openweather(){
        given().when().get("http://api.openweathermap.org/").then().statusCode(200);
    }

    @Test
    public void simpleRequest(){
        given().when().get(URL).then()
                .statusCode(200)
                .body("id", equalTo(LONDON_ID));
    }
}
