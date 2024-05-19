package cy.ac.nup.lms.usecase.scenario;

import cy.ac.nup.lms.domain.Course;
import cy.ac.nup.lms.domain.CourseCode;
import cy.ac.nup.lms.usecase.GetCourse;
import cy.ac.nup.lms.usecase.GetCourse.Error.CourseNotFound;
import cy.ac.nup.lms.usecase.access.CourseExtractor;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetCourseUseCase implements GetCourse {

    private final CourseExtractor courseExtractor;

    @Override
    public Either<Error, Course> execute(CourseCode code) {
        return courseExtractor.findByCode(code)
                .map(Either::<Error, Course>right)
                .orElseGet(() -> Either.left(new CourseNotFound(code)));
    }

}
