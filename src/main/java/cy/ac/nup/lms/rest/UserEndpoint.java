package cy.ac.nup.lms.rest;

import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserEndpoint {

    @RolesAllowed("USER")
    @GetMapping("/hello")
    public String hello() {
        return "Hello, user!";
    }
}
