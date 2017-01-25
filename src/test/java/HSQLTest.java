import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by slava23 on 1/25/2017.
 */
public class HSQLTest {

    private EmbeddedDatabase database;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        database = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("database/create-db.sql")
                .addScript("database/import.sql")
                .build();
        jdbcTemplate = new JdbcTemplate(database);
    }

    @Test
    public void getContent() throws IOException {
        Integer count = jdbcTemplate.queryForObject("SELECT count(*) FROM city", Integer.class);
        assertThat(count, equalTo(3));
    }

    @After
    public void tearDown() {
        database.shutdown();
    }
}
