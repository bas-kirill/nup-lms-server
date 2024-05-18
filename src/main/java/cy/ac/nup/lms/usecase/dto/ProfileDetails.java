package cy.ac.nup.lms.usecase.dto;

import cy.ac.nup.lms.domain.Course;
import java.util.List;

public record ProfileDetails(String fullName, String role, List<Course> courses) {
}
