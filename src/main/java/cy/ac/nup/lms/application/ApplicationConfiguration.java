package cy.ac.nup.lms.application;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        ContextConfiguration.class,
        MvcConfiguration.class,
})
@EnableAutoConfiguration
public class ApplicationConfiguration {

}
