package com.bizianov.config;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

/**
 * Created by slava23 on 1/24/2017.
 */

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.bizianov")
public class ApplicationConfig {

    @Bean
    public DataSource dataSource(){
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase database = databaseBuilder
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("database/create-db.sql")
                .addScript("database/import.sql")
                .build();
        return database;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @PostConstruct
    public void startDBManager() {
        DatabaseManagerSwing.main(new String[]{"--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", ""});
    }
}
