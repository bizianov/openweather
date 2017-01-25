*******HOW TO USE*******
1. To launch application just find Launcher class and execute main method
2. When weather service gets all the info from external server, you'll see HSQL Database Manager opened
3. You can use the following SQL queries to check database content:
   SELECT * FROM city;
   SELECT * FROM temperature;
   SELECT name, temp FROM city c INNER JOIN temperature t ON c.id = t.city_id

*******HOW IT WORKS*******
1. http://api.openweathermap.org/ allows to receive weather information using the following request format:
   http://api.openweathermap.org/data/2.5/weather?q=[CITY_NAME]&appid=[UNIQUE_APP_ID]
2. The list of cities is stored in in-memory database (HSQL). import.sql can be amended to add some more cities
3. WeatherService is retrieving the list of cities and performing the following steps for each of them:
        a) delegates WeatherProvider to get raw weather information.
            I) delegates UrlBuilder to prepare full URL in appropriate format that matches openweather API.
            II) delegates RestTemplate using URL to get info from the external server.
        b) delegates TemperatureParser to get the temperature info only from raw data.
        c) delegates JdbcTemplate to save temperature info to database

