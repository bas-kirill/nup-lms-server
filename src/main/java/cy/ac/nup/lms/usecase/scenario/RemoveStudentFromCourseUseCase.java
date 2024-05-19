package cy.ac.nup.lms.usecase.scenario;

import cy.ac.nup.lms.domain.Course;
import cy.ac.nup.lms.domain.CourseCode;
import cy.ac.nup.lms.domain.User;
import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.RemoveStudentFromCourse;
import cy.ac.nup.lms.usecase.access.CourseExtractor;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import cy.ac.nup.lms.usecase.access.UserPersister;
import io.vavr.control.Either;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RemoveStudentFromCourseUseCase implements RemoveStudentFromCourse {

    private final CourseExtractor courseExtractor;
    private final UserExtractor userExtractor;
    private final UserPersister userPersister;

    @Override
    public Either<Error, Void> execute(CourseCode code, Username username) {
        Optional<Course> course = courseExtractor.findByCode(code);

        if (course.isEmpty()) {
            return Either.left(new Error.CourseNotFound(code));
        }

        Optional<User> user = userExtractor.findByUsername(username);
        if (user.isEmpty()) {
            return Either.left(new Error.UserNotFound(username));
        }

        user.get().removeCourse(course.get());
        userPersister.save(user.get());
        return Either.right(null);
    }
}
