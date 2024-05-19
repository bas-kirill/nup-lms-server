package cy.ac.nup.lms.usecase.access;

import cy.ac.nup.lms.domain.User;

public interface UserPersister {

    void save(User user);

}
