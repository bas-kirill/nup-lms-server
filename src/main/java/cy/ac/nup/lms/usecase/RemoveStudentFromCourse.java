package cy.ac.nup.lms.usecase;

import cy.ac.nup.lms.domain.CourseCode;
import cy.ac.nup.lms.domain.Username;
import io.vavr.control.Either;

public interface RemoveStudentFromCourse {

    Either<Error, Void> execute(CourseCode code, Username username);

    sealed class Error {
        public final String message;

        public Error(String message) {
            this.message = message;
        }

        public static final class UserNotFound extends Error {
            public UserNotFound(Username username) {
                super("User `%s` not found: ".formatted(username.value));
            }
        }

        public static final class CourseNotFound extends Error {
            public CourseNotFound(CourseCode code) {
                super("Course `%s` not found: ".formatted(code.value));
            }
        }
    }
}
