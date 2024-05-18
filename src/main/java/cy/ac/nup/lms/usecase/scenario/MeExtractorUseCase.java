package cy.ac.nup.lms.usecase.scenario;

import cy.ac.nup.lms.domain.User;
import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.MeExtractor;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MeExtractorUseCase implements MeExtractor {

    private final UserExtractor userExtractor;

    @Override
    public Either<Error, User> execute(Username username) {
        return userExtractor.findByUsername(username)
                .map(Either::<Error, User>right)
                .orElseGet(() -> Either.left(new Error.NotFound(username)));
    }
}
