package cy.ac.nup.lms.usecase.dto;

import cy.ac.nup.lms.domain.Course;
import java.util.Set;

public record ProfileDetails(String fullName, String role, Set<Course> courses) {
}
