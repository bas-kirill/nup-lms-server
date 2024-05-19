package cy.ac.nup.lms.domain;

import cy.ac.nup.lms.common.ValueObject;

public class FacultyCode implements ValueObject {

    public final String value;

    private FacultyCode(String value) {
        this.value = value;
    }

    public static FacultyCode from(String value) {
        return new FacultyCode(value);
    }
}
