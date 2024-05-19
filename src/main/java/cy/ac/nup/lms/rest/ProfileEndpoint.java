package cy.ac.nup.lms.rest;

import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.ShowProfile;
import cy.ac.nup.lms.usecase.dto.ProfileDetails;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProfileEndpoint {

    private final ShowProfile showProfile;

    @GetMapping("/profile")
    public Object showProfile(Principal principal) {
        Username username = Username.from(principal.getName());
        return showProfile.execute(username)
                .fold(error -> error.message, ProfileModel::from);
    }

    record ProfileModel(String fullName, String role, List<Course> courses) {

        static ProfileModel from(ProfileDetails details) {
            return new ProfileModel(
                    details.fullName(),
                    details.role(),
                    details.courses().stream().map(course -> new Course(course.code().value, course.name())).toList());
        }

        record Course(String code, String name) {

        }
    }
}
