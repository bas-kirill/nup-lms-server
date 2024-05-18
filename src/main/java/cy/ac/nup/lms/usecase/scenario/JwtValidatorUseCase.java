package cy.ac.nup.lms.usecase.scenario;

import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.JwtValidator;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class JwtValidatorUseCase implements JwtValidator {

    private final String jwtSecretKey;
    private final UserExtractor userRepository;

    @Override
    public boolean execute(String jwtRaw) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(jwtRaw)
                .getBody();

        Username username = Username.from(claims.getSubject());
        UserDetails user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return false;
        }
        Date expiration = claims.getExpiration();
        return (username.value.equals(user.getUsername())) && (new Date().before(expiration));
    }
}
