package cy.ac.nup.lms.domain;

import cy.ac.nup.lms.common.ValueObject;
import java.util.Objects;

public class Announcement implements ValueObject {

    public final String value;

    private Announcement(String value) {
        this.value = value;
    }

    public static Announcement from(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Announcement `%s` cannot be null or empty".formatted(value));
        }
        return new Announcement(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Announcement that = (Announcement) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
