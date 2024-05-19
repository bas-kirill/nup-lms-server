package cy.ac.nup.lms.persistence;

import cy.ac.nup.lms.domain.Course;
import cy.ac.nup.lms.usecase.access.CourseExtractor;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InMemoryCourseRepository implements CourseExtractor {

    private final Set<Course> storage;

    @Override
    public Set<Course> findAll() {
        return storage;
    }
}
