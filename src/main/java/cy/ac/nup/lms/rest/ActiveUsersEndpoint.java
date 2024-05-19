package cy.ac.nup.lms.rest;

import cy.ac.nup.lms.domain.User;
import jakarta.annotation.security.RolesAllowed;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ActiveUsersEndpoint {

    private final SessionRegistry sessionRegistry;

    @RolesAllowed({"ADMIN", "FACULTY"})
    @GetMapping("/users/active")
    public List<UserModel> findActiveUsers() {
        return sessionRegistry.getAllPrincipals().stream()
                .filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty())
                .map(User.class::cast)
                .map(user -> new UserModel(user.fullName(), user.username().value))
                .toList();
    }

    record UserModel(String fullName, String login) {

    }
}
