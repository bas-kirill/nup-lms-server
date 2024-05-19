package cy.ac.nup.lms.rest;

import cy.ac.nup.lms.domain.User;
import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.GetUserInfo;
import jakarta.annotation.security.RolesAllowed;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserInfoEndpoint {

    private final GetUserInfo getUserInfo;

    @RolesAllowed({"ADMIN", "FACULTY"})
    @GetMapping("/user")
    public Object getUserInfo(@RequestParam String login) {
        Username username = Username.from(login);
        return getUserInfo.execute(username)
                .fold(error -> error.message, UserInfoModel::from);
    }

    record UserInfoModel(String fullName, String role, String username, List<CourseModel> courses) {

        static UserInfoModel from(User user) {
            return new UserInfoModel(
                    user.fullName,
                    user.authority,
                    user.username.value,
                    user.courses().values().stream().map(course -> new CourseModel(course.code.value, course.name))
                            .toList());
        }

        record CourseModel(String code, String name) {

        }

    }
}
