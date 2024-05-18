package cy.ac.nup.lms.usecase.scenario;

import cy.ac.nup.lms.domain.Password;
import cy.ac.nup.lms.domain.User;
import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.JwtGenerator;
import cy.ac.nup.lms.usecase.Login;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RequiredArgsConstructor
public class LoginUseCase implements Login {

    private final UserExtractor userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;

    @Override
    public String execute(Username username, Password password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username.value,
                        password.value
                )
        );

        User user = userRepository.findByUsername(username).orElseThrow();
        return jwtGenerator.execute(Username.from(user.getUsername()));
    }
}
