package cy.ac.nup.lms.rest;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminEndpoint {

    @GetMapping("/hello")
    public String hello(Principal principal) {
        return "Hello admin, `%s`!".formatted(principal.getName());
    }
}
