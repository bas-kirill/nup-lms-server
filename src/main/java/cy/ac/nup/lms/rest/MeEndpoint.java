package cy.ac.nup.lms.rest;

import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.MeExtractor;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MeEndpoint {

    private final MeExtractor meExtractor;

    @GetMapping("/me")
    public Object me(Principal principal) {
        Username username = Username.from(principal.getName());
        return meExtractor.execute(username)
                .fold(error -> error.message, user -> new MeModel(user.fullName));
    }

    record MeModel(String fullName) {

    }
}
