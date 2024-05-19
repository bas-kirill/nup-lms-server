package cy.ac.nup.lms.rest;

import cy.ac.nup.lms.domain.User;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import jakarta.annotation.security.RolesAllowed;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UsersEndpoint {

    private final UserExtractor userExtractor;

    @RolesAllowed({"ADMIN", "FACULTY"})
    @GetMapping("/users")
    public List<UsersModel> getUsers() {
        return userExtractor.findAll().stream()
                .map(UsersModel::from)
                .toList();
    }

    record UsersModel(String username, String fullName, String role, String faculty) {
        static UsersModel from(User user) {
            return new UsersModel(
                    user.username.value,
                    user.fullName,
                    user.authority,
                    user.facultyCode.value);
        }
    }
}
