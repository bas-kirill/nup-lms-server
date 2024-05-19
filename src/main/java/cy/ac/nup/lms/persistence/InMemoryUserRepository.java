package cy.ac.nup.lms.persistence;

import cy.ac.nup.lms.domain.User;
import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InMemoryUserRepository implements UserExtractor {

    private final Map<Username, User> storage;

    @Override
    public Optional<User> findByUsername(Username username) {
        return Optional.ofNullable(storage.get(username));
    }

    @Override
    public Collection<User> findAll() {
        return storage.values();
    }

}
