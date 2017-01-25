package com.bizianov.provider;

import com.bizianov.builder.UrlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by slava23 on 1/24/2017.
 */

@Component
public class CurrentWeatherProvider implements WeatherProvider {

    @Autowired
    private UrlBuilder urlBuilder;
    @Autowired
    private RestTemplate restTemplate;

    public String getWeather(String city) {
        return restTemplate.getForObject(urlBuilder.build(city), String.class);
    }
}
