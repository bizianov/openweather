package com.bizianov.service;

import com.bizianov.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.bizianov.parser.TemperatureParser;
import com.bizianov.provider.WeatherProvider;

import java.io.IOException;
import java.util.List;

/**
 * Created by slava23 on 1/25/2017.
 */

@Service
public class CurrentWeatherService implements WeatherService {
    
    public static final String QUERY_FOR_CITY = "SELECT * FROM city";
    public static final String TEMPERATURE_INSERT =
            "INSERT INTO temperature VALUES ((SELECT id FROM city WHERE name = :city_name), :temperature_value)";
    
    @Autowired
    private WeatherProvider weatherProvider;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private TemperatureParser temperatureParser;

    @Override
    public void service() throws IOException {
        List<String> cityList = jdbcTemplate.query(QUERY_FOR_CITY, new CityMapper());
        if (!cityList.isEmpty()){
            for (String city : cityList){
                String rawWeather = weatherProvider.getWeather(city);
                Double temperature = temperatureParser.parse(rawWeather);
                jdbcTemplate.update(TEMPERATURE_INSERT, city, temperature);
            }
        }
    }

    public void setWeatherProvider(WeatherProvider weatherProvider) {
        this.weatherProvider = weatherProvider;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setTemperatureParser(TemperatureParser temperatureParser) {
        this.temperatureParser = temperatureParser;
    }
}
