package cy.ac.nup.lms.rest;

import cy.ac.nup.lms.domain.CourseCode;
import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.AddStudentToCourse;
import cy.ac.nup.lms.usecase.RemoveStudentFromCourse;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import jakarta.annotation.security.RolesAllowed;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseStudentsEndpoint {

    private final AddStudentToCourse addStudentToCourse;
    private final UserExtractor userExtractor;
    private final RemoveStudentFromCourse removeStudentFromCourse;

    @RolesAllowed({"ADMIN", "FACULTY"})
    @PostMapping("/course/{code}")
    public Object addStudentToCourse(@PathVariable String code, @RequestBody AddStudentToCourseRequest request) {
        CourseCode courseCode = CourseCode.from(code);
        Username username = Username.from(request.login);
        return addStudentToCourse.execute(courseCode, username)
                .fold(error -> error.message, user -> new AddStudentToCourseModel(user.fullName, user.username.value));
    }

    @RolesAllowed({"ADMIN", "FACULTY"})
    @GetMapping("/course/{courseCode}/students")
    public Set<GetCourseStudentsModel> getCourseStudents(@PathVariable String courseCode) {
        CourseCode code = CourseCode.from(courseCode);
        return userExtractor.findAll().stream()
                .filter(user -> user.courses().containsKey(code))
                .filter(user -> user.authority.equals("ROLE_STUDENT"))
                .map(user -> new GetCourseStudentsModel(user.fullName, user.username.value))
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

    record AddStudentToCourseRequest(String login) {

    }

    record AddStudentToCourseModel(String fullName, String login) {

    }

    record GetCourseStudentsModel(String fullName, String login) {

    }
}
