package cy.ac.nup.lms.usecase.scenario;

import cy.ac.nup.lms.domain.User;
import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.GetUserInfo;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import io.vavr.control.Either;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetUserInfoUseCase implements GetUserInfo {

    private final UserExtractor userExtractor;

    @Override
    public Either<Error, User> execute(Username username) {
        return userExtractor.findByUsername(username)
                .map(Either::<GetUserInfo.Error, User>right)
                .orElseGet(() -> Either.left(new Error.UserNotFound(username)));
    }
}
