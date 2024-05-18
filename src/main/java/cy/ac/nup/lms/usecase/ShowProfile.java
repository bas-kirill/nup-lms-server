package cy.ac.nup.lms.usecase;

import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.dto.ProfileDetails;
import io.vavr.control.Either;

public interface ShowProfile {

    Either<Error, ProfileDetails> execute(Username username);

    sealed class Error {
        public final String message;

        public Error(String message) {
            this.message = message;
        }

        public static final class UserNotFound extends Error {
            public UserNotFound(Username username) {
                super("User with login `%s` not found".formatted(username.value));
            }
        }
    }
}
