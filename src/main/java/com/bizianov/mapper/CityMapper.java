package com.bizianov.mapper;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by slava23 on 1/25/2017.
 */
public class CityMapper implements RowMapper<String> {

    public static final Integer CITY_COLUMN = 2;

    @Override
    public String mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString(CITY_COLUMN);
    }
}
