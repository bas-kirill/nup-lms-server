package cy.ac.nup.lms.usecase.scenario;

import cy.ac.nup.lms.domain.Course;
import cy.ac.nup.lms.domain.CourseCode;
import cy.ac.nup.lms.domain.User;
import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.AddStudentToCourse;
import cy.ac.nup.lms.usecase.access.CourseExtractor;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import cy.ac.nup.lms.usecase.access.UserPersister;
import io.vavr.control.Either;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddStudentToCourseUseCase implements AddStudentToCourse {

    private final UserExtractor userExtractor;
    private final CourseExtractor courseExtractor;
    private final UserPersister userPersister;

    @Override
    public Either<Error, User> execute(CourseCode courseCode, Username username) {
        Optional<User> user = userExtractor.findByUsername(username);
        if (user.isEmpty()) {
            return Either.left(new Error.UserNotFound(username));
        }

        Optional<Course> course = courseExtractor.findByCode(courseCode);

        if (course.isEmpty()) {
            return Either.left(new Error.CourseNotFound(courseCode));
        }

        user.get().addCourse(course.get());
        userPersister.save(user.get());
        return Either.right(user.get());
    }
}
