package cy.ac.nup.lms.rest;

import cy.ac.nup.lms.domain.Password;
import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.Login;
import cy.ac.nup.lms.usecase.scenario.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginEndpoint {

    private final Login login;

    @PostMapping("/login")
    public LoginModel login(@RequestBody LoginRequest loginRequest) {
        Username username = Username.from(loginRequest.username());
        Password password = Password.from(loginRequest.password());
        return new LoginModel(login.execute(username, password));
    }

    record LoginRequest(String username, String password) {

    }

    record LoginModel(String token) {

    }
}


