package cy.ac.nup.lms.usecase.access;

import cy.ac.nup.lms.domain.Course;
import cy.ac.nup.lms.domain.CourseCode;
import java.util.Collection;
import java.util.Optional;

public interface CourseExtractor {

    Optional<Course> findByCode(CourseCode code);

    Collection<Course> findAll();
}
