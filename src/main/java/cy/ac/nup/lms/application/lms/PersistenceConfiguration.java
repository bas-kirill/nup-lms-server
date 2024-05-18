package cy.ac.nup.lms.application.lms;

import cy.ac.nup.lms.domain.Role;
import cy.ac.nup.lms.domain.User;
import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.persistence.InMemoryUserRepository;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfiguration {

    @Bean
    public InMemoryUserRepository userRepository() {
        // https://stackoverflow.com/questions/56762121/configure-nooppasswordencoder-in-spring
        Set<User> users = Set.of(
                new User(Username.from("admin"), "{noop}123", "ROLE_ADMIN"),
                new User(Username.from("kiryuxa"), "{noop}321", "ROLE_USER")
        );
        Map<Username, User> storage = users.stream().collect(Collectors.toMap(User::username, Function.identity()));
        return new InMemoryUserRepository(storage);
    }
}
