import com.bizianov.parser.Parser;
import com.bizianov.parser.TemperatureParser;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by slava23 on 1/25/2017.
 */
public class ParserTest {

    private static final String response =
            "{\"coord\":" +
                "{\"lon\":-0.13,\"lat\":51.51}," +
                    "\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01d\"}]," +
                    "\"base\":\"stations\"," +
                    "\"main\":{\"temp\":267.424,\"pressure\":1034.11,\"humidity\":75,\"temp_min\":267.424," +
                    "\"temp_max\":267.424,\"sea_level\":1042.2,\"grnd_level\":1034.11}," +
                    "\"wind\":{\"speed\":2.31,\"deg\":146.001},\"clouds\":" +
                    "{\"all\":0},\"dt\":1485337049,\"sys\":" +
                    "{\"message\":0.0024,\"country\":\"GB\",\"sunrise\":1485330481,\"sunset\":1485362310}," +
                    "\"id\":2643743,\"name\":\"London\",\"cod\":200}";

    private Parser<Double> temperatureParser;

    @Before
    public void setUp(){
        temperatureParser = new TemperatureParser();
    }

    @Test
    public void parse() throws IOException {
        Double value = temperatureParser.parse(response);
        assertThat(value, equalTo(new Double(-5.6)));
    }
}
