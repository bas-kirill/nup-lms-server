package cy.ac.nup.lms.domain;

import java.time.LocalDate;

// Course active on interval: (start date; end date)
public record Course(CourseCode code, String name, LocalDate startDate, LocalDate endDate) {

}
