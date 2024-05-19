package cy.ac.nup.lms.usecase;

import cy.ac.nup.lms.domain.User;
import cy.ac.nup.lms.domain.Username;
import io.vavr.control.Either;

public interface GetUserInfo {

    Either<Error, User> execute(Username username);

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
