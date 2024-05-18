package cy.ac.nup.lms.usecase.access;

import cy.ac.nup.lms.domain.User;
import cy.ac.nup.lms.domain.Username;
import java.util.Optional;

public interface UserExtractor {

    Optional<User> findByUsername(Username username);
}
