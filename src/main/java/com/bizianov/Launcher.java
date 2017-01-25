package com.bizianov;

import com.bizianov.config.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.bizianov.service.WeatherService;

import java.io.IOException;

/**
 * Created by slava23 on 1/24/2017.
 */

public class Launcher {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        WeatherService weatherService = context.getBean(WeatherService.class);
        weatherService.service();
    }
}
