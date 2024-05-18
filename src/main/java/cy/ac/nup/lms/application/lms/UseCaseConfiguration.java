package cy.ac.nup.lms.application.lms;

import cy.ac.nup.lms.usecase.JwtGenerator;
import cy.ac.nup.lms.usecase.JwtUsernameExtractor;
import cy.ac.nup.lms.usecase.JwtValidator;
import cy.ac.nup.lms.usecase.Login;
import cy.ac.nup.lms.usecase.MeExtractor;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import cy.ac.nup.lms.usecase.scenario.JwtGeneratorUseCase;
import cy.ac.nup.lms.usecase.scenario.JwtUsernameExtractorUseCase;
import cy.ac.nup.lms.usecase.scenario.JwtValidatorUseCase;
import cy.ac.nup.lms.usecase.scenario.LoginUseCase;
import cy.ac.nup.lms.usecase.scenario.MeExtractorUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
public class UseCaseConfiguration {

    @Bean
    public JwtGenerator jwtGenerator(
            @Value("${security.jwt.secret-key}") String jwtSecretKey,
            @Value("${security.jwt.expiration-time}") long jwtExpiration) {
        return new JwtGeneratorUseCase(jwtSecretKey, jwtExpiration);
    }

    @Bean
    public JwtValidator jwtValidator(
            @Value("${security.jwt.secret-key}") String jwtSecretKey,
            UserExtractor userExtractor) {
        return new JwtValidatorUseCase(jwtSecretKey, userExtractor);
    }

    @Bean
    public JwtUsernameExtractor jwtUsernameExtractor(@Value("${security.jwt.secret-key}") String jwtSecretKey) {
        return new JwtUsernameExtractorUseCase(jwtSecretKey);
    }

    @Bean
    public Login login(UserExtractor userExtractor, AuthenticationManager authenticationManager,
            JwtGenerator jwtGenerator) {
        return new LoginUseCase(userExtractor, authenticationManager, jwtGenerator);
    }

    @Bean
    public MeExtractor meExtractor(UserExtractor userExtractor) {
        return new MeExtractorUseCase(userExtractor);
    }
}
