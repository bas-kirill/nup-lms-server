package cy.ac.nup.lms.usecase;

import cy.ac.nup.lms.domain.User;

public interface JwtGenerator {

    String execute(User user);
}
