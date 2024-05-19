package cy.ac.nup.lms.persistence;

import cy.ac.nup.lms.domain.Course;
import cy.ac.nup.lms.domain.CourseCode;
import cy.ac.nup.lms.usecase.access.CourseExtractor;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InMemoryCourseRepository implements CourseExtractor {

    private final Map<CourseCode, Course> storage;

    @Override
    public Optional<Course> findByCode(CourseCode code) {
        return Optional.ofNullable(storage.get(code));
    }

    @Override
    public Collection<Course> findAll() {
        return storage.values();
    }
}
