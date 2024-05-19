package cy.ac.nup.lms.rest;

import jakarta.annotation.security.RolesAllowed;
import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class StudentEndpoint {

    @RolesAllowed("STUDENT")
    @GetMapping("/hello")
    public String hello(Principal principal) {
        return "Hello, student `%s`!".formatted(principal.getName());
    }
}
