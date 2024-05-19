package cy.ac.nup.lms.usecase.scenario;

import cy.ac.nup.lms.domain.Username;
import cy.ac.nup.lms.usecase.ShowProfile;
import cy.ac.nup.lms.usecase.access.UserExtractor;
import cy.ac.nup.lms.usecase.dto.ProfileDetails;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShowProfileUseCase implements ShowProfile {

    private final UserExtractor userExtractor;

    @Override
    public Either<Error, ProfileDetails> execute(Username username) {
        return userExtractor.findByUsername(username)
                .<Either<Error, ProfileDetails>>map(
                        user -> Either.right(new ProfileDetails(user.fullName, user.authority, user.courses().values())))
                .orElse(Either.left(new Error.UserNotFound(username)));
    }
}
