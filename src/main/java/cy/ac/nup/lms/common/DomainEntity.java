package cy.ac.nup.lms.common;

public abstract class DomainEntity<T> {

    protected final T id;

    protected DomainEntity(T id) {
        this.id = id;
    }
}
