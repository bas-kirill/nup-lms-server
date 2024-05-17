package cy.ac.nup.lms.application;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfiguration {

    @Bean
    public DataSource postgreSqlDataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver") // `org.postgresql.Driver.class.getName()` not accessible
                .url("jdbc:postgresql://postgres:5432/lms")
                .username("lms")
                .password("lms")
                .build();
    }
}
