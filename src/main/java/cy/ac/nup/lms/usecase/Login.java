package cy.ac.nup.lms.usecase;

import cy.ac.nup.lms.domain.Password;
import cy.ac.nup.lms.domain.Username;

public interface Login {

    String execute(Username username, Password password);
}
