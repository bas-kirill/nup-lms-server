package cy.ac.nup.lms.rest;

import cy.ac.nup.lms.domain.CourseCode;
import cy.ac.nup.lms.usecase.GetCourse;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseDetailsEndpoint {

    private final GetCourse getCourse;

    @RolesAllowed({"ADMIN", "FACULTY", "STUDENT"})
    @GetMapping("/course/{code}/details")
    public Object getCourseDetails(@PathVariable String code) {
        CourseCode courseCode = CourseCode.from(code);
        return getCourse.execute(courseCode)
                .fold(error -> error.message, course -> new CourseModel(course.code.value, course.name));
    }

    record CourseModel(String courseCode, String name) {

    }
}
