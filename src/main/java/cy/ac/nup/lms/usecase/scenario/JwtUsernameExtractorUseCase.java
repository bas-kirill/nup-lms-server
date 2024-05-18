package cy.ac.nup.lms.usecase.scenario;

import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.JwtUsernameExtractor;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtUsernameExtractorUseCase implements JwtUsernameExtractor {

    private final String jwtSecretKey;

    @Override
    public Username execute(String jwtRaw) {
        return Username.from(Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(jwtRaw)
                .getBody()
                .getSubject());
    }
}
