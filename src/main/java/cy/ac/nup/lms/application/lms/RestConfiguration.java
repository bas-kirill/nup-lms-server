package cy.ac.nup.lms.application.lms;

import cy.ac.nup.lms.rest.AdminEndpoint;
import cy.ac.nup.lms.rest.LoginEndpoint;
import cy.ac.nup.lms.rest.MeEndpoint;
import cy.ac.nup.lms.rest.StudentEndpoint;
import cy.ac.nup.lms.usecase.Login;
import cy.ac.nup.lms.usecase.MeExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfiguration {

    @Bean
    public LoginEndpoint loginEndpoint(Login login) {
        return new LoginEndpoint(login);
    }

    @Bean
    public AdminEndpoint adminEndpoint() {
        return new AdminEndpoint();
    }

    @Bean
    public StudentEndpoint userEndpoint() {
        return new StudentEndpoint();
    }

    @Bean
    public MeEndpoint meEndpoint(MeExtractor meExtractor) {
        return new MeEndpoint(meExtractor);
    }
}
