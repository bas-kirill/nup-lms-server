package cy.ac.nup.lms.usecase.access;

import cy.ac.nup.lms.domain.Course;
import java.util.Set;

public interface CourseExtractor {

    Set<Course> findAll();
}
