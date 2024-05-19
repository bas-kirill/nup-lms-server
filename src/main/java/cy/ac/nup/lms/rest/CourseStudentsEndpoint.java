package cy.ac.nup.lms.rest;

import cy.ac.nup.lms.domain.CourseCode;
import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.RemoveStudentFromCourse;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import jakarta.annotation.security.RolesAllowed;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseStudentsEndpoint {

    private final UserExtractor userExtractor;
    private final RemoveStudentFromCourse removeStudentFromCourse;

    @RolesAllowed({"ADMIN", "FACULTY"})
    @GetMapping("/course/{courseCode}/students")
    public Set<CourseStudentsModel> getCourseStudents(@PathVariable String courseCode) {
        CourseCode code = CourseCode.from(courseCode);
        return userExtractor.findAll().stream()
                .filter(user -> user.courses().containsKey(code))
                .filter(user -> user.authority.equals("ROLE_STUDENT"))
                .map(user -> new CourseStudentsModel(user.fullName, user.username.value))
                .collect(Collectors.toSet());
    }

    @RolesAllowed({"ADMIN", "FACULTY"})
    @DeleteMapping("/course/{code}/students/{login}")
    public Object deleteStudentFromCourse(@PathVariable String code, @PathVariable String login) {
        CourseCode courseCode = CourseCode.from(code);
        Username username = Username.from(login);
        return removeStudentFromCourse.execute(courseCode, username)
                .fold(error -> error.message, success -> "Success!");
    }

    record CourseStudentsModel(String fullName, String login) {

    }
}
