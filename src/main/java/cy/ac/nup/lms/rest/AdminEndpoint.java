package cy.ac.nup.lms.rest;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminEndpoint {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {
        return "Hello admin, `%s`!".formatted(authentication.getName());
    }
}
