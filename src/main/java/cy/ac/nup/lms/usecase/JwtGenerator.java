package cy.ac.nup.lms.usecase;

import cy.ac.nup.lms.domain.Username;

public interface JwtGenerator {

    String execute(Username username);
}
