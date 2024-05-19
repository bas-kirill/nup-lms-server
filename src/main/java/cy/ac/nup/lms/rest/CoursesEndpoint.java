package cy.ac.nup.lms.rest;

import cy.ac.nup.lms.usecase.access.CourseExtractor;
import jakarta.annotation.security.RolesAllowed;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CoursesEndpoint {

    private final CourseExtractor courseExtractor;

    @RolesAllowed("ADMIN")
    @RequestMapping("/courses")
    public List<CourseModel> getCourses() {
        return courseExtractor.findAll().stream()
                .map(course -> new CourseModel(course.code(), course.name()))
                .toList();
    }

    record CourseModel(String code, String name) {

    }
}
