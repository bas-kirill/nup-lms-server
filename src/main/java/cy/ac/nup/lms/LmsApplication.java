package cy.ac.nup.lms;


import cy.ac.nup.lms.application.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;

public class LmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }
}
