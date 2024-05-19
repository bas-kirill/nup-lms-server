package cy.ac.nup.lms.domain;

import cy.ac.nup.lms.common.ValueObject;
import java.util.Objects;

public class CourseCode implements ValueObject {

    public final String value;

    private CourseCode(String value) {
        this.value = value;
    }

    public static CourseCode from(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Course code `%s` cannot be null or empty".formatted(value));
        }
        return new CourseCode(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseCode that = (CourseCode) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
