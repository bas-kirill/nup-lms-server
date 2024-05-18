package cy.ac.nup.lms.domain;

import cy.ac.nup.lms.common.ValueObject;
import java.util.Objects;

public class Username implements ValueObject {

    public final String value;

    private Username(String value) {
        this.value = value;
    }

    public static Username from(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Username `%s` cannot be null or empty".formatted(value));
        }
        return new Username(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Username username = (Username) o;
        return Objects.equals(value, username.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
