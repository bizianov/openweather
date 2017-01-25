import com.bizianov.parser.TemperatureParser;
import com.bizianov.provider.CurrentWeatherProvider;
import com.bizianov.provider.WeatherProvider;
import com.bizianov.service.CurrentWeatherService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by slava23 on 1/25/2017.
 */
public class ServiceTest {

    private static final String CITY_NAME = "Kiev";
    private EmbeddedDatabase database;
    private WeatherProvider weatherProvider;
    private TemperatureParser temperatureParser;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        database = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("database/create-db.sql")
                .addScript("database/import.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(database);
        weatherProvider = mock(CurrentWeatherProvider.class);
        temperatureParser = mock(TemperatureParser.class);
    }

    @Test
    public void service() throws IOException {
        CurrentWeatherService weatherService = new CurrentWeatherService();
        weatherService.setJdbcTemplate(jdbcTemplate);
        weatherService.setWeatherProvider(weatherProvider);
        weatherService.setTemperatureParser(temperatureParser);
        weatherService.service();
        verify(weatherProvider).getWeather(CITY_NAME);
        verify(temperatureParser, times(3)).parse(null);
    }
}
