package cy.ac.nup.lms.usecase.scenario;

import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.JwtGenerator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtGeneratorUseCase implements JwtGenerator {

    private final String jwtSecretKey;
    private final long jwtExpiration;

    @Override
    public String execute(Username username) {
        return Jwts.builder()
                .setSubject(username.value)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }
}
