package com.bizianov.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by slava23 on 1/25/2017.
 */

@Component
public class TemperatureParser implements Parser<Double> {

    public static final Double KELVIN = 273.0;

    @Override
    public Double parse(String rawData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(rawData);
        JsonNode mainNode = jsonNode.get("main");
        JsonNode temperatureNode = mainNode.path("temp");
        return Double.valueOf(
                new DecimalFormat("##.#").format(temperatureNode.asDouble() - KELVIN));
    }
}
