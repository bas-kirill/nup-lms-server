package cy.ac.nup.lms.usecase;

import cy.ac.nup.lms.domain.DaySchedule;
import cy.ac.nup.lms.domain.Username;
import io.vavr.control.Either;
import java.time.LocalDate;

public interface GetDaySchedule {

    Either<Error, DaySchedule> execute(Username username, LocalDate day);

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
