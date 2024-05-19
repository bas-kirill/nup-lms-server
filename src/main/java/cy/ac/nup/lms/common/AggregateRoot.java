package cy.ac.nup.lms.common;

public abstract class AggregateRoot<T> extends DomainEntity<T> {

    protected AggregateRoot(T id) {
        super(id);
    }
}
