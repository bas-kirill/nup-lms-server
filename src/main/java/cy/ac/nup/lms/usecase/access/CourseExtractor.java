package cy.ac.nup.lms.usecase.access;

import cy.ac.nup.lms.domain.Course;
import java.util.Collection;

public interface CourseExtractor {

    Collection<Course> findAll();
}
