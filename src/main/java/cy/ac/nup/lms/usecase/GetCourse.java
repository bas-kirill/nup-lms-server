package cy.ac.nup.lms.usecase;

import cy.ac.nup.lms.domain.Course;
import cy.ac.nup.lms.domain.CourseCode;
import io.vavr.control.Either;

public interface GetCourse {

    Either<Error, Course> execute(CourseCode code);

    sealed class Error {

        public final String message;

        public Error(String message) {
            this.message = message;
        }

        public static final class CourseNotFound extends Error {
            public CourseNotFound(CourseCode code) {
                super("User with login `%s` not found".formatted(code.value));
            }
        }
    }
}
