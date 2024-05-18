package cy.ac.nup.lms.domain;

import cy.ac.nup.lms.common.ValueObject;

public class Password implements ValueObject {

    public final String value;

    private Password(String value) {
        this.value = value;
    }

    public static Password from(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Password `%s` cannot be null or empty".formatted(value));
        }
        return new Password(value);
    }

}
