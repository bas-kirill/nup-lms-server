package cy.ac.nup.lms.rest;

import cy.ac.nup.lms.domain.CourseCode;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import jakarta.annotation.security.RolesAllowed;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseStudentsEndpoint {

    private final UserExtractor userExtractor;

    @RolesAllowed({"ADMIN", "FACULTY"})
    @GetMapping("/course/{courseCode}/students")
    public Set<CourseStudentsModel> getCourseStudents(@PathVariable String courseCode) {
        CourseCode code = CourseCode.from(courseCode);
        return userExtractor.findAll().stream()
                .filter(user -> user.courses().stream().anyMatch(course -> course.code().equals(code)))
                .filter(user -> user.authority().equals("ROLE_STUDENT"))
                .map(user -> new CourseStudentsModel(user.fullName()))
                .collect(Collectors.toSet());
    }

    record CourseStudentsModel(String fullName) {

    }
}
