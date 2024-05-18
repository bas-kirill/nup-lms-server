package cy.ac.nup.lms.application;

import cy.ac.nup.lms.application.lms.PersistenceConfiguration;
import cy.ac.nup.lms.application.lms.RestConfiguration;
import cy.ac.nup.lms.application.lms.SecurityConfiguration;
import cy.ac.nup.lms.application.lms.UseCaseConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        RestConfiguration.class,
        UseCaseConfiguration.class,
        PersistenceConfiguration.class,
        SecurityConfiguration.class,
})
public class ContextConfiguration {

}
