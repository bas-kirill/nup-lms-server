package cy.ac.nup.lms.usecase.dto;

import cy.ac.nup.lms.domain.Course;
import cy.ac.nup.lms.domain.User;
import java.util.Collection;

public record ProfileDetails(String fullName, String role, String faculty, Collection<Course> courses) {

    public static ProfileDetails from(User user) {
        return new ProfileDetails(user.fullName, user.authority, user.facultyCode.value, user.courses().values());
    }
}
