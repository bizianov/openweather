package com.bizianov.builder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by slava23 on 1/25/2017.
 */

@Component
public class CurrentWeatherUrlBuilder implements UrlBuilder {

    public static final String SEARCH_BY_CITY_SUFFIX = "?q=";
    public static final String SEARCH_API_SUFFIX = "&appid=";
    @Value("${weather.base.url}")
    private String baseUrl;
    @Value("${weather.current.url}")
    private String currentUrl;
    @Value("${weather.app.id}")
    private String appId;

    public String build(String city) {
        return new StringBuilder(baseUrl)
                .append(currentUrl)
                .append(SEARCH_BY_CITY_SUFFIX)
                .append(city)
                .append(SEARCH_API_SUFFIX)
                .append(appId)
                .toString();
    }
}
