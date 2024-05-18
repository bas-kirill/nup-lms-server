package cy.ac.nup.lms.usecase.scenario;

import cy.ac.nup.lms.domain.User;
import cy.ac.nup.lms.usecase.JwtGenerator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtGeneratorUseCase implements JwtGenerator {

    private final String jwtSecretKey;
    private final long jwtExpiration;

    @Override
    public String execute(User user) {
        Claims claims = Jwts.claims().setSubject(user.username().value);
        claims.put("role", user.authority());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .compact();
    }
}
