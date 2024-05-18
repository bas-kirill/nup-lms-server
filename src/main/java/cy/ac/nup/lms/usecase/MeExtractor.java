package cy.ac.nup.lms.usecase;

import cy.ac.nup.lms.domain.User;
import cy.ac.nup.lms.domain.Username;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

public interface MeExtractor {

    Either<Error, User> execute(Username username);

    @RequiredArgsConstructor
    sealed class Error {

        private final String message;

        public static final class NotFound extends Error {

            public NotFound(Username username) {
                super("User with login `%s` not found".formatted(username.value));
            }
        }
    }
}
