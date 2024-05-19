package cy.ac.nup.lms.usecase.dto;

import cy.ac.nup.lms.domain.Course;
import java.util.Collection;

public record ProfileDetails(String fullName, String role, Collection<Course> courses) {
}
