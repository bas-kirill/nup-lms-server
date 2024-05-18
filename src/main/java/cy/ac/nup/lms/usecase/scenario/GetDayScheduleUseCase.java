package cy.ac.nup.lms.usecase.scenario;

import cy.ac.nup.lms.domain.Course;
import cy.ac.nup.lms.domain.DaySchedule;
import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.GetDaySchedule;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import io.vavr.control.Either;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetDayScheduleUseCase implements GetDaySchedule {

    private final UserExtractor userExtractor;

    @Override
    public Either<Error, cy.ac.nup.lms.domain.DaySchedule> execute(Username username, LocalDate day) {
        return userExtractor.findByUsername(username)
                .map(user -> {
                    List<Course> dayCourses = user.courses().stream()
                            .filter(course -> course.startDate().isBefore(day) && day.isBefore(course.endDate()))
                            .toList();
                    return new cy.ac.nup.lms.domain.DaySchedule(dayCourses);
                })
                .map(Either::<Error, DaySchedule>right)
                .orElseGet(() -> Either.left(new Error.UserNotFound(username)));
    }
}
