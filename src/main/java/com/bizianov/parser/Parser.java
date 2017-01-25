package com.bizianov.parser;

import java.io.IOException;

/**
 * Created by slava23 on 1/25/2017.
 */
public interface Parser<T> {
    T parse(String rawData) throws IOException;
}
